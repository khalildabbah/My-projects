package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Medication;
import model.Treatment;
import view.utils.Callback;
import view.utils.Finder;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddMedication extends FrameParent implements Callback {

	private JPanel contentPane;
	private JTextField codeF;
	private JTextField nameF;
	private JTextField dosageF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private JTextField numDosesF;
	private Medication medication;
	
	public AddMedication() {
		this (null, null);
	}
	
	public AddMedication(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddMedication(Operation op, Medication med, Callback call) {
		super("Medication", 300, 220, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && med != null) {
			codeF.setText(med.getCode() + "");
			nameF.setText(med.getName());
			dosageF.setText(med.getDosage() + "");
			numDosesF.setText(med.getNumberOfDose() + "");
			codeF.setEditable(false);
			nameF.setEditable(false);
			dosageF.setEditable(false);
			numDosesF.setEditable(false);
			submitBTN.setText("Close");
		}
	}
	
	private void init() {
		
		codeF = new JTextField();
		codeF.setBounds(124, 11, 150, 20);
		contentPane.add(codeF);
		codeF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Code:");
		lblNewLabel.setBounds(10, 14, 104, 14);
		contentPane.add(lblNewLabel);
		
		nameF = new JTextField();
		nameF.setColumns(10);
		nameF.setBounds(124, 42, 150, 20);
		contentPane.add(nameF);
		
		dosageF = new JTextField();
		dosageF.setColumns(10);
		dosageF.setBounds(124, 73, 150, 20);
		contentPane.add(dosageF);
		
		JLabel lblFirstName = new JLabel("Name:");
		lblFirstName.setBounds(10, 45, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel dosLbl = new JLabel("Dosage:");
		dosLbl.setBounds(10, 76, 104, 14);
		contentPane.add(dosLbl);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 138, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		JLabel lblSpecialization = new JLabel("No. of Doses:");
		lblSpecialization.setBounds(10, 108, 104, 14);
		contentPane.add(lblSpecialization);
		
		numDosesF = new JTextField();
		numDosesF.setColumns(10);
		numDosesF.setBounds(124, 104, 150, 20);
		contentPane.add(numDosesF);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (codeF.getText().isBlank() || nameF.getText().isBlank() || 
						dosageF.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						medication = new Medication(Integer.parseInt(codeF.getText()), nameF.getText(), 
								Double.parseDouble(dosageF.getText()),
								Integer.parseInt(numDosesF.getText()));
						Finder.treatments(this);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}

	@Override
	public void callback(Object data) {
		if (data instanceof Treatment) {
			((Treatment) data).getMedicationsList().add(medication);
			callback.callback(medication);	
			JOptionPane.showMessageDialog(this, "Medication is added!");
			dispose();
		}
	}
}
