package ui;

import controller.SystemController;
import data.UserRoles;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.Component;

public class MainUI extends JFrame {

    private static JPanel currentPanel;
    private SystemController systemController = SystemController.getInstance();
    private JPanel contentPane;
    private static JPanel loader;
    private JButton btnNewMember;
    private JButton btnEditMember;
    private JButton btnViewMembers;
    private JButton btnAddAuthor;
    private JButton btnAddBook;
    private JButton btnBookList;
    private JButton btnCheckoutBook;
    private JButton btnReturnBook;
    private JButton btnViewCheckouts;
    private JButton btnUpdateBookCopy;
    private static MainUI frame;
    private JPanel titlePanel;
    private JPanel btnPanel;
//    private JButton btnLogOut;
    private JLabel btnLogOut;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public static void showUI() {
        if (MainUI.frame == null) {
            MainUI.frame = new MainUI();
        }

        MainUI.frame.setVisible(true);
    }

    public static void hideUI() {
        frame.setVisible(false);
    }

    /**
     * Create the frame.
     */
    private MainUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 620);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(6, 6, 285, 580);
        contentPane.add(panel);

        loader = new JPanel();
        loader.setBackground(Color.WHITE);
        loader.setBounds(303, 6, 691, 580);
        contentPane.add(loader);
        loader.setLayout(new BoxLayout(loader, BoxLayout.X_AXIS));
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setIcon(new ImageIcon(MainUI.class.getResource("/ui/imgs/home.jpg")));
        loader.add(lblNewLabel_1);
        panel.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));
        titlePanel.setBounds(0, 0, 285, 129);
        panel.add(titlePanel);
        titlePanel.setLayout(null);

        JLabel lblLoginInfo = new JLabel(getLoginDetails());
        lblLoginInfo.setBounds(18, 47, 261, 20);        
        titlePanel.add(lblLoginInfo);
        lblLoginInfo.setForeground(new Color(95, 158, 160));
        lblLoginInfo.setFont(new Font("Poppins", Font.BOLD, 24));
        lblLoginInfo.setHorizontalAlignment(SwingConstants.LEFT);
        

        JLabel lblLoginDesignation = new JLabel(getDesignation());
        lblLoginDesignation.setBounds(18, 69, 261, 20);
        titlePanel.add(lblLoginDesignation);
        lblLoginDesignation.setForeground(Color.BLACK);
        lblLoginDesignation.setFont(new Font("Poppins", Font.ITALIC, 13));
        lblLoginDesignation.setHorizontalAlignment(SwingConstants.LEFT);
        
        lblNewLabel = new JLabel("Welcome");
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(18, 20, 61, 16);
        titlePanel.add(lblNewLabel);
        
        btnLogOut = new JLabel("Log Out");
        btnLogOut.setForeground(Color.GRAY);
        btnLogOut.setFont(new Font("Poppins", Font.PLAIN, 12));
        btnLogOut.setBounds(18, 95, 61, 16);
        btnLogOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	systemController.logOut();
            	systemController.reset();
                hideUI();
//                LoginUI.showUI();
                LoginUI.restartUI();
            }
        });
        
        titlePanel.add(btnLogOut);

        btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBounds(0, 128, 285, 452);
        panel.add(btnPanel);
        btnPanel.setLayout(new GridLayout(0, 1, 0, 0));

        btnNewMember = new JButton(">  Add Member");
        btnNewMember.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnNewMember.setHorizontalAlignment(SwingConstants.LEFT);
        btnPanel.add(btnNewMember);
        btnNewMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddMemberUI());
            }
        });
        btnNewMember.setBackground(Color.WHITE);
        btnNewMember.setOpaque(true);

        btnEditMember = new JButton(">  Update Member");
        btnEditMember.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnEditMember.setHorizontalAlignment(SwingConstants.LEFT);
        btnEditMember.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new EditExistingMemberUI());
        	}
        });
        btnPanel.add(btnEditMember);
        btnEditMember.setBackground(Color.WHITE);
        btnEditMember.setOpaque(true);

        btnViewMembers = new JButton(">  List Members");
        btnViewMembers.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnViewMembers.setHorizontalAlignment(SwingConstants.LEFT);
        btnPanel.add(btnViewMembers);
        btnViewMembers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new MemberListUI());
            }
        });
        btnViewMembers.setBackground(Color.WHITE);
        btnViewMembers.setOpaque(true);

        btnAddAuthor = new JButton(">  Add Author");
        btnAddAuthor.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnAddAuthor.setHorizontalAlignment(SwingConstants.LEFT);
        btnPanel.add(btnAddAuthor);
        btnAddAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddAuthorUI());
            }
        });
        btnAddAuthor.setBackground(Color.WHITE);
        btnAddAuthor.setOpaque(true);

        btnAddBook = new JButton(">  Add Book");
        btnAddBook.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnAddBook.setHorizontalAlignment(SwingConstants.LEFT);
        btnPanel.add(btnAddBook);
        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddBookUI());
            }
        });
        btnAddBook.setBackground(Color.WHITE);
        btnAddBook.setOpaque(true);

        btnBookList = new JButton(">  List Books");
        btnBookList.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnBookList.setHorizontalAlignment(SwingConstants.LEFT);
        btnPanel.add(btnBookList);
        btnBookList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AllBooksList());
            }
        });
        btnBookList.setBackground(Color.WHITE);
        btnBookList.setOpaque(true);

        btnCheckoutBook = new JButton(">  Checkout a Book");
        btnCheckoutBook.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnCheckoutBook.setHorizontalAlignment(SwingConstants.LEFT);
        btnCheckoutBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new CheckoutBookUI());
        	}
        });
        btnPanel.add(btnCheckoutBook);
        btnCheckoutBook.setBackground(Color.WHITE);
        btnCheckoutBook.setOpaque(true);

        btnReturnBook = new JButton(">  Return a Book");
        btnReturnBook.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnReturnBook.setHorizontalAlignment(SwingConstants.LEFT);
        btnReturnBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new ReturnBookUI());
        	}
        });
        btnPanel.add(btnReturnBook);
        btnReturnBook.setBackground(Color.WHITE);
        btnReturnBook.setOpaque(true);

        btnViewCheckouts = new JButton(">  View Checkout History");
        btnViewCheckouts.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnViewCheckouts.setHorizontalAlignment(SwingConstants.LEFT);
        btnViewCheckouts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new CheckoutBookListUI());
        	}
        });
        btnPanel.add(btnViewCheckouts);
        btnViewCheckouts.setBackground(Color.WHITE);
        btnViewCheckouts.setOpaque(true);

        btnUpdateBookCopy = new JButton(">  Update Book Copy");
        btnUpdateBookCopy.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnUpdateBookCopy.setHorizontalAlignment(SwingConstants.LEFT);
        btnUpdateBookCopy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new UpdateBookCopyUI());
        	}
        });
        btnPanel.add(btnUpdateBookCopy);
        btnUpdateBookCopy.setBackground(Color.WHITE);
        btnUpdateBookCopy.setOpaque(true);

        //enable buttons based on roles
        enableButtonBasedOnRole();

    }

    private String getLoginDetails() {
        var user = this.systemController.getLoggedInUser();
        if (user == null) {
            return "...";
        }
        var str = user.firstName;

        return str;
    }

    private String getDesignation() {
        var user = this.systemController.getLoggedInUser();
        if (user == null) {
            return "...";
        }
        var str = "Logged in as " + user.role.toString();

        return str;
    }

    private void enableButtonBasedOnRole() {
        var user = systemController.getLoggedInUser();
        disableAllBtns();
        if (user == null) {
            return;
        }

        if (user.role == UserRoles.Librarian) {
            enableLibrarianBtns();
        } else if (user.role == UserRoles.Admin) {
            enableAdminBtns();
        } else if (user.role == UserRoles.Both) {
            enableLibrarianBtns();
            enableAdminBtns();
        }
    }


    private void disableAllBtns() {
        //disable all btns
        btnNewMember.setEnabled(false);
        btnEditMember.setEnabled(false);
        btnViewMembers.setEnabled(false);
        btnAddAuthor.setEnabled(false);
        btnAddBook.setEnabled(false);
        btnBookList.setEnabled(false);
        btnCheckoutBook.setEnabled(false);
        btnReturnBook.setEnabled(false);
        btnViewCheckouts.setEnabled(false);
        btnUpdateBookCopy.setEnabled(false);
    }

    private void enableLibrarianBtns() {
        btnBookList.setEnabled(true);
        btnCheckoutBook.setEnabled(true);
        btnViewCheckouts.setEnabled(true);
        btnReturnBook.setEnabled(true);
    }

    private void enableAdminBtns() {
        btnNewMember.setEnabled(true);
        btnEditMember.setEnabled(true);
        btnViewMembers.setEnabled(true);
        btnAddAuthor.setEnabled(true);
        btnAddBook.setEnabled(true);
        btnBookList.setEnabled(true);
        btnUpdateBookCopy.setEnabled(true);
    }


    static void LoadUI(JPanel panel) {
        currentPanel = panel;
        loader.removeAll();
        loader.add(panel);
        loader.revalidate();
    }


}
