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
import model.Treatment;
import view.add.AddTreatment;
import view.utils.Callback;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class TreatmentView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JTable table;
	private JButton backBTN;
	private JButton addBTN;
	private JButton removeBTN;
	private int index = 0;
	private Map<Integer, Treatment> treatments = new HashMap<>();
	private JButton viewBTN;
	private JLabel profilePic;
	
	public TreatmentView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Treatments");
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
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Treatment prob = treatments.get(index);
				if (prob != null && Hospital.getInstance().removeTreatment(prob)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Treatment is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the Treatment!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == addBTN) {
			new AddTreatment(Operation.ADD, this).setVisible(true);
		}  else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == viewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Treatment prob = treatments.get(index);
				if (prob != null) {
					new AddTreatment(Operation.VIEW, prob, null).setVisible(true);
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
		treatments.clear();
		for (Treatment each: Hospital.getInstance().getTreatments().values()) {
			tableData.add(each.getTableValues());
			treatments.put(index++, each);
		}
		table = new JTable(tableData.toArray(new String[][] {}), 
				Treatment.getTableNames());
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
		if (data instanceof Treatment) {
			try {
				if (!Hospital.getInstance().addTreatment((Treatment) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the Treatment.");
				} else {
					treatments.put(index++, (Treatment) data);
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
