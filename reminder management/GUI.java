import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GUI implements ActionListener {
    JFrame frame;
    JButton loginButton;
    JButton signupButton;
    JTextField usernameField;
    JPasswordField passwordField;

    public GUI() {
        frame = new JFrame("Reminder Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        // Set the bounds for components
        usernameField.setBounds(150, 30, 200, 25);
        passwordField.setBounds(150, 70, 200, 25);
        loginButton.setBounds(150, 110, 90, 30);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String Username = usernameField.getText();
                String Passcode = String.valueOf(passwordField.getPassword());

                User user = getAuthenticatedUser(Username, Passcode);

                if (user != null) {
                    Mainframe mainframe = new Mainframe();
                    mainframe.initialise(user);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "username or invalid passwond",
                            "Try ag", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        signupButton.setBounds(260, 110, 90, 30);

        frame.add(new JLabel("Username:")).setBounds(50, 30, 100, 25);
        frame.add(usernameField);
        frame.add(new JLabel("Password:")).setBounds(50, 70, 100, 25);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);
        frame.setVisible(true);

    }

    User getAuthenticatedUser(String Username, String Passcode) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/reminder_managment";
        final String dbUsername = "root";
        final String dbPassword = "!Shadoey01";

        try (Connection connection = DriverManager.getConnection(DB_URL, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM Users WHERE Username = ? AND Passcode = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, Username);
                statement.setString(2, Passcode);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.Username = resultSet.getString("Username");
                        user.UserID = resultSet.getString("UserID");
                        user.Email = resultSet.getString("Email");
                        user.Number = resultSet.getString("PhoneNumber");
                        user.Passcode = resultSet.getString("Passcode");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
