import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProducstMangement extends JFrame {

    private boolean isManager;
    CheckOrUpdateProducts checkOrUpdateProducts;
    BrowseProductsPanel browseProductsPanel;
    DevicesRunner devices;

    public ProducstMangement(boolean isManager){

        this.isManager = isManager;

        setTitle("Computer Products Management System");
        setBounds(150,200,1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Initialise J Components
        JTabbedPane tabbedPane  = new JTabbedPane();
        JButton logOutButton = new JButton("Click to Log out",new ImageIcon("D:\\Massey\\ObjectOrientedProgramming_159234\\Git_Assignment_three\\src\\logo.png") );
        logOutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        logOutButton.setVerticalTextPosition(SwingConstants.TOP);
        logOutButton.setFont(new Font("Helvetica", Font.PLAIN, 40));

// Initialize CheckOrUpdateProducts and BrowseProductsPanel after DevicesRunner
        checkOrUpdateProducts = new CheckOrUpdateProducts(isManager);
        browseProductsPanel = new BrowseProductsPanel(checkOrUpdateProducts);
         devices = new DevicesRunner();






        //Creates a tabbed pane frame and add tabbed panels
        tabbedPane.addTab("Browse Products", browseProductsPanel);
        tabbedPane.addTab("Check/Update Products Details", checkOrUpdateProducts);
        add(tabbedPane, BorderLayout.CENTER);

        add(logOutButton, BorderLayout.SOUTH);

        //Add Button action listener
        logOutButton.addActionListener(e -> {
            dispose();
            new StoreRunner();
        });

        setVisible(true);



    }
}
