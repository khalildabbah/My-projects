package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.Hospital;
import model.Doctor;
import model.Nurse;
import model.StaffMember;
import view.add.AddDoctor;
import view.add.AddNurse;
import view.utils.Callback;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class StaffMemberView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JTable table;
	private JButton backBTN;
	private JButton doctorAddBTN;
	private JButton removeBTN;
	private JButton nurseAddBTN;
	private int index = 0;
	private Map<Integer, StaffMember> staffList = new HashMap<>();
	private JButton viewBTN;
	private JLabel profilePic;
	
	public StaffMemberView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Staff Members");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 769, 75);
		add(lblNewLabel);
		
		backBTN = new JButton("< Back");
		backBTN.setForeground(new Color(255, 255, 255));
		backBTN.setBackground(new Color(128, 0, 0));
		backBTN.setBounds(10, 8, 89, 23);
		backBTN.addActionListener(this);
		add(backBTN);
		
		doctorAddBTN = new JButton("Add Doctor");
		doctorAddBTN.setForeground(Color.WHITE);
		doctorAddBTN.setBackground(new Color(0, 128, 0));
		doctorAddBTN.setBounds(10, 489, 135, 30);
		doctorAddBTN.addActionListener(this);
		add(doctorAddBTN);
		
		nurseAddBTN = new JButton("Add Nurse");
		nurseAddBTN.setForeground(Color.WHITE);
		nurseAddBTN.setBackground(new Color(0, 128, 0));
		nurseAddBTN.setBounds(155, 489, 135, 30);
		nurseAddBTN.addActionListener(this);
		add(nurseAddBTN);
		
		removeBTN = new JButton("Remove");
		removeBTN.setForeground(Color.WHITE);
		removeBTN.setBackground(new Color(128, 0, 0));
		removeBTN.setBounds(300, 489, 100, 30);
		removeBTN.addActionListener(this);
		add(removeBTN);
		
		viewBTN = new JButton("View");
		viewBTN.setForeground(Color.WHITE);
		viewBTN.setBackground(new Color(0, 128, 0));
		viewBTN.setBounds(410, 489, 100, 30);
		viewBTN.addActionListener(this);
		add(viewBTN);
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				StaffMember prob = staffList.get(index);
				if (prob != null && Hospital.getInstance().removeStaffMember(prob)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Staff Member is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the staff member!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == nurseAddBTN) {
			new AddNurse(Operation.ADD, this).setVisible(true);
		} else if (e.getSource() == doctorAddBTN) {
			new AddDoctor(Operation.ADD, this).setVisible(true);
		}  else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == viewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				StaffMember obj = staffList.get(index);
				if (obj instanceof Doctor) {
					(new AddDoctor(Operation.VIEW, (Doctor) obj, null)).setVisible(true);
				} else if (obj instanceof Nurse) {
					(new AddNurse(Operation.VIEW, (Nurse) obj, null)).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Unable to view this data.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		}
	}
	
	private void updateTable() {
		try {
			remove(pane);
		} catch (Exception e) {}
		
		List<String[]> tableData = new ArrayList<>();
		index = 0;
		staffList.clear();
		for (StaffMember each: Hospital.getInstance().getStaffMembers().values()) {
			tableData.add(each.getTableData());
			staffList.put(index++, each);
		}
		
		table = new JTable(tableData.toArray(new String[][] {}), 
				StaffMember.getTableNames());
		pane = new JScrollPane(table);
		table.setBounds(10, 97, 854, 377);
		pane.setBounds(10, 97, 854, 377);
		add(pane);
		
		profilePic = new JLabel("");
		profilePic.setHorizontalAlignment(SwingConstants.CENTER);
		profilePic.setFont(new Font("Tahoma", Font.BOLD, 18));
		profilePic.setBounds(789, 8, 75, 75);
		add(profilePic);
		
	}

	@Override
	public void callback(Object data) {
		if (data instanceof StaffMember) {
			try {
				if (!Hospital.getInstance().addStaffMember((StaffMember) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the staff member.");
				} else {
					staffList.put(index++, (StaffMember) data);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
			}
			updateTable();
		}
	}

	@Override
	public void setViewType(boolean enable) {
		doctorAddBTN.setEnabled(enable);
		nurseAddBTN.setEnabled(enable);
		removeBTN.setEnabled(enable);
		viewBTN.setEnabled(enable);
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	}
}
