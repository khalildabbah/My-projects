package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Injury;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddInjury extends FrameParent {

	private JPanel contentPane;
	private JTextField nameF;
	private JTextField locF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	private JTextField recF;
	
	public AddInjury() {
		this (null, null);
	}
	
	public AddInjury(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddInjury(Operation op, Injury inj, Callback call) {
		super("Injury", 300, 170, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && inj != null) {
			nameF.setText(inj.getName());
			locF.setText(inj.getLocation());
			recF.setText(inj.getCommonRecoveryTime() + "");
			nameF.setEditable(false);
			locF.setEditable(false);
			recF.setEditable(false);
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
		
		JLabel lblRequireCast = new JLabel("Recovery Time:");
		lblRequireCast.setBounds(10, 73, 104, 14);
		contentPane.add(lblRequireCast);
		
		recF = new JTextField();
		recF.setColumns(10);
		recF.setBounds(124, 70, 150, 20);
		contentPane.add(recF);
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
						callback.callback(new Injury(nameF.getText(), 
								null, Integer.parseInt(recF.getText()), locF.getText()));	
						JOptionPane.showMessageDialog(this, "Injury is added!");
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}
}
