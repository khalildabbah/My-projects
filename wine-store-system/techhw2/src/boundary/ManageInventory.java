package boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import control.DatabaseController;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageInventory extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable wineTbl;
    private DefaultTableModel tableModel;
    private JButton moveWinebtn;

    private ArrayList<String> storageSerials;
    private int currentIndex = 0;
    private JTextField pageIndicator;

  
    public ManageInventory() {
        setBounds(250, 10, 698, 609);
        setBackground(SystemColor.activeCaption);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Inventory & Storages");
        lblNewLabel.setBounds(177, 11, 335, 51);
        lblNewLabel.setFont(new Font("Engravers MT", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Storage Name :");
        lblNewLabel_1.setBounds(193, 85, 142, 20);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Storage Serial:");
        lblNewLabel_1_1.setBounds(193, 133, 142, 20);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Storage ID :");
        lblNewLabel_1_2.setBounds(193, 184, 142, 20);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblNewLabel_1_2);

        textField = new JTextField();
        textField.setBounds(345, 84, 148, 27);
        textField.setEditable(false);
        add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(345, 132, 148, 27);
        textField_1.setColumns(10);
        textField_1.setEditable(false);
        add(textField_1);

        textField_2 = new JTextField();
        textField_2.setBounds(345, 183, 148, 27);
        textField_2.setColumns(10);
        textField_2.setEditable(false);
        add(textField_2);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(177, 73, 335, 147);
        lblNewLabel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        add(lblNewLabel_3);

        // Left Arrow Button (Previous Storage)
        JButton leftArrowButton = new JButton("<");
        leftArrowButton.setBounds(193, 239, 50, 30);
        leftArrowButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        leftArrowButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                currentIndex = storageSerials.size() - 1; // Circular navigation
            }
            updatePage();
        });
        add(leftArrowButton);

        // Page Indicator Text Field
        pageIndicator = new JTextField();
        pageIndicator.setBounds(300, 240, 80, 30);
        pageIndicator.setHorizontalAlignment(SwingConstants.CENTER);
        pageIndicator.setFont(new Font("Tahoma", Font.BOLD, 14));
        pageIndicator.setEditable(false);
        add(pageIndicator);

        // Right Arrow Button (Next Storage)
        JButton rightArrowButton = new JButton(">");
        rightArrowButton.setBounds(443, 239, 50, 30);
        rightArrowButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        rightArrowButton.addActionListener(e -> {
            if (currentIndex < storageSerials.size() - 1) {
                currentIndex++;
            } else {
                currentIndex = 0; // Circular navigation
            }
            updatePage();
        });
        add(rightArrowButton);

        moveWinebtn = new CustomButton("Transfer Wines", 497, 148, 51);
        moveWinebtn.setFont(new Font("Arial", Font.BOLD, 18));
        moveWinebtn.setBounds(265, 497, 165, 67);
        add(moveWinebtn);

        // Open MovingWines JFrame when clicking the button
        moveWinebtn.addActionListener(e -> new MovingWines(ManageInventory.this));


        // Table Model with column names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Wine Serial");
        tableModel.addColumn("Quantity");

        // Create JTable and wrap in JScrollPane
        wineTbl = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(wineTbl);
        scrollPane.setBounds(104, 298, 491, 188);
        add(scrollPane);
        
        MouseAdapter doubleClickListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) { 
                    if (!storageSerials.isEmpty() && currentIndex >= 0 && currentIndex < storageSerials.size()) {
                        String storageSerial = storageSerials.get(currentIndex);

                        // Fetch full details
                        HashMap<String, String> details = DatabaseController.getStorageDetails(storageSerial);
                        if (!details.isEmpty()) {
                            new EditStorageFrame(ManageInventory.this, 
                                    details.get("Name"), 
                                    details.get("Storage Serial"), 
                                    details.get("ID"));
                        }
                    }
                }
            }
        };

        textField.addMouseListener(doubleClickListener);
        textField_1.addMouseListener(doubleClickListener);
        textField_2.addMouseListener(doubleClickListener);
        lblNewLabel_3.addMouseListener(doubleClickListener);

        storageSerials = new ArrayList<>();

        // Load data
        loadStorageSerials();
        if (!storageSerials.isEmpty()) updatePage();
    
        
        JButton leftArrowButton_1 = new JButton("|<");
        leftArrowButton_1.addActionListener(e -> {
            if (!storageSerials.isEmpty()) {
                currentIndex = 0; // Go to the first storage
                updatePage();
            }
        });
        
        
        leftArrowButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        leftArrowButton_1.setBounds(104, 239, 60, 30);
        add(leftArrowButton_1);
        
        JButton rightArrowButton_1 = new JButton(">|");
        rightArrowButton_1.addActionListener(e -> {
            if (!storageSerials.isEmpty()) {
                currentIndex = storageSerials.size() - 1; // Go to the last storage
                updatePage();
            }
        });
        rightArrowButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        rightArrowButton_1.setBounds(523, 239, 60, 30);
        add(rightArrowButton_1);

        // Load all storages
        loadStorageSerials();

        // Load first storage details
        if (!storageSerials.isEmpty()) {
            updatePage();
        }
    }

    /**
     * Updates the page indicator, storage details, and wine details.
     */
    private void updatePage() {
        if (!storageSerials.isEmpty()) {
            String storageSerial = storageSerials.get(currentIndex);
            loadStorageDetails(storageSerial);
            loadWineDetails(storageSerial);
            pageIndicator.setText((currentIndex + 1) + " of " + storageSerials.size());
        }
    }

    /**
     * Fetches storage details and fills text fields.
     */
    private void loadStorageDetails(String storageSerial) {
        HashMap<String, String> details = DatabaseController.getStorageDetails(storageSerial);

        if (!details.isEmpty()) {
            textField.setText(details.get("Name"));  // Storage Name
            textField_1.setText(details.get("Storage Serial"));  // Storage Serial
            textField_2.setText(details.get("ID"));  // Storage ID
        }
    }

    /**
     * Loads all wines for the selected storage and displays them in the table.
     */
    private void loadWineDetails(String storageSerial) {
        ArrayList<HashMap<String, String>> wineList = DatabaseController.getWinesForStorage(storageSerial);

        tableModel.setRowCount(0); // Clear previous data
        for (HashMap<String, String> wine : wineList) {
            tableModel.addRow(new Object[]{wine.get("Wine Serial"), wine.get("Quantity")});
        }
    }

   
    private void loadStorageSerials() {
        storageSerials = DatabaseController.getAllStorageSerials();
    }

    /**
     * Refreshes inventory details after a wine transfer.
     */
    public void refreshInventory() {
        if (!storageSerials.isEmpty()) {
            loadWineDetails(storageSerials.get(currentIndex));
        }
    }
}
