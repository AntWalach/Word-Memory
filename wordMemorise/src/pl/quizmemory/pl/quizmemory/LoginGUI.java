package pl.quizmemory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graficzna implementacja ekranu logowania
 */
public class LoginGUI extends MenuGUI implements ActionListener {

    protected static MenuGUI.Frame mainFrame;
    protected static MenuGUI.Panel panelLogin;
    protected static JTextField userText;
    protected static JLabel passwordLabel;
    protected static JPasswordField userPassword;
    protected static JButton loginButton;
    protected static JButton registerButton;
    protected static JLabel success;

    /**
     * Dodanie graficznych elementów do wyświetlanego panelu
     */
    public void ILogin() {
        panelLogin = new MenuGUI.Panel();
        mainFrame = new MenuGUI.Frame();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(panelLogin);

        panelLogin.setLayout(null);

        JLabel label = new JLabel("User");
        label.setBounds(450, 330, 80, 25);
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        panelLogin.add(label);

        userText = new JTextField();
        userText.setBounds(580, 320, 180, 40);
        userText.setFont(new Font("Arial", Font.PLAIN, 20));
        panelLogin.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(450, 380, 150, 25);
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        panelLogin.add(passwordLabel);

        userPassword = new JPasswordField();
        userPassword.setBounds(580, 370, 180, 40);
        userPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        panelLogin.add(userPassword);

        loginButton = new JButton("Login");
        loginButton.setBounds(580, 440, 80, 25);
        loginButton.addActionListener(new LoginGUI());
        panelLogin.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(670, 440, 90, 25);
        registerButton.addActionListener(new LoginGUI());
        panelLogin.add(registerButton);

        success = new JLabel("");
        success.setBounds(500, 470, 300, 25);
        panelLogin.add(success);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        LoginGUI.mainFrame.add(panelLogin);
    }

    /**
     * Funkcja wykonująca operacja logowania/rejestracji
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        UserManagement userManagement = new UserManagement();
        String user = userText.getText();
        String password = userPassword.getText();
        if (e.getSource() == loginButton) {
            userManagement.loginUser(user, password);
        } else if (e.getSource() == registerButton) {
            userManagement.registerUser(user, password, false);
        }
    }
}
