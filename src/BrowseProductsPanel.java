//import javax.swing.*;
//import java.awt.*;
//import java.util.HashSet;
//import java.util.Set;
//
//public class BrowseProductsPanel extends JPanel {
//
//    JLabel computerCategoryLabel;
//    JComboBox computerCategory;
//    JLabel computerTypeLabel;
//    JComboBox computerType;
//    DevicesRunner devices = new DevicesRunner();
//
//    public BrowseProductsPanel(){
//
//        computerCategoryLabel = new JLabel("Computer Category");
//        computerCategory = new JComboBox();
//        computerTypeLabel = new JLabel("Computer Type");
//        computerType = new JComboBox();
//
//        setLayout(null);
//
//        // Set fonts
//        computerCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
//        computerCategory.setFont(new Font("Arial", Font.PLAIN, 15));
//        computerTypeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
//        computerType.setFont(new Font("Arial", Font.PLAIN, 15));
//
//        // Set bounds (x, y, width, height)
//        computerCategoryLabel.setBounds(10, 10, 140, 20);
//        computerCategory.setBounds(160, 10, 140, 20);
//        computerTypeLabel.setBounds(10, 40, 140, 20);
//        computerType.setBounds(160, 40, 140, 20);
//
//
//        //call method to add item to JComboBox
//        updateJComboBox();
//
//        //Create a table model
//        DevicesTabelModel model = new DevicesTabelModel(devices.getDevices());
//
//        //Create JTable with custom model
//        JTable table = new JTable(model);
//
//        //Add the table to a JScrollPane
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        // Set bounds for the JScrollPane
//        scrollPane.setBounds(10, 80, 460, 300);  // Adjust the size as needed
//        // Add components to panel
//        add(computerCategoryLabel);
//        add(computerCategory);
//        add(computerTypeLabel);
//        add(computerType);
//        add(scrollPane);
//
//
//
//
//    }
//
//    public void updateJComboBox(){
//
//
//        Set<String> addedCategories = new HashSet<>();
//        Set<String> addedComputerTypes = new HashSet<>();
//
//        for(Devices aDevice : devices.getDevices()){
//
//            String category = aDevice.getCategory();
//            String type = aDevice.getType();
//
//            if(!addedCategories.contains(category)) {
//                computerCategory.addItem(aDevice.getCategory());
//                addedCategories.add(category);
//            }
//
//            if(!addedComputerTypes.contains(type)) {
//                computerType.addItem(aDevice.getType());
//                addedComputerTypes.add(type);
//
//            }
//
//        }
//    }
//
//}

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BrowseProductsPanel extends JPanel {

    JLabel computerCategoryLabel;
    JComboBox<String> computerCategory;
    JLabel computerTypeLabel;
    JComboBox<String> computerType;
    DevicesRunner devices = new DevicesRunner();
    DevicesTabelModel model;

    public BrowseProductsPanel() {
        // Initialize components
        computerCategoryLabel = new JLabel("Computer Category");
        computerCategory = new JComboBox<>();
        computerTypeLabel = new JLabel("Computer Type");
        computerType = new JComboBox<>();

        // Set fonts
        computerCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerCategory.setFont(new Font("Arial", Font.PLAIN, 15));
        computerTypeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerType.setFont(new Font("Arial", Font.PLAIN, 15));

        // Call method to add items to JComboBox
        updateJComboBox();

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
                sorter.setRowFilter(new RowFilter() {
                    @Override
                    public boolean include(Entry entry) {
                        String name = entry.getValue(0).toString();
                        return name.contentEquals(((String)computerCategory.getSelectedItem()));

                    }
                });
            }
        });

        computerType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(new RowFilter() {
                    @Override
                    public boolean include(Entry entry) {
                        String name = entry.getValue(0).toString();
                        return name.contentEquals(((String)computerType.getSelectedItem()));

                    }
                });
            }
        });

        // Ensure proper repainting
        revalidate();
        repaint();
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
    }
}