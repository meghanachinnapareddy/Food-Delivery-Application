<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.tap.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Menu - TastyTrials</title>
<link rel="stylesheet" href="popularmenu.css">
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
					href="#menu" class="nav-link active">Menu</a> <a href="login.html"
					class="nav-link">Login</a> <a href="cart.jsp" class="nav-link">Orders</a>
			</div>

			<div class="nav-right">
				<div class="nav-icons">
					<a href="cart.jsp" class="icon cart-icon"> <i
						class="fas fa-shopping-cart"></i> <span class="badge">0</span>
					</a> <a href="#" class="icon"><i class="fas fa-user"></i></a>
				</div>
			</div>
		</div>
	</nav>

	<!-- Hero Section -->
	<section class="hero">
		<div class="hero-content">
			<h1>Our Delicious Menu</h1>
			<p>Explore our wide variety of mouth-watering dishes</p>
		</div>
	</section>

	<!-- Menu Section -->
	<section class="menu-section">
		<div class="container">
			<h2>Popular Dishes</h2>

			<div class="menu-grid">
				<%
				List<Menu> allMenusByRestaurant = (List<Menu>) request.getAttribute("allMenusByRestaurant");

				// Added a Null and Empty Check to prevent the page from crashing
				if (allMenusByRestaurant != null && !allMenusByRestaurant.isEmpty()) {
					for (Menu menu : allMenusByRestaurant) {
				%>

				<!-- Menu Item Card -->
				<div class="menu-item">
					<div class="menu-image">
						<!-- FIX 1: Added '/images/' right after getContextPath() -->
						<img
							src="<%=request.getContextPath()%>/images/<%=menu.getImageUrl()%>"
							alt="<%=menu.getName()%>">
						<div class="menu-badge">BESTSELLER</div>
					</div>

					<!-- FIX 2: Removed the extra closing </div> that was here -->

					<div class="menu-info">
						<h3><%=menu.getName()%></h3>
						<p class="description"><%=menu.getDescription()%></p>

						<div class="menu-meta">
							<div class="rating">
								<i class="fas fa-star"></i> <span><%=menu.getRating()%></span>
							</div>
							<div class="price">
								₹<%=menu.getPrice()%></div>
						</div>

						<!-- The form MUST be the outermost element here -->
						<form action="cart">

							<!-- Hidden data -->
							<input type="hidden" name="itemId" value="<%=menu.getMenuId()%>">
							<input type="hidden" name="restaurantId"
								value="<%=menu.getRestaurantId()%>"> <input
								type="hidden" name="quantity" value="1"> <input
								type="hidden" name="action" value="add">

							<!-- The only button element should be inside the form -->
							<button type="submit" class="add-to-cart">
								<i class="fas fa-shopping-cart"></i> add to cart
							</button>

						</form>
						</button>
					</div>
				</div>
				<!-- End Menu Item Card (Properly closed here!) -->

				<%
				} // End of Java for-loop
				} else {
				%>
				<!-- If the list is empty, display this message instead of a blank screen -->
				<h3>No menu items found for this restaurant.</h3>
				<%
				} // End of Java if-statement
				%>
			</div>
			<!-- Closes menu-grid -->
		</div>
		<!-- Closes container -->
	</section>
	<!-- Closes menu-section -->
</body>
</html>