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
import java.util.Calendar;

public class AddEmployee extends JFrame {
    private JTextField idField, nameField, phoneField, addressField, emailField;
    private JSpinner dateField;
    private JComboBox<String> departmentBox;
    private ManageEmployees ef; // reference to manage employee page

    public AddEmployee(ManageEmployees main) {
        
        setBackground(SystemColor.activeCaption);
        ef = main; // set reference
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
    	getContentPane().setBackground(SystemColor.activeCaption);

        JLabel lblId = new JLabel("ID :");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(20, 20, 149, 25);
        getContentPane().add(lblId);
        
        idField = new JTextField();
        idField.setFont(new Font("Dialog", Font.PLAIN, 16));
        idField.setBounds(174, 20, 166, 25);
        getContentPane().add(idField);
        
        JLabel lblName = new JLabel("Name :");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setBounds(20, 60, 149, 25);
        getContentPane().add(lblName);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
        nameField.setBounds(174, 60, 166, 25);
        getContentPane().add(nameField);
        
        JLabel lblPhone = new JLabel("Phone Number :");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPhone.setBounds(20, 100, 149, 25);
        getContentPane().add(lblPhone);
        
        phoneField = new JTextField();
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 16));
        phoneField.setBounds(174, 100, 166, 25);
        getContentPane().add(phoneField);
        
        JLabel lblAddress = new JLabel("Office Address :");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAddress.setBounds(20, 140, 149, 25);
        getContentPane().add(lblAddress);
        
        addressField = new JTextField();
        addressField.setFont(new Font("Dialog", Font.PLAIN, 16));
        addressField.setBounds(174, 140, 166, 25);
        getContentPane().add(addressField);
        
        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmail.setBounds(20, 180, 149, 25);
        getContentPane().add(lblEmail);
        
        emailField = new JTextField();
        emailField.setFont(new Font("Dialog", Font.PLAIN, 16));
        emailField.setBounds(174, 180, 166, 25);
        getContentPane().add(emailField);
        
        JLabel lblDate = new JLabel("Employment Date :");
        lblDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDate.setBounds(20, 220, 149, 25);
        getContentPane().add(lblDate);
        
        dateField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateField, "MM/dd/yyyy");
        dateField.setEditor(dateEditor);
        dateField.setBounds(174, 220, 166, 25);
        getContentPane().add(dateField);
        
        JLabel lblDepartment = new JLabel("Department :");
        lblDepartment.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDepartment.setBounds(20, 260, 149, 25);
        getContentPane().add(lblDepartment);
        
        departmentBox = new JComboBox<>(new String[]{"Sales", "Marketing"});
        departmentBox.setFont(new Font("Dialog", Font.PLAIN, 16));
        departmentBox.setBounds(174, 260, 166, 25);
        getContentPane().add(departmentBox);
        
        JButton addButton = new CustomButton("Add Employee",150,50,40);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBounds(83, 310, 200, 30);
        getContentPane().add(addButton);
        
        addButton.addActionListener(e -> addEmployee());
        
        setVisible(true);
    }
    
    private void addEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String email = emailField.getText().trim();
            Date sqlDate = new Date(((java.util.Date) dateField.getValue()).getTime());
            String department = departmentBox.getSelectedItem().toString();
            
            Employee emp = new Employee(id, name, phone, address, email, sqlDate, department);
            boolean success = DatabaseController.addEmployee(emp);

            if (success) {
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
                ef.loadDataFromDatabase(); // Refresh employee table after adding
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Undefined Error. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
