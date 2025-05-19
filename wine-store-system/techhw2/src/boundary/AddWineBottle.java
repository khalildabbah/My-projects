package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import control.DatabaseController;
import entity.Manufacturer;
import entity.WineBottle;
import entity.WineBottleComposite;
import entity.WineType;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddWineBottle extends JPanel {
	
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
	private JComboBox<Integer> comboBox_1;
	private DefaultListModel<WineBottle> listModel_1;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_2_1_2;
	private JLabel lblNewLabel_2_1_3;
	private JLabel lblNewLabel_2_1_4;
	private JLabel lblNewLabel_2_1_5;
	private JLabel lblNewLabel_2_1_6;
	private JLabel lblNewLabel_2_1_7;
	private JLabel lblNewLabel_2_1_8;
	private JTextField catalognumber_tf;
	private JTextField wineName_tf;
	private JTextField description_tf;
	private JTextField prodYear_tf;
	private JTextField price_tf;
	private JTextField sweetness_tf;
	private JButton edit_btn;
    private JButton confirm_btn;
    private JComboBox<WineType> wineTypeComboBox;
    private JLabel lblNewLabel_1;
    private JButton chooseImg;
	private ImageIcon winePhoto;
	private String photoName;


	/**
	 * Create the panel.
	 */
	public AddWineBottle() {

		setBounds(100, 100, 623, 481);
		setBackground(SystemColor.activeCaption);
		setBorder(new EmptyBorder(5, 5, 5, 5));		
        setLayout(null);
		
        lblNewLabel_1_3 = new JLabel("Wine Bottle Specifics");
        lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel_1_3.setBounds(1, 13, 329, 33);
        add(lblNewLabel_1_3);
        
        lblNewLabel_2_1_2 = new JLabel("Manufacturer Id");
        lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_2.setBounds(10, 58, 163, 19);
        add(lblNewLabel_2_1_2);
        
        lblNewLabel_2_1_3 = new JLabel("Catalog Number");
        lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_3.setBounds(10, 88, 163, 19);
        add(lblNewLabel_2_1_3);
        
        lblNewLabel_2_1_4 = new JLabel("Name");
        lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_4.setBounds(10, 118, 163, 19);
        add(lblNewLabel_2_1_4);
        
        lblNewLabel_2_1_5 = new JLabel("Description");
        lblNewLabel_2_1_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_5.setBounds(10, 148, 163, 19);
        add(lblNewLabel_2_1_5);
        
        lblNewLabel_2_1_6 = new JLabel("Production Year");
        lblNewLabel_2_1_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_6.setBounds(10, 178, 163, 19);
        add(lblNewLabel_2_1_6);
        
        lblNewLabel_2_1_7 = new JLabel("Price");
        lblNewLabel_2_1_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_7.setBounds(10, 208, 163, 19);
        add(lblNewLabel_2_1_7);
        
        lblNewLabel_2_1_8 = new JLabel("Sweetness Level");
        lblNewLabel_2_1_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_8.setBounds(10, 238, 163, 19);
        add(lblNewLabel_2_1_8);
        
        catalognumber_tf = new JTextField();
        catalognumber_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        catalognumber_tf.setColumns(10);
        catalognumber_tf.setBounds(171, 87, 136, 20);
        add(catalognumber_tf);
        
        wineName_tf = new JTextField();
        wineName_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        wineName_tf.setColumns(10);
        wineName_tf.setBounds(171, 117, 136, 20);
        add(wineName_tf);
        
        description_tf = new JTextField();
        description_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        description_tf.setColumns(10);
        description_tf.setBounds(171, 147, 136, 20);
        add(description_tf);
        
        prodYear_tf = new JTextField();
        prodYear_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prodYear_tf.setColumns(10);
        prodYear_tf.setBounds(171, 177, 136, 20);
        PlainDocument doc = (PlainDocument)prodYear_tf.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        add(prodYear_tf);
        
        price_tf = new JTextField();
        price_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        price_tf.setColumns(10);
        price_tf.setBounds(171, 207, 136, 20);
        doc = (PlainDocument)price_tf.getDocument();
        doc.setDocumentFilter(new PositiveDoublesFilter());
        add(price_tf);
        
        sweetness_tf = new JTextField();
        sweetness_tf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sweetness_tf.setColumns(10);
        sweetness_tf.setBounds(171, 237, 136, 20);
        add(sweetness_tf);
        
        confirm_btn = new CustomButton("Edit", 150, 50, 40);
        confirm_btn.setFont(new Font("Arial", Font.BOLD, 18));
        confirm_btn.setText("Confirm");
        confirm_btn.setBounds(504, 11, 89, 23);
        confirm_btn.addActionListener(e -> addWineBottle());
        add(confirm_btn);
        
        comboBox_1 = new JComboBox<>();
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox_1.setBounds(171, 56, 136, 20);
        for(Manufacturer man : DatabaseController.getInstance().getAllManufacturers().values()) {
        	comboBox_1.addItem(man.getManufacturerId());
        }
        add(comboBox_1);
        
        JLabel lblNewLabel_2_1_8_1 = new JLabel("Wine Type");
        lblNewLabel_2_1_8_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_8_1.setBounds(10, 268, 163, 19);
        add(lblNewLabel_2_1_8_1);
        
	    wineTypeComboBox = new JComboBox<>();
	    wineTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    wineTypeComboBox.setBounds(171, 267, 136, 20);
	    HashMap<String,WineType> winetypeData = DatabaseController.getAllWineTypes();
	    for(WineType wt : winetypeData.values()) {
	    	wineTypeComboBox.addItem(wt);
	    }
	    add(wineTypeComboBox);
	    
	    lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    lblNewLabel_1.setBounds(4, 44, 325, 253);
	    add(lblNewLabel_1);
	    
		chooseImg = new JButton("Choose Image");
		chooseImg.setBackground(new Color(179, 229, 251));
		chooseImg.setHorizontalAlignment(SwingConstants.CENTER);
		chooseImg.setBounds(401, 45, 140, 252);
		chooseImg.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		chooseImg.addActionListener(e -> uploadImg());
		add(chooseImg);
	    
                
	}
	
	private void addWineBottle() {
		
		int id = (Integer)comboBox_1.getSelectedItem();
		String cat = catalognumber_tf.getText();
		String name = wineName_tf.getText();
		String description = description_tf.getText();
		String str1 = prodYear_tf.getText();
		int prodYear = 0;
		
		if(str1.isEmpty()==false)
			prodYear = Integer.valueOf(prodYear_tf.getText());
		
		String str2 = price_tf.getText();
		Double price = 0.0;
		if(str2.isEmpty()==false)
			price = Double.valueOf(price_tf.getText());
		
		String sweetness = sweetness_tf.getText();
		
		WineType type = (WineType)wineTypeComboBox.getSelectedItem();
		
		WineBottle bottle = new WineBottle(cat,id,name,description,prodYear,price,sweetness, photoName ,type);
		WineBottleComposite composite = new WineBottleComposite(bottle.getWineSerialNum(),bottle.getManufacturerID(),bottle.getCatalogNumber());
		
		if(DatabaseController.getInstance().getAllWineComps().containsValue(composite)==false)
			DatabaseController.addWineBottleComposite(composite);
		if(DatabaseController.getInstance().getAllWineBottles().containsKey(bottle.getWineSerialNum())==false) {
			DatabaseController.addWine(bottle);
	        JOptionPane.showMessageDialog(null, 
	                "WineBottle Successfully Added!", 
	                "Success", 
	                JOptionPane.INFORMATION_MESSAGE);
		}
		else
	        JOptionPane.showMessageDialog(null, 
	                "Failed To Add WineBottle.", 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
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
            try {

                File resourcesDir = new File("resources/images/");
                if (!resourcesDir.exists()) {
                    resourcesDir.mkdirs();
                }
                	            
	            File destinationFile = new File(resourcesDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to save the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            photoName = selectedFile.getName();
            // Load the image from the selected file
            ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());

            // Scale the image to fit within the label's size
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                chooseImg.getWidth(), chooseImg.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            winePhoto = scaledIcon;
            // Set the scaled icon to the label
            chooseImg.setIcon(scaledIcon);
            chooseImg.setText(""); // Remove any existing text from the label
            

          
        }
    }
        
}
