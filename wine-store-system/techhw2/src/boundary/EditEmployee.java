package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import control.DatabaseController;
import entity.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class EditEmployee extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField, nameField, phoneField, addressField, emailField, dateField;
    private JComboBox<String> departmentBox;
    private ManageEmployees ef; // reference to manage employee page


    public EditEmployee(ManageEmployees main, Employee emp) {
    	
		setBackground(SystemColor.activeCaption);
    	ef = main; // set reference
    	getContentPane().setBackground(SystemColor.activeCaption);

        setTitle("Edit Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel lblId = new JLabel("ID :");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(20, 20, 100, 25);
        getContentPane().add(lblId);
        
        idField = new JTextField();
        idField.setFont(new Font("Dialog", Font.PLAIN, 16));
        idField.setBounds(180, 20, 190, 25);
        idField.setText(String.valueOf(emp.getId()));
        idField.setEditable(false);
        getContentPane().add(idField);
        
        JLabel lblName = new JLabel("Name :");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setBounds(20, 60, 100, 25);
        getContentPane().add(lblName);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
        nameField.setBounds(180, 60, 190, 25);
        nameField.setText(emp.getName());
        getContentPane().add(nameField);
        
        JLabel lblPhone = new JLabel("Phone Number :");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPhone.setBounds(20, 100, 152, 25);
        getContentPane().add(lblPhone);
        
        phoneField = new JTextField();
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 16));
        phoneField.setBounds(180, 100, 190, 25);
        phoneField.setText(emp.getPhoneNumber());
        getContentPane().add(phoneField);
        
        JLabel lblAddress = new JLabel("Office Address :");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAddress.setBounds(20, 140, 152, 25);
        getContentPane().add(lblAddress);
        
        addressField = new JTextField();
        addressField.setFont(new Font("Dialog", Font.PLAIN, 16));
        addressField.setBounds(180, 140, 190, 25);
        addressField.setText(emp.getOfficeAddress());
        getContentPane().add(addressField);
        
        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmail.setBounds(20, 180, 120, 25);
        getContentPane().add(lblEmail);
        
        emailField = new JTextField();
        emailField.setFont(new Font("Dialog", Font.PLAIN, 16));
        emailField.setBounds(180, 180, 190, 25);
        emailField.setText(emp.getEmail());
        getContentPane().add(emailField);
        
        JLabel lblDate = new JLabel("Employment Date :");
        lblDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDate.setBounds(20, 220, 152, 25);
        getContentPane().add(lblDate);
        
        dateField = new JTextField();
        dateField.setFont(new Font("Dialog", Font.PLAIN, 16));
        dateField.setBounds(180, 220, 190, 25);
        
        java.sql.Date sqlDate = (Date) emp.getEmployementStartDate();

        // Convert to java.util.Date
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

        // Format to MM/dd/yy
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        String formattedDate = formatter.format(utilDate);

        dateField.setText(String.valueOf(formattedDate));
        getContentPane().add(dateField);
        
        JLabel lblDepartment = new JLabel("Department :");
        lblDepartment.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDepartment.setBounds(20, 260, 100, 25);
        getContentPane().add(lblDepartment);
        
        departmentBox = new JComboBox<>(new String[]{"Sales", "Marketing"});
        departmentBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        departmentBox.setBounds(180, 260, 190, 25);
        if(emp.getDepartment().equalsIgnoreCase("Sales"))
        	departmentBox.setSelectedIndex(0);
        else
        	departmentBox.setSelectedIndex(1);
        getContentPane().add(departmentBox);
        
        JButton addButton = new CustomButton("Save",150,50,40);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBounds(89, 310, 200, 30);
        getContentPane().add(addButton);
        
        addButton.addActionListener(e -> saveEmployee());
        
        setVisible(true);
    }
    
    private void saveEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String email = emailField.getText().trim();
            String empDate = dateField.getText().trim();
            String department = departmentBox.getSelectedItem().toString();
            
            java.sql.Date sqlDate;
            
            if (empDate == null || empDate.trim().isEmpty() || empDate.length() < 8) {  
                // System.out.println("Ignoring invalid date: " + empDate);
            	// set default date
                //LocalDate today = LocalDate.now();
                //sqlDate = java.sql.Date.valueOf(today);  // Converts LocalDate to SQL Date
                JOptionPane.showMessageDialog(this, "Error reading employement date./n "
                		+ "Please use the following format MM/DD/YY", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
	            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");  // Matches input format
	            java.util.Date utilDate = formatter.parse(empDate);
	            sqlDate = new java.sql.Date(utilDate.getTime());  // Convert correctly
            }
            
            Employee emp = new Employee(id,name,phone,address,email,sqlDate,department);
            
            boolean success = DatabaseController.updateEmployee(emp);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee data saved successfully!");
                ef.loadDataFromDatabase(); // Refresh employee table after adding
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving employee data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	        ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Undefined Error. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);

	    }
    }
}
