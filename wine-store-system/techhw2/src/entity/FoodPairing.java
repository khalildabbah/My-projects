package entity;

import java.util.ArrayList;
import java.util.Objects;

public class FoodPairing {
	
	private int id;
	private String type;
	private String dishName;
	private ArrayList<String>recipes;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public ArrayList<String> getRecipes() {
		return recipes;
	}
	public void setRecipes(ArrayList<String> recipes) {
		this.recipes = recipes;
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, dishName, id, recipes);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodPairing other = (FoodPairing) obj;
		return Objects.equals(dishName, other.dishName) && Objects.equals(type, other.type) 
				&& Objects.equals(id, other.id)
				&& Objects.equals(recipes, other.recipes);
	}
	@Override
	public String toString() {
		return dishName;
	}
	public FoodPairing(int id, String type, String dishName, ArrayList<String> recipes) {
		super();
		this.id = id;
		this.dishName = dishName;
		this.recipes = recipes;
	}
	
	public FoodPairing(int id, String type, String dishName) {
		super();
		this.id = id;
		this.type = type;
		this.dishName = dishName;
		this.recipes = new ArrayList<>();
		
	}
	

}
