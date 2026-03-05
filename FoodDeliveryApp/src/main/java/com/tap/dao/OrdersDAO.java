package com.tap.dao;

import java.util.List;

import com.tap.model.Orders;

public interface OrdersDAO {
	int addOrders(Orders o);
	Orders getOrders(int id);
	void updateOrders(Orders o);
	void deleteOrders(int id);
	List<Orders>getAllOrders();


}
