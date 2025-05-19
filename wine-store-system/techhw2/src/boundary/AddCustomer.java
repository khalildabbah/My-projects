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

public class AddCustomer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField IDField, nameField, phoneField, emailField, addressField;
    private ManageCustomers parentPanel; // Reference to parent panel
    private JSpinner dateSpinner;

    public AddCustomer(ManageCustomers parent) {
        this.parentPanel = parent;
        setBackground(SystemColor.activeCaption);
        setTitle("Add Customer");
        setSize(391, 367);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Labels & Text Fields
        JLabel lblId = new JLabel("ID :");
        lblId.setHorizontalAlignment(SwingConstants.LEFT);
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(10, 34, 131, 25);
        getContentPane().add(lblId);

        IDField = new JTextField();
        IDField.setFont(new Font("Dialog", Font.PLAIN, 16));
        IDField.setBounds(140, 34, 184, 25);
        getContentPane().add(IDField);

        JLabel lblName = new JLabel("Name :");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setBounds(10, 70, 131, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
        nameField.setBounds(140, 70, 184, 25);
        getContentPane().add(nameField);

        JLabel lblPhone = new JLabel("Phone :");
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhone.setBounds(10, 110, 131, 25);
        getContentPane().add(lblPhone);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 16));
        phoneField.setBounds(140, 110, 184, 25);
        getContentPane().add(phoneField);

        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setBounds(10, 150, 131, 25);
        getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setFont(new Font("Dialog", Font.PLAIN, 16));
        emailField.setBounds(140, 150, 184, 25);
        getContentPane().add(emailField);

        JLabel lblAddress = new JLabel("Address :");
        lblAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblAddress.setBounds(10, 190, 131, 25);
        getContentPane().add(lblAddress);

        addressField = new JTextField();
        addressField.setFont(new Font("Dialog", Font.PLAIN, 16));
        addressField.setBounds(140, 190, 184, 25);
        getContentPane().add(addressField);

        JLabel lblDate = new JLabel("First Contact :");
        lblDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDate.setHorizontalAlignment(SwingConstants.LEFT);
        lblDate.setBounds(10, 226, 131, 25);
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
        getContentPane().add(dateSpinner);
        
        // Add Button
        JButton btnAdd = new CustomButton("Add Customer", 226, 150, 35);
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 262, 118, 41);
        getContentPane().add(btnAdd);

        // Action Listener for Add Button
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomerToDatabase();
            }
        });

        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void addCustomerToDatabase() {
        int id;
		try {
		    id = Integer.parseInt(IDField.getText()); // Ensure ID is a valid number
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(this, "ID must be a valid number!", "Input Error", JOptionPane.WARNING_MESSAGE);
		    return;
		}

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

		String query = "INSERT INTO Customer_tbl (ID, Name, PhoneNumber, Email, DeliveryAddress, DateOfFirstContact) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = AccessDatabaseConnection.getConnection();
		     PreparedStatement stmt = conn.prepareStatement(query)) {

		    stmt.setInt(1, id);
		    stmt.setString(2, name);
		    stmt.setString(3, phone);
		    stmt.setString(4, email);
		    stmt.setString(5, address);
		    stmt.setDate(6, sqlDate);

		    stmt.executeUpdate();
		    JOptionPane.showMessageDialog(this, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		    
		    // Refresh the parent panel's table
		    parentPanel.loadCustomersFromDatabase();

		    // Close the AddCustomer window
		    dispose();

            } catch (SQLException e) {
                String errorMessage = e.getMessage().toLowerCase();
                if (errorMessage.contains("unique constraint") || errorMessage.contains("integrity constraint violation")) {
                    JOptionPane.showMessageDialog(this, "Error: ID already used!", "Database Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }
}
