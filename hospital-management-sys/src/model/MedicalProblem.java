package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;

public abstract class MedicalProblem implements Serializable {
	
	private static int SERIAL_NUMBER =1;//static parameter for counting Medical Problems
	private String code;//PK
	private String name;
	private Department department;
	private HashSet<Treatment> treatmentsList;
	
	public String[] getTableData() {
		String type = "Disease";
		if (this instanceof Fracture) {
			type = "Fracture";
		} else if (this instanceof Injury) {
			type = "Injury";
		}
		return new String[] { type, code, name};
	}
	
	public static String[] getTableNames() {
		return new String[] {"Type", "Code", "Name"};
	}
	
	//constructors
	public MedicalProblem(String type,String name, Department department,
			HashSet<Treatment> treatmentsList) {
		super();
		setCode(type);
		this.name = name;
		this.department = department;
		this.treatmentsList = treatmentsList;
	}
	
	public MedicalProblem(String type, String name, Department department) {
		super();
		setCode(type);
		this.name = name;
		this.department = department;
		this.treatmentsList = new HashSet<Treatment>();
	}
	
	//getters
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}


	public Department getDepartment() {
		return department;
	}

	public HashSet<Treatment> getTreatmentsList() {
		return treatmentsList;
	}
	
	//setters
	private void setCode(String type) {
		//set the code to the be a letters that indicates he type and to running number
		this.code=type+SERIAL_NUMBER;
		SERIAL_NUMBER++;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setTreatmentsList(HashSet<Treatment> treatmentsList) {
		this.treatmentsList = treatmentsList;
	}

	//hashCode and equals according to the PK
	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalProblem other = (MedicalProblem) obj;
		return Objects.equals(code, other.code);
	}

	//toString
	@Override
	public String toString() {
		return "code=" + code + ", name=" + name + ", department=" + department;
	}

	//add
	public boolean addTreatment(Treatment treatment) {
		if(treatment==null) {
			throw new NullPointerException();
		}
		if (treatmentsList.contains(treatment)) {
			throw new ObjectAlreadyExistsException(treatment, this);
		}
		return treatmentsList.add(treatment);
	}
	
	//remove
	public boolean removeTreatment(Treatment treatment) {
		if(treatment==null) {
			throw new NullPointerException();
		}
		if (!treatmentsList.contains(treatment)) {
			throw new ObjectDoesNotExist(treatment.getSerialNumber(), treatment.getClass().getSimpleName(), this);
		}
		return treatmentsList.remove(treatment);
	}
	
	public abstract void describeSpecialProperties();
	
	
}
