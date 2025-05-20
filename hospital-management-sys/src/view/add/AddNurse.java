package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import control.Hospital;
import model.Department;
import model.Nurse;
import utils.UtilsMethods;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.MainFrame;
import view.utils.Operation;

public class AddNurse extends FrameParent {

	private JPanel contentPane;
	private JTextField idF;
	private JTextField fnameF;
	private JTextField lnameF;
	private JTextField birthF;
	private JTextField addressF;
	private JTextField phoneF;
	private JTextField emailF;
	private JTextField genderF;
	private JTextField workF;
	private JTextField salaryF;
	private JTextField passwordF;
	private JButton submitBTN, profileBTN;
	private JTextField profileF;
	private JTextField licenseF;
	private Operation operation;
	private Nurse nurse;
	private Callback callback;
	
	public AddNurse() {
		this (Operation.ADD, null);
	}
	
	public AddNurse(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddNurse(Operation op, Nurse nurse, Callback call) {
		super("Nurse", 300, 524, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.nurse = nurse;
		this.callback = call;
		init();
		if (op == Operation.VIEW && nurse != null) {
			idF.setText(nurse.getId() + "");
			fnameF.setText(nurse.getFirstName());
			lnameF.setText(nurse.getLastName());
			birthF.setText(UtilsMethods.formatDate(nurse.getBirthDate()));
			addressF.setText(nurse.getAddress());
			phoneF.setText(nurse.getPhoneNumber());
			emailF.setText(nurse.getEmail());
			genderF.setText(nurse.getGender());
			workF.setText(UtilsMethods.formatDate(nurse.getWorkStartDate()));
			salaryF.setText(nurse.getSalary() + "");
			passwordF.setText(nurse.getPassword());
			licenseF.setText(nurse.getLicenseNumber() + "");
			idF.setEditable(false);
			fnameF.setEditable(false);
			lnameF.setEditable(false);
			birthF.setEditable(false);
			addressF.setEditable(false);
			phoneF.setEditable(false);
			emailF.setEditable(false);
			genderF.setEditable(false);
			workF.setEditable(false);
			salaryF.setEditable(false);
			passwordF.setEditable(false);
			licenseF.setEditable(false);
			submitBTN.setText("Close");
			profileBTN.setText("View");
			profileF.setText(nurse.getProfilePicture());
		} else if (op == Operation.UPDATE) {
			idF.setText(nurse.getId() + "");
			fnameF.setText(nurse.getFirstName());
			lnameF.setText(nurse.getLastName());
			birthF.setText(UtilsMethods.formatDate(nurse.getBirthDate()));
			addressF.setText(nurse.getAddress());
			phoneF.setText(nurse.getPhoneNumber());
			emailF.setText(nurse.getEmail());
			genderF.setText(nurse.getGender());
			workF.setText(UtilsMethods.formatDate(nurse.getWorkStartDate()));
			salaryF.setText(nurse.getSalary() + "");
			passwordF.setText(nurse.getPassword());
			licenseF.setText(nurse.getLicenseNumber() + "");
			submitBTN.setText("Update");
		}
	}
	
	private void init() {
		
		idF = new JTextField();
		idF.setBounds(124, 11, 150, 20);
		contentPane.add(idF);
		idF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 14, 104, 14);
		contentPane.add(lblNewLabel);
		
		fnameF = new JTextField();
		fnameF.setColumns(10);
		fnameF.setBounds(124, 42, 150, 20);
		contentPane.add(fnameF);
		
		lnameF = new JTextField();
		lnameF.setColumns(10);
		lnameF.setBounds(124, 73, 150, 20);
		contentPane.add(lnameF);
		
		birthF = new JTextField();
		birthF.setColumns(10);
		birthF.setBounds(124, 104, 150, 20);
		contentPane.add(birthF);
		
		addressF = new JTextField();
		addressF.setColumns(10);
		addressF.setBounds(124, 135, 150, 20);
		contentPane.add(addressF);
		
		phoneF = new JTextField();
		phoneF.setColumns(10);
		phoneF.setBounds(124, 166, 150, 20);
		contentPane.add(phoneF);
		
		emailF = new JTextField();
		emailF.setColumns(10);
		emailF.setBounds(124, 197, 150, 20);
		contentPane.add(emailF);
		
		genderF = new JTextField();
		genderF.setColumns(10);
		genderF.setBounds(124, 228, 150, 20);
		contentPane.add(genderF);
		
		workF = new JTextField();
		workF.setColumns(10);
		workF.setBounds(124, 259, 150, 20);
		contentPane.add(workF);
		
		salaryF = new JTextField();
		salaryF.setColumns(10);
		salaryF.setBounds(124, 290, 150, 20);
		contentPane.add(salaryF);
		
		passwordF = new JTextField();
		passwordF.setColumns(10);
		passwordF.setBounds(124, 321, 150, 20);
		contentPane.add(passwordF);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 45, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 76, 104, 14);
		contentPane.add(lblLastName);
		
		JLabel lblBirthDate = new JLabel("Birth Date:");
		lblBirthDate.setBounds(10, 107, 104, 14);
		contentPane.add(lblBirthDate);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 138, 104, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 169, 104, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 200, 104, 14);
		contentPane.add(lblEmail);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(10, 231, 104, 14);
		contentPane.add(lblGender);
		
		JLabel lblWorkStart = new JLabel("Work Start");
		lblWorkStart.setBounds(10, 262, 104, 14);
		contentPane.add(lblWorkStart);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 293, 104, 14);
		contentPane.add(lblSalary);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 324, 104, 14);
		contentPane.add(lblPassword);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 450, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		licenseF = new JTextField();
		licenseF.setColumns(10);
		licenseF.setBounds(124, 352, 150, 20);
		contentPane.add(licenseF);
		
		JLabel lblLicense = new JLabel("License #:");
		lblLicense.setBounds(10, 355, 104, 14);
		contentPane.add(lblLicense);
		
		JLabel lblProfile = new JLabel("Profile Picture:");
		lblProfile.setBounds(10, 385, 104, 14);
		contentPane.add(lblProfile);
		
		profileBTN = new JButton("Choose");
		profileBTN.setForeground(new Color(255, 255, 255));
		profileBTN.setBackground(new Color(0, 128, 0));
		profileBTN.addActionListener(this);
		profileBTN.setBounds(124, 382, 150, 22);
		contentPane.add(profileBTN);
		
		profileF = new JTextField();
		profileF.setBounds(10, 415, 264, 22);
		profileF.setEditable(false);
		contentPane.add(profileF);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (idF.getText().isBlank() || fnameF.getText().isBlank() || 
						lnameF.getText().isBlank() || birthF.getText().isBlank() || 
						addressF.getText().isBlank() || phoneF.getText().isBlank() || 
						emailF.getText().isBlank() || genderF.getText().isBlank() || 
						workF.getText().isBlank() || salaryF.getText().isBlank() || 
						passwordF.getText().isBlank() || licenseF.getText().isBlank() ||
						profileF.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						String fileName = "data/"+UUID.randomUUID()+".jpg";
						UtilsMethods.resizeAndCopyImage(new File(profileF.getText()), 
								new File(fileName));
						Nurse newNurse = new Nurse(Integer.parseInt(idF.getText()), fnameF.getText(), 
								lnameF.getText(), UtilsMethods.parseDate(birthF.getText()), 
								addressF.getText(), phoneF.getText(), emailF.getText(),
								genderF.getText(), UtilsMethods.parseDate(workF.getText()),
								new HashSet<Department>(), Double.parseDouble(salaryF.getText()),
								passwordF.getText(), Integer.parseInt(licenseF.getText()));
						newNurse.setProfilePicture(fileName);
						if (operation == Operation.UPDATE) {
							Hospital.getInstance().removeNurse(nurse);
							Hospital.getInstance().addNurse(newNurse);
							MainFrame.setStaff(newNurse);
							JOptionPane.showMessageDialog(this, "Nurse is updated!");
						} else {
							callback.callback(newNurse);	
							JOptionPane.showMessageDialog(this, "Nurse is added!");
						}
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		} else if (e.getSource() == profileBTN) {
			if (operation == Operation.VIEW) {
				UtilsMethods.openImage(profileF.getText());
			} else {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                "Image files", "png", "jpg", "jpeg");
		        fileChooser.setFileFilter(filter);
		        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	File file = fileChooser.getSelectedFile();
		        	profileF.setText(file.getAbsolutePath());
		        }
			}
		}
	}
}
