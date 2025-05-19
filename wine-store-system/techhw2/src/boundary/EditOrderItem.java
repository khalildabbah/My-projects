package boundary;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

import control.DatabaseController;
import entity.Employee;
import entity.Order;
import entity.OrderItem;
import entity.WineBottle;
import utils.BackgroundPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;


public class EditOrderItem extends JFrame {
	

	private static final long serialVersionUID = 1L;


    private JTextField numberField, orderDateField, statusField, shipmentField, empIdField, dateField;
    private JComboBox<String> statusBox;
    private ManageOrders ef; // reference to manage employee page
    private JTextField customerIdField;
    private JCheckBox urgentChkBox;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_2_1_2;
	private JLabel lblNewLabel_2_1_3;
	private JLabel lblNewLabel_2_1_4;
	private JLabel lblNewLabel_2_1_5;
	private JLabel lblNewLabel_2_1_6;
	private JLabel lblNewLabel_2_1_7;
	private JLabel lblNewLabel_2_1_8;
	private JLabel lblNewLabel_2_1_10;
	private JTextField manId_tf;
	private JTextField catalognumber_tf;
	private JTextField wineName_tf;
	private JTextField description_tf;
	private JTextField prodYear_tf;
	private JTextField price_tf;
	private JTextField sweetness_tf;
	private JComboBox<WineBottle> comboBox;
	private JLabel lblWineBottleDetails;
    private int orderItemNumber;
    private BackgroundPanel backgroundPanel;
    private JSpinner spinner;

	
    public EditOrderItem(ManageOrders main, OrderItem i) {
    	
		setBackground(SystemColor.activeCaption);
    	ef = main; // set reference
    	orderItemNumber = i.getOrderItemNumber();
    	getContentPane().setBackground(SystemColor.activeCaption);

        setTitle("Edit Order Item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(583, 477);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel lblId = new JLabel("Order Number :");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(20, 20, 152, 25);
        getContentPane().add(lblId);
        
        numberField = new JTextField();
        numberField.setEditable(false);
        numberField.setBounds(182, 20, 136, 20);
        numberField.setText(String.valueOf(i.getOrderNumber()));
        getContentPane().add(numberField);
        
        JLabel lblName = new JLabel("Quantity");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setBounds(20, 50, 100, 25);
        getContentPane().add(lblName);
        
        lblNewLabel_2_1_2 = new JLabel("Manufacturer Id");
        lblNewLabel_2_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_2.setBounds(20, 148, 158, 19);
        getContentPane().add(lblNewLabel_2_1_2);
        
        lblNewLabel_2_1_3 = new JLabel("Catalog Number");
        lblNewLabel_2_1_3.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_3.setBounds(20, 178, 158, 19);
        getContentPane().add(lblNewLabel_2_1_3);
        
        lblNewLabel_2_1_4 = new JLabel("Name");
        lblNewLabel_2_1_4.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_4.setBounds(20, 208, 158, 19);
        getContentPane().add(lblNewLabel_2_1_4);
        
        lblNewLabel_2_1_5 = new JLabel("Description");
        lblNewLabel_2_1_5.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_5.setBounds(20, 238, 158, 19);
        getContentPane().add(lblNewLabel_2_1_5);
        
        lblNewLabel_2_1_6 = new JLabel("Production Year");
        lblNewLabel_2_1_6.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_6.setBounds(20, 268, 158, 19);
        getContentPane().add(lblNewLabel_2_1_6);
        
        lblNewLabel_2_1_7 = new JLabel("Price");
        lblNewLabel_2_1_7.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_7.setBounds(20, 298, 158, 19);
        getContentPane().add(lblNewLabel_2_1_7);
        
        lblNewLabel_2_1_8 = new JLabel("Sweetness Level");
        lblNewLabel_2_1_8.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel_2_1_8.setBounds(20, 328, 158, 19);
        getContentPane().add(lblNewLabel_2_1_8);

        manId_tf = new JTextField();
        manId_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        manId_tf.setEditable(false);
        manId_tf.setColumns(10);
        manId_tf.setBounds(182, 148, 136, 20);
        getContentPane().add(manId_tf);
        
        catalognumber_tf = new JTextField();
        catalognumber_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        catalognumber_tf.setEditable(false);
        catalognumber_tf.setColumns(10);
        catalognumber_tf.setBounds(182, 178, 136, 20);
        getContentPane().add(catalognumber_tf);
        
        wineName_tf = new JTextField();
        wineName_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        wineName_tf.setEditable(false);
        wineName_tf.setColumns(10);
        wineName_tf.setBounds(182, 208, 136, 20);
        getContentPane().add(wineName_tf);
        
        description_tf = new JTextField();
        description_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        description_tf.setEditable(false);
        description_tf.setColumns(10);
        description_tf.setBounds(182, 238, 136, 20);
        getContentPane().add(description_tf);
        
        prodYear_tf = new JTextField();
        prodYear_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        prodYear_tf.setEditable(false);
        prodYear_tf.setColumns(10);
        prodYear_tf.setBounds(182, 268, 136, 20);
        PlainDocument doc = (PlainDocument)prodYear_tf.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        getContentPane().add(prodYear_tf);
        
        price_tf = new JTextField();
        price_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        price_tf.setEditable(false);
        price_tf.setColumns(10);
        price_tf.setBounds(182, 298, 136, 20);
        doc = (PlainDocument)price_tf.getDocument();
        doc.setDocumentFilter(new PositiveDoublesFilter());
        getContentPane().add(price_tf);
        
        sweetness_tf = new JTextField();
        sweetness_tf.setFont(new Font("Dialog", Font.PLAIN, 16));
        sweetness_tf.setEditable(false);
        sweetness_tf.setColumns(10);
        sweetness_tf.setBounds(182, 328, 136, 20);
        getContentPane().add(sweetness_tf);
       
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBox.setBounds(20, 110, 298, 27);
		HashMap<String,WineBottle>avblBottles = DatabaseController.getAllWineBottles();
		for(WineBottle bottle : avblBottles.values()) {
			if(bottle!=null)
				comboBox.addItem(bottle);
		}
		comboBox.addActionListener(e -> displayWineDetails());
		comboBox.setSelectedItem(i.getWine());
		getContentPane().add(comboBox);
		
		lblWineBottleDetails = new JLabel("Wine Bottle Details");
		lblWineBottleDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblWineBottleDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		lblWineBottleDetails.setBounds(20, 78, 298, 25);
		getContentPane().add(lblWineBottleDetails);
/*
        backgroundPanel = new BackgroundPanel("/boundary/homepage.jpg");
        backgroundPanel.setLayout(new BorderLayout()); // Ensure text stays visible
        backgroundPanel.setBounds(371, 120, 176, 227);
        getContentPane().add(backgroundPanel);
        */
    
        JButton addButton = new CustomButton("Save",150,50,40);
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBounds(173, 375, 200, 30);
        addButton.addActionListener(e -> saveOrder());
        getContentPane().add(addButton);
        
        spinner = new JSpinner();
        spinner.setBounds(182, 47, 136, 20);
        spinner.setValue(i.getQuantity());
        getContentPane().add(spinner);
        
        setVisible(true);
    }
    
	private void displayWineDetails() {
		WineBottle bottle = (WineBottle)comboBox.getSelectedItem();
		
		if(bottle != null) {
			manId_tf.setText(String.valueOf(bottle.getManufacturerID()));
			catalognumber_tf.setText(bottle.getCatalogNumber());
			wineName_tf.setText(bottle.getName());
			description_tf.setText(bottle.getDescription());
			prodYear_tf.setText(String.valueOf(bottle.getProductionYear()));
			price_tf.setText(String.valueOf(bottle.getPricePerBottle()));
			sweetness_tf.setText(bottle.getSweetnessLevel());
			
	        if (backgroundPanel != null) {
	            getContentPane().remove(backgroundPanel);
	        }

	        
			backgroundPanel = new BackgroundPanel(bottle.getProductImage());
			backgroundPanel.setLayout(new BorderLayout()); // Ensure text stays visible
	        backgroundPanel.setBounds(371, 120, 176, 227);
	        getContentPane().add(backgroundPanel);
	        backgroundPanel.revalidate();
	        backgroundPanel.repaint();
	        backgroundPanel.setVisible(true);

            // Refresh UI
            getContentPane().revalidate();
            getContentPane().repaint();
		}
		

        
	}
	    private void saveOrder() {
	    	
	    	try {
	            int orderNum = Integer.parseInt(numberField.getText());
	            int quantity = (int) spinner.getValue();
	            OrderItem item = new OrderItem(orderItemNumber, orderNum, (WineBottle)comboBox.getSelectedItem(), quantity);
	            boolean success = DatabaseController.updateOrderItem(item);	
	            
	            if (success) {
	                JOptionPane.showMessageDialog(this, "Item data saved successfully!");
                    ef.loadDataFromDatabase(); 
                    ef.updOrderItems(DatabaseController.getAllOrders().get(orderNum));
                    dispose(); // Close the window after successful deletion
	            } else {
	                JOptionPane.showMessageDialog(this, "Error saving Item data.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.", "Input Error", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception e) {
		        e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Undefined Error. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
	    }
}
    

