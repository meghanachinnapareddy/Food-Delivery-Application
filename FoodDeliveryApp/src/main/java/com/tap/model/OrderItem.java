package com.tap.model;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int menuId;
	private String orderItemName;
	private int quantity;
	private int price;
	private double totalPrice;


	public OrderItem() {

	}

	public OrderItem(int orderItemId, int orderId, int menuId,String orderItemName, int quantity, int price, int totalPrice) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.orderItemName=orderItemName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	

	public OrderItem(int orderItemId, int orderId,String orderItemName, int quantity,int totalPrice) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.orderItemName = orderItemName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}


	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public String getOrderItemName() {
		return orderItemName;
	}

	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderItem[orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId
				+ ", orderItemName=" + orderItemName + ", quantity=" + quantity + ", price=" + price + ", totalPrice="
				+ totalPrice + "]";
	}

}