package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.SystemController;
import data.Book;
import data.CheckoutBooks;
import data.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBookUI extends JPanel {

    private JTable table;
    private JTable tblBookList;
    SystemController controller = SystemController.getInstance();
    
	/**
	 * Create the panel.
	 */
	public ReturnBookUI() {
		setLayout(null);

        JLabel lblNewLabel = new JLabel("Return Book");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(26, 10, 125, 32);
        add(lblNewLabel);

        var books = controller.getCheckoutBooks();
        String[][] data = new String[books.size()][5];

        var i = 0;
        for (CheckoutBooks checkout : books) {
            data[i][0] = checkout.book.title;
            data[i][1] = checkout.book.ISBN;
            data[i][2] = checkout.book.author.toString();
            data[i][3] = String.valueOf(checkout.member.MemberId);
            data[i][4] = checkout.member.toString();

            i++;
        }
        String[] columnNames = {"Book Title", "ISBN", "Author", "Member ID", "Member Name"};
        tblBookList = new JTable(data, columnNames);
        tblBookList.setBounds(26, 54, 664, 362);

        add(tblBookList);
        
        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		var selectedRow = tblBookList.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(null, "Select a book from the table to return");
                    return;
                }
                controller.returnBook(selectedRow);
        	}
        });
        btnReturnBook.setBounds(498, 438, 194, 82);
        add(btnReturnBook);
	}
}
