package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Customer {

	private final int id;
	private String name;
	private String phoneNumber;
	private String email;
	private String deliveryAddress;
	private Date dateOfFirstContact;
	private ArrayList<Order>customerOrders;
	
	
	public Customer(int id, String name, String phoneNumber, String email, String deliveryAddress,
			Date dateOfFirstContact, ArrayList<Order> customerOrders) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.deliveryAddress = deliveryAddress;
		this.dateOfFirstContact = dateOfFirstContact;
		this.customerOrders = customerOrders;
	}
	
		public Customer(int id, String name, String phoneNumber, String email, String deliveryAddress,
				Date dateOfFirstContact) {
			super();
			this.id = id;
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
			this.deliveryAddress = deliveryAddress;
			this.dateOfFirstContact = dateOfFirstContact;
			this.customerOrders = new ArrayList<>();
	}

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

		public String getDeliveryAddress() {
			return deliveryAddress;
		}

		public void setDeliveryAddress(String deliveryAddress) {
			this.deliveryAddress = deliveryAddress;
		}

		public Date getDateOfFirstContact() {
			return dateOfFirstContact;
		}

		public void setDateOfFirstContact(Date dateOfFirstContact) {
			this.dateOfFirstContact = dateOfFirstContact;
		}

		public ArrayList<Order> getCustomerOrders() {
			return customerOrders;
		}

		public void setCustomerOrders(ArrayList<Order> customerOrders) {
			this.customerOrders = customerOrders;
		}

		public int getId() {
			return id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(customerOrders, dateOfFirstContact, deliveryAddress, email, id, name, phoneNumber);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Customer other = (Customer) obj;
			return Objects.equals(customerOrders, other.customerOrders)
					&& Objects.equals(dateOfFirstContact, other.dateOfFirstContact)
					&& Objects.equals(deliveryAddress, other.deliveryAddress) && Objects.equals(email, other.email)
					&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
					&& Objects.equals(phoneNumber, other.phoneNumber);
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
					+ ", deliveryAddress=" + deliveryAddress + ", dateOfFirstContact=" + dateOfFirstContact
					+ ", customerOrders=" + customerOrders + "]";
		}
		
		
	
	
}
