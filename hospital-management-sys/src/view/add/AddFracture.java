package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Fracture;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddFracture extends FrameParent {

	private JPanel contentPane;
	private JTextField nameF;
	private JTextField locF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private JCheckBox castF;
	
	public AddFracture() {
		this (null, null);
	}
	
	public AddFracture(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddFracture(Operation op, Fracture fract, Callback call) {
		super("Fracture", 300, 170, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && fract != null) {
			nameF.setText(fract.getName());
			locF.setText(fract.getLocation());
			castF.setSelected(fract.isRequiresCast());
			nameF.setEditable(false);
			locF.setEditable(false);
			castF.setEnabled(false);
			submitBTN.setText("Close");
		}
	}
	
	private void init() {
		
		nameF = new JTextField();
		nameF.setColumns(10);
		nameF.setBounds(124, 11, 150, 20);
		contentPane.add(nameF);
		
		locF = new JTextField();
		locF.setColumns(10);
		locF.setBounds(124, 42, 150, 20);
		contentPane.add(locF);
		
		JLabel lblFirstName = new JLabel("Name:");
		lblFirstName.setBounds(10, 14, 104, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Location:");
		lblLastName.setBounds(10, 45, 104, 14);
		contentPane.add(lblLastName);
		
		submitBTN = new JButton("Add");
		submitBTN.setForeground(new Color(255, 255, 255));
		submitBTN.setBackground(new Color(0, 128, 0));
		submitBTN.setBounds(10, 98, 104, 23);
		submitBTN.addActionListener(this);
		contentPane.add(submitBTN);
		
		castF = new JCheckBox("Yes");
		castF.setBackground(new Color(144, 238, 144));
		castF.setBounds(124, 69, 97, 23);
		contentPane.add(castF);
		
		JLabel lblRequireCast = new JLabel("Require Cast:");
		lblRequireCast.setBounds(10, 73, 104, 14);
		contentPane.add(lblRequireCast);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitBTN) {
			if (operation == Operation.VIEW) {
				this.dispose();
			} else {
				if (nameF.getText().isBlank() || 
						locF.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						callback.callback(new Fracture(nameF.getText(), 
								null, new HashSet<>(), locF.getText(), castF.isSelected()));	
						JOptionPane.showMessageDialog(this, "Fracture is added!");
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}
}
