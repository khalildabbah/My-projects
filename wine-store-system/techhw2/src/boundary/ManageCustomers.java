package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import utils.AccessDatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageCustomers extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private static final String TABLE_NAME = "Customer_tbl";

    /**
     * Create the panel.
     */
    public ManageCustomers() {
        setBackground(SystemColor.activeCaption);
        setLayout(null);

        // Table setup
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Prevent table editing
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setFocusable(false);

        // Ensure rows stay highlighted
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    cell.setBackground(Color.LIGHT_GRAY); // Highlight selected row
                } else {
                    cell.setBackground(Color.WHITE);
                }
                return cell;
            }
        });

        // Add columns
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("PhoneNumber");
        tableModel.addColumn("Email");
        tableModel.addColumn("DeliveryAddress");
        tableModel.addColumn("DateOfFirstContact");

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 74, 590, 300);
        add(scrollPane);

        // Add Customer Button
        JButton btnAdd = new CustomButton("Add Customer", 385, 150, 40);
        btnAdd.setBounds(482, 385, 150, 40);
        add(btnAdd);

        // Delete Customer Button
        JButton btnDelete = new CustomButton("Delete Customer", 385, 150, 40);
        btnDelete.setBounds(311, 385, 150, 40);
        add(btnDelete);

        JLabel lblNewLabel = new JLabel("Customers");
        lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(246, 11, 161, 52);
        add(lblNewLabel);

        // Load initial data
        loadCustomersFromDatabase();

        // Add Button Action Listener
        btnAdd.addActionListener(e -> new AddCustomer(ManageCustomers.this));

        // Delete Button Action Listener
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        // Add double-click listener to open EditCustomer
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) { // Left double-click detected
                    int row = table.rowAtPoint(evt.getPoint()); // Detect row at mouse click
                    if (row != -1) {
                        table.setRowSelectionInterval(row, row); // Keep row selected

                        // Fetch selected row data
                        int id = (int) tableModel.getValueAt(row, 0);
                        String name = tableModel.getValueAt(row, 1).toString();
                        String phone = tableModel.getValueAt(row, 2).toString();
                        String email = tableModel.getValueAt(row, 3).toString();
                        String address = tableModel.getValueAt(row, 4).toString();
                        Date date = (Date)tableModel.getValueAt(row, 5);

                        // Open EditCustomer JFrame
                        SwingUtilities.invokeLater(() -> new EditCustomer(ManageCustomers.this, id, name, phone, email, address, date));
                    }
                }
            }
        });
    }

    // Load customers from the database
    public void loadCustomersFromDatabase() {
        tableModel.setRowCount(0); // Clear table

        String query = "SELECT * FROM " + TABLE_NAME;

        try (Connection conn = AccessDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("DeliveryAddress"),
                        rs.getDate("DateOfFirstContact") // Fetch as java.sql.Date
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int customerId = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

            try (Connection conn = AccessDatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, customerId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Customer deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadCustomersFromDatabase(); // Refresh table

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
