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
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

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
    private JButton btnLogOut;


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
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 51, 51));
        panel.setBounds(6, 6, 285, 580);
        contentPane.add(panel);

        loader = new JPanel();
        loader.setBackground(UIManager.getColor("Button.disabledText"));
        loader.setBounds(303, 6, 691, 580);
        contentPane.add(loader);
        loader.setLayout(new BoxLayout(loader, BoxLayout.X_AXIS));
        panel.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        titlePanel.setBounds(0, 0, 285, 83);
        panel.add(titlePanel);
        titlePanel.setLayout(null);

        JLabel lblLoginInfo = new JLabel(getLoginDetails());
        lblLoginInfo.setBounds(6, 47, 273, 20);
        titlePanel.add(lblLoginInfo);
        lblLoginInfo.setForeground(UIManager.getColor("CheckBox.background"));
        lblLoginInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblLoginInfo.setHorizontalAlignment(SwingConstants.CENTER);

        btnLogOut = new JButton("Log out");
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                systemController.logOut();
                hideUI();
                LoginUI.showUI();
            }
        });
        btnLogOut.setBounds(187, 6, 92, 29);
        btnLogOut.setForeground(Color.RED);
        btnLogOut.setBackground(Color.WHITE);
        titlePanel.add(btnLogOut);

        btnPanel = new JPanel();
        btnPanel.setBounds(0, 86, 285, 494);
        panel.add(btnPanel);
        btnPanel.setLayout(new GridLayout(0, 1, 0, 0));

        btnNewMember = new JButton("Add new member");
        btnPanel.add(btnNewMember);
        btnNewMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddMemberUI());
            }
        });
        btnNewMember.setBackground(UIManager.getColor("CheckBox.background"));
        btnNewMember.setOpaque(true);

        btnEditMember = new JButton("Edit existing member");
        btnEditMember.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new EditExistingMemberUI());
        	}
        });
        btnPanel.add(btnEditMember);
        btnEditMember.setBackground(UIManager.getColor("CheckBox.background"));
        btnEditMember.setOpaque(true);

        btnViewMembers = new JButton("View All Members");
        btnPanel.add(btnViewMembers);
        btnViewMembers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new MemberListUI());
            }
        });
        btnViewMembers.setBackground(UIManager.getColor("CheckBox.background"));
        btnViewMembers.setOpaque(true);

        btnAddAuthor = new JButton("Add New Author");
        btnPanel.add(btnAddAuthor);
        btnAddAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddAuthorUI());
            }
        });
        btnAddAuthor.setBackground(UIManager.getColor("CheckBox.background"));
        btnAddAuthor.setOpaque(true);

        btnAddBook = new JButton("Add New Book");
        btnPanel.add(btnAddBook);
        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AddBookUI());
            }
        });
        btnAddBook.setBackground(UIManager.getColor("CheckBox.background"));
        btnAddBook.setOpaque(true);

        btnBookList = new JButton("All Books");
        btnPanel.add(btnBookList);
        btnBookList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadUI(new AllBooksList());
            }
        });
        btnBookList.setBackground(UIManager.getColor("CheckBox.background"));
        btnBookList.setOpaque(true);

        btnCheckoutBook = new JButton("Checkout Book");
        btnCheckoutBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new CheckoutBookUI());
        	}
        });
        btnPanel.add(btnCheckoutBook);
        btnCheckoutBook.setBackground(UIManager.getColor("CheckBox.background"));
        btnCheckoutBook.setOpaque(true);

        btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new ReturnBookUI());
        	}
        });
        btnPanel.add(btnReturnBook);
        btnReturnBook.setBackground(UIManager.getColor("CheckBox.background"));
        btnReturnBook.setOpaque(true);

        btnViewCheckouts = new JButton("View Checkouts");
        btnViewCheckouts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new CheckoutBookListUI());
        	}
        });
        btnPanel.add(btnViewCheckouts);
        btnViewCheckouts.setBackground(UIManager.getColor("CheckBox.background"));
        btnViewCheckouts.setOpaque(true);

        btnUpdateBookCopy = new JButton("Update Book Copy");
        btnUpdateBookCopy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadUI(new UpdateBookCopyUI());
        	}
        });
        btnPanel.add(btnUpdateBookCopy);
        btnUpdateBookCopy.setBackground(UIManager.getColor("CheckBox.background"));
        btnUpdateBookCopy.setOpaque(true);

        //enable buttons based on roles
        enableButtonBasedOnRole();

    }

    private String getLoginDetails() {
        var user = this.systemController.getLoggedInUser();
        if (user == null) {
            return "Login first!";
        }
        var str = user.firstName + " - " + user.role.toString();

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
