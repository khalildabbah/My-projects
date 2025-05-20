package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Doctor;
import model.Nurse;
import model.StaffMember;
import view.add.AddDoctor;
import view.add.AddNurse;
import view.utils.IView;
import view.utils.MainFrame;
import view.utils.Operation;
import view.utils.ViewType;

public class DashboardView extends JPanel implements ActionListener, IView {

	private JButton deptBTN;
	private JButton medProbBTN;
	private JButton medBTN;
	private JButton staffBTN;
	private JButton patientsBTN;
	private JButton treatBTN;
	private JButton visitBTN;
	private JButton logoutBTN;
	private boolean admin;
	private JLabel profilePic;

	public DashboardView() {
		
		setSize(874, 551);
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		profilePic = new JLabel("");
		profilePic.setBounds(789, 11, 75, 75);
		add(profilePic);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		panel.setBounds(20, 97, 844, 420);
		add(panel);
		panel.setLayout(new GridLayout(2, 4, 10, 10));
		
		deptBTN = new JButton("Departments");
		panel.add(deptBTN);
		deptBTN.setForeground(new Color(255, 255, 255));
		deptBTN.setBackground(new Color(0, 128, 0));
		deptBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		medProbBTN = new JButton("<html><center>Medical<br>\r\nProblems</center></html>");
		panel.add(medProbBTN);
		medProbBTN.setForeground(new Color(255, 255, 255));
		medProbBTN.setBackground(new Color(0, 128, 0));
		medProbBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		medBTN = new JButton("Medications");
		panel.add(medBTN);
		medBTN.setForeground(new Color(255, 255, 255));
		medBTN.setBackground(new Color(0, 128, 0));
		medBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		staffBTN = new JButton("<html><center>Staff<br>Members</center></html>");
		panel.add(staffBTN);
		staffBTN.setForeground(new Color(255, 255, 255));
		staffBTN.setBackground(new Color(0, 128, 0));
		staffBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		patientsBTN = new JButton("<html><center>Patients<br>");
		panel.add(patientsBTN);
		patientsBTN.setForeground(new Color(255, 255, 255));
		patientsBTN.setBackground(new Color(0, 128, 0));
		patientsBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		treatBTN = new JButton("<html><center>Treatments<br>");
		panel.add(treatBTN);
		treatBTN.setForeground(new Color(255, 255, 255));
		treatBTN.setBackground(new Color(0, 128, 0));
		treatBTN.addActionListener(this);
		treatBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		visitBTN = new JButton("<html><center>Visits<br>");
		panel.add(visitBTN);
		visitBTN.setForeground(new Color(255, 255, 255));
		visitBTN.setBackground(new Color(0, 128, 0));
		visitBTN.addActionListener(this);
		visitBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		logoutBTN = new JButton("<html><center>Logout<br>");
		panel.add(logoutBTN);
		logoutBTN.setForeground(new Color(255, 255, 255));
		logoutBTN.setBackground(new Color(255, 0, 0));
		logoutBTN.addActionListener(this);
		logoutBTN.setFont(new Font("Tahoma", Font.BOLD, 18));
		patientsBTN.addActionListener(this);
		staffBTN.addActionListener(this);
		medBTN.addActionListener(this);
		medProbBTN.addActionListener(this);
		deptBTN.addActionListener(this);
		
		JLabel title = new JLabel("Dashboard");
		title.setFont(new Font("Tahoma", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(10, 11, 755, 75);
		add(title);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deptBTN) {
			MainFrame.getInstance().setView(ViewType.DEPARTMENT_VIEW);
		} else if (e.getSource() == medProbBTN) {
			MainFrame.getInstance().setView(ViewType.MEDICAL_PROBLEM_VIEW);
		} else if (e.getSource() == medBTN) {
			MainFrame.getInstance().setView(ViewType.MEDICATION_VIEW);
		} else if (e.getSource() == staffBTN) {
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
		} else if (e.getSource() == patientsBTN) {
			MainFrame.getInstance().setView(ViewType.PATIENT_VIEW);
		} else if (e.getSource() == treatBTN) {
			MainFrame.getInstance().setView(ViewType.TREATMENT_VIEW);
		} else if (e.getSource() == visitBTN) {
			MainFrame.getInstance().setView(ViewType.VISIT_VIEW);
		} else if (e.getSource() == logoutBTN) {
			MainFrame.getInstance().setJMenuBar(null);
			MainFrame.getInstance().setView(ViewType.LOGIN);
		}
	}
	


	@Override
	public void setViewType(boolean enable) {
		this.admin = enable;
		profilePic.setIcon(new ImageIcon(MainFrame.getProfileImage()));
	}
}
