package entity;

import java.util.Objects;

public class Occasion {
	
	
	private int id;
	private String type;
	private String name;
	private String season;
	private String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Occasion(int id, String type, String name, String season, String location) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.season = season;
		this.location = location;
	}
	@Override
	public String toString() {
		return name + " " +  season + " " + location;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id,type, location, name, season);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Occasion other = (Occasion) obj;
		return Objects.equals(id, other.id) && Objects.equals(type, other.type) &&
				Objects.equals(location, other.location) &&
				Objects.equals(name, other.name) && Objects.equals(season, other.season);
	}
	
	

}
