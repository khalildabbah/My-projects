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
import model.Disease;
import model.Fracture;
import model.Injury;
import model.MedicalProblem;
import view.add.AddDisease;
import view.add.AddFracture;
import view.add.AddInjury;
import view.utils.Callback;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class MedicalProblemView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JTable table;
	private JButton backBTN;
	private JButton diseaseAddBTN;
	private JButton removeBTN;
	private JButton fractureAddBTN;
	private JButton injuryAddBTN;
	private int index = 0;
	private Map<Integer, MedicalProblem> problems = new HashMap<>();
	private JButton viewBTN;
	private JLabel profilePic;
	
	public MedicalProblemView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Medical Problems");
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
		
		diseaseAddBTN = new JButton("Add Disease");
		diseaseAddBTN.setForeground(Color.WHITE);
		diseaseAddBTN.setBackground(new Color(0, 128, 0));
		diseaseAddBTN.setBounds(10, 489, 135, 30);
		diseaseAddBTN.addActionListener(this);
		add(diseaseAddBTN);
		
		fractureAddBTN = new JButton("Add Fracture");
		fractureAddBTN.setForeground(Color.WHITE);
		fractureAddBTN.setBackground(new Color(0, 128, 0));
		fractureAddBTN.setBounds(155, 489, 135, 30);
		fractureAddBTN.addActionListener(this);
		add(fractureAddBTN);
		
		injuryAddBTN = new JButton("Add Injury");
		injuryAddBTN.setForeground(Color.WHITE);
		injuryAddBTN.setBackground(new Color(0, 128, 0));
		injuryAddBTN.setBounds(300, 489, 135, 30);
		injuryAddBTN.addActionListener(this);
		add(injuryAddBTN);
		
		removeBTN = new JButton("Remove");
		removeBTN.setForeground(Color.WHITE);
		removeBTN.setBackground(new Color(128, 0, 0));
		removeBTN.setBounds(445, 489, 100, 30);
		removeBTN.addActionListener(this);
		add(removeBTN);
		
		viewBTN = new JButton("View");
		viewBTN.setForeground(Color.WHITE);
		viewBTN.setBackground(new Color(0, 128, 0));
		viewBTN.setBounds(555, 489, 100, 30);
		viewBTN.addActionListener(this);
		add(viewBTN);
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				MedicalProblem prob = problems.get(index);
				if (prob != null && Hospital.getInstance().removeMedicalProblem(prob)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Medical Problem is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the medical problem!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == injuryAddBTN) {
			new AddInjury(Operation.ADD, this).setVisible(true);
		} else if (e.getSource() == fractureAddBTN) {
			new AddFracture(Operation.ADD, this).setVisible(true);
		} else if (e.getSource() == diseaseAddBTN) {
			new AddDisease(Operation.ADD, this).setVisible(true);
		}  else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == viewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				MedicalProblem obj = problems.get(index);
				if (obj instanceof Disease) {
					(new AddDisease(Operation.VIEW, (Disease) obj, null)).setVisible(true);
				} else if (obj instanceof Fracture) {
					(new AddFracture(Operation.VIEW, (Fracture) obj, null)).setVisible(true);
				} else if (obj instanceof Injury) {
					(new AddInjury(Operation.VIEW, (Injury) obj, null)).setVisible(true);
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
		problems.clear();
		for (MedicalProblem each: Hospital.getInstance().getMedicalProblems().values()) {
			tableData.add(each.getTableData());
			problems.put(index++, each);
		}
		
		table = new JTable(tableData.toArray(new String[][] {}), 
				MedicalProblem.getTableNames());
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
		if (data instanceof MedicalProblem) {
			try {
				if (!Hospital.getInstance().addMedicalProblem((MedicalProblem) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the medical problem.");
				} else {
					problems.put(index++, (MedicalProblem) data);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
			}
			updateTable();
		}
	}

	@Override
	public void setViewType(boolean enable) {
		removeBTN.setEnabled(enable);
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	}
}
