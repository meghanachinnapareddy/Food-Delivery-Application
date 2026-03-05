package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoImplementation.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		List<Restaurant> allRestaurant = restaurantDAOImpl.getAllRestaurant();
		for(Restaurant restaurant:allRestaurant) {
			System.out.println(restaurant);
		}
		
		req.setAttribute("allRestaurant", allRestaurant);
		RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
		rd.forward(req, resp);
		
		
	}
}
