package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import utils.AccessDatabaseConnection;
import java.io.*;
import java.sql.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryReport extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private boolean isFiltered = false; // Track filter state
    private static final String FILE_PATH = "src/resources/inventory_report.json"; // File path

    public InventoryReport() {

        // Table setup
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        
        // Add columns to the table
        tableModel.addColumn("Wine Serial");
        tableModel.addColumn("Wine Name");
        tableModel.addColumn("Year");
        tableModel.addColumn("Price");
        tableModel.addColumn("Sweetness");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Storage");
        setLayout(null);

        // Scroll pane for table (smaller size)
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(38, 110, 621, 293);
        add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Inventory Report");
        lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 19));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(237, 35, 220, 52);
        add(lblNewLabel);
        
        JButton btnNewButton = new CustomButton("Generate Report", 414, 150, 73);
        btnNewButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
        btnNewButton.setBounds(272, 414, 141, 73);
        add(btnNewButton);
        
        JButton cstmbtnFilterWines = new CustomButton("Filter Wines", 449, 150, 38);
        cstmbtnFilterWines.setFont(new Font("Baskerville Old Face", Font.BOLD, 13));
        cstmbtnFilterWines.setBounds(144, 432, 103, 41);
        add(cstmbtnFilterWines);
        
        JLabel lblNewLabel_1 = new JLabel("Show low on stock wines only ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setEnabled(false);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(103, 468, 179, 19);
        add(lblNewLabel_1);

        // Load initial data
        loadDataFromDatabase(false);

        // Add action listener for filtering wines
        cstmbtnFilterWines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFiltered = !isFiltered; // Toggle filter state
                loadDataFromDatabase(isFiltered);
            }
        });

        // Action listener for saving JSON
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTableDataToJson();
            }
        });
    }

    private void loadDataFromDatabase(boolean filter) {
        tableModel.setRowCount(0); // Clear table before reloading

        String query = "SELECT w.WineBottleSerial, w.WineName, w.ProductionYear, w.PricePerBottle, w.SweetnessLevel, " +
                       "ws.Quantity, ws.[Storage Serial] " +
                       "FROM WineBottle_tbl w " +
                       "LEFT JOIN WineStorage_tbl ws ON w.WineBottleSerial = ws.[WineBottle Serial]";

        if (filter) {
            query += " WHERE ws.Quantity < 120"; // Apply filter
        }

        try (Connection conn = AccessDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("WineBottleSerial"),
                        rs.getString("WineName"),
                        rs.getInt("ProductionYear"),
                        rs.getDouble("PricePerBottle"),
                        rs.getString("SweetnessLevel"),
                        rs.getInt("Quantity"),
                        rs.getString("Storage Serial")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to manually write JSON
    private void saveTableDataToJson() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure directories exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("[\n"); // Start JSON array

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String jsonEntry = formatWineEntry(
                    tableModel.getValueAt(i, 0).toString(),
                    tableModel.getValueAt(i, 1).toString(),
                    tableModel.getValueAt(i, 2).toString(),
                    tableModel.getValueAt(i, 3).toString(),
                    tableModel.getValueAt(i, 4).toString(),
                    tableModel.getValueAt(i, 5).toString(),
                    tableModel.getValueAt(i, 6).toString()
                );

                writer.write(jsonEntry);

                // Add a comma except for the last entry
                if (i < tableModel.getRowCount() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("]"); // End JSON array
            writer.flush();

            JOptionPane.showMessageDialog(this, "Report saved successfully to:\n" + FILE_PATH, "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving report: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to format a single wine entry as JSON
    private String formatWineEntry(String serial, String name, String year, String price, String sweetness, String quantity, String storage) {
        return String.format(
            "  {\n" +
            "    \"Wine Serial\": \"%s\",\n" +
            "    \"Wine Name\": \"%s\",\n" +
            "    \"Year\": \"%s\",\n" +
            "    \"Price\": \"%s\",\n" +
            "    \"Sweetness\": \"%s\",\n" +
            "    \"Quantity\": \"%s\",\n" +
            "    \"Storage\": \"%s\"\n" +
            "  }",
            serial, name, year, price, sweetness, quantity, storage
        );
    }
}
