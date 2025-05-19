package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import control.DatabaseController;
import utils.AccessDatabaseConnection;

public class EditStorageFrame extends JFrame {
    private JTextField nameField;
    private JTextField serialField;
    private JTextField idField;
    private ManageInventory parent;

    public EditStorageFrame(ManageInventory parent, String name, String serial, String id) {
        this.parent = parent;

        setTitle("Edit Storage");
        setSize(348, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null); // ✅ Absolute layout (no layout manager)

        JLabel nameLabel = new JLabel("Storage Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        nameLabel.setBounds(30, 30, 120, 25);
        getContentPane().add(nameLabel);

        nameField = new JTextField(name);
        nameField.setBounds(160, 30, 145, 25);
        getContentPane().add(nameField);

        JLabel serialLabel = new JLabel("Storage Serial:");
        serialLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        serialLabel.setBounds(30, 70, 120, 25);
        getContentPane().add(serialLabel);

        serialField = new JTextField(serial);
        serialField.setBounds(160, 70, 145, 25);
        serialField.setEditable(false);
        getContentPane().add(serialField);

        JLabel idLabel = new JLabel("Storage ID:");
        idLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        idLabel.setBounds(40, 109, 120, 25);
        getContentPane().add(idLabel);

        idField = new JTextField(id);
        idField.setBounds(160, 110, 145, 25);
        getContentPane().add(idField);

        JButton saveButton = new CustomButton("Save", 173, 120, 40);
        saveButton.setBounds(106, 173, 120, 40); // ✅ Better positioned button
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        getContentPane().add(saveButton);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveChanges() {
        String newName = nameField.getText();
        String newIdStr = idField.getText();
        String serial = serialField.getText();

        int newId;

        // Ensure ID is a valid number
        try {
            newId = Integer.parseInt(newIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Storage ID. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("Error: Storage ID is not a valid number: " + newIdStr);
            return;
        }

        // Database connection and update query
        String sql = "UPDATE Storages_tbl SET Name = ?, ID = ? WHERE [Storage Serial] = ?";

        try (Connection conn = AccessDatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, newId);
            stmt.setString(3, serial);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Storage updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Storage updated: Serial=" + serial + ", Name=" + newName + ", ID=" + newId);
                parent.refreshInventory(); // Refresh parent frame
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No changes made. Storage not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                System.err.println("Warning: No storage updated. Check if Storage Serial exists: " + serial);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error. Failed to update storage.", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException: Failed to update storage. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
