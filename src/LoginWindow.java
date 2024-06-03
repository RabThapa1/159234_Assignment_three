import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    private final JFrame parentFrame;  // Reference to the parent frame

    public LoginWindow(JFrame parentFrame){

        //Set properties to the login window
        setTitle("Sales Person Login");
        setBounds(300,90,400,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        this.parentFrame = parentFrame;

        //Initialise JComponents required for Login window.
        JLabel userNameLabel = new JLabel("Username: ");
        JLabel  passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);
        JTextField userNameField = new JTextField(20);
        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;


        //Set size, location property for the components.
        userNameLabel.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,10,10);
        container.add(userNameLabel, gbc);


        userNameField.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx =1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        container.add(userNameField, gbc);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        container.add(passwordLabel, gbc);

        passwordField.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth =2;
        container.add(passwordField, gbc);

        login.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx =0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        container.add(login, gbc);

        cancel.setFont(new Font("Arial", Font.PLAIN,15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        container.add(cancel,gbc);

        //Add Action listener to login button for authentication
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText = userNameField.getText();
                String pwdText = new String(passwordField.getPassword());
                boolean isSuccesfull = false;

                //Initialise the staff class and load the credential.
                Staff staff = new Staff();
                staff.loadStaffCredentials();

                try {
                    staff.loadStaffCredentials(); // Load staff credentials from file
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error loading staff credentials: " + ex.getMessage());
                    return;
                }


                //loop through the credentials
                for(Staff aStaff : staff.getStaffCredentials()){
                    if(userText.equals(aStaff.getUserName()) && pwdText.equals(aStaff.getPassword())){
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        isSuccesfull = true;
                        parentFrame.dispose(); //disposes Parent frame that calls the login window.
                        dispose();
                        if(userText.equals("m1") || userText.equals("m2")){
                            new ProductManagement(true);
                        }
                        else {
                            new ProductManagement(false);
                        }

                    }
                }
                if(!isSuccesfull){
                    JOptionPane.showMessageDialog(null, "Username or Password incorrect!");
                }
            }
        });

        //Add Action listener to logOut button
        cancel.addActionListener(e -> dispose());



        setVisible(true);



    }

}
