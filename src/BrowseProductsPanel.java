import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class BrowseProductsPanel extends JPanel {

    JLabel computerCategoryLabel;
    JComboBox<String> computerCategory;
    JLabel computerTypeLabel;
    JComboBox<String> computerType;
    DevicesRunner devices = new DevicesRunner();
    CheckOrUpdateProducts checkOrUpdateProductsPanel;

    public BrowseProductsPanel(CheckOrUpdateProducts checkOrUpdateProductsPanel) {
        // Initialize components
        computerCategoryLabel = new JLabel("Computer Category");
        computerCategory = new JComboBox<>();
        computerTypeLabel = new JLabel("Computer Type");
        computerType = new JComboBox<>();
        this.checkOrUpdateProductsPanel = checkOrUpdateProductsPanel;

        // Set fonts
        computerCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerCategory.setFont(new Font("Arial", Font.PLAIN, 15));
        computerTypeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerType.setFont(new Font("Arial", Font.PLAIN, 15));

        // Call method to add items to JComboBox
        updateJComboBox();

        //When this panel is loaded, I want to have All selected by default in the categoryComboBox
        computerCategory.setSelectedItem("All");

        // Create a table model
        DevicesTabelModel model = new DevicesTabelModel(devices.getDevices());

        // Create JTable with custom model
        JTable table = new JTable(model);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        TableRowSorter sorter = new TableRowSorter(model);
        table.setRowSorter(sorter);


        // Set the main panel layout
        setLayout(new BorderLayout());

        // Create top panel for labels and combo boxes
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc  = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(computerCategoryLabel, gbc);


        gbc.gridx = 1;
        topPanel.add(computerCategory, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(computerTypeLabel, gbc);

        gbc.gridx = 1;
        topPanel.add(computerType, gbc);

        // Add panels to the main panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //Add action listener to the category combo box to filter table
        computerCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) computerCategory.getSelectedItem();
                sorter.setRowFilter(new RowFilter<DevicesTabelModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DevicesTabelModel, ? extends Integer> entry) {
                        if ("All".equals(selectedCategory)) {
                            return true; // Include all rows
                        }
                        String category = entry.getValue(0).toString();
                        return category.equals(selectedCategory);
                    }
                });
            }
        });

        computerType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) computerType.getSelectedItem();
                sorter.setRowFilter(new RowFilter<DevicesTabelModel, Integer>() {
                    @Override
                    public boolean include(Entry<? extends DevicesTabelModel, ? extends Integer> entry) {
                        if ("All".equals(selectedType)) {
                            return true; // Include all rows
                        }
                        String type = entry.getValue(1).toString();
                        return type.equals(selectedType);
                    }
                });
            }
        });


        // Add ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Convert the selected row index in the view to the model index
                        int modelRow = table.convertRowIndexToModel(selectedRow);
                        Devices selectedDevice = devices.getDevices().get(modelRow);

                        String id = selectedDevice.getId();
                        String category = selectedDevice.getCategory();
                        String type = selectedDevice.getType();
                        String cpuFamily = selectedDevice.getCpuFamily();
                        String brand = selectedDevice.getBrand();
                        double price = selectedDevice.getPrice();

                        String ssdCapacity = String.valueOf(selectedDevice instanceof HasSSD ? ((HasSSD) selectedDevice).getSsdCapacity():null);
                        String memorySize = String.valueOf(selectedDevice instanceof HasMemorySize ? ((HasMemorySize) selectedDevice).getMemorySize():null);
                        String screenSize = String.valueOf(selectedDevice instanceof HasScreenSize ? ((HasScreenSize) selectedDevice).getScreenSize():null);


                        // Update the CheckOrUpdateProducts panel with the selected row data
                        checkOrUpdateProductsPanel.updateFields( category,type,id,brand, cpuFamily, memorySize,ssdCapacity,screenSize,price );

                    }
                }
            }
        });


    }







//Populate the combo box with categories and computer types.
    public void updateJComboBox() {
        Set<String> addedCategories = new HashSet<>();
        Set<String> addedComputerTypes = new HashSet<>();

        for (Devices aDevice : devices.getDevices()) {
            String category = aDevice.getCategory();
            String type = aDevice.getType();

            if (!addedCategories.contains(category)) {
                computerCategory.addItem(category);
                addedCategories.add(category);
            }

            if (!addedComputerTypes.contains(type)) {
                computerType.addItem(type);
                addedComputerTypes.add(type);
            }
        }
        // Add "All" option to JComboBox
        computerCategory.addItem("All");
        computerType.addItem("All");

    }
}