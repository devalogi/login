package reg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {

	private String url="jdbc:mysql://localhost:3306/register";
	private String username="root";
	private String password="Deva@123";
	private String db="com.mysql.jdbc.Driver";
	
	public void loaddriver(String db) {
		try {
			Class.forName(db);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public String insert(member m)  {
		loaddriver(db);
		Connection con=getConnection();
		String res="Data entered successfully";
		String query="insert into m values(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, m.getUsername());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getEmail());
			ps.setString(4, m.getPhone());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res="Data not entered";
		}
		return null;
	}
}
