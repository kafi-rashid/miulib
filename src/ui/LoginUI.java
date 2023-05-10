package ui;

import controller.SystemController;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private static LoginUI frame;

    SystemController systemController = SystemController.getInstance();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new LoginUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void showUI() {
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    private LoginUI() {
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(6, 6, 367, 460);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon(LoginUI.class.getResource("/ui/imgs/loginBackground.jpg")));
        lblNewLabel_2.setBounds(0, 0, 361, 460);
        panel.add(lblNewLabel_2);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.DARK_GRAY);
        panel_1.setBounds(385, 6, 409, 460);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(32, 125, 62, 16);
        panel_1.add(lblNewLabel);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(32, 203, 59, 16);
        panel_1.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
        	}
        });
        txtUsername.setBounds(22, 141, 368, 44);
        panel_1.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
        	}
        });
        txtPassword.setBounds(22, 219, 371, 44);
        panel_1.add(txtPassword);
        txtPassword.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
        	}
        });
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnLogin.setBounds(264, 388, 126, 44);
        panel_1.add(btnLogin);

        JLabel lblNewLabel_1 = new JLabel("MPP - LIBRARY SYSTEM");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
        lblNewLabel_1.setBounds(9, 6, 384, 68);
        panel_1.add(lblNewLabel_1);
    }


    private void login() {
    	var user = systemController.Login(txtUsername.getText(), txtPassword.getText());
        if(user == null){
            JOptionPane.showMessageDialog(frame, "Wrong username or password", "Authentication" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        MainUI.showUI();
        txtUsername.setText("");
        txtPassword.setText("");
        hideUI();
    }

    private void hideUI() {
        frame.setVisible(false);
    }
}
