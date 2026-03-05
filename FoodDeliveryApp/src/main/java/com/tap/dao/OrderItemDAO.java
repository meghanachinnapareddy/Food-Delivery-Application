package com.tap.dao;

import java.util.List;

import com.tap.model.OrderItem;

public interface OrderItemDAO {

	int addOrderItem(OrderItem oi);
	OrderItem getOrderItem(int id);
	void updateOrderItem(OrderItem oi);
	void deleteOrderItem(int id);
	List<OrderItem> getAllOrderItems();
}
