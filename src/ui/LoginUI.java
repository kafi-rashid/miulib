package ui;

import controller.SystemController;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
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
        setBounds(100, 100, 1000, 650);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(589, 35, 386, 510);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(6, 177, 304, 16);
        panel_1.add(lblNewLabel);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBounds(6, 255, 304, 16);
        panel_1.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Poppins", Font.PLAIN, 13));
        
        Border paddingBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
        txtUsername.setBorder(compoundBorder);

        txtUsername.setBackground(UIManager.getColor("Button.highlight"));
        txtUsername.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
        	}
        });
        txtUsername.setBounds(6, 199, 288, 44);
        panel_1.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Poppins", Font.PLAIN, 13));
        txtPassword.setBackground(UIManager.getColor("Button.highlight"));
        txtPassword.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
        	}
        });
        txtPassword.setBounds(6, 277, 288, 44);

        paddingBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10);
        lineBorder = BorderFactory.createLineBorder(Color.GRAY);
        compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
        txtPassword.setBorder(compoundBorder);
        
        panel_1.add(txtPassword);
        txtPassword.setColumns(10);

        JButton btnLogin = new JButton("LOG IN");
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setFont(new Font("Poppins", Font.PLAIN, 13));
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
        btnLogin.setBounds(168, 333, 126, 44);
        panel_1.add(btnLogin);

        JLabel lblNewLabel_1 = new JLabel("Harmony");
        lblNewLabel_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 36));
        lblNewLabel_1.setBounds(6, 49, 175, 68);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Lib");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1_1.setForeground(new Color(50, 205, 50));
        lblNewLabel_1_1.setFont(new Font("Poppins", Font.BOLD, 36));
        lblNewLabel_1_1.setBounds(175, 49, 105, 68);
        panel_1.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_2.setIcon(new ImageIcon(LoginUI.class.getResource("/ui/imgs/login.jpg")));
        lblNewLabel_2.setBounds(107, 41, 449, 466);
        contentPane.add(lblNewLabel_2);
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

	public static void restartUI() {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                frame.dispose(); // Dispose the existing frame
	                frame = new LoginUI(); // Recreate the LoginUI frame
	                frame.setVisible(true); // Make the frame visible
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}
