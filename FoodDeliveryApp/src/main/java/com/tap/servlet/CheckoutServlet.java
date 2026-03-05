package com.tap.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.daoImplementation.OrderItemDAOImpl;
import com.tap.daoImplementation.OrdersDAOImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.OrderItem;
import com.tap.model.Orders;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

        // ✅ If user not logged in → go to login page
        if (user == null) {
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.forward(req, resp);
            return;   // IMPORTANT
        }

        // ✅ If restaurant missing → redirect to restaurant page
        if (restaurantId == null) {
            resp.sendRedirect("RestaurantServlet");
            return;
        }

        // ✅ If cart empty → redirect to cart page
        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        String address = req.getParameter("address");
        String paymentMethod = req.getParameter("paymentMethod");
     // Ensure the payment method strictly matches the ENUM format
        if (paymentMethod != null) {
            paymentMethod = paymentMethod.trim().toUpperCase();
        } else {
            paymentMethod = "CASH"; // Default fallback if something goes wrong
        }
        
        
        // ✅ Create Order
        Orders order = new Orders();
        order.setUserId(user.getUserId());
        order.setRestaurantId(restaurantId);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setAddress(address);
        order.setStatus("PLACED");
        order.setPaymentMethod(paymentMethod);

        // ✅ Calculate total amount
        double totalAmount = 0.0;
        for (CartItem item : cart.getItems().values()) {
            totalAmount += item.getQuantity() * item.getPrice();
        }
        order.setTotalAmount(totalAmount);

        // ✅ Save order
        OrdersDAOImpl ordersDAO = new OrdersDAOImpl();
        int orderId = ordersDAO.addOrders(order);

        // ✅ Save order items
        OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl();

        for (CartItem item : cart.getItems().values()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(item.getItemId());
            orderItem.setOrderItemName(item.getName()); 
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());

            orderItemDAO.addOrderItem(orderItem);
        }

        // ✅ Clear session cart
        session.removeAttribute("cart");
        session.removeAttribute("oldRestaurantId");

        // ✅ Redirect to confirmation page
        resp.sendRedirect("orderConfirmation.jsp");
    }
}