package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

import control.DatabaseController;
import entity.Employee;
import entity.Order;
import utils.UtillsMethods;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class EditOrder extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private JTextField numberField, orderDateField, statusField, shipmentField, empIdField, dateField;
    private JComboBox<String> statusBox;
    private ManageOrders ef; // reference to manage employee page
    private JTextField customerIdField;
    private JCheckBox urgentChkBox;
    private JSpinner dateSpinner,dateSpinner2,dateSpinner3;
    private JTextField textField;
    private JLabel lblPriority_1,lblPriority;
    private String initialType;

    public EditOrder(ManageOrders main, Order o) {
    	
		setBackground(SystemColor.activeCaption);
    	ef = main; // set reference
    	getContentPane().setBackground(SystemColor.activeCaption);
    	setTitle("Edit Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(491, 424);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel lblId = new JLabel("Order Number :");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(20, 20, 142, 25);
        getContentPane().add(lblId);
        
        numberField = new JTextField();
        numberField.setFont(new Font("Dialog", Font.PLAIN, 12));
        numberField.setBounds(154, 20, 200, 25);
        numberField.setText(String.valueOf(o.getOrderNumber()));
        numberField.setEditable(false);
        getContentPane().add(numberField);
        
        JLabel lblName = new JLabel("Order Date :");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setBounds(20, 60, 100, 25);
        getContentPane().add(lblName);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(UtillsMethods.parseDate("10/03/2025"));
        Date initDate = calendar.getTime();
        calendar.set(1900, Calendar.JANUARY, 1);
        Date earliestDate = calendar.getTime();
        calendar.setTime(UtillsMethods.parseDate("10/03/3000"));
        Date latestDate = calendar.getTime();
        
        SpinnerDateModel dateModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setFont(new Font("Dialog", Font.PLAIN, 16));
        dateSpinner.setBounds(151, 60, 200, 25);
        JSpinner.DateEditor de_dateSpinner = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(de_dateSpinner);
        dateSpinner.setValue(o.getOrderDate());
        getContentPane().add(dateSpinner);
        
        JLabel lblPhone = new JLabel("Status :");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPhone.setBounds(20, 100, 100, 25);
        getContentPane().add(lblPhone);
        
        statusField = new JTextField();
        statusField.setBounds(20, 287, 100, 25);
        statusField.setText(o.getCurrentStatus());
        getContentPane().add(statusField);
        statusField.setVisible(false);
        
        JLabel lblAddress = new JLabel("Shipment Date :");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAddress.setBounds(20, 140, 142, 25);
        getContentPane().add(lblAddress);
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(UtillsMethods.parseDate("10/03/2025"));
        Date initDate2 = calendar2.getTime();
        calendar2.set(1900, Calendar.JANUARY, 1);
        Date earliestDate2 = calendar2.getTime();
        calendar2.setTime(UtillsMethods.parseDate("10/03/3000"));
        Date latestDate2 = calendar2.getTime();
        
        SpinnerDateModel dateModel2 = new SpinnerDateModel(initDate2, earliestDate2, latestDate2, Calendar.DAY_OF_MONTH);
        dateSpinner2 = new JSpinner(dateModel2);
        dateSpinner2.setFont(new Font("Dialog", Font.PLAIN, 16));
        dateSpinner2.setBounds(151, 140, 200, 25);
        JSpinner.DateEditor de_dateSpinner2 = new JSpinner.DateEditor(dateSpinner2, "dd/MM/yyyy");
        dateSpinner2.setEditor(de_dateSpinner2);
        dateSpinner2.setValue(o.getShipmentDate());
        getContentPane().add(dateSpinner2);
        
        JLabel lblEmail = new JLabel("Employee ID :");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmail.setBounds(20, 180, 124, 25);
        getContentPane().add(lblEmail);
        
        empIdField = new JTextField();
        empIdField.setFont(new Font("Dialog", Font.PLAIN, 12));
        empIdField.setBounds(154, 180, 200, 25);
        empIdField.setText(String.valueOf(o.getEmployeeId()));
        PlainDocument doc = (PlainDocument)empIdField.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        getContentPane().add(empIdField);
        
        statusBox = new JComboBox<>(new String[]{"In process", "Dispatched", "Delivered", "Paid", "Suspended", "Canceled"});
        statusBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        statusBox.setBounds(154, 100, 100, 25);
	    switch(o.getCurrentStatus()) {

        case "In process":
            statusBox.setSelectedIndex(0);
            break;
        case "Dispatched":
            statusBox.setSelectedIndex(1);
            break;
        case "Delivered":
            statusBox.setSelectedIndex(2);
            break;
        case "Paid":
            statusBox.setSelectedIndex(3);
            break;
        case "Suspended":
            statusBox.setSelectedIndex(4);
            break;
        case "Canceled":
            statusBox.setSelectedIndex(5);
            break;
	    }
        getContentPane().add(statusBox);
        
        JButton addButton = new CustomButton("Save",150,50,40);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBounds(96, 335, 200, 30);
        getContentPane().add(addButton);
        
        urgentChkBox = new JCheckBox("Urgent");
        urgentChkBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        urgentChkBox.setBounds(255, 101, 99, 23);
        if(o.getOrderType().equalsIgnoreCase("Urgent"))
        	urgentChkBox.setSelected(true);
        else
        	urgentChkBox.setSelected(false);
        
        getContentPane().add(urgentChkBox);
        
        JLabel lblCustomerid = new JLabel("Customer ID :");
        lblCustomerid.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCustomerid.setBounds(20, 220, 124, 25);
        getContentPane().add(lblCustomerid);
        
        customerIdField = new JTextField();
        customerIdField.setFont(new Font("Dialog", Font.PLAIN, 12));
        customerIdField.setText("0");
        customerIdField.setBounds(154, 220, 200, 25);
        customerIdField.setText(String.valueOf(o.getCustomerId()));
        PlainDocument doc2 = (PlainDocument)customerIdField.getDocument();
        doc2.setDocumentFilter(new PositiveNumbersFilter());
        getContentPane().add(customerIdField);
        


            lblPriority  = new JLabel("Priority Level :");
            lblPriority.setFont(new Font("Dialog", Font.BOLD, 16));
            lblPriority.setBounds(20, 260, 124, 25);
            getContentPane().add(lblPriority);
            
            textField = new JTextField();
            textField.setFont(new Font("Dialog", Font.PLAIN, 12));
            textField.setText("0");
            textField.setBounds(154, 260, 200, 25);
	        if(o.getOrderType().equals("Urgent"))
	        	textField.setText(String.valueOf(o.getPriorityLevel()));
            PlainDocument doc3 = (PlainDocument)textField.getDocument();
            doc3.setDocumentFilter(new PositiveNumbersFilter());
            getContentPane().add(textField);
	        
	        lblPriority_1 = new JLabel("Delivery Date :");
	        lblPriority_1.setFont(new Font("Dialog", Font.BOLD, 16));
	        lblPriority_1.setBounds(20, 300, 124, 25);
	        getContentPane().add(lblPriority_1);
	        
	        Calendar calendar3 = Calendar.getInstance();
	        calendar3.setTime(UtillsMethods.parseDate("10/03/2025"));
	        Date initDate3 = calendar3.getTime();
	        calendar3.set(1900, Calendar.JANUARY, 1);
	        Date earliestDate3 = calendar3.getTime();
	        calendar3.setTime(UtillsMethods.parseDate("10/03/3000"));
	        Date latestDate3 = calendar3.getTime();
	        
	        SpinnerDateModel dateModel3 = new SpinnerDateModel(initDate3, earliestDate3, latestDate3, Calendar.DAY_OF_MONTH);
	        dateSpinner3 = new JSpinner(dateModel3);
	        dateSpinner3.setFont(new Font("Dialog", Font.PLAIN, 16));
	        dateSpinner3.setBounds(154, 300, 200, 25);
	        JSpinner.DateEditor de_dateSpinner3 = new JSpinner.DateEditor(dateSpinner3, "dd/MM/yyyy");
	        dateSpinner3.setEditor(de_dateSpinner3);
	        if(o.getOrderType().equals("Urgent"))
	        	dateSpinner3.setValue(o.getSpecificDeliveryDate());
	        getContentPane().add(dateSpinner3);
        
    		lblPriority.setVisible(false);
    		textField.setVisible(false);
    		lblPriority_1.setVisible(false);
    		dateSpinner3.setVisible(false);
    		
    		urgentOrderFields();
        urgentChkBox.addActionListener(e -> urgentOrderFields());
        addButton.addActionListener(e -> saveOrder());
        initialType = o.getOrderType();
        
        setVisible(true);
    }
    

    private void saveOrder() {
        try {
            int orderNum = Integer.parseInt(numberField.getText());
            String status = statusBox.getSelectedItem().toString();
            int empId = Integer.parseInt(empIdField.getText());
            String orderType = "Regular";
            int custId = Integer.parseInt(customerIdField.getText());


            if(urgentChkBox.isSelected())
            	orderType = "Urgent";
            
    		Date parsedDate = (Date)dateSpinner.getValue();
    		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

    		Date parsedDate2 = (Date)dateSpinner2.getValue();
    		java.sql.Date sqlDate2 = new java.sql.Date(parsedDate2.getTime());
    		

            
            if(DatabaseController.getAllEmployees().containsKey(empId)==false) {
                JOptionPane.showMessageDialog(this, "Employee ID does not exist. please enter a valid ID", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(DatabaseController.getAllCustomers().containsKey(custId)==false) {
                JOptionPane.showMessageDialog(this, "Customer ID does not exist. please enter a valid ID", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(initialType.equals(orderType) == false) 
            	if(initialType.equals("Urgent"))
            		DatabaseController.deleteUrgentOrder(orderNum);
            

            
            Order o = new Order(orderNum, orderType, sqlDate,status, sqlDate2,empId,custId);
            boolean success = false;
            

            if(orderType == "Urgent") {
            	int priority = Integer.parseInt(textField.getText());
	    		Date parsedDate3 = (Date)dateSpinner3.getValue();
	    		java.sql.Date sqlDate3 = new java.sql.Date(parsedDate3.getTime());
	    		o = new Order(orderNum, orderType, sqlDate,status, sqlDate2,empId,custId, priority, sqlDate3);
	            
	    		if(initialType.equals(orderType) == false) {
	    			if(initialType.equals("Regular")) {
	    				DatabaseController.addUrgentOrder(o, orderNum);
	    			}
	    		}else {
	    			success = DatabaseController.updateUrgentOrder(o);	
	    		}
            }
            success = DatabaseController.updateOrder(o);	
            

            if (success) {
                JOptionPane.showMessageDialog(this, "Order data saved successfully!");
                ef.loadDataFromDatabase(); // Refresh order table after adding
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving order data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	        ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Undefined Error. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);

	    }
    }
    
    private void urgentOrderFields() {
    	if(urgentChkBox.isSelected()) {
    		lblPriority.setVisible(true);
    		textField.setVisible(true);
    		lblPriority_1.setVisible(true);
    		dateSpinner3.setVisible(true);
    	}else {
    		lblPriority.setVisible(false);
    		textField.setVisible(false);
    		lblPriority_1.setVisible(false);
    		dateSpinner3.setVisible(false);
    	}
    }
}
