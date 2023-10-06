package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String url="jdbc:mysql://localhost:3306/reg";
			String username="root";
			String password="Deva@123";
			PrintWriter out = response.getWriter();  
			response.setContentType("text/css");  
			Connection con=DriverManager.getConnection(url, username, password);
			String n=request.getParameter("username");
			String p=request.getParameter("password");
			
			PreparedStatement ps=con.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, n);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp"); 
				//response.sendRedirect("welcome.jsp");
		        rd.forward(request,response);  
			}
			else {
				out.println("<h1>Sorry Username or password incorrect!!</h1>");
				out.println("<a href=login.jsp>Try Again!!</a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
