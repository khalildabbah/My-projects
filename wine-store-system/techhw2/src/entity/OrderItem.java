package entity;

import java.util.Objects;

public class OrderItem {

	
	private final int orderItemNumber;
	private final int orderNumber;
	private WineBottle wine;
	private int quantity;
	
	public OrderItem(int orderItemNumber, int orderNumber, WineBottle wine, int quantity) {
		super();
		this.orderItemNumber = orderItemNumber;
		this.orderNumber = orderNumber;
		this.wine = wine;
		this.quantity = quantity;
	}


	public WineBottle getWine() {
		return wine;
	}

	public void setWine(WineBottle wine) {
		this.wine = wine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderItemNumber() {
		return orderItemNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemNumber=" + orderItemNumber + ", orderNumber=" + orderNumber + ", wine=" + wine
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderItemNumber, orderNumber, quantity, wine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(orderItemNumber, other.orderItemNumber) && Objects.equals(orderNumber, other.orderNumber)
				&& quantity == other.quantity && Objects.equals(wine, other.wine);
	}

	
	
	
	
}