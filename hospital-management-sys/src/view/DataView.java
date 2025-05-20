package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Department;
import model.Disease;
import model.Doctor;
import model.Fracture;
import model.Injury;
import model.Medication;
import model.Nurse;
import model.Patient;
import model.Treatment;
import model.Visit;
import view.add.AddDepartment;
import view.add.AddDisease;
import view.add.AddDoctor;
import view.add.AddFracture;
import view.add.AddInjury;
import view.add.AddMedication;
import view.add.AddNurse;
import view.add.AddPatient;
import view.add.AddTreatment;
import view.add.AddVisit;
import view.utils.FrameParent;
import view.utils.Operation;

public class DataView extends FrameParent {

	private JPanel contentPane;
	private JTable table;
	private JButton viewBTN;
	private List<? extends Object> objects;
	
	public DataView(String[] titles, String[][] data, 
			List<? extends Object> objects) {
		
		super("Viewer", 700, 450, false);
		this.objects = objects;
		contentPane = super.getMainPane();
		
		table = new JTable(data, titles);
		JScrollPane pane = new JScrollPane(table);
		table.setBounds(10, 11, 664, 362);
		pane.setBounds(10, 11, 664, 362);
		contentPane.add(pane);
		
		viewBTN = new JButton("View");
		viewBTN.setForeground(new Color(255, 255, 255));
		viewBTN.setBackground(new Color(0, 128, 0));
		viewBTN.addActionListener(this);
		viewBTN.setBounds(585, 384, 89, 23);
		contentPane.add(viewBTN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRowCount() == 1) {
			Object obj = this.objects.get(table.getSelectedRow());
			if (obj instanceof Doctor) {
				(new AddDoctor(Operation.VIEW, (Doctor) obj, null)).setVisible(true);
			} else if (obj instanceof Department) {
				(new AddDepartment(Operation.VIEW, (Department) obj, null)).setVisible(true);
			} else if (obj instanceof Disease) {
				(new AddDisease(Operation.VIEW, (Disease) obj, null)).setVisible(true);
			} else if (obj instanceof Fracture) {
				(new AddFracture(Operation.VIEW, (Fracture) obj, null)).setVisible(true);
			} else if (obj instanceof Injury) {
				(new AddInjury(Operation.VIEW, (Injury) obj, null)).setVisible(true);
			} else if (obj instanceof Medication) {
				(new AddMedication(Operation.VIEW, (Medication) obj, null)).setVisible(true);
			} else if (obj instanceof Nurse) {
				(new AddNurse(Operation.VIEW, (Nurse) obj, null)).setVisible(true);
			} else if (obj instanceof Patient) {
				(new AddPatient(Operation.VIEW, (Patient) obj, null)).setVisible(true);
			} else if (obj instanceof Treatment) {
				(new AddTreatment(Operation.VIEW, (Treatment) obj, null)).setVisible(true);
			} else if (obj instanceof Visit) {
				(new AddVisit(Operation.VIEW, (Visit) obj, null)).setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Select 1 row from table!");
		}
	}
}
