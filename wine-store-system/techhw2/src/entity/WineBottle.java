package entity;

import java.util.Objects;

public class WineBottle {

	private String wineSerialNum;
	private String catalogNumber;
	private int manufacturerID;
	private String name;
	private String description;
	private int productionYear;
	private double pricePerBottle;
	private String sweetnessLevel;
	private String productImage;
	private WineType type;
	
	
	
	
	public WineBottle() {
		super();
	}
	
	


	public WineBottle(String wineSerialNum, String catalogNumber, int manufacturerID, String name, String description,
			int productionYear, double pricePerBottle, String sweetnessLevel, String productImage, WineType type) {
		super();
		this.wineSerialNum = wineSerialNum;
		this.catalogNumber = catalogNumber;
		this.manufacturerID = manufacturerID;
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		this.productImage = productImage;
		this.type = type;
	}




	public WineBottle(String catalogNumber, int manufacturerID, String name, String description, int productionYear,
			double pricePerBottle, String sweetnessLevel, String productImage, WineType type) {
		super();
		this.catalogNumber = catalogNumber;
		this.manufacturerID = manufacturerID;
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		this.productImage = productImage;
		this.type = type;
		this.wineSerialNum = manufacturerID + "-" + catalogNumber;
	}
	
	public WineBottle(String catalogNumber, int manufacturerID, String name, String description, int productionYear,
			double pricePerBottle, String sweetnessLevel, WineType type) {
		super();
		this.catalogNumber = catalogNumber;
		this.manufacturerID = manufacturerID;
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		this.type = type;
		this.wineSerialNum = manufacturerID + "-" + catalogNumber;
	}

	public WineBottle(String catalogNumber, int manufacturerID, String name, String description, int productionYear,
			double pricePerBottle, String sweetnessLevel) {
		super();
		this.catalogNumber = catalogNumber;
		this.manufacturerID = manufacturerID;
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		this.productImage = productImage;
		this.wineSerialNum = manufacturerID + "-" + catalogNumber;
	}
	
	

	public String getWineSerialNum() {
		return wineSerialNum;
	}




	public void setWineSerialNum(String wineSerialNum) {
		this.wineSerialNum = wineSerialNum;
	}




	public String getCatalogNumber() {
		return catalogNumber;
	}


	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}


	public int getManufacturerID() {
		return manufacturerID;
	}


	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getProductionYear() {
		return productionYear;
	}


	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}


	public double getPricePerBottle() {
		return pricePerBottle;
	}


	public void setPricePerBottle(double pricePerBottle) {
		this.pricePerBottle = pricePerBottle;
	}


	public String getSweetnessLevel() {
		return sweetnessLevel;
	}


	public void setSweetnessLevel(String sweetnessLevel) {
		this.sweetnessLevel = sweetnessLevel;
	}


	public String getProductImage() {
		return productImage;
	}


	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}


	public WineType getType() {
		return type;
	}


	public void setType(WineType type) {
		this.type = type;
	}


	


	@Override
	public String toString() {
		return name + "-" + catalogNumber;
	}




	@Override
	public int hashCode() {
		return Objects.hash(catalogNumber, description, manufacturerID, name, pricePerBottle, productImage,
				productionYear, sweetnessLevel, type, wineSerialNum);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WineBottle other = (WineBottle) obj;
		return Objects.equals(catalogNumber, other.catalogNumber) && Objects.equals(description, other.description)
				&& manufacturerID == other.manufacturerID && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(pricePerBottle) == Double.doubleToLongBits(other.pricePerBottle)
				&& Objects.equals(productImage, other.productImage) && productionYear == other.productionYear
				&& Objects.equals(sweetnessLevel, other.sweetnessLevel) && Objects.equals(type, other.type)
				&& Objects.equals(wineSerialNum, other.wineSerialNum);
	}



	
	
}
