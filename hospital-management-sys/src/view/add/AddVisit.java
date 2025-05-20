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
import model.Patient;
import model.Visit;
import utils.UtilsMethods;
import view.utils.Callback;
import view.utils.Finder;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddVisit extends FrameParent implements Callback {

	private JPanel contentPane;
	private JTextField numF;
	private JTextField startF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private Visit visit;
	private Patient patient;
	private JTextField endF;
	
	public AddVisit() {
		this (null, null);
	}
	
	public AddVisit(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddVisit(Operation op, Visit visit, Callback call) {
		super("Visit", 300, 170, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && visit != null) {
			numF.setText(visit.getNumber() + "");
			startF.setText(visit.getStartDate().toString());
			endF.setText(visit.getEndDate().toString());
			submitBTN.setText("Close");
			numF.setEditable(false);
			startF.setEditable(false);
			endF.setEditable(false);
		}
	}
	
	private void init() {
		
		numF = new JTextField();
		numF.setColumns(10);
		numF.setBounds(124, 11, 150, 20);
		contentPane.add(numF);
		
		startF = new JTextField();
		startF.setColumns(10);
		startF.setBounds(124, 42, 150, 20);
		contentPane.add(startF);
		
		JLabel lblFirstName = new JLabel("Number:");
		lblFirstName.setBounds(10, 14, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Start Date:");
		lblLastName.setBounds(10, 45, 104, 14);
		contentPane.add(lblLastName);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 98, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		JLabel lblRequireCast = new JLabel("End Date:");
		lblRequireCast.setBounds(10, 73, 104, 14);
		contentPane.add(lblRequireCast);
		
		endF = new JTextField();
		endF.setColumns(10);
		endF.setBounds(124, 70, 150, 20);
		contentPane.add(endF);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (numF.getText().isBlank() || 
						startF.getText().isBlank())  {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						visit = new Visit(Integer.parseInt(numF.getText()), 
								null, UtilsMethods.parseDate(startF.getText()),
								UtilsMethods.parseDate(endF.getText()),
								new HashSet<>(), new HashSet<>());
						Finder.patients(this);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}

	@Override
	public void callback(Object data) {
		if (data instanceof Patient) {
			patient = (Patient) data;
			Finder.medicalProblems(this);
		} else if (data instanceof MedicalProblem) {
			MedicalProblem problem = (MedicalProblem) data;
			visit.setPatient(patient);
			visit.getMedicalProblemsList().add(problem);
			callback.callback(visit);
			JOptionPane.showMessageDialog(this, "Visit is added!");
			dispose();
		}
	}
}
