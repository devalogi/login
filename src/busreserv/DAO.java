package busreserv;
import java.sql.*;
public class DAO {
	public void display() throws SQLException {
		String query="select * from bus";
		Connection con=Databaseconne.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println("bus_no "+ rs.getInt(1));
			if(rs.getInt(2)==0) {
				System.out.println("AC : No");
			}
			else {
				System.out.println("AC : Yes");
			}
			System.out.println("capacity "+ rs.getInt(3));
		}
	}
	public int getcapacity(int id) throws SQLException {
		String query="select capacity from bus where id="+id;
		Connection con=Databaseconne.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}
}
