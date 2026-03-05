<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.CartItem"%>
<%@ page import="com.tap.model.Cart"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart | FoodDeliveryApp</title>

<!-- Responsive -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Google Font (Optional - Modern Look) -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">

<!-- Cart CSS File -->
<link rel="stylesheet" href="cartItems.css">
</head>
<body>
	<h1>Your Cart</h1>
	<div class="cart-container">
		<%
        Cart cart = (Cart) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

        if (cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) {
        %>
		<div class="cart-item">
			<div class="cart-item-details">
				<h3><%=item.getName()%></h3>
				<p>
					Price: ₹<%=item.getPrice()%>
				</p>
				<p>
					Total: ₹<%=item.getTotalPrice()%>
				</p>
			</div>

			<div class="quantity-controls">
				<form action="cart" style="display: inline;">
					<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="restaurantId" value="<%=restaurantId%>">
					<input type="hidden" name="quantity"
						value="<%=item.getQuantity() + 1%>">
					<button class="quantity-btn">+</button>
				</form>

				<p><%=item.getQuantity()%></p>

				<form action="cart" style="display: inline;">
					<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="restaurantId" value="<%=restaurantId%>">

					<input type="hidden" name="quantity"
						value="<%=item.getQuantity() - 1%>">
					<button class="quantity-btn" <%if (item.getQuantity() == 1) {%>
						disabled <%}%>>-</button>
				</form>
			</div>

			<form action="cart">
				<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
				<input type="hidden" name="restaurantId"
					value="<%=restaurantId%>">
				<!-- Updated -->
				<input type="hidden" name="action" value="remove">
				<button class="remove-btn">Remove</button>
			</form>
		</div>
		<%
            }
        %>

		<div class="total">
			Grand Total: ₹<%=cart.getTotalPrice()%>
		</div>

		<div class="add-more-items">
			<a
				href="menu?restaurantId=<%=session.getAttribute("oldRestaurantId")%>"
				class="btn add-more-items-btn"> Add More Items</a>
		</div>

		<%
        } else {
        %>
		<p style="text-align: center; color: #757575;">Your cart is empty.</p>
		<div class="add-more-items">
			<a href="restaurant">Add Items</a>
		</div>
		<%
        }
        %>

		<%
        if (session.getAttribute("cart") != null) {
        %>
		<form action="checkout.jsp" method="post">
			<input type="submit" value="Proceed to Checkout"
				class="btn proceed-to-checkout-btn">
		</form>
		<%
        }
        %>
	</div>
</body>
</html>