package com.tap.dao;
import java.util.List;
import com.tap.model.User;

public interface UserDAO {
	int addUser(User u);
	User getUser(int id);
	void updateUser(User u);
	void deleteUser(int id);
	List<User>getAllUser();
	
}
