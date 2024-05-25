import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class BrowseProductsPanel extends JPanel {

    JLabel computerCategoryLabel;
    JComboBox computerCategory;
    JLabel computerTypeLabel;
    JComboBox computerType;
    DevicesRunner devices = new DevicesRunner();

    public BrowseProductsPanel(){

        computerCategoryLabel = new JLabel("Computer Category");
        computerCategory = new JComboBox();
        computerTypeLabel = new JLabel("Computer Type");
        computerType = new JComboBox();

        setLayout(null);

        // Set fonts
        computerCategoryLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerCategory.setFont(new Font("Arial", Font.PLAIN, 15));
        computerTypeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        computerType.setFont(new Font("Arial", Font.PLAIN, 15));

        // Set bounds (x, y, width, height)
        computerCategoryLabel.setBounds(10, 10, 140, 20);
        computerCategory.setBounds(160, 10, 140, 20);
        computerTypeLabel.setBounds(10, 40, 140, 20);
        computerType.setBounds(160, 40, 140, 20);


        //call method to add item to JComboBox
        updateJComboBox();

        //Create a table model
        DevicesTabelModel model = new DevicesTabelModel(devices.getDevices());

        //Create JTabel with custom model
        JTable table = new JTable(model);

        //Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Set bounds for the JScrollPane
        scrollPane.setBounds(10, 80, 460, 300);  // Adjust the size as needed
        // Add components to panel
        add(computerCategoryLabel);
        add(computerCategory);
        add(computerTypeLabel);
        add(computerType);
        add(scrollPane);




    }

    public void updateJComboBox(){


        Set<String> addedCategories = new HashSet<>();
        Set<String> addedComputerTypes = new HashSet<>();

        for(Devices aDevice : devices.getDevices()){

            String category = aDevice.getCategory();
            String type = aDevice.getType();

            if(!addedCategories.contains(category)) {
                computerCategory.addItem(aDevice.getCategory());
                addedCategories.add(category);
            }

            if(!addedComputerTypes.contains(type)) {
                computerType.addItem(aDevice.getType());
                addedComputerTypes.add(type);

            }

        }
    }

}
