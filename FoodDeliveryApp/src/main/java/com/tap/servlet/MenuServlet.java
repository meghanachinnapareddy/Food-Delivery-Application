package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoImplementation.MenuDAOImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Extract the restaurant ID from the URL
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		
		// UPDATED LINE: Pass the 'restaurantId' variable instead of the hardcoded '1'
		List<Menu> allMenusByRestaurant = menuDAOImpl.getMenu(restaurantId);
		
		// Print to console for backend debugging
		for(Menu menu : allMenusByRestaurant) {
			System.out.println(menu);
		}
		
		// Send the data to the JSP page
		req.setAttribute("allMenusByRestaurant", allMenusByRestaurant);
		RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	}

}