import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProducstMangement extends JFrame {

    public ProducstMangement(){

        setTitle("Computer Products Management System");
        setBounds(150,200,1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Initialise J Components
        JTabbedPane tabbedPane  = new JTabbedPane();
        JButton logOutButton = new JButton("Click to Log out",new ImageIcon("D:\\Massey\\ObjectOrientedProgramming_159234\\Git_Assignment_three\\src\\logo.png") );
        logOutButton.setHorizontalTextPosition(SwingConstants.CENTER);
        logOutButton.setVerticalTextPosition(SwingConstants.TOP);
        logOutButton.setFont(new Font("Helvetica", Font.PLAIN, 40));

        //Creates a tabbed pane frame and add tabbed panels
        tabbedPane.addTab("Browse Products", new BrowseProductsPanel());
        tabbedPane.addTab("Check/Update Products Details", new CheckOrUpdateProducts());
        add(tabbedPane, BorderLayout.CENTER);

        add(logOutButton, BorderLayout.SOUTH);

        //Add Button action listener
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StoreRunner();
            }
        });

        setVisible(true);



    }
}
