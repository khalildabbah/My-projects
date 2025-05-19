package boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import control.DatabaseController;

public class MovingWines extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> sourceStorageComboBox;
    private JComboBox<String> wineComboBox;
    private JComboBox<String> destinationStorageComboBox;
    private JTextField serialField;
    private JTextField inventoryField;
    private JTextField transferField;
    private JButton transferButton;
    private ManageInventory mainPanel;

    public MovingWines(ManageInventory main) {
        this.mainPanel = main;
        setBackground(SystemColor.activeCaption);
        setTitle("Move Wines Between Storages");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 400);
        setBackground(SystemColor.activeCaption);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("TRANSFER WINES BETWEEN STORAGES");
        lblTitle.setBounds(80, 10, 500, 30);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTitle);

        // Select source storage
        JLabel lblSourceStorage = new JLabel("From Storage:");
        lblSourceStorage.setBounds(50, 60, 150, 25);
        lblSourceStorage.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblSourceStorage);

        sourceStorageComboBox = new JComboBox<>();
        sourceStorageComboBox.setBounds(220, 60, 250, 25);
        getContentPane().add(sourceStorageComboBox);

        // Select wine from the selected storage
        JLabel lblWine = new JLabel("Select Wine:");
        lblWine.setBounds(50, 100, 150, 25);
        lblWine.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblWine);

        wineComboBox = new JComboBox<>();
        wineComboBox.setBounds(220, 100, 250, 25);
        getContentPane().add(wineComboBox);

        // Wine details (Serial, Name, Quantity)
        JLabel lblSerial = new JLabel("Wine Serial:");
        lblSerial.setBounds(50, 140, 150, 25);
        lblSerial.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblSerial);

        serialField = new JTextField();
        serialField.setBounds(220, 140, 250, 25);
        serialField.setEditable(false);
        getContentPane().add(serialField);

        JLabel lblInventory = new JLabel("Available Quantity:");
        lblInventory.setBounds(50, 186, 150, 25);
        lblInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblInventory);

        inventoryField = new JTextField();
        inventoryField.setBounds(220, 188, 100, 25);
        inventoryField.setEditable(false);
        getContentPane().add(inventoryField);

        // Select destination storage
        JLabel lblDestinationStorage = new JLabel("To Storage:");
        lblDestinationStorage.setBounds(50, 222, 150, 25);
        lblDestinationStorage.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblDestinationStorage);

        destinationStorageComboBox = new JComboBox<>();
        destinationStorageComboBox.setBounds(220, 224, 250, 25);
        getContentPane().add(destinationStorageComboBox);

        // Transfer Quantity
        JLabel lblTransfer = new JLabel("Transfer Quantity:");
        lblTransfer.setBounds(50, 258, 150, 25);
        lblTransfer.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(lblTransfer);

        transferField = new JTextField();
        transferField.setBounds(220, 260, 100, 25);
        getContentPane().add(transferField);

        transferButton = new CustomButton("Transfer", 260, 120, 25);
        transferButton.setBounds(344, 260, 120, 25);
        transferButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        getContentPane().add(transferButton);

        // Load storage list
        loadStorageOptions();

        // Event listeners
        sourceStorageComboBox.addActionListener(e -> loadWineOptions());
        wineComboBox.addActionListener(e -> updateWineDetails());
        transferButton.addActionListener(e -> performTransfer());

        setVisible(true);
    }

    /**
     * Loads all available storages into both source and destination combo boxes.
     */
    private void loadStorageOptions() {
        ArrayList<String> storages = DatabaseController.getAllStorageSerials();
        sourceStorageComboBox.addItem("Select Storage");
        destinationStorageComboBox.addItem("Select Storage");
        for (String storage : storages) {
            sourceStorageComboBox.addItem(storage);
            destinationStorageComboBox.addItem(storage);
        }
    }

    /**
     * Loads wines that belong to the selected source storage.
     */
    private void loadWineOptions() {
        wineComboBox.removeAllItems();
        String selectedStorage = (String) sourceStorageComboBox.getSelectedItem();

        if (selectedStorage != null && !selectedStorage.equals("Select Storage")) {
            ArrayList<HashMap<String, String>> wines = DatabaseController.getWinesForStorage(selectedStorage);
            wineComboBox.addItem("Select Wine");

            for (HashMap<String, String> wine : wines) {
                wineComboBox.addItem(wine.get("Wine Serial"));
            }
        }
    }

    /**
     * Updates wine details fields when a wine is selected.
     */
    private void updateWineDetails() {
        String selectedWine = (String) wineComboBox.getSelectedItem();
        String selectedStorage = (String) sourceStorageComboBox.getSelectedItem();

        if (selectedWine != null && selectedStorage != null &&
                !selectedWine.equals("Select Wine") && !selectedStorage.equals("Select Storage")) {

            HashMap<String, String> wineDetails = DatabaseController.getWineDetails(selectedWine, selectedStorage);
            serialField.setText(wineDetails.get("Wine Serial"));
           
            inventoryField.setText(wineDetails.get("Quantity"));
        } else {
            serialField.setText("");
          
            inventoryField.setText("");
        }
    }

    /**
     * Handles wine transfer and closes the window after success.
     */
    private void performTransfer() {
        String selectedWine = (String) wineComboBox.getSelectedItem();
        String sourceStorage = (String) sourceStorageComboBox.getSelectedItem();
        String destinationStorage = (String) destinationStorageComboBox.getSelectedItem();
        String transferAmountStr = transferField.getText();

        if (selectedWine == null || sourceStorage == null || destinationStorage == null ||
                selectedWine.equals("Select Wine") || sourceStorage.equals("Select Storage") || destinationStorage.equals("Select Storage")) {
            JOptionPane.showMessageDialog(this, "Please select source storage, wine, and destination storage.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sourceStorage.equals(destinationStorage)) {
            JOptionPane.showMessageDialog(this, "Cannot transfer to the same storage!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int transferAmount = Integer.parseInt(transferAmountStr);
            int currentInventory = Integer.parseInt(inventoryField.getText());

            if (transferAmount <= 0 || transferAmount > currentInventory) {
                JOptionPane.showMessageDialog(this, "Invalid transfer amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = DatabaseController.transferWine(selectedWine, sourceStorage, destinationStorage, transferAmount);
            if (success) {
                JOptionPane.showMessageDialog(this, "Wine transferred successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (mainPanel != null) {
                    mainPanel.refreshInventory();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Transfer failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
