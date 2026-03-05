package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;
import com.tap.utility.DbConnection;

public class OrderItemDAOImpl implements OrderItemDAO{
	
	private String ADD_ORDER_ITEM =
		    "INSERT INTO orderitem (orderId, menuId, orderItemName, quantity, price, totalPrice) "
		    + "VALUES (?, ?, ?, ?, ?, ?)";


	private String GET_ORDER_ITEM =
	        "SELECT * FROM orderitem WHERE orderItemId = ?";

	private String UPDATE_ORDER_ITEM =
	        "UPDATE orderitem SET orderId = ?, orderItemName = ?, quantity = ?, totalPrice = ? "
	                + "WHERE orderItemId = ?";

	private String DELETE_ORDER_ITEM =
	        "DELETE FROM orderitem WHERE orderItemId = ?";

	private String GET_ALL_ORDER_ITEMS =
	        "SELECT * FROM orderitem";


	@Override
	public int addOrderItem(OrderItem oi) {
		
		int res = 0;

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER_ITEM)) {

			preparedStatement.setInt(1, oi.getOrderId());
			preparedStatement.setInt(2, oi.getMenuId());
			preparedStatement.setString(3, oi.getOrderItemName());
			preparedStatement.setInt(4, oi.getQuantity());
			preparedStatement.setDouble(5, oi.getPrice());       // <-- must set this
			preparedStatement.setDouble(6, oi.getTotalPrice());


		    res = preparedStatement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return res;


	}

	@Override
	public OrderItem getOrderItem(int id) {
	    OrderItem oi = null;

	    try (Connection connection = DbConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEM)) {

	        preparedStatement.setInt(1, id);   // orderItemId

	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) { // use if, since only one item expected
	            int orderItemId = rs.getInt("orderItemId");
	            int orderId = rs.getInt("orderId");
	            int menuId=rs.getInt("menuId");
	            String orderItemName = rs.getString("orderItemName");
	            int quantity = rs.getInt("quantity");
	            int price=rs.getInt("price");
	            int totalPrice = rs.getInt("totalPrice");

	            oi = new OrderItem(orderItemId, orderId,menuId, orderItemName, quantity,price, totalPrice);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return oi;
	}


	@Override
	public void updateOrderItem(OrderItem oi) {
		
		try (Connection connection = DbConnection.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM )) {

			    // Set parameters based on your OrderItem object 'oi'
			    preparedStatement.setInt(1, oi.getOrderId());              // orderId
			    preparedStatement.setString(2, oi.getOrderItemName());     // itemName
			    preparedStatement.setInt(3, oi.getQuantity());             // quantity
			    preparedStatement.setDouble(4, oi.getTotalPrice());        // totalPrice
			    preparedStatement.setInt(5, oi.getOrderItemId());          // WHERE orderItemId = ?

			    preparedStatement.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}

	}

	@Override
	public void deleteOrderItem(int id) {
		try (Connection connection = DbConnection.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM)) {

			    preparedStatement.setInt(1, id);   // orderItemId to delete

			    preparedStatement.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}

	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> orderItemsList = new ArrayList<>();

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDER_ITEMS)) {

		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()) {

		        OrderItem oi = new OrderItem();

		        oi.setOrderItemId(rs.getInt("orderItemId"));
		        oi.setOrderId(rs.getInt("orderId"));
		        oi.setMenuId(rs.getInt("menuId"));
		        oi.setOrderItemName(rs.getString("orderItemName")); // or "orderItemName" depending on DB column
		        oi.setQuantity(rs.getInt("quantity"));
		        oi.setPrice(rs.getInt("price"));
		        oi.setTotalPrice(rs.getInt("totalPrice"));

		        orderItemsList.add(oi);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return orderItemsList;

	}

}

