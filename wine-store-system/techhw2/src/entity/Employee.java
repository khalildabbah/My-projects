package entity;

import java.util.Date;
import java.util.Objects;

public class Employee {

	
	private final int id;
	private String name;
	private String phoneNumber;
	private String email;
	private String officeAddress;
	private Date employementStartDate;
	private String department;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Date getEmployementStartDate() {
		return employementStartDate;
	}
	public void setEmployementStartDate(Date employementStartDate) {
		this.employementStartDate = employementStartDate;
	}
	public int getId() {
		return id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Employee(int id, String name, String phoneNumber, String email, String officeAddress,
			Date employementStartDate, String department) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.officeAddress = officeAddress;
		this.employementStartDate = employementStartDate;
		this.department = department;
	}
	@Override
	public int hashCode() {
		return Objects.hash(department, email, employementStartDate, id, name, officeAddress, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(employementStartDate, other.employementStartDate) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(officeAddress, other.officeAddress)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", officeAddress=" + officeAddress + ", employementStartDate=" + employementStartDate
				+ ", department=" + department + "]";
	}


	
	
	
	
}
