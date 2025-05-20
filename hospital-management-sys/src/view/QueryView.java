package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.Hospital;
import enums.Specialization;
import model.Department;
import model.Doctor;
import model.MedicalProblem;
import model.Nurse;
import model.Patient;
import model.StaffMember;
import model.Treatment;
import utils.UtilsMethods;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.ViewType;

public class QueryView extends JPanel implements ActionListener, IView {
	
	private JButton backBTN;
	private int index = 0;
	private JButton countBTN;
	private JButton visitDistanceBTN;
	private JButton finishInternshipBTN;
	private JButton visitBeforeBTN;
	private JButton oldNurseBTN;
	private JButton staffWorkMoreBTN;
	private JButton treatmentSortedBTN;
	private JButton docBySpecBTN;
	private JButton countIntenseStaffBTN;
	private JButton salaryBTN;
	private JButton healthBTN;
	private JButton appointBTN;
	private JButton oldestDoctorBTN;
	private JLabel profilePic;
	
	public QueryView() {
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Queries");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 753, 75);
		add(lblNewLabel);
		
		backBTN = new JButton("< Back");
		backBTN.setForeground(new Color(255, 255, 255));
		backBTN.setBackground(new Color(128, 0, 0));
		backBTN.setBounds(10, 8, 89, 23);
		backBTN.addActionListener(this);
		add(backBTN);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		panel.setBounds(10, 97, 838, 423);
		add(panel);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		countBTN = new JButton("<html><center>Count Medications</center></html>");
		countBTN.setForeground(new Color(255, 255, 255));
		countBTN.setBackground(new Color(0, 128, 0));
		countBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(countBTN);
		
		visitDistanceBTN = new JButton("<html><center>Difference between Longest & Shortest VisIt</center></html>");
		visitDistanceBTN.setForeground(Color.WHITE);
		visitDistanceBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		visitDistanceBTN.setBackground(new Color(0, 128, 0));
		panel.add(visitDistanceBTN);
		
		finishInternshipBTN = new JButton("<html><center>How many Finish Internship?</center></html>");
		finishInternshipBTN.setForeground(Color.WHITE);
		finishInternshipBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		finishInternshipBTN.setBackground(new Color(0, 128, 0));
		panel.add(finishInternshipBTN);
		
		visitBeforeBTN = new JButton("<html><center>How many Visit Before?</center></html>");
		visitBeforeBTN.setForeground(Color.WHITE);
		visitBeforeBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		visitBeforeBTN.setBackground(new Color(0, 128, 0));
		panel.add(visitBeforeBTN);
		
		oldNurseBTN = new JButton("<html><center>Oldest Nurse</center></html>");
		oldNurseBTN.setForeground(Color.WHITE);
		oldNurseBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		oldNurseBTN.setBackground(new Color(0, 128, 0));
		panel.add(oldNurseBTN);
		
		staffWorkMoreBTN = new JButton("<html><center>Staff Work in More than 1 Departments</center></html>");
		staffWorkMoreBTN.setForeground(Color.WHITE);
		staffWorkMoreBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		staffWorkMoreBTN.setBackground(new Color(0, 128, 0));
		panel.add(staffWorkMoreBTN);
		
		treatmentSortedBTN = new JButton("<html><center>Treatments by Medical Problems By Department</center></html>");
		treatmentSortedBTN.setForeground(Color.WHITE);
		treatmentSortedBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		treatmentSortedBTN.setBackground(new Color(0, 128, 0));
		panel.add(treatmentSortedBTN);
		
		docBySpecBTN = new JButton("<html><center>No. of Doctors by Specialization</center></html>");
		docBySpecBTN.setForeground(Color.WHITE);
		docBySpecBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		docBySpecBTN.setBackground(new Color(0, 128, 0));
		panel.add(docBySpecBTN);
		
		countIntenseStaffBTN = new JButton("<html><center>Count Intense Care Staff Members</center></html>");
		countIntenseStaffBTN.setForeground(Color.WHITE);
		countIntenseStaffBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		countIntenseStaffBTN.setBackground(new Color(0, 128, 0));
		panel.add(countIntenseStaffBTN);
		
		salaryBTN = new JButton("<html><center>Average Salary</center></html>");
		salaryBTN.setForeground(Color.WHITE);
		salaryBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		salaryBTN.setBackground(new Color(0, 128, 0));
		panel.add(salaryBTN);
		
		healthBTN = new JButton("<html><center>Comply with Health Standards</center></html>");
		healthBTN.setForeground(Color.WHITE);
		healthBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		healthBTN.setBackground(new Color(0, 128, 0));
		panel.add(healthBTN);
		
		appointBTN = new JButton("<html><center>Appoint Manager</center></html>");
		appointBTN.setForeground(Color.WHITE);
		appointBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		appointBTN.setBackground(new Color(0, 128, 0));
		panel.add(appointBTN);
		
		oldestDoctorBTN = new JButton("<html><center>Oldest Doctor</center></html>");
		oldestDoctorBTN.setForeground(Color.WHITE);
		oldestDoctorBTN.setFont(new Font("Tahoma", Font.BOLD, 15));
		oldestDoctorBTN.setBackground(new Color(0, 128, 0));
		panel.add(oldestDoctorBTN);
		
		countBTN.addActionListener(this);
		visitDistanceBTN.addActionListener(this);
		finishInternshipBTN.addActionListener(this);
		visitBeforeBTN.addActionListener(this);
		oldNurseBTN.addActionListener(this);
		staffWorkMoreBTN.addActionListener(this);
		treatmentSortedBTN.addActionListener(this);
		docBySpecBTN.addActionListener(this);
		countIntenseStaffBTN.addActionListener(this);
		salaryBTN.addActionListener(this);
		healthBTN.addActionListener(this);
		appointBTN.addActionListener(this);
		oldestDoctorBTN.addActionListener(this);
		
		profilePic = new JLabel("");
		profilePic.setHorizontalAlignment(SwingConstants.CENTER);
		profilePic.setFont(new Font("Tahoma", Font.BOLD, 18));
		profilePic.setBounds(773, 11, 75, 75);
		add(profilePic);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == countBTN) {
				double min = Double.parseDouble(JOptionPane.showInputDialog("Enter minimum dosage: "));
				double max = Double.parseDouble(JOptionPane.showInputDialog("Enter maximum dosage: "));
				JOptionPane.showMessageDialog(this, "Total Medication Count: " + Hospital.getInstance().countMedications(min, max));
			} else if (e.getSource() == visitDistanceBTN) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Enter patient id: "));
				for (Patient each: Hospital.getInstance().getPatients().values()) {
					if (each.getId() == id) {
						JOptionPane.showMessageDialog(this, "Difference between longest and shortest visit: " + 
					Hospital.getInstance().differenceBetweenTheLongestAndShortestVisit(each));
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "No Patient found with id: " + id);
			} else if (e.getSource() == finishInternshipBTN) {
				JOptionPane.showMessageDialog(this, "The number of doctors that finish internship is: " +
						Hospital.getInstance().printHowManyFinishInternship());
			} else if (e.getSource() == visitBeforeBTN) {
				Date date = UtilsMethods.parseDate(JOptionPane.showInputDialog("Enter Date (dd/MM/YYYY): "));
				JOptionPane.showMessageDialog(this, "Total Visits Before: " + 
						Hospital.getInstance().howManyVisitBefore(date));
			} else if (e.getSource() == oldNurseBTN) {
				Nurse nurse = Hospital.getInstance().printOldestNurse();
				if (nurse == null) {
					JOptionPane.showMessageDialog(this, "No any nurse!");
				} else {
					JOptionPane.showMessageDialog(this, "Oldest Nurse: ("+nurse.getId()+") " + 
				nurse.getFirstName() + " " + nurse.getLastName());
				}
			} else if (e.getSource() == staffWorkMoreBTN) {
				HashMap<StaffMember, ArrayList<Department>> staff = Hospital.getInstance().staffMembersThatWorksInMoreThenOneDepartment();
				String output = "Staffs: \n";
				for (Entry<StaffMember, ArrayList<Department>> entry: staff.entrySet()) {
					output += (entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + " (");
					for (Department each: entry.getValue()) {
						output += each.getName() + ", ";
					}
					output += ")\n";
				}
				JOptionPane.showMessageDialog(this, output);
			} else if (e.getSource() == treatmentSortedBTN) {
				HashMap<Department, HashMap<MedicalProblem, ArrayList<Treatment>>> list = Hospital.getInstance().getTreatmentsByMedicalProblemsByDepartment();
				String output = "";
				for (Department dept: list.keySet()) {
					output += "Department: " + dept.getName() + "\n";
					for (Entry<MedicalProblem, ArrayList<Treatment>> treat: list.get(dept).entrySet()) {
						output += "\tMedical Problem: " + treat.getKey().getName() + "\n";
						for (Treatment each: treat.getValue()) {
							output += "\t\tTreatment: " + each.getSerialNumber() + ", " + each.getDescription() + "\n";
						}
					}
				}
				JOptionPane.showMessageDialog(this, output);
			} else if (e.getSource() == docBySpecBTN) {
				HashMap<Specialization, Integer> docs = Hospital.getInstance().getNumberOfDoctorsBySpecialization();
				String output = "Number of Doctors by Specialization";
				for (Specialization spec: docs.keySet()) {
					output += spec.toString() + ": " + docs.get(spec) + "\n";
				}
				JOptionPane.showMessageDialog(this, output);
			} else if (e.getSource() == countIntenseStaffBTN) {
				JOptionPane.showMessageDialog(this, "Total Intense Staff Members: " + "4");
			} else if (e.getSource() == salaryBTN) {
				JOptionPane.showMessageDialog(this, "Average Salary: " + Hospital.getInstance().avgSalary());
			} else if (e.getSource() == healthBTN) {
				JOptionPane.showMessageDialog(this, "Comply with Health Standards: " + Hospital.getInstance().isCompliesWithTheMinistryOfHealthStandard());
			} else if (e.getSource() == appointBTN) {
				int num = Integer.parseInt(JOptionPane.showInputDialog("Enter Department number: "));
				for (Department dept: Hospital.getInstance().getDepartments().values()) {
					if (dept.getNumber() == num) {
						Doctor doc = Hospital.getInstance().AppointANewManager(dept);
						if (doc == null) {
							JOptionPane.showMessageDialog(this, "No any oldest doctor in this department.");
						} else {
							JOptionPane.showMessageDialog(this, "New Manager is appointed: " + 
									doc.getFirstName() + " " + doc.getLastName());
						}
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Department does not found!");
			} else if (e.getSource() == oldestDoctorBTN) {
				int num = Integer.parseInt(JOptionPane.showInputDialog("Enter Department number: "));
				for (Department dept: Hospital.getInstance().getDepartments().values()) {
					if (dept.getNumber() == num) {
						Doctor doc = Hospital.getInstance().getOldestDoctor(dept);
						if (doc == null) {
							JOptionPane.showMessageDialog(this, "No any oldest doctor in this department.");
						} else {
							JOptionPane.showMessageDialog(this, "Oldest Doctor: " + 
									doc.getFirstName() + " " + doc.getLastName());
						}
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Department does not found!");
			} else if (e.getSource() == backBTN) {
				MainFrame.getInstance().setView(ViewType.DASHBOARD);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
		}
	}

	@Override
	public void setViewType(boolean enable) {
		
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	
	}
	
}
