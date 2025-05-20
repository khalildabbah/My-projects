package view.utils;

import java.util.ArrayList;
import java.util.List;

import control.Hospital;
import model.Doctor;
import model.MedicalProblem;
import model.Patient;
import model.StaffMember;
import model.Treatment;
import view.FindView;

public class Finder {

	public static void patients(Callback callback) {
		List<String[]> data = new ArrayList<>();
		List<Patient> patients = new ArrayList<>();
		for (Patient each : Hospital.getInstance().getPatients().values()) {
			data.add(each.getTableValues());
			patients.add(each);
		}
		call(callback, "Patient", Patient.getTableNames(), data, patients);
	}

	public static void medicalProblems(Callback callback) {
		List<String[]> data = new ArrayList<>();
		List<MedicalProblem> problems = new ArrayList<>();
		for (MedicalProblem each : Hospital.getInstance().getMedicalProblems().values()) {
			data.add(each.getTableData());
			problems.add(each);
		}
		call(callback, "Medical Problems", MedicalProblem.getTableNames(), data, problems);
	}

	public static void staffMembers(Callback callback) {
		List<String[]> data = new ArrayList<>();
		List<StaffMember> list = new ArrayList<>();
		for (StaffMember each : Hospital.getInstance().getStaffMembers().values()) {
			data.add(new String[] { (each instanceof Doctor ? "Doctor" : "Nurse"), each.getFirstName(),
					each.getLastName(), each.getPhoneNumber(), each.getEmail() });
			list.add(each);
		}
		call(callback, "Staff", new String[] { "Type", "First Name", "Last Name", "Phone Number", "Email" }, data,
				list);
	}
	
	public static void treatments(Callback callback) {
		List<String[]> data = new ArrayList<>();
		List<Treatment> list = new ArrayList<>();
		for (Treatment each : Hospital.getInstance().getTreatments().values()) {
			data.add(each.getTableValues());
			list.add(each);
		}
		call(callback, "Treatments", Treatment.getTableNames(), data, list);
	}

	private static void call(Callback callback, String name, String[] names, List<String[]> data,
			List<? extends Object> list) {
		(new FindView(name, new Callback() {
			@Override
			public void callback(Object data) {
				callback.callback(data);
			}
		}, names, data.toArray(new String[][] {}), list)).setVisible(true);
	}

}
