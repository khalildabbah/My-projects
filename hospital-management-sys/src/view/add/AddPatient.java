package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import enums.BiologicalSex;
import enums.HealthFund;
import model.Patient;
import utils.UtilsMethods;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddPatient extends FrameParent {

	private JPanel contentPane;
	private JTextField idF;
	private JTextField fnameF;
	private JTextField lnameF;
	private JTextField birthF;
	private JTextField addressF;
	private JTextField phoneF;
	private JTextField emailF;
	private JTextField genderF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private JComboBox<HealthFund> healthF;
	private JComboBox<BiologicalSex> sexF;
	
	public AddPatient() {
		this (null, null);
	}
	
	public AddPatient(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddPatient(Operation op, Patient patient, Callback call) {
		super("Patient", 300, 392, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && patient != null) {
			idF.setText(patient.getId() + "");
			fnameF.setText(patient.getFirstName());
			lnameF.setText(patient.getLastName());
			birthF.setText(patient.getBirthDate() + "");
			addressF.setText(patient.getAddress());
			phoneF.setText(patient.getPhoneNumber());
			emailF.setText(patient.getEmail());
			genderF.setText(patient.getGender());
			healthF.setSelectedItem(patient.getHealthFund());
			sexF.setSelectedItem(patient.getBiologicalSex());
			submitBTN.setText("Close");
			idF.setEditable(false);
			fnameF.setEditable(false);
			lnameF.setEditable(false);
			birthF.setEditable(false);
			addressF.setEditable(false);
			phoneF.setEditable(false);
			emailF.setEditable(false);
			genderF.setEditable(false);
			healthF.setEditable(false);
			sexF.setEditable(false);
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
		
		JLabel lblWorkStart = new JLabel("Health Fund");
		lblWorkStart.setBounds(10, 262, 104, 14);
		contentPane.add(lblWorkStart);
		
		JLabel lblSalary = new JLabel("Biological Sex");
		lblSalary.setBounds(10, 293, 104, 14);
		contentPane.add(lblSalary);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 318, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		healthF = new JComboBox<HealthFund>();
		healthF.setBounds(124, 259, 150, 22);
		contentPane.add(healthF);
		
		sexF = new JComboBox<>();
		sexF.setBounds(124, 289, 150, 22);
		contentPane.add(sexF);
	
		for (HealthFund hf: HealthFund.values()) {
			healthF.addItem(hf);
		}
		
		for (BiologicalSex sex: BiologicalSex.values()) {
			sexF.addItem(sex);
		}
		
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
						emailF.getText().isBlank() || genderF.getText().isBlank())  {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						callback.callback(new Patient(Integer.parseInt(idF.getText()), fnameF.getText(), 
								lnameF.getText(), UtilsMethods.parseDate(birthF.getText()), 
								addressF.getText(), phoneF.getText(), emailF.getText(),
								genderF.getText(), (HealthFund)healthF.getSelectedItem(),
								(BiologicalSex)sexF.getSelectedItem()));	
						JOptionPane.showMessageDialog(this, "Patient is added!");
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}
}
