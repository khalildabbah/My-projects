package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import control.Hospital;
import model.Patient;
import model.Visit;
import view.add.AddPatient;
import view.utils.Callback;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class PatientView extends JPanel implements Callback, ActionListener, IView {

	private JScrollPane pane;
	private JTable table;
	private JButton backBTN;
	private JButton addBTN;
	private JButton removeBTN, printBTN;
	private int index = 0;
	private Map<Integer, Patient> patients = new HashMap<>();
	private JButton viewBTN;
	private JLabel profilePic;
	
	public PatientView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patients");
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
		
		printBTN = new JButton("Print to Word");
		printBTN.setForeground(Color.WHITE);
		printBTN.setBackground(new Color(0, 128, 0));
		printBTN.setBounds(375, 489, 200, 30);
		printBTN.addActionListener(this);
		add(printBTN);
		
		updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Patient prob = patients.get(index);
				if (prob != null && Hospital.getInstance().removePatient(prob)) {
					updateTable();
					JOptionPane.showMessageDialog(this, "Patient is removed!");
				} else {
					JOptionPane.showMessageDialog(this, "Unable to remove the patient!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == addBTN) {
			new AddPatient(Operation.ADD, this).setVisible(true);
		}  else if (e.getSource() == backBTN) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == viewBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Patient prob = patients.get(index);
				if (prob != null) {
					new AddPatient(Operation.VIEW, prob, null).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Unable to view this data.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		} else if (e.getSource() == printBTN) {
			if (table.getSelectedRowCount() == 1) {
				int index = table.getSelectedRow();
				Patient prob = patients.get(index);
				if (prob != null) {
					printToWord(prob);
				} else {
					JOptionPane.showMessageDialog(this, "Unable to print this data.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Select only 1 row from table.");
			}
		}
	}
	
	private void printToWord(Patient p) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Document");
		fileChooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory() ? true : f.getName().toLowerCase().endsWith(".docx");
			}

			@Override
			public String getDescription() {
				return "Word Documents (*.docx)";
			}
		});
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			if (!fileToSave.getAbsolutePath().endsWith(".docx")) {
				fileToSave = new File(fileToSave.getAbsolutePath() + ".docx");
			}
			String content = "Patient Details ("+p.getId()+")\n"
					+ "Name: " + p.getFirstName() + " " + p.getLastName() + "\n"
					+ "Birth Date: "+p.getBirthDate()+"\n"
					+ "Address: "+p.getAddress()+"\n"
					+ "Phone: "+p.getPhoneNumber()+"\n"
					+ "Email: "+p.getEmail()+"\n"
					+ "Gender: "+p.getGender()+"\n"
					+ "Health Fund: "+p.getHealthFund()+"\n"
					+ "Biological Sex: "+p.getBiologicalSex() + "\n \n"
					+ "Visits: \n\n";
			for (Visit each: p.getVisitsList()) {
				content += each.toString() + "\n";
			}
			try {
				XWPFDocument document = new XWPFDocument();
	            XWPFParagraph paragraph = document.createParagraph();
	            XWPFRun run = paragraph.createRun();
	            
	            String[] lines = content.split("\n");

	            for (String line : lines) {
	                run.setText(line);
	                run.addCarriageReturn();
	            }
	            
	            FileOutputStream out = new FileOutputStream(fileToSave);
	            document.write(out);
	            JOptionPane.showMessageDialog(this, "File Wrote Succcessfully!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: " + e.toString());
			}
			

		}

	}

	private void updateTable() {
		try {
			remove(pane);
		} catch (Exception e) {}
		
		List<String[]> tableData = new ArrayList<>();
		index = 0;
		patients.clear();
		for (Patient each: Hospital.getInstance().getPatients().values()) {
			tableData.add(each.getTableValues());
			patients.put(index++, each);
		}
		table = new JTable(tableData.toArray(new String[][] {}), 
				Patient.getTableNames());
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
		if (data instanceof Patient) {
			try {
				if (!Hospital.getInstance().addPatient((Patient) data)) {
					JOptionPane.showMessageDialog(this, "Unable to add the patient.");
				} else {
					patients.put(index++, (Patient) data);
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
