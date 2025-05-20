package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import enums.Specialization;
import model.Department;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddDepartment extends FrameParent {

	private JPanel contentPane;
	private JTextField numberF;
	private JTextField nameF;
	private JTextField locationF;
	private JButton submitBTN;
	private JComboBox<Specialization> specialF;
	private Operation operation;
	private Callback callback;
	
	public AddDepartment() {
		this (null, null);
	}
	
	public AddDepartment(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddDepartment(Operation op, Department dept, Callback call) {
		super("Department", 300, 220, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && dept != null) {
			numberF.setText(dept.getNumber() + "");
			nameF.setText(dept.getName());
			locationF.setText(dept.getLocation());
			specialF.setSelectedItem(dept.getSpecialization());
			numberF.setEditable(false);
			nameF.setEditable(false);
			locationF.setEditable(false);
			specialF.setEditable(false);
			submitBTN.setText("Close");
		}
	}
	
	private void init() {
		
		numberF = new JTextField();
		numberF.setBounds(124, 11, 150, 20);
		contentPane.add(numberF);
		numberF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Number:");
		lblNewLabel.setBounds(10, 14, 104, 14);
		contentPane.add(lblNewLabel);
		
		nameF = new JTextField();
		nameF.setColumns(10);
		nameF.setBounds(124, 42, 150, 20);
		contentPane.add(nameF);
		
		locationF = new JTextField();
		locationF.setColumns(10);
		locationF.setBounds(124, 73, 150, 20);
		contentPane.add(locationF);
		
		JLabel lblFirstName = new JLabel("Name:");
		lblFirstName.setBounds(10, 45, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Location:");
		lblLastName.setBounds(10, 76, 104, 14);
		contentPane.add(lblLastName);
		
		specialF = new JComboBox<>();
		for (Specialization sp: Specialization.values()) {
			specialF.addItem(sp);
		}
		specialF.setBounds(124, 104, 150, 22);
		contentPane.add(specialF);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 138, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		JLabel lblSpecialization = new JLabel("Specialization:");
		lblSpecialization.setBounds(10, 108, 104, 14);
		contentPane.add(lblSpecialization);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (numberF.getText().isBlank() || nameF.getText().isBlank() || 
						locationF.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						callback.callback(new Department(Integer.parseInt(numberF.getText()), nameF.getText(), 
								null, locationF.getText(), (Specialization)specialF.getSelectedItem(),
								new HashSet<>()));	
						JOptionPane.showMessageDialog(this, "Department is added!");
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}
}
