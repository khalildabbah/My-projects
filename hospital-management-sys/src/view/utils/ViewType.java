package view.utils;

import javax.swing.JPanel;

import view.DashboardView;
import view.DepartmentView;
import view.LoginView;
import view.MedicalProblemView;
import view.MedicationView;
import view.PatientView;
import view.QueryView;
import view.StaffMemberView;
import view.TreatmentView;
import view.VisitView;

public enum ViewType {

	LOGIN(new LoginView()),
	DASHBOARD(new DashboardView()),
	DEPARTMENT_VIEW(new DepartmentView()),
	MEDICAL_PROBLEM_VIEW(new MedicalProblemView()),
	MEDICATION_VIEW(new MedicationView()),
	STAFF_MEMBER_VIEW(new StaffMemberView()),
	PATIENT_VIEW(new PatientView()),
	QUERY_VIEW(new QueryView()),
	TREATMENT_VIEW(new TreatmentView()),
	VISIT_VIEW(new VisitView());
	
	private JPanel panel;
	
	ViewType (JPanel panel){
		this.panel = panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
}
