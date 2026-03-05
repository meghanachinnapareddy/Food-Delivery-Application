package com.tap.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tap.model.User;
import com.tap.utility.DbConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String QUERY =
        "SELECT * FROM user WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("\n--- LOGIN ATTEMPT ---");
        System.out.println("Attempting login for email: " + email);

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            System.out.println("FAILED: Email or password was empty.");
            resp.sendRedirect("login.html?error=empty_fields");
            return;
        }

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    String dbPassword = rs.getString("password");

                    if (dbPassword != null && dbPassword.equals(password)) {

                        // ✅ Create User Object
                        User user = new User();
                        user.setUserId(rs.getInt("userId"));
                        user.setUsername(rs.getString("userName"));
                        user.setEmail(rs.getString("email"));
                        user.setAddress(rs.getString("address"));
                        user.setPhoneNumber(rs.getString("phoneNumber"));
                        user.setRole(rs.getString("role"));

                        // ✅ Store in session
                        HttpSession session = req.getSession();
                        session.setAttribute("user", user);
                        
                        // DEBUGGING: Print Success and Session ID
                        System.out.println("SUCCESS: User '" + user.getUsername() + "' logged in!");
                        System.out.println("LOGIN SESSION ID: " + session.getId());

                        // ✅ Redirect to restaurant page
                        resp.sendRedirect("restaurant");
                        return;

                    } else {
                        System.out.println("FAILED: Passwords do not match for " + email);
                        resp.sendRedirect("login.html?error=invalid_password");
                        return;
                    }

                } else {
                    System.out.println("FAILED: No user found in Database with email: " + email);
                    resp.sendRedirect("register.html?error=user_not_found");
                    return;
                }
            }

        } catch (Exception e) {
            System.out.println("FAILED: A Database or SQL Exception occurred!");
            e.printStackTrace();
            resp.sendRedirect("login.html?error=exception");
        }
    }
}