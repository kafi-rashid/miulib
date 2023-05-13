package ui;

import controller.SystemController;
import data.Book;
import data.Member;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AllBooksList extends JPanel {
	private JTable jtblBookList;
	private JTable tblBookList;
	SystemController controller = SystemController.getInstance();

	/**
	 * Create the panel.
	 */
	public AllBooksList() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of Books");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 20, 200, 40);
		add(lblNewLabel);

		var books = controller.getBooks();
		String[][] data = new String[books.size()][5];

		var i = 0;
		for(Book book:books){
			data[i][0] = book.title;
			data[i][1] = book.title;
			data[i][2] = book.ISBN;
			data[i][3] = book.author.toString();
			data[i][4] = String.valueOf(book.getCopyOfBooks());
			i++;
		}

//        String[] columnNames = { "Book ID", "Title", "ISBN", "Authors", "Availability" };
//        DefaultTableModel model = new DefaultTableModel(data, columnNames);
//        tblBookList = new JTable(model);
//        tblBookList.setBounds(26, 75, 640, 409);		
//		add(tblBookList);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 640, 409);
		add(scrollPane);

        String[] columnNames = { "Book ID", "Title", "ISBN", "Authors", "Availability" };
        tblBookList = new JTable(data, columnNames);
        tblBookList.setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 13));
//		MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
//		tblBookList.setBorder(border);
		scrollPane.setViewportView(tblBookList);

	}
}
