package entity;

import java.util.ArrayList;
import java.util.Objects;

public class Manufacturer {
	
	
    private int manufacturerId;
    private String name;
    private String phone;
    private String address;
    private String email;
    

    public Manufacturer() {
		super();
	}

	// Constructors, getters, and setters
    public Manufacturer(int manufacturerId, String name, String phone, String address, String email) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    public Manufacturer(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }

	@Override
	public int hashCode() {
		return Objects.hash(address, email, manufacturerId, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(manufacturerId, other.manufacturerId) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}
    
    
}
