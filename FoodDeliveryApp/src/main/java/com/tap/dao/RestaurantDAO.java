package com.tap.dao;

import java.util.List;

import com.tap.model.Restaurant;

public interface RestaurantDAO {
	
	int addRestaurant(Restaurant r);
	Restaurant getRestaurant(int id);
	void updateRestaurant(Restaurant r);
	void deleteRestaurant(int id);
	List<Restaurant>getAllRestaurant();
}
