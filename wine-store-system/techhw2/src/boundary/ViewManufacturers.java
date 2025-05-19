package boundary;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;

import control.DatabaseController;
import control.FileController;
import control.XmlController;
import entity.Manufacturer;
import entity.WineBottle;
import entity.WineBottleComposite;
import utils.BackgroundPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ViewManufacturers extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton xmlFileUpload;
	private JList<Manufacturer> list;
	private DefaultListModel<Manufacturer> listModel;
	private JInternalFrame uploadLogFrame;
	private JLabel lblNewLabel;
	private JScrollPane scrollpane;
	private JTextField phoneNumber_TextField;
	private JTextField address_TextField;
	private JTextField email_TextField;
	private File file;
	private JLabel lblNewLabel_1_2;
	private JComboBox<Manufacturer> comboBox;
    private JList<WineBottle> list_1;
	private DefaultListModel<WineBottle> listModel_1;
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
    private JTextField name_TextField;
    private JLabel lblNewLabel_2_1_9;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JTextField wbNumberDisplay;
    private BackgroundPanel backgroundPanel;



	/**
	 * Create the panel.
	 */
	public ViewManufacturers() {
		setBounds(100, 100, 762, 623);
		setBackground(SystemColor.activeCaption);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		phoneNumber_TextField = new JTextField();
		phoneNumber_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneNumber_TextField.setEditable(false);
		phoneNumber_TextField.setBounds(193, 155, 136, 20);
		add(phoneNumber_TextField);
		phoneNumber_TextField.setColumns(10);
		
		address_TextField = new JTextField();
		address_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address_TextField.setEditable(false);
		address_TextField.setBounds(193, 183, 136, 20);
		add(address_TextField);
		address_TextField.setColumns(10);
		
		email_TextField = new JTextField();
		email_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email_TextField.setEditable(false);
		email_TextField.setBounds(193, 211, 136, 20);
		add(email_TextField);
		email_TextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(23, 155, 177, 19);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Address");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(23, 183, 105, 19);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Email");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(23, 211, 105, 19);
		add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contact Details");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(6, 61, 336, 14);
		add(lblNewLabel_1_1);
		
		lblNewLabel_1_2 = new JLabel("Wines");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(443, 61, 208, 14);
		add(lblNewLabel_1_2);
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(6, 10, 283, 27);
		HashMap<Integer,Manufacturer>importedMans = DatabaseController.getAllManufacturers();
		for(Manufacturer man : importedMans.values()) {
			if(man!=null)
				comboBox.addItem(man);
		}
		comboBox.addActionListener(e -> displayManDetails());
		add(comboBox);
        setLayout(null);
        
        listModel_1 = new DefaultListModel<>();
        list_1 = new JList<>();
        list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        list_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        list_1.setBounds(443, 86, 208, 168);
        list_1.setModel(listModel_1);
        list_1.addListSelectionListener(e -> displayWineDetails());
        add(list_1);
        
        lblNewLabel_1_3 = new JLabel("Wine Bottle Specifics");
        lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(6, 262, 336, 33);
        add(lblNewLabel_1_3);
        
        lblNewLabel_2_1_2 = new JLabel("Manufacturer Id");
        lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_2.setBounds(23, 97, 183, 19);
        add(lblNewLabel_2_1_2);
        
        lblNewLabel_2_1_3 = new JLabel("Catalog Number");
        lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_3.setBounds(25, 318, 175, 19);
        add(lblNewLabel_2_1_3);
        
        lblNewLabel_2_1_4 = new JLabel("Name");
        lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_4.setBounds(25, 348, 118, 19);
        add(lblNewLabel_2_1_4);
        
        lblNewLabel_2_1_5 = new JLabel("Description");
        lblNewLabel_2_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_5.setBounds(25, 378, 118, 19);
        add(lblNewLabel_2_1_5);
        
        lblNewLabel_2_1_6 = new JLabel("Production Year");
        lblNewLabel_2_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_6.setBounds(25, 408, 158, 19);
        add(lblNewLabel_2_1_6);
        
        lblNewLabel_2_1_7 = new JLabel("Price");
        lblNewLabel_2_1_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_7.setBounds(25, 438, 118, 19);
        add(lblNewLabel_2_1_7);
        
        lblNewLabel_2_1_8 = new JLabel("Sweetness Level");
        lblNewLabel_2_1_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_8.setBounds(25, 468, 158, 19);
        add(lblNewLabel_2_1_8);
        
        backgroundPanel = new BackgroundPanel("homepage.jpg");
        backgroundPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        backgroundPanel.setBounds(443, 270, 208, 230);
        add(backgroundPanel);
        
        manId_tf = new JTextField();
        manId_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        manId_tf.setEditable(false);
        manId_tf.setColumns(10);
        manId_tf.setBounds(193, 97, 136, 20);
        add(manId_tf);
        
        catalognumber_tf = new JTextField();
        catalognumber_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        catalognumber_tf.setEditable(false);
        catalognumber_tf.setColumns(10);
        catalognumber_tf.setBounds(193, 318, 136, 20);
        add(catalognumber_tf);
        
        wineName_tf = new JTextField();
        wineName_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wineName_tf.setEditable(false);
        wineName_tf.setColumns(10);
        wineName_tf.setBounds(193, 348, 136, 20);
        add(wineName_tf);
        
        description_tf = new JTextField();
        description_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        description_tf.setEditable(false);
        description_tf.setColumns(10);
        description_tf.setBounds(193, 378, 136, 20);
        add(description_tf);
        
        prodYear_tf = new JTextField();
        prodYear_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prodYear_tf.setEditable(false);
        prodYear_tf.setColumns(10);
        prodYear_tf.setBounds(193, 408, 136, 20);
        PlainDocument doc = (PlainDocument)prodYear_tf.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        add(prodYear_tf);
        
        price_tf = new JTextField();
        price_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price_tf.setEditable(false);
        price_tf.setColumns(10);
        price_tf.setBounds(193, 438, 136, 20);
        doc = (PlainDocument)price_tf.getDocument();
        doc.setDocumentFilter(new PositiveDoublesFilter());
        add(price_tf);
        
        sweetness_tf = new JTextField();
        sweetness_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sweetness_tf.setEditable(false);
        sweetness_tf.setColumns(10);
        sweetness_tf.setBounds(193, 468, 136, 20);
        add(sweetness_tf);
        
        name_TextField = new JTextField();
        name_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name_TextField.setEditable(false);
        name_TextField.setColumns(10);
        name_TextField.setBounds(193, 127, 136, 20);
        add(name_TextField);
        
        lblNewLabel_2_1_9 = new JLabel("Name");
        lblNewLabel_2_1_9.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_9.setBounds(23, 127, 105, 19);
        add(lblNewLabel_2_1_9);
        
        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblNewLabel_3.setBounds(4, 83, 338, 159);
        add(lblNewLabel_3);
        
        lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblNewLabel_4.setBounds(10, 297, 332, 203);
        add(lblNewLabel_4);
        
        JButton nextWbBtn = new JButton(">");
        nextWbBtn.setBounds(498, 566, 105, 33);
        nextWbBtn.addActionListener(e -> nextWb());
        add(nextWbBtn);
        
        JButton firstWbBtn = new JButton("|<");
        firstWbBtn.setBounds(105, 566, 105, 33);
        firstWbBtn.addActionListener(e -> firstWb());
        add(firstWbBtn);
        
        JButton previousWbBtn = new JButton("<");
        previousWbBtn.setBounds(234, 566, 96, 33);
        previousWbBtn.addActionListener(e -> previousWb());
        add(previousWbBtn);
        
        wbNumberDisplay = new JTextField();
        wbNumberDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wbNumberDisplay.setBounds(361, 566, 105, 33);
        wbNumberDisplay.setEditable(false);
        add(wbNumberDisplay);
        wbNumberDisplay.setColumns(10);
        if(comboBox.getItemCount() > 0 )
        	wbNumberDisplay.setText("1 of " + comboBox.getItemCount());
        else
        	wbNumberDisplay.setText("0 of " + comboBox.getItemCount());
        
        JButton lastWbBtn = new JButton(">|");
        lastWbBtn.setBounds(627, 566, 118, 33);
        lastWbBtn.addActionListener(e -> lastWb());
        add(lastWbBtn);
        
        JLabel lblNewLabel_4_1 = new JLabel("");
        lblNewLabel_4_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblNewLabel_4_1.setBounds(443, 270, 208, 230);
        add(lblNewLabel_4_1);
        
        displayManDetails();
	}
	
	private void displayManDetails() {
	
	   Manufacturer man = (Manufacturer)comboBox.getSelectedItem();
		
		if(man!=null) {
			manId_tf.setText(String.valueOf(man.getManufacturerId()));
			name_TextField.setText(man.getName());
			phoneNumber_TextField.setText(man.getPhone());
			address_TextField.setText(man.getAddress());
			email_TextField.setText(man.getEmail());
		}
		HashMap<String,WineBottle>wines = DatabaseController.getAllWineBottles();
		listModel_1.removeAllElements();
		for(WineBottle wine : wines.values()) {
			if(man!=null)
				if(wine.getManufacturerID()==man.getManufacturerId())
					listModel_1.addElement(wine);
		}
		
    	wbNumberDisplay.setText(comboBox.getSelectedIndex()+1 + " of " + comboBox.getItemCount());

	}
	
	private void displayWineDetails() {
		WineBottle bottle = list_1.getSelectedValue();
		if(bottle != null) {
			catalognumber_tf.setText(bottle.getCatalogNumber());
			wineName_tf.setText(bottle.getName());
			description_tf.setText(bottle.getDescription());
			prodYear_tf.setText(String.valueOf(bottle.getProductionYear()));
			price_tf.setText(String.valueOf(bottle.getPricePerBottle()));
			sweetness_tf.setText(bottle.getSweetnessLevel());
	        backgroundPanel.setVisible(false);
	        backgroundPanel = new BackgroundPanel(bottle.getProductImage());
	        backgroundPanel.setLayout(new BorderLayout());
	        backgroundPanel.setBounds(443, 270, 208, 230);
	        add(backgroundPanel);
	        backgroundPanel.setVisible(true);
		}
		else {
			catalognumber_tf.setText("");
			wineName_tf.setText("");
			description_tf.setText("");
			prodYear_tf.setText("");
			price_tf.setText("");
			sweetness_tf.setText("");
	        backgroundPanel.setVisible(false);
	        backgroundPanel = new BackgroundPanel("homepage.jpg");
	        backgroundPanel.setLayout(new BorderLayout()); 
	        backgroundPanel.setBounds(443, 270, 208, 230);
	        add(backgroundPanel);
	        backgroundPanel.setVisible(true);
		}
			
	}
	
	private void nextWb() {
		if(comboBox.getItemCount() > comboBox.getSelectedIndex() + 1) {
			int i = comboBox.getSelectedIndex() + 1;
			comboBox.setSelectedIndex(i);
		}
	}
	
	private void previousWb() {
		int i = comboBox.getSelectedIndex() - 1;
		if(i>=0)
			comboBox.setSelectedIndex(i);
	}
	
	private void lastWb() {
		if(comboBox.getItemCount()>0)
			comboBox.setSelectedIndex(comboBox.getItemCount()-1);
	}
	
	private void firstWb() {
		if(comboBox.getItemCount()>0)
			comboBox.setSelectedIndex(0);
	}
}

