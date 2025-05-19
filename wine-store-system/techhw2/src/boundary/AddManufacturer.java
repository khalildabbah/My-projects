package boundary;

import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import control.DatabaseController;
import entity.Manufacturer;
import entity.WineBottle;
import entity.WineBottleComposite;
import javax.swing.border.EtchedBorder;

public class AddManufacturer extends JPanel {
	
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
	private JButton edit_btn;
    private JButton confirm_btn;
    private JTextField name_TextField;
    private JLabel lblNewLabel_2_1_9;
    private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public AddManufacturer() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(SystemColor.activeCaption);
        setLayout(null);
		
		phoneNumber_TextField = new JTextField();
		phoneNumber_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneNumber_TextField.setBounds(138, 90, 136, 20);
		add(phoneNumber_TextField);
		phoneNumber_TextField.setColumns(10);
		
		address_TextField = new JTextField();
		address_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address_TextField.setBounds(138, 120, 136, 20);
		add(address_TextField);
		address_TextField.setColumns(10);
		
		email_TextField = new JTextField();
		email_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email_TextField.setBounds(138, 150, 136, 20);
		add(email_TextField);
		email_TextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 90, 141, 19);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Address");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 120, 141, 19);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Email");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1_1.setBounds(10, 150, 141, 19);
		add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Manufacturer Contact Details");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(2, 10, 284, 23);
		add(lblNewLabel_1_1);
        
        
        confirm_btn = new CustomButton("Edit", 150, 50, 40);
        confirm_btn.setFont(new Font("Arial", Font.BOLD, 18));
        confirm_btn.setText("Confirm");
        confirm_btn.setBounds(505, 11, 89, 23);
        confirm_btn.addActionListener(e -> addMan());
        add(confirm_btn);
        
        name_TextField = new JTextField();
        name_TextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name_TextField.setColumns(10);
        name_TextField.setBounds(138, 60, 136, 20);
        add(name_TextField);
        
        lblNewLabel_2_1_9 = new JLabel("Name");
        lblNewLabel_2_1_9.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1_9.setBounds(10, 60, 141, 19);
        add(lblNewLabel_2_1_9);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        lblNewLabel_1.setBounds(3, 44, 282, 138);
        add(lblNewLabel_1);
	}
	
	private void addMan() {
		
		String name = name_TextField.getText();
		String phone = phoneNumber_TextField.getText();
		String address = address_TextField.getText();
		String email = email_TextField.getText();
		
		Manufacturer man = new Manufacturer(name,phone,address,email);
		boolean toBeAdded = false;
			for(Manufacturer accessMan : DatabaseController.getInstance().getAllManufacturers().values()) {
				if(accessMan.getAddress().equals(man.getAddress())
						&&accessMan.getEmail().equals(man.getEmail())
						&&accessMan.getName().equals(man.getName())
						&&accessMan.getPhone().equals(man.getPhone()))
					toBeAdded = false;
				else
					toBeAdded = true;
			}
		if(toBeAdded==true) {
			DatabaseController.addManufacturer(man);
	        JOptionPane.showMessageDialog(null, 
	                "Manufacturer Successfully Added!", 
	                "Success", 
	                JOptionPane.INFORMATION_MESSAGE);
		}
		else
	        JOptionPane.showMessageDialog(null, 
	                "Failed To Add Manufacturer.", 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
	}

}
