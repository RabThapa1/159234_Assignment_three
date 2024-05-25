import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class LoginWindow extends JFrame {

    private static LinkedList<Staff> staff;

    public LoginWindow(){

        //Set properties to the login window
        setTitle("Sales Person Login");
        setBounds(300,90,400,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

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

                for(Staff aStaff : staff){
                    if(userText.equals(aStaff.getUserName()) && pwdText.equals(aStaff.getPassword())){
                        JOptionPane.showMessageDialog(null,"Login Successfull");
                        isSuccesfull = true;
                        dispose();
                        new ProducstMangement();
                    }
                }
                if(!isSuccesfull){
                    JOptionPane.showMessageDialog(null, "Username or Password incorrect!");
                }
            }
        });


        //load Staff details to authenticate
        loadStaffDetails();

        setVisible(true);



    }

     static void loadStaffDetails(){
        staff = new LinkedList<>();

        staff.add(new Staff("staff 1 - Salesperson", "p1", "p1"));
        staff.add(new Staff("staff 2 - Salesperson", "p2", "p2"));
        staff.add(new Staff("staff 3 - Salesperson", "p3", "p3"));
        staff.add(new Staff("staff 4 - Manager", "m1", "m1"));
        staff.add(new Staff("staff 5 - Manager", "m2", "m2"));

    }
}
