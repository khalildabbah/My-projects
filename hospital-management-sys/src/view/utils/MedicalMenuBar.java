package view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Doctor;
import model.Nurse;
import model.StaffMember;
import view.add.AddDoctor;
import view.add.AddNurse;

public class MedicalMenuBar extends JMenuBar implements ActionListener {

	private JMenu open, view, account;
	private JMenuItem queries, logout, departments, medicalProblems,
		medical, staff, patients, treatment, visit;
	private boolean admin;
	
	public MedicalMenuBar(boolean admin) {
		
		this.admin = admin;
		open = new JMenu("Open");
		view = new JMenu("View");
		account = new JMenu("Account");
		
		queries = new JMenuItem("Queries");
		logout = new JMenuItem("Logout");
		departments = new JMenuItem("Departments");
		medicalProblems = new JMenuItem("Medical Problems");
		medical = new JMenuItem("Medication");
		staff = new JMenuItem("Staff");
		patients = new JMenuItem("Patients");
		treatment = new JMenuItem("Treatments");
		visit = new JMenuItem("Visits");
		
		open.add(departments);
		open.add(medicalProblems);
		open.add(medical);
		open.add(staff);
		open.add(patients);
		open.add(treatment);
		open.add(visit);
		
		view.add(queries);
		
		account.add(logout);
		
		add(open);
		add(view);
		add(account);
		
		queries.addActionListener(this);
		logout.addActionListener(this);
		departments.addActionListener(this);
		medicalProblems.addActionListener(this);
		medical.addActionListener(this);
		staff.addActionListener(this);
		patients.addActionListener(this);
		treatment.addActionListener(this);
		visit.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == queries) {
			MainFrame.getInstance().setView(ViewType.QUERY_VIEW);
		} else if (e.getSource() == logout) {
			MainFrame.getInstance().setJMenuBar(null);
			MainFrame.getInstance().setView(ViewType.LOGIN);
		} else if (e.getSource() == departments) {
			MainFrame.getInstance().setView(ViewType.DASHBOARD);
		} else if (e.getSource() == medicalProblems) {
			MainFrame.getInstance().setView(ViewType.MEDICAL_PROBLEM_VIEW);
		} else if (e.getSource() == medical) {
			MainFrame.getInstance().setView(ViewType.MEDICATION_VIEW);
		} else if (e.getSource() == staff) {
			if (admin) {
				MainFrame.getInstance().setView(ViewType.STAFF_MEMBER_VIEW);
			} else {
				StaffMember mem = MainFrame.getStaff();
				if (mem instanceof Doctor) {
					(new AddDoctor(Operation.UPDATE, (Doctor)mem, null)).setVisible(true);
				} else if (mem instanceof Nurse) {
					(new AddNurse(Operation.UPDATE, (Nurse)mem, null)).setVisible(true);
				}
			}
		} else if (e.getSource() == patients) {
			MainFrame.getInstance().setView(ViewType.PATIENT_VIEW);
		} else if (e.getSource() == treatment) {
			MainFrame.getInstance().setView(ViewType.TREATMENT_VIEW);
		} else if (e.getSource() == visit) {
			MainFrame.getInstance().setView(ViewType.VISIT_VIEW);
		}
		
	}
	
}
