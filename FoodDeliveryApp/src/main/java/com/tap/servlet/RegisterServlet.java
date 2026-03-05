package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tap.utility.DbConnection;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String role = req.getParameter("role");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
       

        //  Password match validation
        if (!password.equals(confirmPassword)) {
            writer.println("<h3>Passwords do not match!</h3>");
            return;
        }

        try (Connection connection = DbConnection.getConnection()) {

            //  Check if email already exists
            String checkQuery = "SELECT email FROM user WHERE email = ?";
            PreparedStatement checkPs = connection.prepareStatement(checkQuery);
            checkPs.setString(1, email);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                req.setAttribute("message", "You are already registered. Please login.");
                req.getRequestDispatcher("login.html").include(req, resp);
                return;
            }

            //  Insert new user
            String insertQuery = "INSERT INTO user (userName, email, password, address, phoneNumber, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPs = connection.prepareStatement(insertQuery);

            insertPs.setString(1, userName);
            insertPs.setString(2, email);
            insertPs.setString(3, password);
            insertPs.setString(4, address);
            insertPs.setString(5, phoneNumber);
            insertPs.setString(6, role);

            int result = insertPs.executeUpdate();

            if (result == 1) {
                writer.println("<h2>Registration Successful!</h2>");
            } else {
                writer.println("<h2>Registration Failed!</h2>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            writer.println("<h3>Error occurred during registration</h3>");
        }
    }
}


