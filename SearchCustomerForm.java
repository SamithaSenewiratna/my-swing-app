import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

class SearchCustomerForm extends JFrame {

    private JButton btnBack, btnSearch;
    private JLabel lblPhoneNumber, lblTotal;
    private JTextField txtfPhoneNo;
    private JTable tblCustomer;
    private DefaultTableModel dtm;
    private CustomerCollection customerCollection;

    SearchCustomerForm(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

        setSize(450, 400);
        setTitle("Search Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(233, 89, 89));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(10, 10, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePage(customerCollection).setVisible(true);  
            }
        });
        add(btnBack);

        // Phone Number 
        lblPhoneNumber = new JLabel("Phone No :");
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 15));
        lblPhoneNumber.setBounds(35, 60, 100, 35);
        add(lblPhoneNumber);

        txtfPhoneNo = new JTextField();
        txtfPhoneNo.setBounds(130, 60, 150, 35);
        add(txtfPhoneNo);

        // Search button
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
        btnSearch.setBackground(new Color(0, 128, 128));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(300, 60, 100, 30);
        btnSearch.addActionListener(evt -> {
            String phoneNo = txtfPhoneNo.getText();
            Customer customer = customerCollection.searchCustomer(phoneNo);  

            if (customer == null) {
                JOptionPane.showMessageDialog(null, "Customer not found!");
            } else {
                loadCustomerData(customer);
            }
        });
        add(btnSearch);

        // Table 
        String[] columnNames = { "Size", "Qty", "Amount" };
        dtm = new DefaultTableModel(columnNames, 0);
        tblCustomer = new JTable(dtm);

   
        JScrollPane scrollPane = new JScrollPane(tblCustomer);
        scrollPane.setBounds(25, 110, 400, 150);
        add(scrollPane);

        // Total
        lblTotal = new JLabel("Total : 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setBounds(300, 270, 150, 30);
        add(lblTotal);
    }

    private void loadCustomerData(Customer customer) {
        
        dtm.setRowCount(0);

        
        String size = customer.getSize();
        int qty = customer.getQty();
        double amount = customer.getAmount();

        dtm.addRow(new Object[] { size, qty, String.format("%.2f", amount) });

       
        lblTotal.setText("Total : " + String.format("%.2f", amount));
    }
}
