package model;

import java.util.Date;
import java.util.HashSet;

import control.Hospital;
import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectDoesNotExist;
import utils.UtilsMethods;

public abstract class StaffMember extends Person {
	
	private Date workStartDate;
	private HashSet<Department> departments;
	private double salary;
	private String password;
	private String profilePicture;

	//Constructors
	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,
			HashSet<Department> departments, double salary, String password) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = departments;
		this.salary = salary;
		this.password = password;
	}
	
	public String[] getTableData() {
		return new String[] {
				(this instanceof Doctor ? "Doctor" : "Nurse"), 
				getFirstName() + " " + getLastName(),
				getAddress(), getEmail(), getGender()
		};
	}
	
	public static String[] getTableNames() {
		return new String[] {"Type", "Name",
				"Address", "Email", "Gender"};
	}
	
	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,
			 double salary, String password) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = new HashSet<Department>();
		this.salary = salary;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	//getters
	public Date getWorkStartDate() {
		return workStartDate;
	}

	public HashSet<Department> getDepartments() {
		return departments;
	}

	public double getSalary() {
		return salary;
	}
	
	public double getWorkTime() {
		//returns the WorkTime of the StaffMember, in days
		return UtilsMethods.dateDiffInDays(Hospital.TODAY, workStartDate);
	}

	//setters
	public void setWorkStartDate(Date workStartDate) {
		//if the workStartDate is after "today", it set to "today"
		if(workStartDate.after(Hospital.TODAY)) {
			throw new FutureDateException(workStartDate);
		}
		this.workStartDate = workStartDate;
	}

	public void setDepartments(HashSet<Department> departments) {
		this.departments = departments;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setProfilePicture(String pic) {
		this.profilePicture = pic;
	}
	
	public String getProfilePicture() {
		return this.profilePicture;
	}
	
	//add
	public boolean addDepartment(Department department) {
		if(department==null) {
			throw new NullPointerException();
		}
		if(departments.contains(department)) {
			throw new ObjectAlreadyExistsException(department, this);

		}
			return departments.add(department);

	}
	
	//remove
	public boolean removeDepartment(Department department) {
		if(department==null) {
			throw new NullPointerException();
		}
		if(!departments.contains(department)) {
			throw new ObjectDoesNotExist(department.getNumber(), department.getClass().getSimpleName(), this);

		}
			return departments.remove(department);

	}

	//toString based on the super.toString()
	@Override
	public String toString() {
		return super.toString()+", workStartDate=" + workStartDate  +
				", salary=" + salary
				;
	}
	
	public Department getDepartmentBySpecialization(Specialization specialization) {
		for(Department department:departments) {
			if (department.getSpecialization().equals(specialization)) {
				return department;
			}
		}
		return null;
	}
	
	
	
}
