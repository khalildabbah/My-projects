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
import model.MedicalProblem;
import model.Visit;
import utils.SerializedUtils;
import view.add.AddVisit;
import view.utils.Callback;
import view.utils.Finder;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class VisitView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JTable table;
	private JButton backBTN;
	private JButton addBTN;
	private JButton removeBTN, medicalProblemBTN;
	private int index = 0;
	private Map<Integer, Visit> visits = new HashMap<>();
	private JButton viewBTN;
	private JLabel profilePic;
	
	public VisitView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Visits");
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
		
		addBTN = new JButton("Add");
		addBTN.setForeground(Color.WHITE);
		addBTN.setBackground(new Color(0, 128, 0));
		addBTN.setBounds(10, 489, 135, 30);
		addBTN.addActionListener(this);
		add(addBTN);
		
		removeBTN = new JButton("Remove");
		removeBTN.setForeground(Color.WHITE);
		removeBTN.setBackground(new Color(128, 0, 0));
		removeBTN.setBounds(155, 489, 100, 30);
		removeBTN.addActionListener(this);
		add(removeBTN);
		
		viewBTN = new JButton("View");
		viewBTN.setForeground(Color.WHITE);
		viewBTN.setBackground(new Color(0, 128, 0));
		viewBTN.setBounds(265, 489, 100, 30);
		viewBTN.addActionListener(this);
		add(viewBTN);
		
		medicalProblemBTN = new JButton("Add Medical Problem");
		medicalProblemBTN.setForeground(Color.WHITE);
		medicalProblemBTN.setBackground(new Color(0, 128, 0));
		medicalProblemBTN.setBounds(375, 489, 200, 30);
		medicalProblemBTN.addActionListener(this);
		add(medicalProblemBTN);
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Visit prob = visits.get(index);
				if (prob != null && Hospital.getInstance().removeVisit(prob)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Visit is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the Visit!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == addBTN) {
			new AddVisit(Operation.ADD, this).setVisible(true);
		}  else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == viewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Visit prob = visits.get(index);
				if (prob != null) {
					new AddVisit(Operation.VIEW, prob, null).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Unable to view this data.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == medicalProblemBTN) {
			Finder.medicalProblems(this);
		}
	}
	
	private void updateTable() {
		try {
			remove(pane);
		} catch (Exception e) {}
		
		List<String[]> tableData = new ArrayList<>();
		index = 0;
		visits.clear();
		for (Visit each: Hospital.getInstance().getVisits().values()) {
			tableData.add(each.getTableValues());
			visits.put(index++, each);
		}
		table = new JTable(tableData.toArray(new String[][] {}), 
				Visit.getTableNames());
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
		if (data instanceof Visit) {
			try {
				if (!Hospital.getInstance().addVisit((Visit) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the Visit.");
				} else {
					visits.put(index++, (Visit) data);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
			}
			updateTable();
		} else if (data instanceof MedicalProblem) {
			MedicalProblem prob = (MedicalProblem) data;
			try {
				if (table.getSelectedRowCount() == 1) {
					Visit visit = visits.get(table.getSelectedRow());
					visit.getMedicalProblemsList().add(prob);
					try {
						SerializedUtils.writeHospital();
						JOptionPane.showMessageDialog(this, "The medical problem is added!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Unable to write data");
					}
				} else {
					JOptionPane.showMessageDialog(this, "You did not selected 1 visit from table!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error: " + e.toString());
			}
		}
	}

	@Override
	public void setViewType(boolean enable) {
		removeBTN.setEnabled(enable);
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	}
}
