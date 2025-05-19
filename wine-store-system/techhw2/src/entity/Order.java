package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order {
	
	private final int orderNumber;
	private String orderType;
	private Date orderDate;
	private String currentStatus;
	private Date shipmentDate;
	private int employeeId;
	private int customerId;
	private int priorityLevel;
	private Date specificDeliveryDate;
	
	private ArrayList<OrderItem>orderItems;
	
	
	public Order(int orderNumber, String orderType, Date orderDate, String currentStatus, Date shipmentDate,
			int employeeId, int customerId, ArrayList<OrderItem> orderItems) {
		super();
		this.orderNumber = orderNumber;
		this.orderType = orderType;
		this.orderDate = orderDate;
		this.currentStatus = currentStatus;
		this.shipmentDate = shipmentDate;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.orderItems = orderItems;
	}
	
	public Order(int orderNumber, String orderType, Date orderDate, String currentStatus, Date shipmentDate,
			int employeeId, int customerId) {
		super();
		this.orderNumber = orderNumber;
		this.orderType = orderType;
		this.orderDate = orderDate;
		this.currentStatus = currentStatus;
		this.shipmentDate = shipmentDate;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.orderItems = new ArrayList<>();
	}


	
	public Order(int orderNumber, String orderType, Date orderDate, String currentStatus, Date shipmentDate,
			int employeeId, int customerId, int priority, Date specificDeliveryDate) {
		super();
		this.orderNumber = orderNumber;
		this.orderType = orderType;
		this.orderDate = orderDate;
		this.currentStatus = currentStatus;
		this.shipmentDate = shipmentDate;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.priorityLevel = priority;
		this.specificDeliveryDate = specificDeliveryDate;
	}

	
	
	public Order(int orderNumber, String orderType, Date orderDate, String currentStatus, Date shipmentDate,
			int employeeId, int customerId, ArrayList<OrderItem> orderItems ,int priority, Date specificDeliveryDate) {
		super();
		this.orderNumber = orderNumber;
		this.orderType = orderType;
		this.orderDate = orderDate;
		this.currentStatus = currentStatus;
		this.shipmentDate = shipmentDate;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.priorityLevel = priority;
		this.specificDeliveryDate = specificDeliveryDate;
		this.orderItems = orderItems;
	}

	public int getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(int priority) {
		this.priorityLevel = priority;
	}

	public Date getSpecificDeliveryDate() {
		return specificDeliveryDate;
	}

	public void setSpecificDeliveryDate(Date specificDeliveryDate) {
		this.specificDeliveryDate = specificDeliveryDate;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", orderType=" + orderType + ", orderDate=" + orderDate
				+ ", currentStatus=" + currentStatus + ", shipmentDate=" + shipmentDate + ", employeeId=" + employeeId
				+ ", customerId=" + customerId + ", orderItems=" + orderItems + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentStatus, customerId, employeeId, orderDate, orderItems, orderNumber, orderType,
				shipmentDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(currentStatus, other.currentStatus) && customerId == other.customerId
				&& employeeId == other.employeeId && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderItems, other.orderItems) && Objects.equals(orderNumber, other.orderNumber)
				&& Objects.equals(orderType, other.orderType) && Objects.equals(shipmentDate, other.shipmentDate);
	}	
	
	
}
