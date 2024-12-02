import java.util.*;

public class CustomerCollection {
    private Customer[] customerArray;
    private static int z = 0;
    private static int uniqueCount = 0;
    private Customer[] uniqueCustomer;
    private Sort[] sortAmount;  

    public static final double XS = 600;
    public static final double S = 800;
    public static final double M = 900;
    public static final double L = 1000;
    public static final double XL = 1100;
    public static final double XXL = 1200;

    public CustomerCollection() {
        customerArray = new Customer[0];  
        uniqueCustomer = new Customer[0]; 
    }

    // Generate Order ID
    public String generateOrderId() {
        return String.format("ORD#%05d", z + 1);
    }

    // Phone Number Validate
    public boolean phoneNumberValidate(String phoneNumber) {
        return phoneNumber.length() == 10 && phoneNumber.charAt(0) == '0';
    }

    // Size Validate
    public boolean tSize(String size) {
        return size.equalsIgnoreCase("XS") || size.equalsIgnoreCase("S") || size.equalsIgnoreCase("M") ||
               size.equalsIgnoreCase("L") || size.equalsIgnoreCase("XL") || size.equalsIgnoreCase("XXL");
    }

    // Quantity Validate
    public boolean quantity(int qty) {
        return qty > 0;
    }

    // Calculate Amount based on size and quantity
    public static double amount(String size, int qty) {
        double amount = 0;
        switch (size.toUpperCase()) {
            case "XS": amount = XS * qty; break;
            case "S": amount = S * qty; break;
            case "M": amount = M * qty; break;
            case "L": amount = L * qty; break;
            case "XL": amount = XL * qty; break;
            case "XXL": amount = XXL * qty; break;
        }
        return amount;
    }

    // Add Customer to the collection
    public boolean addCustomer(Customer customer) {
        extendArrays();
        customerArray[customerArray.length - 1] = customer; 
        z++;
        return true;
    }

    // Extend Customer Arrays 
    public void extendArrays() {
        Customer[] tempCustomerArray = new Customer[customerArray.length + 1];
        System.arraycopy(customerArray, 0, tempCustomerArray, 0, customerArray.length);
        customerArray = tempCustomerArray;  
    }

    // SeaRCH
    public Customer searchCustomer(String phoneNo) {
        for (int i = 0; i < customerArray.length; i++) {
            if (customerArray[i].getPhoneNumber().equals(phoneNo)) {
                return customerArray[i]; 
            }
        }
        return null;  
    }

    public int searchOrderId(String orderId) {
        for (int i = 0; i < customerArray.length; i++) {
            if (customerArray[i].getOrderId().equals(orderId)) {
                return i;
            }
        }
        return -1;  
    }

    // Delete Customer by Order ID
    public boolean deleteCustomer(String orderId) {
        int index = searchOrderId(orderId);
        if (index >= 0) {
         
            for (int i = index; i < customerArray.length - 1; i++) {
                customerArray[i] = customerArray[i + 1];
            }
            customerArray = Arrays.copyOf(customerArray, customerArray.length - 1);
            z--; 
            return true;
        }
        return false;
    }

    // View Customer 
    public void viewCustomerReport() {
        uniqueCustomer = new Customer[customerArray.length];
        uniqueCount = 0;
        for (int i = 0; i < customerArray.length; i++) {
            boolean exists = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueCustomer[j].getPhoneNumber().equals(customerArray[i].getPhoneNumber())) {
                    int totalQty = uniqueCustomer[j].getQty() + customerArray[i].getQty();
                    double totalAmount = uniqueCustomer[j].getAmount() + customerArray[i].getAmount();
                    uniqueCustomer[j].setViewCustomerValues(customerArray[i].getPhoneNumber(), totalQty, totalAmount);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                uniqueCustomer[uniqueCount++] = customerArray[i];
            }
        }
        uniqueCustomer = Arrays.copyOf(uniqueCustomer, uniqueCount); 
    }

    // Sort Customers by Amount 
    public void sortByAmount() {
        sortAmount = new Sort[6];  
        int[] sizeQty = new int[6];
        double[] sizeTotal = new double[6];

       
        for (int i = 0; i < customerArray.length; i++) {
            switch (customerArray[i].getSize().toUpperCase()) {
                case "XS": sizeQty[0] += customerArray[i].getQty(); sizeTotal[0] += customerArray[i].getAmount(); break;
                case "S": sizeQty[1] += customerArray[i].getQty(); sizeTotal[1] += customerArray[i].getAmount(); break;
                case "M": sizeQty[2] += customerArray[i].getQty(); sizeTotal[2] += customerArray[i].getAmount(); break;
                case "L": sizeQty[3] += customerArray[i].getQty(); sizeTotal[3] += customerArray[i].getAmount(); break;
                case "XL": sizeQty[4] += customerArray[i].getQty(); sizeTotal[4] += customerArray[i].getAmount(); break;
                case "XXL": sizeQty[5] += customerArray[i].getQty(); sizeTotal[5] += customerArray[i].getAmount(); break;
            }
        }

        // Populate the sort array with the data
        sortAmount[0] = new Sort("XS", sizeQty[0], sizeTotal[0]);
        sortAmount[1] = new Sort("S", sizeQty[1], sizeTotal[1]);
        sortAmount[2] = new Sort("M", sizeQty[2], sizeTotal[2]);
        sortAmount[3] = new Sort("L", sizeQty[3], sizeTotal[3]);
        sortAmount[4] = new Sort("XL", sizeQty[4], sizeTotal[4]);
        sortAmount[5] = new Sort("XXL", sizeQty[5], sizeTotal[5]);

        // Sort the array 
        Arrays.sort(sortAmount, (a, b) -> Double.compare(b.getAmount(), a.getAmount()));
    }

    public Customer searchCustomerByOrderId(String orderId) {
        for (Customer customer : customerArray) {
            if (customer.getOrderId().equals(orderId)) {
                return customer;
            }
        }
        return null; 
    }

    // Get all Customer Objects
    public Customer[] getCustomerObjects() {
        return customerArray;
    }

    // Get Unique Customer Objects
    public Customer[] getUniqueCustomer() {
        return uniqueCustomer;
    }

    // Corrected Sort class
    static class Sort {
        private int qty;
        private String size;
        private double amount;

        public Sort(String size, int qty, double amount) {
            this.size = size;
            this.qty = qty;
            this.amount = amount;
        }

        public String getSize() {
            return size;
        }

        public int getQty() {
            return qty;
        }

        public double getAmount() {
            return amount;
        }
    }
}
