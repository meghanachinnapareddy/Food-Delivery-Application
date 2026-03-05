package com.tap.daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrdersDAO;
import com.tap.model.Orders;
import com.tap.utility.DbConnection;

public class OrdersDAOImpl implements OrdersDAO{
	private String ADD_ORDER =
			"INSERT INTO orders (userId, restaurantId, totalAmount, orderDate, address, paymentMethod, status) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private String GET_ORDER =
			"SELECT * FROM orders WHERE orderId = ?";

	private String UPDATE_ORDER =
			"UPDATE orders SET userId = ?, restaurantId = ?, totalAmount = ?, orderDate = ?, "
					+ "address = ?, paymentMethod = ?, status = ? WHERE orderId = ?";

	private String DELETE_ORDER =
			"DELETE FROM orders WHERE orderId = ?";

	private String GET_ALL_ORDERS =
			"SELECT * FROM orders";

	@Override
	public int addOrders(Orders o) {
		int orderId = 0;

		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER,Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, o.getUserId());             // FK → user
			preparedStatement.setInt(2, o.getRestaurantId());       // FK → restaurant
			preparedStatement.setDouble(3, o.getTotalAmount());     // bill amount
			preparedStatement.setTimestamp(4, o.getOrderDate());    // Timestamp
			preparedStatement.setString(5, o.getAddress());
			preparedStatement.setString(6, o.getPaymentMethod());   // CASH / UPI / CARD
			preparedStatement.setString(7, o.getStatus());          // PLACED / DELIVERED

//			res = preparedStatement.executeUpdate();
			int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding order", e);
        }
        return orderId;
    }

	@Override
	public Orders getOrders(int id) {
		Orders o = null;

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {

		    preparedStatement.setInt(1, id);   // orderId

		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()) {

		        int orderId = rs.getInt("orderId");
		        int userId = rs.getInt("userId");
		        int restaurantId = rs.getInt("restaurantId");
		        double totalAmount = rs.getDouble("totalAmount");
		        Timestamp orderDate = rs.getTimestamp("orderDate");
		        String address = rs.getString("address");
		        String paymentMethod = rs.getString("paymentMethod");
		        String status = rs.getString("status");

		        o = new Orders(orderId, userId, restaurantId,
		                       totalAmount, orderDate,
		                       address, paymentMethod, status);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return o;

	}

	@Override
	public void updateOrders(Orders o) {
		try (Connection connection = DbConnection.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {

			    preparedStatement.setInt(1, o.getUserId());
			    preparedStatement.setInt(2, o.getRestaurantId());
			    preparedStatement.setDouble(3, o.getTotalAmount());
			    preparedStatement.setTimestamp(4, o.getOrderDate());
			    preparedStatement.setString(5, o.getAddress());
			    preparedStatement.setString(6, o.getPaymentMethod());
			    preparedStatement.setString(7, o.getStatus());
			    preparedStatement.setInt(8, o.getOrderId());

			    preparedStatement.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}

	}

	@Override
	public void deleteOrders(int id) {
		try (Connection connection = DbConnection.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

			    preparedStatement.setInt(1, id);   // orderId

			    preparedStatement.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}

	}

	@Override
	public List<Orders> getAllOrders() {
		
		List<Orders> ordersList = new ArrayList<>();

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement =
		             connection.prepareStatement(GET_ALL_ORDERS)) {

		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()) {

		        Orders order = new Orders();

		        order.setOrderId(rs.getInt("orderId"));
		        order.setUserId(rs.getInt("userId"));
		        order.setRestaurantId(rs.getInt("restaurantId"));
		        order.setTotalAmount(rs.getDouble("totalAmount"));
		        order.setOrderDate(rs.getTimestamp("orderDate"));
		        order.setAddress(rs.getString("address"));
		        order.setPaymentMethod(rs.getString("paymentMethod"));
		        order.setStatus(rs.getString("status"));

		        ordersList.add(order);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return ordersList;

		
	}



}
