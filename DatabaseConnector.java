import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

	static void queryLaunch(String s) throws SQLException{
		Connection myConn;
		
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/public_laundry1?autoReconnect=true&useSSL=false", "root",
					"pass132");
		Statement myStmt = myConn.createStatement();
		
		ResultSet myRs;
		// String s is a query I declared in advance 
		myRs = myStmt.executeQuery(s);
		while (myRs.next()) {
			try {
				System.out.println(myRs.getString("name") + "  "+ myRs.getInt(2));
			} catch (SQLException e) {					
					e.printStackTrace();
			}
		}
	}	
}
