package com.tap.model;

import java.sql.Timestamp;

public class Orders {
	
	 private int orderId;
	    private int userId;
	    private int restaurantId;
	    private double totalAmount;
	    private Timestamp orderDate;
	    private String address;
	    private String paymentMethod;
	    private String status;
	    
	    public Orders() {
	    	
	    }

		public Orders(int orderId, int userId, int restaurantId, double totalAmount, Timestamp orderDate,
				String address, String paymentMethod, String status) {
			super();
			this.orderId = orderId;
			this.userId = userId;
			this.restaurantId = restaurantId;
			this.totalAmount = totalAmount;
			this.orderDate = orderDate;
			this.address = address;
			this.paymentMethod = paymentMethod;
			this.status = status;
		}
		
		public Orders(int userId,int restaurantId,double totalAmount,
               Timestamp orderDate, String address,String paymentMethod,String status) {
			super();
			this.userId = userId;
			this.restaurantId = restaurantId;
			this.totalAmount = totalAmount;
			this.orderDate = orderDate;
			this.address = address;
			this.paymentMethod = paymentMethod;
			this.status = status;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(int restaurantId) {
			this.restaurantId = restaurantId;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public Timestamp getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Timestamp orderDate) {
			this.orderDate = orderDate;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPaymentMethod() {
			return paymentMethod;
		}

		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "Orders [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId
					+ ", totalAmount=" + totalAmount + ", orderDate=" + orderDate + ", address=" + address
					+ ", paymentMethod=" + paymentMethod + ", status=" + status + "]";
		}
	    

}
