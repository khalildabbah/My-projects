package view.add;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Disease;
import view.utils.Callback;
import view.utils.FrameParent;
import view.utils.Operation;

public class AddDisease extends FrameParent {

	private JPanel contentPane;
	private JTextField nameF;
	private JTextField despF;
	private JButton submitBTN;
	private Operation operation;
	private Callback callback;
	
	public AddDisease() {
		this (null, null);
	}
	
	public AddDisease(Operation op, Callback call) {
		this (op, null, call);
	}
	
	public AddDisease(Operation op, Disease dis, Callback call) {
		super("Disease", 300, 140, false);
		contentPane = super.getMainPane();
		this.operation = op;
		this.callback = call;
		init();
		if (op == Operation.VIEW && dis != null) {
			nameF.setText(dis.getName());
			despF.setText(dis.getDescription());
			nameF.setEditable(false);
			despF.setEditable(false);
			submitBTN.setText("Close");
		}
	}
	
	private void init() {
		
		nameF = new JTextField();
		nameF.setColumns(10);
		nameF.setBounds(124, 11, 150, 20);
		contentPane.add(nameF);
		
		despF = new JTextField();
		despF.setColumns(10);
		despF.setBounds(124, 42, 150, 20);
		contentPane.add(despF);
		
		JLabel lblFirstName = new JLabel("Name:");
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
				if (nameF.getText().isBlank() || 
						despF.getText().isBlank()) {
					JOptionPane.showMessageDialog(this, "No any field should be empty!");
				} else {
					try { 
						callback.callback(new Disease(nameF.getText(), 
								null, new HashSet<>(), despF.getText()));	
						JOptionPane.showMessageDialog(this, "Disease is added!");
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Data is incorrect, try again!");
					}
				}
			}
		}
	}
}
