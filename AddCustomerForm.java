import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class AddCustomerForm extends JFrame {
    private JButton btnBack, btnPlace;
    private JLabel lblOrderId, lblPhoneNumber, lblSize, lblQty, lblAmount;
    private JTextField txtPhoneNumber, txtSize, txtQty, txtAmount, txtOrderId;
    private CustomerCollection customerCollection;

    AddCustomerForm(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;
        setSize(400, 360);
        setTitle("Add Customer");
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
            new HomePage(customerCollection).setVisible(true); 
            dispose();
        });

        // Place Button
        btnPlace = new JButton("Place");
        btnPlace.setFont(new Font("Arial", Font.BOLD, 16));
        btnPlace.setBackground(new Color(0, 128, 128));
        btnPlace.setForeground(Color.WHITE);
        btnPlace.setBounds(300, 280, 80, 30);
        add(btnPlace);
        btnPlace.addActionListener(evt -> placeOrder());

        // Order ID
        lblOrderId = new JLabel("Order ID:");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 14));
        lblOrderId.setBounds(35, 60, 200, 25);
        add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(130, 60, 100, 25);
        txtOrderId.setEditable(false); 
        add(txtOrderId);
        txtOrderId.setText(String.valueOf(customerCollection.generateOrderId()));

        // Phone Number
        lblPhoneNumber = new JLabel("Phone No:");
        lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 14));
        lblPhoneNumber.setBounds(35, 100, 100, 25);
        add(lblPhoneNumber);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(130, 100, 100, 25);
        add(txtPhoneNumber);

        // Size
        lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Arial", Font.BOLD, 14));
        lblSize.setBounds(35, 140, 100, 25);
        add(lblSize);

        txtSize = new JTextField();
        txtSize.setBounds(130, 140, 100, 25);
        add(txtSize);

        // Qty
        lblQty = new JLabel("Qty:");
        lblQty.setFont(new Font("Arial", Font.BOLD, 14));
        lblQty.setBounds(35, 180, 100, 25);
        add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(130, 180, 100, 25);
        add(txtQty);

        // Amount
        lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmount.setBounds(35, 220, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(130, 220, 100, 25);
        txtAmount.setEditable(false); 
        add(txtAmount);

      
        txtSize.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateAmount();
            }
        });

        txtQty.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                calculateAmount();
            }
        });
    }

   
    private void calculateAmount() {
        String size = txtSize.getText().toLowerCase();
        String qtyText = txtQty.getText();

        if (!qtyText.matches("\\d+")) {  
            txtAmount.setText("0.00");
            return;
        }

        int qty = Integer.parseInt(qtyText);
        int pricePerItem = 0;

      
        switch (size) {
            case "s": pricePerItem = 600; break;
            case "m": pricePerItem = 800; break;
            case "l": pricePerItem = 1000; break;
            case "xl": pricePerItem = 1200; break;
            case "xxl": pricePerItem = 1400; break;
            default: pricePerItem = 0; break;
        }

        double totalAmount = pricePerItem * qty;
        txtAmount.setText(String.valueOf(totalAmount));
    }

    
    private void placeOrder() {
        String phoneNumber = txtPhoneNumber.getText();
        String size = txtSize.getText();
        String qtyText = txtQty.getText();

       
        if (phoneNumber.isEmpty() || size.isEmpty() || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

      
        boolean isNumber = customerCollection.phoneNumberValidate(phoneNumber);
        boolean isSize = customerCollection.tSize(size);
        boolean isQty = customerCollection.quantity(Integer.parseInt(qtyText));

      
        if (!isNumber) {
            JOptionPane.showMessageDialog(null, "Invalid phone number.");
            txtPhoneNumber.setText("");
        } else if (!isSize) {
            JOptionPane.showMessageDialog(null, "Invalid Size.");
            txtSize.setText("");
        } else if (!isQty) {
            JOptionPane.showMessageDialog(null, "Invalid Qty.");
            txtQty.setText("");
        } else {
            double amount = customerCollection.amount(size, Integer.parseInt(qtyText));
         
          Customer c1 = new Customer(txtOrderId.getText(), phoneNumber, size, Integer.parseInt(qtyText), amount);
             c1.setStatus("Processing");
                boolean isAdded = customerCollection.addCustomer(c1);

            if (isAdded) {
                JOptionPane.showMessageDialog(null, "Customer added successfully.");
               
                txtOrderId.setText(customerCollection.generateOrderId());
                txtPhoneNumber.setText("");
                txtSize.setText("");
                txtQty.setText("");
                txtAmount.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add customer.");
            }
        }
    }
}
