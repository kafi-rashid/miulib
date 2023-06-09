package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import controller.SystemController;
import data.Book;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class UpdateBookCopyUI extends JPanel {
    private JTable jtblBookList;
    private JTable tblBookList;
    SystemController controller = SystemController.getInstance();
    private JTextField txtAvailableCopies;
    private JButton btnUpdateCopies;

    /**
     * Create the panel.
     */
    @SuppressWarnings("serial")
	public UpdateBookCopyUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Update Copies of Book");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        lblNewLabel.setBounds(26, 20, 370, 40);
        add(lblNewLabel);

        var books = controller.getBooks();
        String[][] data = new String[books.size()][5];

        var i = 0;
        for (Book book : books) {
            data[i][0] = book.title;
            data[i][1] = book.title;
            data[i][2] = book.ISBN;
            data[i][3] = book.author.toString();
            data[i][4] = String.valueOf(book.getCopyOfBooks());

            i++;
        }
        String[] columnNames = {"Book ID", "Title", "ISBN", "Authors", "Availability"};
        tblBookList = new JTable(data, columnNames);
        tblBookList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				var selectedRow = tblBookList.getSelectedRow();
                if(selectedRow == -1){
                    btnUpdateCopies.setEnabled(false);
                    return;
                }
                btnUpdateCopies.setEnabled(true);
                var selectedBook = controller.getBooks().get(selectedRow);
                txtAvailableCopies.setText(String.valueOf(selectedBook.getCopyOfBooks()));
			}
        });
        tblBookList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
            private final EmptyBorder emptyBorder = new EmptyBorder(1, 1, 0, 0);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) cellComponent).setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
                return cellComponent;
            }
        });
        tblBookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        tblBookList.setBounds(26, 75, 640, 312);

        tblBookList.setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 75, 640, 329);
		add(scrollPane);
		
		scrollPane.setViewportView(tblBookList);
        
        txtAvailableCopies = new JTextField();
        txtAvailableCopies.setFont(new Font("Poppins", Font.PLAIN, 13));
        txtAvailableCopies.setBounds(144, 420, 82, 40);
        add(txtAvailableCopies);
        txtAvailableCopies.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Available copies:");
        lblNewLabel_2.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(26, 420, 135, 40);
        add(lblNewLabel_2);
        
        btnUpdateCopies = new JButton("Update Copies");
        btnUpdateCopies.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnUpdateCopies.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                var msg = controller.updateBookCopies(tblBookList.getSelectedRow(), txtAvailableCopies.getText());
                JOptionPane.showMessageDialog(null, msg);
                MainUI.LoadUI(new UpdateBookCopyUI());
        	}
        });
        btnUpdateCopies.setBounds(238, 421, 153, 40);
        add(btnUpdateCopies);
    }

}
