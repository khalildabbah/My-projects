package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MedicalProblem;
import model.Treatment;
import view.utils.Callback;
import view.utils.Finder;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddTreatment extends FrameParent implements Callback {

	private JPanel contentPane;
	private JTextField serialF;
	private JTextField despF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private Treatment treatment;
	
	public AddTreatment() {
		this (null, null);
	}
	
	public AddTreatment(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddTreatment(Operation op, Treatment treat, Callback call) {
		super("Treatment", 300, 140, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && treat != null) {
			serialF.setText(treat.getSerialNumber() + "");
			despF.setText(treat.getDescription());
			serialF.setEditable(false);
			despF.setEditable(false);
			submitBTN.setText("Close");
		}
	}
	
	private void init() {
		
		serialF = new JTextField();
		serialF.setColumns(10);
		serialF.setBounds(124, 11, 150, 20);
		contentPane.add(serialF);
		
		despF = new JTextField();
		despF.setColumns(10);
		despF.setBounds(124, 42, 150, 20);
		contentPane.add(despF);
		
		JLabel lblFirstName = new JLabel("Serial #:");
		lblFirstName.setBounds(10, 14, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Description:");
		lblLastName.setBounds(10, 45, 104, 14);
		contentPane.add(lblLastName);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 70, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (serialF.getText().isBlank() || 
						despF.getText().isBlank())  {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						treatment = new Treatment(Integer.parseInt(serialF.getText()), despF.getText(),
								new HashSet<>(), new HashSet<>(), new HashSet<>());
						Finder.medicalProblems(this);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}

	@Override
	public void callback(Object data) {
		if (data instanceof MedicalProblem) {
			MedicalProblem prob = (MedicalProblem) data;
			prob.getTreatmentsList().add(treatment);
			treatment.getMedicalProblemsList().add(prob);
			callback.callback(treatment);
			JOptionPane.showMessageDialog(this, "Treatment is added!");
			dispose();
		}
	}
}
