import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

class OrderByAmount extends JFrame {

    private JButton btnBack;
    private JTable tblCustomer; 
    private DefaultTableModel dtm; 
    private CustomerCollection customerCollection;

    // Constructor
    OrderByAmount(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

        // Frame Setup
        setSize(550, 360);
        setTitle("Order By Amount");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(233, 89, 89));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(10, 10, 80, 30);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new ViewReportsHomePage(customerCollection).setVisible(true);
                dispose();
            }
        });
        add(btnBack);

        // Table 
        String[] columnNames = { "OrderId", "Phone No", "Size", "Qty", "Amount", "Status" };
        dtm = new DefaultTableModel(columnNames, 0);
        tblCustomer = new JTable(dtm);
        JScrollPane tablePane = new JScrollPane(tblCustomer);
        tablePane.setBounds(15, 50, 500, 250);
        add(tablePane);

       
        loadCustomerData();
    }

   
    private void loadCustomerData() {
        dtm.setRowCount(0); 

        // Sort customer array by the amount
        Arrays.sort(customerCollection.getCustomerObjects(), new Comparator<Customer>() {
            public int compare(Customer c1, Customer c2) {
                // Compare customers by amount in descending order
                return Double.compare(c2.getAmount(), c1.getAmount());
            }
        });

        // Iterate over the sorted customer array and add rows to the table
        for (Customer customer : customerCollection.getCustomerObjects()) {
            dtm.addRow(new Object[]{
                customer.getOrderId(),
                customer.getPhoneNumber(),
                customer.getSize(),
                customer.getQty(),
                customer.getAmount(),
                customer.getStatus()
            });
        }
    }
}
