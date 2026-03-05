package com.tap.daoImplementation;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.utility.DbConnection;

public class MenuDAOImpl implements MenuDAO {
	
	private String ADD_MENU="INSERT INTO `menu`(restaurantId, name, description, price, rating, imageUrl, type, isAvailable) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private String GET_MENU ="SELECT * FROM menu WHERE restaurantId = ?";

	private String UPDATE_MENU = "UPDATE menu SET name = ?,description = ?,price = ?,rating = ?,imageUrl = ?,type = ?,isAvailable = ? WHERE menuId = ?";

	private String DELETE_MENU ="DELETE FROM menu WHERE menuId = ?";
	
	private String GET_ALL_MENU ="SELECT * FROM menu";

	@Override
	public int addMenu(Menu m) {
		
		int res = 0;

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement = connection.prepareStatement(ADD_MENU)) {

		    preparedStatement.setInt(1, m.getRestaurantId());      // FK
		    preparedStatement.setString(2, m.getName());
		    preparedStatement.setString(3, m.getDescription());
		    preparedStatement.setDouble(4, m.getPrice());
		    preparedStatement.setDouble(5, m.getRating());
		    preparedStatement.setString(6, m.getImageUrl());
		    preparedStatement.setString(7, m.getType());           // VEG / NON_VEG
		    preparedStatement.setBoolean(8, m.getIsAvailable());   // boolean → tinyint

		    res = preparedStatement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return res;

	}
	@Override
	public Menu getMenuById(int menuId) {

	    Menu menu = null;

	    try (Connection connection = DbConnection.getConnection();
	         PreparedStatement preparedStatement = 
	             connection.prepareStatement("SELECT * FROM menu WHERE menuId = ?")) {

	        preparedStatement.setInt(1, menuId);

	        try (ResultSet rs = preparedStatement.executeQuery()) {

	            if (rs.next()) {

	                menu = new Menu(
	                        rs.getInt("menuId"),
	                        rs.getInt("restaurantId"),
	                        rs.getString("name"),
	                        rs.getString("description"),
	                        rs.getDouble("price"),
	                        rs.getDouble("rating"),
	                        rs.getString("imageUrl"),
	                        rs.getString("type"),
	                        rs.getBoolean("isAvailable")
	                );
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return menu;
	}

	@Override
	public List<Menu> getMenu(int restaurantId) {

	    List<Menu> menuList = new ArrayList<>();

	    try (Connection connection = DbConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU)) {

	        preparedStatement.setInt(1, restaurantId);

	        try (ResultSet rs = preparedStatement.executeQuery()) {

	            while (rs.next()) {

	                Menu m = new Menu(
	                        rs.getInt("menuId"),
	                        rs.getInt("restaurantId"),
	                        rs.getString("name"),
	                        rs.getString("description"),
	                        rs.getDouble("price"),
	                        rs.getDouble("rating"),
	                        rs.getString("imageUrl"),
	                        rs.getString("type"),
	                        rs.getBoolean("isAvailable")
	                );

	                menuList.add(m);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return menuList;
	}

	@Override
	public void updateMenu(Menu m) {
			try (Connection connection = DbConnection.getConnection();
				     PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU)) {

				    preparedStatement.setString(1, m.getName());
				    preparedStatement.setString(2, m.getDescription());
				    preparedStatement.setDouble(3, m.getPrice());
				    preparedStatement.setDouble(4, m.getRating());
				    preparedStatement.setString(5, m.getImageUrl());
				    preparedStatement.setString(6, m.getType());
				    preparedStatement.setBoolean(7, m.getIsAvailable());
				    preparedStatement.setInt(8, m.getMenuId());

				    preparedStatement.executeUpdate();

				} catch (SQLException e) {
				    e.printStackTrace();
				}
	}

	@Override
	public void deleteMenu(int id) {
		
		try (Connection connection = DbConnection.getConnection();
			     PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU)) {

			    preparedStatement.setInt(1, id);

			    preparedStatement.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace();
			}
		
	}

	@Override
	public List<Menu> getAllMenu() {
		
		List<Menu> menuList = new ArrayList<>();

		try (Connection connection = DbConnection.getConnection();
		     PreparedStatement preparedStatement =
		             connection.prepareStatement(GET_ALL_MENU)) {

		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()) {

		        Menu menu = new Menu();

		        menu.setMenuId(rs.getInt("menuId"));
		        menu.setRestaurantId(rs.getInt("restaurantId"));
		        menu.setName(rs.getString("name"));
		        menu.setDescription(rs.getString("description"));
		        menu.setPrice(rs.getDouble("price"));
		        menu.setRating(rs.getDouble("rating"));
		        menu.setImageUrl(rs.getString("imageUrl"));
		        menu.setType(rs.getString("type"));
		        menu.setIsAvailable(rs.getBoolean("isAvailable"));

		        menuList.add(menu);
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return menuList;

	}



}
