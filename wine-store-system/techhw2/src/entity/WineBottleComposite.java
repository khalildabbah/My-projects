package entity;

import java.util.Objects;

public class WineBottleComposite {
	
	private String wineBottleSerial;
	private int manufacturerID;
	private String catalogNumber;
	
	public WineBottleComposite(String wineBottleSerial, int manufacturerID, String catalogNumber) {
		super();
		this.wineBottleSerial = wineBottleSerial;
		this.manufacturerID = manufacturerID;
		this.catalogNumber = catalogNumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(catalogNumber, manufacturerID, wineBottleSerial);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WineBottleComposite other = (WineBottleComposite) obj;
		return Objects.equals(catalogNumber, other.catalogNumber) && manufacturerID == other.manufacturerID
				&& Objects.equals(getWineBottleSerial(), other.getWineBottleSerial());
	}
	@Override
	public String toString() {
		return "WineBottleComposite [WineBottleSerial=" + wineBottleSerial + ", ManufacturerID=" + manufacturerID
				+ ", CatalogNumber=" + catalogNumber + "]";
	}
	public String getWineBottleSerial() {
		return wineBottleSerial;
	}
	public void setWineBottleSerial(String wineBottleSerial) {
		wineBottleSerial = wineBottleSerial;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(int manufacturerID) {
		manufacturerID = manufacturerID;
	}
	public String getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(String catalogNumber) {
		catalogNumber = catalogNumber;
	}
	
	

}
