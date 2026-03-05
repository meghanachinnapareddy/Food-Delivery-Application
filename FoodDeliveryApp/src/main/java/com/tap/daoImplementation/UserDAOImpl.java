package com.tap.daoImplementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.tap.dao.UserDAO;
import com.tap.model.User;
import com.tap.utility.DbConnection;

public class UserDAOImpl implements UserDAO{
	
	private String ADD_USER="INSERT INTO `user` (`username`,`password`,`email`,`address`,`phoneNumber`,`role`,`lastLoginDate`,`createdDate`) "
			+ "values(?,?,?,?,?,?,?,?)";
	
	private String GET_USER="SELECT * FROM `user` WHERE `userId`=?";
	
	private String UPDATE_USER = "UPDATE `user` SET `password`=?,`email`=?,`address`=?,`phoneNumber`=?,`role`=? WHERE `userid`=?";

	private String DELETE_USER = "DELETE FROM `user` WHERE `userId`=?";
	
    String GET_ALL_USERS = "SELECT * FROM user";

	@Override
	public int addUser(User u) {
		int res=0;
		
		try (Connection connection=DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(ADD_USER);){
			
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2,u.getPassword());
			preparedStatement.setString(3,u.getEmail());
			preparedStatement.setString(4,u.getAddress());
			preparedStatement.setString(5, u.getPhoneNumber());
			preparedStatement.setString(6,u.getRole());
			preparedStatement.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(8,new Timestamp(System.currentTimeMillis()));
			
			res=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public User getUser(int id) {
		
		
		User u=null;
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);){
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int userId = rs.getInt("userId");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phoneNumber=rs.getString("phoneNumber");
				String role = rs.getString("role");
				Timestamp createdDate = rs.getTimestamp("createdDate");
				Timestamp lastLoginDate = rs.getTimestamp("lastLoginDate");

				u = new User(userId,username, password, email, address,phoneNumber, role,createdDate,lastLoginDate);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public void updateUser(User u) {
		
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER);){
			
			preparedStatement.setString(1, u.getPassword());
			preparedStatement.setString(2,  u.getEmail());
			preparedStatement.setString(3, u.getAddress());
			preparedStatement.setString(4,u.getPhoneNumber());
			preparedStatement.setString(5, u.getRole());
			preparedStatement.setInt(6, u.getUserId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int id) {
		
		
		try(Connection connection = DbConnection.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER);){
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUser() {

	    List<User> userList = new ArrayList<>();


	    try(Connection connection = DbConnection.getConnection();
	    	PreparedStatement preparedStatement =
                connection.prepareStatement(GET_ALL_USERS);){
	        

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {

	            User user = new User();

	            user.setUserId(rs.getInt("userId"));
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            user.setAddress(rs.getString("address"));
	            user.setRole(rs.getString("role"));
	            user.setCreatedDate(rs.getTimestamp("createdDate"));
	            user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));

	            userList.add(user);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}
}
