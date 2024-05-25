import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreRunner extends JFrame{
    public StoreRunner() {

        //set Frame behaviour

        setTitle("Sales Person login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setBounds(200,200,900,350);

        //Initialise components for the Frame
        JButton loginButton = new JButton("Click to Login", new ImageIcon("D:\\Massey\\ObjectOrientedProgramming_159234\\Git_Assignment_three\\src\\logo.png"));

        //setLayout(new FlowLayout());
        loginButton.setHorizontalTextPosition(SwingConstants.CENTER);
        loginButton.setVerticalTextPosition(SwingConstants.TOP);
        loginButton.setFont(new Font("Helvetica", Font.PLAIN, 40));
        add(loginButton, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow window = new LoginWindow();
                dispose();
            }
        });
        setResizable(false);
        setVisible(true);

    }
    public static void main(String[] args){

        //Start the main Window for the program
        new StoreRunner();

    }
}
