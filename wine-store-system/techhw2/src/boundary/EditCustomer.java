package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import utils.AccessDatabaseConnection;
import utils.UtillsMethods;

public class EditCustomer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField IDField, nameField, phoneField, emailField, addressField, dateField;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Date format
    private ManageCustomers parentPanel; // Reference to parent panel
    private int customerId; // Store customer ID
    private JSpinner dateSpinner;

    public EditCustomer(ManageCustomers parent, int id, String name, String phone, String email, String address, Date date) {
        this.parentPanel = parent;
        this.customerId = id;

        setBackground(SystemColor.activeCaption);
        setTitle("Edit Customer");
        setSize(391, 367);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Labels & Text Fields
        JLabel lblId = new JLabel("ID :");
        lblId.setHorizontalAlignment(SwingConstants.LEFT);
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(22, 34, 119, 25);
        getContentPane().add(lblId);

        IDField = new JTextField(String.valueOf(id)); // Pre-fill ID
        IDField.setFont(new Font("Dialog", Font.PLAIN, 16));
        IDField.setBounds(140, 34, 184, 25);
        IDField.setEditable(false); // Prevent editing ID
        getContentPane().add(IDField);

        JLabel lblName = new JLabel("Name :");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setBounds(22, 70, 119, 25);
        getContentPane().add(lblName);

        nameField = new JTextField(name); // Pre-fill Name
        nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
        nameField.setBounds(140, 70, 184, 25);
        getContentPane().add(nameField);

        JLabel lblPhone = new JLabel("Phone :");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhone.setBounds(22, 110, 119, 25);
        getContentPane().add(lblPhone);

        phoneField = new JTextField(phone); // Pre-fill Phone
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 16));
        phoneField.setBounds(140, 110, 184, 25);
        getContentPane().add(phoneField);

        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setBounds(22, 150, 119, 25);
        getContentPane().add(lblEmail);

        emailField = new JTextField(email); // Pre-fill Email
        emailField.setFont(new Font("Dialog", Font.PLAIN, 16));
        emailField.setBounds(140, 150, 184, 25);
        getContentPane().add(emailField);

        JLabel lblAddress = new JLabel("Address :");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblAddress.setBounds(22, 190, 119, 25);
        getContentPane().add(lblAddress);

        addressField = new JTextField(address); // Pre-fill Address
        addressField.setFont(new Font("Dialog", Font.PLAIN, 16));
        addressField.setBounds(140, 190, 184, 25);
        getContentPane().add(addressField);

        JLabel lblDate = new JLabel("First Contact :");
        lblDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDate.setHorizontalAlignment(SwingConstants.LEFT);
        lblDate.setBounds(22, 226, 119, 25);
        getContentPane().add(lblDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(UtillsMethods.parseDate("10/03/2025"));
        Date initDate = calendar.getTime();
        calendar.set(1900, Calendar.JANUARY, 1);
        Date earliestDate = calendar.getTime();
        calendar.setTime(UtillsMethods.parseDate("10/03/2025"));
        Date latestDate = calendar.getTime();
        
        SpinnerDateModel dateModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setFont(new Font("Dialog", Font.PLAIN, 16));
        dateSpinner.setBounds(140, 226, 184, 25);
        JSpinner.DateEditor de_dateSpinner = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(de_dateSpinner);
        dateSpinner.setValue(date);
        getContentPane().add(dateSpinner);

        // Save Button
        JButton btnSave = new CustomButton("Save", 226, 150, 35);
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBounds(170, 262, 118, 41);
        getContentPane().add(btnSave);

        // Action Listener for Save Button
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerInDatabase();
            }
        });

        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    // Update customer in the database
    private void updateCustomerInDatabase() {
        String name = nameField.getText();
		String phone = phoneField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		Date parsedDate = (Date)dateSpinner.getValue();
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

		if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.WARNING_MESSAGE);
		    return;
		}

		String query = "UPDATE Customer_tbl SET Name=?, PhoneNumber=?, Email=?, DeliveryAddress=?, DateOfFirstContact=? WHERE ID=?";
		try (Connection conn = AccessDatabaseConnection.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(query)) {

		    stmt.setString(1, name);
		    stmt.setString(2, phone);
		    stmt.setString(3, email);
		    stmt.setString(4, address);
		    stmt.setDate(5, sqlDate);
		    stmt.setInt(6, customerId);

		    stmt.executeUpdate();
		    JOptionPane.showMessageDialog(this, "Customer updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		    
		    // Refresh the parent panel's table
		    parentPanel.loadCustomersFromDatabase();

		    // Close the EditCustomer window
		    dispose();

		} catch (SQLException e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(this, "Error updating customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
    }
}
