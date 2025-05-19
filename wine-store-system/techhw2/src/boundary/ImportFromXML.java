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

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

public class ImportFromXML extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton xmlFileUpload;
	private JList<Manufacturer> manList;
	private JList<WineBottle> wbList;
	private DefaultListModel<Manufacturer> listModel;
	private DefaultListModel<WineBottle> listModel_Wb;
	private JInternalFrame manPreviewFrame;
	private JInternalFrame wbPreviewFrame;
	private JLabel lblNewLabel;
	private JScrollPane scrollpane;
	private JScrollPane scrollpane_Wb;
	private JLabel lblNewLabel_1;
	private JTextField phoneNumber_TextField;
	private JTextField address_TextField;
	private JTextField email_TextField;
	private File file;
	private JTextField txtFileName;
	private JComboBox<WineBottle> comboBox;
	private JLabel lblFileName;
	private JLabel lblNewLabel_2_1_2;
	private JLabel lblNewLabel_2_1_3;
	private JLabel lblNewLabel_2_1_4;
	private JLabel lblNewLabel_2_1_5;
	private JLabel lblNewLabel_2_1_6;
	private JLabel lblNewLabel_2_1_7;
	private JLabel lblNewLabel_2_1_8;
	private JTextField manId_tf;
	private JTextField catalognumber_tf;
	private JTextField wineName_tf;
	private JTextField description_tf;
	private JTextField prodYear_tf;
	private JTextField price_tf;
	private JTextField sweetness_tf;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel imageLabel;
	private JLabel lblNewLabel_6;


	/**
	 * Create the frame.
	 */
	public ImportFromXML() {
		setBounds(100, 100, 623, 554);
		setBackground(SystemColor.activeCaption);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// upload single file
		xmlFileUpload = new JButton("XML File");
		xmlFileUpload.setBounds(270, 9, 89, 68);
		xmlFileUpload.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		xmlFileUpload.addActionListener(e -> uploadFile());
		setLayout(null);
		add(xmlFileUpload);
		
		JLabel lblNewLabel = new JLabel("Choose XML file to import data :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 9, 240, 45);
		add(lblNewLabel);
		
		
		listModel = new DefaultListModel<>();
		
		listModel_Wb = new DefaultListModel<>();

        txtFileName = new JTextField();
        txtFileName.setText("File Not Chosen");
        txtFileName.setEditable(false);
        txtFileName.setBounds(100, 52, 136, 20);
        add(txtFileName);
        txtFileName.setColumns(10);
        
        lblFileName = new JLabel("File Name");
        lblFileName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFileName.setBounds(10, 38, 96, 45);
        add(lblFileName);
        
        CustomButton displayWbDetails = new CustomButton("View Wine Bottles", 150, 50, 40);
        displayWbDetails.setBounds(412, 49, 195, 23);
        displayWbDetails.addActionListener(e -> displayWbDetails());
        add(displayWbDetails);
        
        CustomButton displayManDetails = new CustomButton("View Manufacturers", 150, 50, 40);
        displayManDetails.setBounds(412, 20, 195, 23);
        add(displayManDetails);
        displayManDetails.addActionListener(e -> displayManDetails());

		
		manList = new JList<>();
		manList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		manList.setBounds(5, 40, 170, 170);
		manList.setModel(listModel);
		manList.addListSelectionListener(e -> displayManDetails());
		scrollpane = new JScrollPane(manList);
		scrollpane.setBounds(5, 40, 170, 170);
		
		
		manPreviewFrame = new JInternalFrame();
		manPreviewFrame.setBounds(0, 90, 607, 349);
		add(manPreviewFrame);
		manPreviewFrame.getContentPane().setBackground(SystemColor.activeCaption);
		manPreviewFrame.setFrameIcon(null);
		manPreviewFrame.setTitle("XML Data Preview");
		manPreviewFrame.setClosable(true);
		manPreviewFrame.setResizable(false);
		manPreviewFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		manPreviewFrame.getContentPane().setLayout(null);
		manPreviewFrame.getContentPane().add(scrollpane);
		
				
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(128, 128, 128));
		scrollpane.setColumnHeaderView(lblNewLabel_4);
			
		lblNewLabel_1 = new JLabel("Manufacturers Names");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(5, 11, 182, 14);
		manPreviewFrame.getContentPane().add(lblNewLabel_1);
				
		phoneNumber_TextField = new JTextField();
		phoneNumber_TextField.setBounds(359, 42, 136, 20);
		manPreviewFrame.getContentPane().add(phoneNumber_TextField);
		phoneNumber_TextField.setColumns(10);
		phoneNumber_TextField.setEditable(false);
				
		address_TextField = new JTextField();
		address_TextField.setBounds(359, 72, 136, 20);
		manPreviewFrame.getContentPane().add(address_TextField);
		address_TextField.setColumns(10);
		address_TextField.setEditable(false);
			
		email_TextField = new JTextField();
		email_TextField.setBounds(359, 102, 136, 20);
		manPreviewFrame.getContentPane().add(email_TextField);
		email_TextField.setColumns(10);
		email_TextField.setEditable(false);
				
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(231, 42, 118, 19);
		manPreviewFrame.getContentPane().add(lblNewLabel_2);
				
		JLabel lblNewLabel_2_1 = new JLabel("Address");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(231, 72, 105, 19);
		manPreviewFrame.getContentPane().add(lblNewLabel_2_1);
				
		JLabel lblNewLabel_2_1_1 = new JLabel("Email");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(231, 102, 105, 19);
		manPreviewFrame.getContentPane().add(lblNewLabel_2_1_1);
				
		JLabel lblNewLabel_1_1 = new JLabel("Details");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(260, 13, 182, 14);
		manPreviewFrame.getContentPane().add(lblNewLabel_1_1);
				
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Wine Bottles");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1_1_1.setBounds(231, 132, 105, 19);
		manPreviewFrame.getContentPane().add(lblNewLabel_2_1_1_1);
				
		comboBox = new JComboBox<WineBottle>();
		comboBox.setBounds(359, 132, 136, 20);
		manPreviewFrame.getContentPane().add(comboBox);
				
				
		JButton confirmImport_2 = new CustomButton("Confirm Import",150,50,40);
		confirmImport_2.setBounds(463, 290, 136, 23);
		confirmImport_2.addActionListener(e -> importData());
		manPreviewFrame.getContentPane().add(confirmImport_2);
				
				lblNewLabel_6 = new JLabel("");
				lblNewLabel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				lblNewLabel_6.setBounds(217, 32, 289, 136);
				manPreviewFrame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNewLabel_5_1.setBounds(4, 9, 250, 68);
		add(lblNewLabel_5_1);
		
		wbPreviewFrame = new JInternalFrame();
		wbPreviewFrame.setBounds(0, 88, 607, 349);
		add(wbPreviewFrame);
		wbPreviewFrame.getContentPane().setBackground(SystemColor.activeCaption);
		wbPreviewFrame.setFrameIcon(null);
		wbPreviewFrame.setTitle("XML Data Preview");
		wbPreviewFrame.setClosable(true);
		wbPreviewFrame.setResizable(false);
		wbPreviewFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		wbPreviewFrame.getContentPane().setLayout(null);
		wbList = new JList<>();
		wbList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wbList.setBounds(5, 40, 170, 170);
		wbList.setModel(listModel_Wb);
		wbList.addListSelectionListener(e -> displayWbDetails());
		scrollpane_Wb = new JScrollPane(wbList);
		scrollpane_Wb.setBounds(5, 40, 170, 170);
		wbPreviewFrame.getContentPane().add(scrollpane_Wb);
		
		JButton confirmImport_1 = new CustomButton("Confirm Import",150,50,40);
		confirmImport_1.setBounds(463, 290, 136, 23);
		confirmImport_1.addActionListener(e -> importData());
		wbPreviewFrame.getContentPane().add(confirmImport_1);
		
				wbPreviewFrame.setVisible(false);
				
				lblNewLabel_2_1_2 = new JLabel("Manufacturer Id");
				lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_2.setBounds(231, 42, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_2);
				
				lblNewLabel_2_1_3 = new JLabel("Catalog Number");
				lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_3.setBounds(231, 72, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_3);
				
				lblNewLabel_2_1_4 = new JLabel("Name");
				lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_4.setBounds(231, 102, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_4);
				
				lblNewLabel_2_1_5 = new JLabel("Description");
				lblNewLabel_2_1_5.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_5.setBounds(231, 132, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_5);
				
				lblNewLabel_2_1_6 = new JLabel("Production Year");
				lblNewLabel_2_1_6.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_6.setBounds(231, 162, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_6);
				
				lblNewLabel_2_1_7 = new JLabel("Price");
				lblNewLabel_2_1_7.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_7.setBounds(231, 192, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_7);
				
				lblNewLabel_2_1_8 = new JLabel("Sweetness Level");
				lblNewLabel_2_1_8.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2_1_8.setBounds(231, 222, 118, 19);
				wbPreviewFrame.getContentPane().add(lblNewLabel_2_1_8);
				
				manId_tf = new JTextField();
				manId_tf.setEditable(false);
				manId_tf.setColumns(10);
				manId_tf.setBounds(359, 42, 136, 20);
				manId_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(manId_tf);
				
				catalognumber_tf = new JTextField();
				catalognumber_tf.setEditable(false);
				catalognumber_tf.setColumns(10);
				catalognumber_tf.setBounds(359, 72, 136, 20);
				catalognumber_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(catalognumber_tf);
				
				wineName_tf = new JTextField();
				wineName_tf.setEditable(false);
				wineName_tf.setColumns(10);
				wineName_tf.setBounds(359, 102, 136, 20);
				wineName_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(wineName_tf);
				
				description_tf = new JTextField();
				description_tf.setEditable(false);
				description_tf.setColumns(10);
				description_tf.setBounds(359, 132, 136, 20);
				description_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(description_tf);
				
				prodYear_tf = new JTextField();
				prodYear_tf.setEditable(false);
				prodYear_tf.setColumns(10);
				prodYear_tf.setBounds(359, 162, 136, 20);
				PlainDocument doc = (PlainDocument)prodYear_tf.getDocument();
				prodYear_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(prodYear_tf);
				
				price_tf = new JTextField();
				price_tf.setEditable(false);
				price_tf.setColumns(10);
				price_tf.setBounds(359, 192, 136, 20);
				doc = (PlainDocument)price_tf.getDocument();
				price_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(price_tf);
				
				sweetness_tf = new JTextField();
				sweetness_tf.setEditable(false);
				sweetness_tf.setColumns(10);
				sweetness_tf.setBounds(359, 222, 136, 20);
				sweetness_tf.setEditable(false);
				wbPreviewFrame.getContentPane().add(sweetness_tf);
				
				lblNewLabel_3 = new JLabel("Wine Bottle Names");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_3.setBounds(5, 11, 182, 14);
				wbPreviewFrame.getContentPane().add(lblNewLabel_3);
				
				imageLabel = new JLabel("");
				imageLabel.setBounds(512, 45, 70, 196);
				wbPreviewFrame.getContentPane().add(imageLabel);
				
				JLabel lblNewLabel_5 = new JLabel("");
				lblNewLabel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				lblNewLabel_5.setBounds(217, 30, 299, 223);
				wbPreviewFrame.getContentPane().add(lblNewLabel_5);
				
				JLabel lblNewLabel_3_1 = new JLabel("Details");
				lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_3_1.setBounds(217, 11, 278, 14);
				wbPreviewFrame.getContentPane().add(lblNewLabel_3_1);

        
	}
	
	private void uploadFile() {
	
	    JFileChooser fileChooser = new JFileChooser(); // Create a file chooser dialog
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only allow files, not directories
	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(
	    		"XML ONLY", "xml");
	    fileChooser.setFileFilter(fileFilter);
	
	    // Show the file chooser and get the result
	    int result = fileChooser.showOpenDialog(this);
	
	    if (result == JFileChooser.APPROVE_OPTION) {
	        file = fileChooser.getSelectedFile(); // Get the selected file
	        String filePath = file.getPath();
	        
			ArrayList<Manufacturer>importedMans = XmlController.parseManufacturers(filePath);
			listModel.removeAllElements();
			for(Manufacturer man : importedMans) {
	        	listModel.addElement(man);
	        }
			
			ArrayList<WineBottle>importedWb = XmlController.parseWines(filePath);
			listModel_Wb.removeAllElements();
			for(WineBottle wb : importedWb) {
	        	listModel_Wb.addElement(wb);
	        }
			txtFileName.setText(file.getName());
	    }
	    
	    manPreviewFrame.setVisible(true);
	}	
	
	private void displayManDetails() {
		Manufacturer man = manList.getSelectedValue();
		
		if(man!=null) {
			phoneNumber_TextField.setText(man.getPhone());
			address_TextField.setText(man.getAddress());
			email_TextField.setText(man.getEmail());
		}
		comboBox.removeAllItems();
		for(WineBottle wb : DatabaseController.getAllWineBottles().values()) {
			if(man!=null)
				if(wb.getManufacturerID() == man.getManufacturerId())
					comboBox.addItem(wb);
		}
		
		manPreviewFrame.setVisible(true);
		wbPreviewFrame.setVisible(false);
		        
	}
	
	private void displayWbDetails() {
		
		WineBottle bottle = wbList.getSelectedValue();
		
		if(bottle != null) {
			manId_tf.setText(String.valueOf(bottle.getManufacturerID()));
			catalognumber_tf.setText(bottle.getCatalogNumber());
			wineName_tf.setText(bottle.getName());
			description_tf.setText(bottle.getDescription());
			prodYear_tf.setText(String.valueOf(bottle.getProductionYear()));
			price_tf.setText(String.valueOf(bottle.getPricePerBottle()));
			sweetness_tf.setText(bottle.getSweetnessLevel());
		}
		
		manPreviewFrame.setVisible(false);
		wbPreviewFrame.setVisible(true);
	}
	
	private void importData() {
		
	    int response = JOptionPane.showConfirmDialog(null, 
	            "Are you sure you want to import all manufacturer and wine bottle data to the database?", 
	            "Confirm Import", 
	            JOptionPane.YES_NO_OPTION);
	    
	    if (response == JOptionPane.YES_OPTION) 
	    	FileController.importToAccess(file);
	}
}

