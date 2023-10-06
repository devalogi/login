package busreserv;
import java.util.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
public class Booking {
	String passengerName;
	int busNo;
	Date date;
	Time tim;
	
	Booking(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter name of passenger: ");
		passengerName = s.next();
		System.out.println("Enter bus no: ");
		busNo = s.nextInt();
		System.out.println("Enter date dd-mm-yyyy");
		String dateInput = s.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//String currDate = dateFormat.format(date.getTime());  
		//System.out.println("The formatted date is: " + currDate);  
		try {
			date = dateFormat.parse(dateInput);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean isAvailable() throws SQLException{
		DAO busdao=new DAO();
		BookingDAO bookingdao=new BookingDAO();
		int capacity = busdao.getcapacity(busNo);
		
		
		int booked = bookingdao.getbookedcount(busNo,date);
		
		return booked<capacity?true:false;
		
	}
}
