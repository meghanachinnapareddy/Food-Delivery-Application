package com.tap.daoImplementation;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.utility.DbConnection;

public class RestaurantDAOImpl implements RestaurantDAO{

	private String ADD_RESTAURANT="INSERT INTO `restaurant` (`restaurantName`,`adminId`,`address`,`cuisineType`,`rating`,`eta`,`imageUrl`,`isAvailable`) "
			+ "values(?,?,?,?,?,?,?,?)";
	
	private String GET_RESTAURANT="SELECT * FROM `restaurant` WHERE `restaurantId`=?";
	
	private String UPDATE_RESTAURANT = "UPDATE `restaurant` SET `isAvailable`=?,`address`=?,`cuisineType`=?,`rating`=?,`eta`=? WHERE `restaurantid`=?";

	private String DELETE_RESTAURANT = "DELETE FROM `restaurant` WHERE `restaurantId`=?";
	
//    String GET_ALL_RESTAURANTS = "SELECT * FROM `restaurant`";


	
	@Override
	public int addRestaurant(Restaurant r) {
		int res=0;
		
		try (Connection connection=DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(ADD_RESTAURANT);){
			
			preparedStatement.setString(1, r.getRestaurantName());
			preparedStatement.setInt(2,r.getAdminId());
			preparedStatement.setString(3,r.getAddress());
			preparedStatement.setString(4,r.getCuisineType());
			preparedStatement.setDouble(5, r.getRating());
			preparedStatement.setInt(6,r.getEta());
			preparedStatement.setString(7,r.getImageUrl());
			preparedStatement.setBoolean(8,r.getIsAvailable());
			
			res=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		Restaurant r=null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT);){
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int restaurantId = rs.getInt("restaurantId");
				String restaurantName = rs.getString("restaurantName");
				int adminId = rs.getInt("adminId");
				String address = rs.getString("address");
				String cuisineType = rs.getString("cuisineType");
				double rating=rs.getDouble("rating");
				int eta = rs.getInt("eta");
				String imageUrl = rs.getString("imageUrl");
				boolean isAvailable = rs.getBoolean("isAvailable");

			    r = new Restaurant(restaurantId,restaurantName, adminId, address, cuisineType, rating, eta, imageUrl, isAvailable);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;

	}

	@Override
	public void updateRestaurant(Restaurant r) {
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_RESTAURANT);){
			
			preparedStatement.setBoolean(1, r.getIsAvailable());
			preparedStatement.setString(2,  r.getAddress());
			preparedStatement.setString(3, r.getCuisineType());
			preparedStatement.setDouble(4,r.getRating());
			preparedStatement.setInt(5, r.getEta());
			preparedStatement.setInt(6, r.getRestaurantId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteRestaurant(int id) {

		try(Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(DELETE_RESTAURANT);){
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
	    List<Restaurant> restaurantList = new ArrayList<>();

	    String GET_ALL_RESTAURANTS = "SELECT * FROM restaurant";

	    try (Connection connection = DbConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(GET_ALL_RESTAURANTS);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Restaurant restaurant = new Restaurant();

	            // Using camelCase column names as per your table
	            restaurant.setRestaurantId(rs.getInt("restaurantId"));
	            restaurant.setRestaurantName(rs.getString("restaurantName"));
	            restaurant.setAdminId(rs.getInt("adminId"));
	            restaurant.setAddress(rs.getString("address"));
	            restaurant.setCuisineType(rs.getString("cuisineType"));
	            restaurant.setRating(rs.getDouble("rating"));
	            restaurant.setEta(rs.getInt("eta"));
	            restaurant.setImageUrl(rs.getString("imageUrl"));
	            restaurant.setIsAvailable(rs.getBoolean("isAvailable"));

	            restaurantList.add(restaurant);

	            // Debug log to confirm each row
//	            System.out.println("Fetched restaurant: " + restaurant.getRestaurantName());
	        }

//	        System.out.println("Total restaurants fetched: " + restaurantList.size());

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return restaurantList;
	}
}
