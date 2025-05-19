package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import control.DatabaseController;
import control.JasperReportRunner;
import entity.Employee;
import entity.WineBottle;
import utils.AccessDatabaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.Font;

public class UnproductiveReport extends JPanel {

    private static final long serialVersionUID = 1L;
    private JSpinner startDateField;
    private JSpinner endDateField;
    private JButton generateButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    /**
     * Create the panel.
     */
    public UnproductiveReport() {
        setLayout(null);

        // Start Date Label
        JLabel startDateLabel = new JLabel("Start Date (MM/DD/YYYY):");
        startDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startDateLabel.setBounds(162, 11, 175, 25);
        add(startDateLabel);

        startDateField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateField, "MM/dd/yyyy");
        startDateField.setEditor(startEditor);
        startDateField.setBounds(175, 47, 150, 25);
        add(startDateField);

        // End Date Label
        JLabel endDateLabel = new JLabel("End Date (MM/DD/YYYY):");
        endDateLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        endDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endDateLabel.setBounds(385, 11, 175, 25);
        add(endDateLabel);

        endDateField = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateField, "MM/dd/yyyy");
        endDateField.setEditor(endEditor);
        endDateField.setBounds(395, 47, 150, 25);
        add(endDateField);
        
        // Generate Report Button
        generateButton = new CustomButton("Generate Report", 83, 163, 30);
        generateButton.setText("Submit\r\n");
        generateButton.setBounds(304, 83, 119, 30);
        add(generateButton);

        // Table ScrollPane
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(61, 124, 600, 277);
        add(scrollPane);
        
        JButton btnNewButton = new CustomButton("Generate Report", 423, 150, 59);
        btnNewButton.addActionListener(e -> generateReport());

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(289, 412, 150, 59);
        add(btnNewButton);

        // Button Action
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReportToDatabase();
                loadReportFromDatabase();
            }
        });
    }

    /**
     * Method to delete old data and save the new report into the Access database.
     */
    private void saveReportToDatabase() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = (Date) startDateField.getValue();
        Date endDate = (Date) endDateField.getValue();

        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Please select both dates.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            Connection conn = AccessDatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            // Step 1: Clear previous data
            String deleteSQL = "DELETE FROM Temp_UnproductiveEmployees";
            stmt.executeUpdate(deleteSQL);

            // Step 2: Insert new data with strict filtering
            String insertSQL = "INSERT INTO Temp_UnproductiveEmployees (EmployeeID, Name, PhoneNumber, Email, OfficeAddress, EmploymentDate, RegularOrders, UrgentOrders) " +
                    "SELECT e.ID, e.Name, e.[PhoneNumber], e.Email, e.[OfficeAddress], e.[EmploymentDate], " +
                    "COALESCE(r.RegularOrders, 0) AS RegularOrders, COALESCE(u.UrgentOrders, 0) AS UrgentOrders " +
                    "FROM Employee_tbl e " +
                    "LEFT JOIN ( " +
                    "    SELECT EmployeeID, COUNT(*) AS RegularOrders " +
                    "    FROM Order_tbl " +
                    "    WHERE OrderType = 'Regular' AND OrderDate BETWEEN ? AND ? " +
                    "    GROUP BY EmployeeID " +
                    ") r ON e.ID = r.EmployeeID " +
                    "LEFT JOIN ( " +
                    "    SELECT EmployeeID, COUNT(*) AS UrgentOrders " +
                    "    FROM Order_tbl " +
                    "    WHERE OrderType = 'Urgent' AND OrderDate BETWEEN ? AND ? " +
                    "    GROUP BY EmployeeID " +
                    ") u ON e.ID = u.EmployeeID " +
                    "WHERE (COALESCE(r.RegularOrders, 0) < 4 OR COALESCE(u.UrgentOrders, 0) < 2) " + 
                    "AND NOT (COALESCE(u.UrgentOrders, 0) >= 2) " +
                    "AND NOT (COALESCE(r.RegularOrders, 0) >= 4) " +
                    "ORDER BY (COALESCE(r.RegularOrders, 0) + COALESCE(u.UrgentOrders, 0)) DESC";

            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setDate(1, sqlStartDate);
            pstmt.setDate(2, sqlEndDate);
            pstmt.setDate(3, sqlStartDate);
            pstmt.setDate(4, sqlEndDate);

            pstmt.executeUpdate();
            pstmt.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Method to load the saved report from the Access database into the JTable.
     */
    private void loadReportFromDatabase() {
        try {
            Connection conn = AccessDatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            String selectSQL = "SELECT EmployeeID, Name, PhoneNumber, Email, OfficeAddress, EmploymentDate, RegularOrders, UrgentOrders " +
                               "FROM Temp_UnproductiveEmployees " +
                               "ORDER BY (RegularOrders + UrgentOrders) DESC"; // Sorts when loading into JTable

            ResultSet rs = stmt.executeQuery(selectSQL);

            // Load data into JTable
            loadTableData(rs);

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadTableData(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        Vector<String> columns = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columns.add(metaData.getColumnName(i));
        }
        tableModel.setColumnIdentifiers(columns);

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            tableModel.addRow(row);
        }
    }
    
	private void generateReport() {

		JasperReportRunner.initiate("unProdReport.jasper");
		
	}
}
