import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

class AllCustomerReport extends JFrame {

    private JButton btnBack;
    private JTable tblCustomer;
    private DefaultTableModel dtm;
    private CustomerCollection customerCollection;

   
    AllCustomerReport(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

       
        setSize(600, 400);
        setTitle("All Customer Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(233, 89, 89));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(10, 10, 80, 30);
        add(btnBack);
        btnBack.addActionListener(evt -> {
            new ViewReportsHomePage(customerCollection).setVisible(true);
            dispose();
        });

        // Table setup
        String[] columnNames = {"Order ID", "Phone Number", "XS", "S", "M", "L", "XL", "XXL", "Amount", "Status"};
        dtm = new DefaultTableModel(columnNames, 0);
        tblCustomer = new JTable(dtm);
        JScrollPane tablePane = new JScrollPane(tblCustomer);
        tablePane.setBounds(15, 60, 560, 300);
        add(tablePane);

      
        loadCustomerData();
    }

    private void loadCustomerData() {
        dtm.setRowCount(0); 

        // Get the customer objects from the collection
        Customer[] customers = customerCollection.getCustomerObjects();

     
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) { // Check
                Customer c1 = customers[i];

               
                int xsQty = c1.getSize().equalsIgnoreCase("xs") ? c1.getQty() : 0;
                int sQty = c1.getSize().equalsIgnoreCase("s") ? c1.getQty() : 0;
                int mQty = c1.getSize().equalsIgnoreCase("m") ? c1.getQty() : 0;
                int lQty = c1.getSize().equalsIgnoreCase("l") ? c1.getQty() : 0;
                int xlQty = c1.getSize().equalsIgnoreCase("xl") ? c1.getQty() : 0;
                int xxlQty = c1.getSize().equalsIgnoreCase("xxl") ? c1.getQty() : 0;

             
                dtm.addRow(new Object[]{
                    c1.getOrderId(),
                    c1.getPhoneNumber(),
                    xsQty,
                    sQty,
                    mQty,
                    lQty,
                    xlQty,
                    xxlQty,
                    c1.getAmount(),
                    c1.getStatus()
                });
            }
        }
    }
}
