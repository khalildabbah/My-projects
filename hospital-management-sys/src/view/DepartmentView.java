package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import model.Department;
import model.StaffMember;
import utils.SerializedUtils;
import view.add.AddDepartment;
import view.utils.Callback;
import view.utils.Finder;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class DepartmentView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JButton addBTN;
	private JButton removeBTN;
	private JButton addStaff;
	private JTable table;
	private Map<Integer, Department> depts = new HashMap<>();
	private int index = 0;
	private JButton staffViewBTN;
	private JButton backBTN;
	private JLabel profilePic;
	
	public DepartmentView() {
		
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Department");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 769, 75);
		add(lblNewLabel);
		
		addBTN = new JButton("Add Department");
		addBTN.setForeground(new Color(255, 255, 255));
		addBTN.setBackground(new Color(0, 128, 0));
		addBTN.setBounds(10, 485, 150, 30);
		addBTN.addActionListener(this);
		add(addBTN);
		
		removeBTN = new JButton("Remove Department");
		removeBTN.setForeground(new Color(255, 255, 255));
		removeBTN.setBackground(new Color(128, 0, 0));
		removeBTN.setBounds(170, 485, 150, 30);
		removeBTN.addActionListener(this);
		add(removeBTN);
		
		addStaff = new JButton("Add Staff to Department");
		addStaff.setForeground(new Color(255, 255, 255));
		addStaff.setBackground(new Color(0, 128, 0));
		addStaff.setBounds(330, 485, 200, 30);
		addStaff.addActionListener(this);
		add(addStaff);
		
		staffViewBTN = new JButton("View Staff");
		staffViewBTN.setForeground(Color.WHITE);
		staffViewBTN.addActionListener(this);
		staffViewBTN.setBackground(new Color(0, 128, 0));
		staffViewBTN.setBounds(540, 485, 130, 30);
		add(staffViewBTN);
		
		backBTN = new JButton("< Back");
		backBTN.setForeground(new Color(255, 255, 255));
		backBTN.setBackground(new Color(128, 0, 0));
		backBTN.setBounds(10, 8, 89, 23);
		backBTN.addActionListener(this);
		add(backBTN);
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBTN) {
			AddDepartment dept = new AddDepartment(Operation.ADD, this);
			dept.setVisible(true);
		} else if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Department dept = depts.get(index);
				if (dept != null && Hospital.getInstance().removeDepartment(dept)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Department is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the department!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == addStaff) {
			Finder.staffMembers(this);
		} else if (e.getSource() == staffViewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Department dept = depts.get(index);
				if (dept != null) {
					List<String[]> list = new ArrayList<>();
					List<Object> objList = new ArrayList<>();
					for (StaffMember each: dept.getStaffMembersList()) {
						objList.add(each);
						list.add(new String[] {
								each.getFirstName(), each.getLastName(),
								each.getAddress(), each.getEmail(), 
								each.getPhoneNumber()
						});
					}
					DataView view = new DataView(new String[] {"First Name",
							"Last Name", "Address", "Email", "Phone"},
							list.toArray(new String[][] {}), 
							objList);
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Unable to fetch details of the department!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		}
	}
	
	private void updateTable() {
		try {
			remove(pane);
		} catch (Exception e) {}
		
		List<String[]> tableData = new ArrayList<>();
		index = 0;
		depts.clear();
		for (Department each: Hospital.getInstance().getDepartments().values()) {
			tableData.add(each.getTableData());
			depts.put(index++, each);
		}
		
		table = new JTable(tableData.toArray(new String[][] {}), 
				Department.getTableColumns());
		pane = new JScrollPane(table);
		table.setBounds(10, 97, 854, 377);
		pane.setBounds(10, 97, 854, 377);
		add(pane);
		
		profilePic = new JLabel("");
		profilePic.setHorizontalAlignment(SwingConstants.CENTER);
		profilePic.setFont(new Font("Tahoma", Font.BOLD, 18));
		profilePic.setBounds(789, 11, 75, 75);
		add(profilePic);
		
	}

	@Override
	public void callback(Object data) {
		if (data instanceof Department) {
			try {
				if (!Hospital.getInstance().addDepartment((Department) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the department.");
				} else {
					depts.put(index++, (Department) data);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
			}
			updateTable();
		} else if (data instanceof StaffMember) {
			int index = table.getSelectedRow();
			Department dept = depts.get(index);
			StaffMember person = (StaffMember) data;
			if (table.getSelectedRowCount() == 1 && person != null && dept != null) {
				try {
					dept.addStaffMember(person);
					try {
						SerializedUtils.writeHospital();
						JOptionPane.showMessageDialog(this, "Staff is added!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Unable to write data");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Error: " + e.toString());
				}
			}
		}
	}

	@Override
	public void setViewType(boolean enable) {
		addBTN.setEnabled(enable);
		removeBTN.setEnabled(enable);
		addStaff.setEnabled(enable);
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	}
}
