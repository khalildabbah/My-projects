package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import view.utils.Callback;
import view.utils.FrameParent;

public class FindView extends FrameParent {

	private JPanel contentPane;
	private JTable table;
	private JButton selectBTN;
	private JLabel mainLbl;
	private JScrollPane pane;
	private Callback callback;
	private List<? extends Object> originalObjects;

	public FindView(String title, Callback callback, 
			String[] titles, String[][] data, List<? extends Object> originalObjects) {
		
		super(title, 500, 300, false);
		contentPane = super.getMainPane();
		this.callback = callback;
		this.originalObjects = originalObjects;

		table = new JTable(data, titles);
		pane = new JScrollPane(table);
		table.setBounds(10, 37, 464, 179);
		pane.setBounds(10, 37, 464, 179);
		contentPane.add(pane);
		
		selectBTN = new JButton("Select");
		selectBTN.setForeground(new Color(255, 255, 255));
		selectBTN.setBackground(new Color(0, 128, 0));
		selectBTN.setBounds(10, 227, 175, 23);
		selectBTN.addActionListener(this);
		contentPane.add(selectBTN);
		
		mainLbl = new JLabel("Select " + title);
		mainLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainLbl.setBounds(10, 8, 464, 23);
		contentPane.add(mainLbl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectBTN) {
			if (table.getSelectedRowCount() == 1) {
				int row = table.getSelectedRow();
				dispose();
				callback.callback(originalObjects.get(row));
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row!");
			}
		}
	}
}
