package ui;

import controller.SystemController;
import data.Book;
import data.Member;

import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CheckoutBookUI extends JPanel {

    private SystemController controller = SystemController.getInstance();
    JLabel lblCheckoutDaysInfo;
    private JTextField txtMemberId;
    private JTextField txtBookId;

    /**
     * Create the panel.
     */
    public CheckoutBookUI() {
        setBackground(SystemColor.window);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Select book to checkout");
        lblNewLabel.setBounds(17, 87, 178, 16);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Book Checkout");
        lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(17, 6, 676, 37);
        add(lblNewLabel_1);

        JLabel lblLastName = new JLabel("Select a Member");
        lblLastName.setBounds(17, 158, 122, 16);
        add(lblLastName);

        lblCheckoutDaysInfo = new JLabel("Maximum checkout days");
        lblCheckoutDaysInfo.setBounds(16, 229, 286, 16);
        add(lblCheckoutDaysInfo);

        JComboBox cmbBookList = new JComboBox();
        cmbBookList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                var book = controller.getBook(cmbBookList.getSelectedIndex());
                lblCheckoutDaysInfo.setText("Maximum checkout days: " + book.maxCheckoutDays);
            }
        });
        cmbBookList.setBounds(6, 109, 544, 37);
        for (Book book : controller.getBooks()) {
            cmbBookList.addItem(book.toString());
        }
        add(cmbBookList);

        JComboBox cmbMemberList = new JComboBox();
        cmbMemberList.setBounds(6, 180, 544, 37);
        for (Member member : controller.getMembers()) {
            cmbMemberList.addItem(member.getFirstName());
        }
        add(cmbMemberList);

        JButton btnCheckoutBook = new JButton("Checkout Book");
        btnCheckoutBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var result = controller.checkoutBook(cmbBookList.getSelectedIndex(), cmbMemberList.getSelectedIndex());
                if (!result) {
                    JOptionPane.showMessageDialog(null, "No available copies of this book", "Not available", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "The book successfully checked out!", "Success", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        btnCheckoutBook.setBounds(332, 272, 218, 75);
        add(btnCheckoutBook);

        txtMemberId = new JTextField();
        txtMemberId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    var selectedId = controller.getMemberIndexById(txtMemberId.getText());
                    if(selectedId == -1){
                        JOptionPane.showMessageDialog(null, "ID not found");
                        return;
                    }
                    cmbMemberList.setSelectedIndex(selectedId);
                }
            }
        });
        txtMemberId.setBounds(134, 153, 130, 26);
        add(txtMemberId);
        txtMemberId.setColumns(10);

        txtBookId = new JTextField();
        txtBookId.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    var selectedId = controller.getBookIndexByISBN(txtBookId.getText());
                    if (selectedId == -1) {
                        JOptionPane.showMessageDialog(null, "Book not found");
                        return;
                    }
                    cmbBookList.setSelectedIndex(selectedId);
                }
        	}
        });
        txtBookId.setBounds(186, 82, 130, 26);
        add(txtBookId);
        txtBookId.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("(press enter)");
        lblNewLabel_2.setBounds(317, 87, 116, 16);
        add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("(press enter)");
        lblNewLabel_2_1.setBounds(265, 158, 116, 16);
        add(lblNewLabel_2_1);
    }
}
