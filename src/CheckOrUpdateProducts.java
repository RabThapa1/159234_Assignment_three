import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckOrUpdateProducts extends JPanel {

    private final boolean isManager;
    private final JTextField modelField;
    private final JTextField brandField;
    private final JComboBox<String> categoryField;
    private final JComboBox<String> typeField;
    private final JTextField cpuFamilyField;
    private final JLabel memorySizeLabel;
    private final JTextField memorySizeField;
    private final JLabel ssdCapacityLabel ;
    private final JTextField ssdCapacityField;
    private final JLabel screenSizeLabel ;
    private final JTextField screenSizeField ;
    private final JLabel priceLabel ;
    private final JTextField priceField ;
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton clearButton;
    DevicesRunner devices;
    DevicesTableModel model;


    public CheckOrUpdateProducts(boolean isManager, DevicesTableModel tableModel, DevicesRunner devices){

        this.isManager = isManager;
        this.devices = devices;
        this.model = tableModel;


        //initialise J Components
        JLabel modelLabel = new JLabel("Model ID:");
         modelField = new JTextField(20);
        JLabel brandLabel = new JLabel("Brand");
         brandField  = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
         categoryField = new JComboBox<String>();
        JLabel typeLabel = new JLabel("Type:");
         typeField = new JComboBox<String>();
        JLabel cpuFamilyLabel = new JLabel("CPU Family");
         cpuFamilyField = new JTextField(20);
         memorySizeLabel = new JLabel("Memory Size");
         memorySizeField = new JTextField(20);
         ssdCapacityLabel = new JLabel("SSD Capacity:");
         ssdCapacityField = new JTextField(20);
         screenSizeLabel = new JLabel("Screen Size");
         screenSizeField = new JTextField(20);
         priceLabel =new JLabel("Price");
         priceField = new JTextField(20);
         addButton = new JButton("Add");
         updateButton = new JButton("Update");
         deleteButton = new JButton("Delete");
         clearButton = new JButton("Clear");


        //Set Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding around components
        gbc.fill = GridBagConstraints.BOTH; // Make components grow both horizontally and vertically
        gbc.weightx = 1; // Allow horizontal expansion
        gbc.weighty = 1; // Allow vertical expansion

        // Add components to panel. addComponent method/function is called.
        addComponent(gbc, modelLabel, 0, 0);
        addComponent(gbc, modelField, 1, 0);
        addComponent(gbc, categoryLabel, 0, 1);
        addComponent(gbc, categoryField, 1, 1);
        addComponent(gbc, typeLabel, 0, 2);
        addComponent(gbc, typeField, 1, 2);
        addComponent(gbc, brandLabel, 0, 3);
        addComponent(gbc, brandField, 1, 3);
        addComponent(gbc, cpuFamilyLabel, 0, 4);
        addComponent(gbc, cpuFamilyField, 1, 4);
        addComponent(gbc, memorySizeLabel, 0, 5);
        addComponent(gbc, memorySizeField, 1, 5);
        addComponent(gbc, ssdCapacityLabel, 0, 6);
        addComponent(gbc, ssdCapacityField, 1, 6);
        addComponent(gbc, screenSizeLabel, 0, 7);
        addComponent(gbc, screenSizeField, 1, 7);
        addComponent(gbc, priceLabel, 0,8);
        addComponent(gbc,priceField,1,8);

        // Add buttons
        gbc.gridwidth = 1; // Reset gridwidth to 1
        addComponent(gbc, addButton, 0, 9);
        addComponent(gbc, updateButton, 1, 9);
        addComponent(gbc, deleteButton, 0, 10);
        addComponent(gbc, clearButton, 1, 10);

        //Add items to the category and type combobox
        String[] categories = {"Desktop PC", "Laptop", "Tablet"};
        for(String category : categories){
            categoryField.addItem(category);
        }

        //Ensure appropriate text field is editable based on category selected when the frame loads
        updateFieldEditabilityBasedOnCategory((String) categoryField.getSelectedItem());

        String[] types = {"Gaming", "Home & Study", "Business", "Compact", "Thin & Light", "Android", "Windows"};
        for(String type : types){
            typeField.addItem(type);
        }


        //call the method to enable or disable ability to edit the field.
        updateFieldEditability();

        //Add action listener to the Clear button
        clearButton.addActionListener(e -> {

            //calling method clearFields to clear the Fields of any text.
            clearFields();

        });

        //Action listener method to the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = modelField.getText();
                String category = String.valueOf(categoryField.getSelectedItem());
                String type = String.valueOf(typeField.getSelectedItem());
                String cpuFamily = cpuFamilyField.getText();
                String brand = brandField.getText();

                AtomicBoolean errorFlag = new AtomicBoolean(false);

                double price = getDoubleValue(priceField, priceLabel,errorFlag);
                int ssdCapacity = getIntegerValue(ssdCapacityField, ssdCapacityLabel,errorFlag);
                int memorySize = getIntegerValue(memorySizeField, memorySizeLabel,errorFlag);
                double screenSize = getDoubleValue(screenSizeField, screenSizeLabel,errorFlag);

                // Check for error flag
                if(errorFlag.get()){
                    // Show an error message or do nothing
                    JOptionPane.showMessageDialog(null, "Please correct the input value");
                }else {
                    // Add the device only if all values are correct
                    devices.addDevice(category, type, id, brand, cpuFamily, memorySize, ssdCapacity, screenSize, price);
                    model.refreshData(devices.getDevices());



                }
            }
        });


        //Action listener method to delete device entry
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = modelField.getText();

                devices.deleteDevice(id);

                //once the item is deleted, calling clearField method to clear the fields of any text
                clearFields();
                model.refreshData(devices.getDevices());


            }
        });

        //Action listener method to update device entry
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = modelField.getText();
                String category = String.valueOf(categoryField.getSelectedItem());
                String type = String.valueOf(typeField.getSelectedItem());
                String cpuFamily = cpuFamilyField.getText();
                String brand = brandField.getText();

                AtomicBoolean errorFlag = new AtomicBoolean(false);

                double price = getDoubleValue(priceField, priceLabel,errorFlag);
                int ssdCapacity = getIntegerValue(ssdCapacityField, ssdCapacityLabel,errorFlag);
                int memorySize = getIntegerValue(memorySizeField, memorySizeLabel,errorFlag);
                double screenSize = getDoubleValue(screenSizeField, screenSizeLabel,errorFlag);

                // Check for error flag
                if(errorFlag.get()){
                    // Show an error message or do nothing
                    JOptionPane.showMessageDialog(null, "Please correct the input value");
                }else {
                    // Add the device only if all values are correct
                    System.out.println("Update");
                    devices.updateDevice(category, type, id, brand, cpuFamily, memorySize, ssdCapacity, screenSize, price);
                    model.refreshData(devices.getDevices());



                }

            }
        });

           //Action to listen the change in category combo box change
        categoryField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = String.valueOf(categoryField.getSelectedItem());
                updateFieldEditabilityBasedOnCategory(selectedCategory);
            }
        });

    }





    //This method makes the Field editable if a Manager logs in
    private void updateFieldEditability() {
        modelField.setEditable(isManager);
        categoryField.setEnabled(isManager);
        typeField.setEnabled(isManager);
        brandField.setEditable(isManager);
        cpuFamilyField.setEditable(isManager);
        addButton.setEnabled(isManager);
        updateButton.setEnabled(isManager);
        deleteButton.setEnabled(isManager);
        clearButton.setEnabled(isManager);
        priceField.setEditable(isManager);
        ssdCapacityField.setEditable(isManager);
        memorySizeField.setEditable(isManager);
        screenSizeField.setEditable(isManager);
    }


    //This provides a method to add JComponents in a GridBagLayout. Helps reduce clutter
    private void addComponent(GridBagConstraints gbc, JComponent component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    //This method is called to update the Fields when a row is selected in the JTable. Called in the BrowseProductPanel Class
    public void updateFields(String category, String type, String id,String brand, String cpuFamily, String memorySize,String ssdCapacity,String  screenSize,double price){
        categoryField.setSelectedItem(category);
        modelField.setText(id);
        typeField.setSelectedItem(type);
        cpuFamilyField.setText(cpuFamily);
        brandField.setText(brand);
        priceField.setText(String.valueOf(price));

        //Handle Optional Fields, SetOptionalFieldText method is called to update the fields accordingly.
        setOptionalFieldText(ssdCapacityField, ssdCapacity);
        setOptionalFieldText(memorySizeField, memorySize);
        setOptionalFieldText(screenSizeField, screenSize);

        memorySizeLabel.getName();


    }


    //Method to set optional field based on device category type.
    private void setOptionalFieldText(JTextField field, String value){
        if (!value.equals("null") ) {
            field.setText(value);
            field.setEditable(true);
        } else {
            field.setEditable(false);
            field.setText("");// Set to an empty string if value is null or empty
        }
    }

    //Method to run try catch block and return integer value. Reduce code clutter
    private int getIntegerValue(JTextField field, JLabel label, AtomicBoolean errorFlag){

        /*Not all the field are required for all category, if a certain field is empty/null zero is returned. when the
        method to add the item is called located in DevicesRunner class, it will use constructor based on the category*/

        int temp = 0;

        if(field.getText().trim().isEmpty()){
            return temp;
        }

        else {
            try {
                temp = Integer.parseInt(field.getText());
            } catch (NullPointerException ignored) {
            } catch (NumberFormatException event) {
                JOptionPane.showMessageDialog(null, "Wrong format, Please provide int value for " + label.getText());
                field.setText("");
                errorFlag.set(true);
            }
        }

        return temp;
    }

    //Method to run try catch block and return double value. Reduce code Clutter
    private double getDoubleValue(JTextField field, JLabel label, AtomicBoolean errorFlag){

        /*Not all the field are required for all category, if a certain field is empty/null zero is returned. when the
        method to add the item is called located in DevicesRunner class, it will use constructor based on the category*/

        double temp = 0.0;

      if(field.getText().trim().isEmpty()){
          return temp;
      }else {
          try {
              temp = Double.parseDouble(field.getText());
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(null, "Wrong format, Please provide double value for " + label.getText());
              field.setText("");
              errorFlag.set(true);
          }
      }
        return temp;
    }

    // Method to update field editability based on the selected category
    private void updateFieldEditabilityBasedOnCategory(String selectedCategory) {

        /* Update field edit ability based on the selected category. Example Desktop PC category has no Screen Size Field.
           Additionally, cleat the field that are disabled of any values
        * */
        if ("Desktop PC".equals(selectedCategory)) {
            screenSizeField.setEnabled(false);
            screenSizeField.setEditable(false); //Make sure users can't edit the field
            screenSizeField.setText("");

            memorySizeField.setEnabled(true);
            memorySizeField.setEditable(true);
            ssdCapacityField.setEnabled(true);
            ssdCapacityField.setEditable(true);
        } else if ("Tablet".equals(selectedCategory)) {
            screenSizeField.setEnabled(true);
            screenSizeField.setEditable(true);

            memorySizeField.setEnabled(false);
            memorySizeField.setEditable(true);
            memorySizeField.setText("");

            ssdCapacityField.setEnabled(false);
            ssdCapacityField.setEditable(true);
            ssdCapacityField.setText("");
        } else if ("Laptop".equals(selectedCategory)) {
            screenSizeField.setEnabled(true);
            screenSizeField.setEditable(true);
            memorySizeField.setEnabled(true);
            memorySizeField.setEditable(true);
            ssdCapacityField.setEnabled(true);
            ssdCapacityField.setEditable(true);
        }

        // Revalidate and repaint to ensure UI updates
        revalidate();
        repaint();
    }

    //Method to clear the fields
    private void clearFields(){
        modelField.setText("");
        brandField.setText("");
        cpuFamilyField.setText("");
        memorySizeField.setText("");
        screenSizeField.setText("");
        ssdCapacityField.setText("");
        priceField.setText("");

        ssdCapacityField.setEditable(isManager);
        memorySizeField.setEditable(isManager);
        screenSizeField.setEditable(isManager);
    }



}
