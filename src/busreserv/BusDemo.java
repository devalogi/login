package busreserv;
import java.sql.SQLException;
import java.util.*;
//import java.util.ArrayList;
public class BusDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DAO dao=new DAO();
		try {
			dao.display();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int option = 1;
		Scanner s = new Scanner(System.in);
		
		while(option==1) {
			System.out.println("Enter 1 to Book and 2 to exit");
			option = s.nextInt();
			if(option == 1) {
				Booking booking = new Booking();
				if(booking.isAvailable()) {
					BookingDAO bookingdao=new BookingDAO();
					bookingdao.addBooking(booking);
					System.out.println("Your booking is confirmed");
				}
				else
					System.out.println("Sorry. Bus is full. Try another bus or date.");
			}
		}
		s.close();
	}

	}

