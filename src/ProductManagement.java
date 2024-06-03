import javax.swing.*;
import java.awt.*;

public class ProductManagement extends JFrame {


    public ProductManagement(boolean isManager){

        //Set Frame details.
        setTitle("Computer Products Management System");
        setBounds(150,200,1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Initialise J Components
        JTabbedPane tabbedPane  = new JTabbedPane();
        JButton logOutButton = new JButton("Click to Log out",new ImageIcon("D:\\Massey\\ObjectOrientedProgramming_159234\\Git_Assignment_three\\src\\logo.png") );
        logOutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        logOutButton.setVerticalTextPosition(SwingConstants.TOP);
        logOutButton.setFont(new Font("Helvetica", Font.PLAIN, 40));

         // Initialize Components
         DevicesRunner devices = new DevicesRunner();
         DevicesTableModel tableModel = new DevicesTableModel(devices.getDevices());
         CheckOrUpdateProducts checkOrUpdateProducts = new CheckOrUpdateProducts(isManager, tableModel, devices);
         BrowseProductsPanel browseProductsPanel = new BrowseProductsPanel(checkOrUpdateProducts, tableModel, devices);

        //Creates a tabbed pane frame and add tabbed panels
        tabbedPane.addTab("Browse Products", browseProductsPanel);
        tabbedPane.addTab("Check/Update Products Details", checkOrUpdateProducts);
        add(tabbedPane, BorderLayout.CENTER);

        add(logOutButton, BorderLayout.SOUTH);

        //Action listener for logout button
        logOutButton.addActionListener(e -> {
            dispose();
            new StoreRunner();
        });

        setVisible(true);



    }
}
