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


        JLabel lblNewLabel_1 = new JLabel("Checkout a Book");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(26, 20, 400, 40);
        add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Select book to checkout or enter ISBN and press enter");
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel.setBounds(26, 75, 365, 40);
        add(lblNewLabel);
        
        JLabel lblLastName = new JLabel("Select a Member or enter Member ID and press enter");
        lblLastName.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblLastName.setBounds(26, 188, 365, 40);
        add(lblLastName);

        lblCheckoutDaysInfo = new JLabel("Maximum checkout days");
        lblCheckoutDaysInfo.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblCheckoutDaysInfo.setBounds(26, 292, 286, 40);
        add(lblCheckoutDaysInfo);

        JComboBox cmbBookList = new JComboBox();
        cmbBookList.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                var book = controller.getBook(cmbBookList.getSelectedIndex());
                lblCheckoutDaysInfo.setText("Maximum checkout days: " + book.maxCheckoutDays);
            }
        });
        cmbBookList.setBounds(26, 128, 497, 40);
        for (Book book : controller.getBooks()) {
            cmbBookList.addItem(book.toString());
        }
        add(cmbBookList);

        JComboBox cmbMemberList = new JComboBox();
        cmbMemberList.setBounds(26, 240, 497, 40);
        for (Member member : controller.getMembers()) {
            cmbMemberList.addItem(member.getFirstName());
        }
        add(cmbMemberList);

        JButton btnCheckoutBook = new JButton("Checkout Book");
        btnCheckoutBook.setFont(new Font("Poppins", Font.PLAIN, 13));
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
        btnCheckoutBook.setBounds(26, 348, 178, 40);
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
        txtMemberId.setBounds(393, 187, 130, 40);
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
        txtBookId.setBounds(393, 74, 130, 40);
        add(txtBookId);
        txtBookId.setColumns(10);
    }
}
