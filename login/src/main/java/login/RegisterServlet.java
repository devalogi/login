package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    // Define your database connection parameters here
    String jdbcUrl = "jdbc:mysql://localhost:3306/reg";
    String dbUser = "root";
    String dbPassword = "Deva@123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            response.getWriter().write("All fields must be filled out");
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.getWriter().write("Passwords do not match");
            return;
        }

        Connection connection = null;

        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Check if the username is already taken
            String checkQuery = "SELECT username FROM user WHERE username = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            ResultSet result = checkStatement.executeQuery();

            if (result.next()) {
                response.getWriter().write("Username already exists");
            } else {
                // Insert the new user
                String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);

                int rows = insertStatement.executeUpdate();

                if (rows > 0) {
                	System.out.println("loged");
                    response.getWriter().write("Registration successful!");
                   
                    RequestDispatcher dispatcher = request.getRequestDispatcher("registrationSuccess.jsp");
                    dispatcher.forward(request, response);

                } else {
                    response.getWriter().write("Registration failed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("registrationFailure.jsp");
                    dispatcher.include(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("Registration failed");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
