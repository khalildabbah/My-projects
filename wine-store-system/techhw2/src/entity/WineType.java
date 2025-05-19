package entity;

import java.util.ArrayList;
import java.util.Objects;

public class WineType {
	
	private String wineTypeSerial;
	private String name;
	private String serialNumber;
	private ArrayList<Occasion>occasions;
	private ArrayList<FoodPairing>foodPairings;
	
	
	public WineType(String WineTypeSerial,  String name,String serialNumber, ArrayList<Occasion> occasions,
			ArrayList<FoodPairing> foodPairings) {
		
		super();
		this.wineTypeSerial =WineTypeSerial;
		this.name = name;
		this.serialNumber = serialNumber;
		this.occasions = occasions;
		this.foodPairings = foodPairings;
	}
	
	public WineType(String WineTypeSerial, String name,String serialNumber) {
		super();
		this.wineTypeSerial =WineTypeSerial;
		this.name = name;
		this.serialNumber = serialNumber;
		this.occasions = new ArrayList<>();
		this.foodPairings = new ArrayList<>();
	}


	
	

	@Override
	public int hashCode() {
		return Objects.hash(wineTypeSerial, foodPairings, name, occasions, serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WineType other = (WineType) obj;
		return Objects.equals(wineTypeSerial, other.wineTypeSerial) && Objects.equals(foodPairings, other.foodPairings)
				&& Objects.equals(name, other.name) && Objects.equals(occasions, other.occasions)
				&& Objects.equals(serialNumber, other.serialNumber);
	}
	
	

	@Override
	public String toString() {
		return name;
	}

	public String getWineTypeSerial() {
		return wineTypeSerial;
	}

	public void setWineTypeSerial(String wineTypeSerial) {
		wineTypeSerial = wineTypeSerial;
	}

	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Occasion> getOccasions() {
		return occasions;
	}


	public void setOccasions(ArrayList<Occasion> occasions) {
		this.occasions = occasions;
	}


	public ArrayList<FoodPairing> getFoodPairings() {
		return foodPairings;
	}


	public void setFoodPairings(ArrayList<FoodPairing> foodPairings) {
		this.foodPairings = foodPairings;
	}
	
	

}
