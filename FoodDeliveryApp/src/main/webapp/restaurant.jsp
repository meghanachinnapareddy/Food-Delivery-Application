<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurants - ZUZU EATS</title>
<link rel="stylesheet" href="res.css">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<!-- Navigation Bar -->
	<nav class="navbar">
		<div class="nav-container">
			<div class="nav-left">
				<div class="logo">
					<img src="images/appicon.jfif" alt="TastyTrials Logo"
						class="logo-image"> <span class="logo-text">TastyTrials</span>
				</div>
			</div>

			<div class="nav-center">
				<a href="restaurant" class="nav-link">Restaurants</a> <a
					href="login.html" class="nav-link">Login</a> <a href="register.html"
					class="nav-link">Sign Up</a> <a href="cart.jsp" class="nav-link">Orders</a>
			</div>

			<div class="nav-right">
				<div class="nav-icons">
					<a href="cart.jsp" class="icon cart-icon">
                <i class="fas fa-shopping-cart"></i>
                <span class="badge">0</span>
            </a>
            <a href="#" class="icon"><i class="fas fa-user"></i></a>
				</div>
			</div>
		</div>
	</nav>

	<!-- Hero Section -->
	<section class="hero">
		<div class="hero-content">
			<h1>Find Best Restaurants</h1>
			<p>Discover amazing food from top restaurants</p>
		</div>
	</section>

	<!-- Restaurants Section -->
	<section class="restaurants">
		<div class="container">
			<h2>All Restaurants</h2>
			

			<div class="restaurant-grid">
				<!-- Java Loop Starts Here -->
				<%
                List<Restaurant> allRestaurant = (List<Restaurant>) request.getAttribute("allRestaurant");
                if (allRestaurant != null) {
                    for(Restaurant restaurant : allRestaurant) {
                %>
                
				<div class="restaurant-card">
					<!-- Make the whole card clickable and remove purple text -->
					<a href="menu?restaurantId=<%=restaurant.getRestaurantId()%>">
						
						<div class="restaurant-image">
							<!-- Dynamically fetch the image name from database -->
							<img src="images/<%= restaurant.getImageUrl() %>"
								alt="<%= restaurant.getRestaurantName() %>">
							<div class="restaurant-badge"><%= restaurant.getCuisineType().toUpperCase() %></div>
							<div class="restaurant-rating">
								<i class="fas fa-star"></i>
								<%= restaurant.getRating() %>
							</div>
						</div>

						<div class="restaurant-info">
							<!-- Dynamically fetch Name, Cuisine, Address, and ETA -->
							<h3><%= restaurant.getRestaurantName() %></h3>
							<p class="cuisine"><%= restaurant.getCuisineType() %></p>
							<p class="address"><%= restaurant.getAddress() %></p>
							
							<div class="restaurant-meta">
								<span class="delivery-time"><%= restaurant.getEta() %> min</span>
								<span class="price">₹300</span>
								
								<div class="restaurant-status"> 
									<!-- Dynamic Status Check -->
									<% if (restaurant.getIsAvailable()) { %>
										<span class="status open">Open Now</span>
									<% } else { %>
										<span class="status closed">Closed</span>
									<% } %>
								</div>
							</div>
						</div>
						
					</a> <!-- Closes the anchor tag properly inside the card -->
				</div> <!-- Closes the restaurant-card div -->

				<%
                    } // Ends for-loop
                } // Ends if-statement
                %>
			</div>
			<!-- Closes restaurant-grid -->
		</div>
		<!-- Closes container -->
	</section>
	<!-- Closes section -->

</body>
</html>