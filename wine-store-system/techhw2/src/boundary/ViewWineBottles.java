package boundary;

import java.awt.EventQueue;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

public class ViewWineBottles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton xmlFileUpload;
	private JList<WineBottle> list;
	private DefaultListModel<WineBottle> listModel;
	private JInternalFrame uploadLogFrame;
	private JLabel lblNewLabel;
	private JScrollPane scrollpane;
	private File file;
	private JComboBox<WineBottle> comboBox;
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
    private JLabel lblNewLabel_2;
    private JTextField wbNumberDisplay;
    private BackgroundPanel backgroundPanel;
	private ImageIcon staffPhoto;
	private JButton chooseImg;


	/**
	 * Create the jpanel.
	 */
	public ViewWineBottles() {
		setBounds(100, 100, 729, 625);
		setBackground(SystemColor.activeCaption);
		setBorder(new EmptyBorder(5, 5, 5, 5));		
        setLayout(null);
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(10, 14, 276, 27);
		HashMap<String,WineBottle>importedWineBottles = DatabaseController.getAllWineBottles();
		for(WineBottle bottle : importedWineBottles.values()) {
			if(bottle!=null)
				comboBox.addItem(bottle);
		}
		comboBox.addActionListener(e -> displayWineDetails());
		add(comboBox);
        
        listModel_1 = new DefaultListModel<>();
        
        lblNewLabel_1_3 = new JLabel("Wine Bottle Specifics");
        lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(7, 57, 342, 33);
        add(lblNewLabel_1_3);
        
        lblNewLabel_2_1_2 = new JLabel("Manufacturer Id");
        lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_2.setBounds(10, 107, 158, 19);
        add(lblNewLabel_2_1_2);
        
        lblNewLabel_2_1_3 = new JLabel("Catalog Number");
        lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_3.setBounds(10, 137, 158, 19);
        add(lblNewLabel_2_1_3);
        
        lblNewLabel_2_1_4 = new JLabel("Name");
        lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_4.setBounds(10, 167, 158, 19);
        add(lblNewLabel_2_1_4);
        
        lblNewLabel_2_1_5 = new JLabel("Description");
        lblNewLabel_2_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_5.setBounds(10, 197, 158, 19);
        add(lblNewLabel_2_1_5);
        
        lblNewLabel_2_1_6 = new JLabel("Production Year");
        lblNewLabel_2_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_6.setBounds(10, 227, 158, 19);
        add(lblNewLabel_2_1_6);
        
        lblNewLabel_2_1_7 = new JLabel("Price");
        lblNewLabel_2_1_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_7.setBounds(10, 257, 158, 19);
        add(lblNewLabel_2_1_7);
        
        lblNewLabel_2_1_8 = new JLabel("Sweetness Level");
        lblNewLabel_2_1_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_8.setBounds(10, 287, 158, 19);
        add(lblNewLabel_2_1_8);
        
        
        backgroundPanel = new BackgroundPanel("/boundary/homepage.jpg");
        backgroundPanel.setLayout(new BorderLayout()); // Ensure text stays visible
        backgroundPanel.setBounds(421, 92, 176, 227);
        add(backgroundPanel);

        manId_tf = new JTextField();
        manId_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        manId_tf.setEditable(false);
        manId_tf.setColumns(10);
        manId_tf.setBounds(182, 107, 136, 20);
        add(manId_tf);
        
        catalognumber_tf = new JTextField();
        catalognumber_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        catalognumber_tf.setEditable(false);
        catalognumber_tf.setColumns(10);
        catalognumber_tf.setBounds(182, 137, 136, 20);
        add(catalognumber_tf);
        
        wineName_tf = new JTextField();
        wineName_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wineName_tf.setEditable(false);
        wineName_tf.setColumns(10);
        wineName_tf.setBounds(182, 167, 136, 20);
        add(wineName_tf);
        
        description_tf = new JTextField();
        description_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        description_tf.setEditable(false);
        description_tf.setColumns(10);
        description_tf.setBounds(182, 197, 136, 20);
        add(description_tf);
        
        prodYear_tf = new JTextField();
        prodYear_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prodYear_tf.setEditable(false);
        prodYear_tf.setColumns(10);
        prodYear_tf.setBounds(182, 227, 136, 20);
        PlainDocument doc = (PlainDocument)prodYear_tf.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        add(prodYear_tf);
        
        price_tf = new JTextField();
        price_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price_tf.setEditable(false);
        price_tf.setColumns(10);
        price_tf.setBounds(182, 257, 136, 20);
        doc = (PlainDocument)price_tf.getDocument();
        doc.setDocumentFilter(new PositiveDoublesFilter());
        add(price_tf);
        
        sweetness_tf = new JTextField();
        sweetness_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sweetness_tf.setEditable(false);
        sweetness_tf.setColumns(10);
        sweetness_tf.setBounds(182, 287, 136, 20);
        add(sweetness_tf);
        
        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblNewLabel_2.setBounds(5, 94, 344, 225);
        add(lblNewLabel_2);
        
        JButton nextWbBtn = new JButton(">");
        nextWbBtn.setBounds(370, 339, 89, 23);
        nextWbBtn.addActionListener(e -> nextWb());
        add(nextWbBtn);
        
        JButton firstWbBtn = new JButton("|<");
        firstWbBtn.setBounds(72, 339, 89, 23);
        firstWbBtn.addActionListener(e -> firstWb());
        add(firstWbBtn);
        
        JButton previousWbBtn = new JButton("<");
        previousWbBtn.setBounds(165, 339, 89, 23);
        previousWbBtn.addActionListener(e -> previousWb());
        add(previousWbBtn);
        
        wbNumberDisplay = new JTextField();
        wbNumberDisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wbNumberDisplay.setBounds(264, 340, 96, 23);
        wbNumberDisplay.setEditable(false);
        add(wbNumberDisplay);
        wbNumberDisplay.setColumns(10);
        if(comboBox.getItemCount() > 0 )
        	wbNumberDisplay.setText("1 of " + comboBox.getItemCount());
        else
        	wbNumberDisplay.setText("0 of " + comboBox.getItemCount());
        
        JButton lastWbBtn = new JButton(">|");
        lastWbBtn.setBounds(469, 339, 89, 23);
        lastWbBtn.addActionListener(e -> lastWb());
        add(lastWbBtn);
        
        /*
		chooseImg = new JButton("Choose Image");
		chooseImg.setBackground(new Color(179, 229, 251));
		chooseImg.setHorizontalAlignment(SwingConstants.CENTER);
		chooseImg.setBounds(393, 97, 140, 225);
		chooseImg.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chooseImg.addActionListener(e -> uploadImg());
		add(chooseImg);
        */
        displayWineDetails();
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
			
	        
	        backgroundPanel.setVisible(false);
	        backgroundPanel = new BackgroundPanel(bottle.getProductImage());
	        backgroundPanel.setLayout(new BorderLayout()); // Ensure text stays visible
	        backgroundPanel.setBounds(421, 92, 176, 227);
	        add(backgroundPanel);
	        backgroundPanel.setVisible(true);
		
			
		
    	wbNumberDisplay.setText(comboBox.getSelectedIndex()+1 + " of " + comboBox.getItemCount());
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
	

    private void uploadImg() {

        JFileChooser fileChooser = new JFileChooser(); // Create a file chooser dialog
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only allow files, not directories
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
        		"Image Files (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(imageFilter);


        // Show the file chooser and get the result
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); // Get the selected file

            // Load the image from the selected file
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());

            // Scale the image to fit within the label's size
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                chooseImg.getWidth(), chooseImg.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            staffPhoto = scaledIcon;
            // Set the scaled icon to the label
            chooseImg.setIcon(scaledIcon);
            chooseImg.setText(""); // Remove any existing text from the label
            
            try {

            String jarPath = new File(ManageWineBottles.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            File destination = new File(jarPath, selectedFile.getName());   
            Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
          
        }
    }
}

