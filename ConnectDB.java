/**
 * @author al00001 and el0034
 */

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectDB {
	//Where the database will be hosted and how (derby)
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL = "jdbc:derby:ConnectDB;create=true";
	
	 static Connection conn;

// Constructor for making a connection
public ConnectDB() {
	//Make a connection
	try {
		conn = DriverManager.getConnection(JDBC_URL);
		if(conn!=null) {
			System.out.println("Connection Successful");
		}
	}
	catch(SQLException e){
		System.out.println("Connection Unsuccessful");
		 showMessageDialog(null,"The Application May already be Running!");
	}
	
}

/**
 * method to create the accounts table
 */
public  void CreateDBUsers(){
	try{
     //statement object to store action
     Statement stmt = conn.createStatement();
        //creating the table
		stmt.execute("CREATE TABLE ACCOUNTS"+ "("+"Name VARCHAR(20),"+"Email VARCHAR(30) ,"+"Username VARCHAR(20) ,"+"Password VARCHAR(25) ,"+"Admin VARCHAR(5), Bio VARCHAR(200))");	 
		System.out.println("ACCOUNTS Table created.");
	}
	catch (SQLException ex)
  {
     System.out.println("ERROR: " + ex.getMessage());
  }
}
/**
 * method to create a media table for a user with param user
 * @param userName
 */
public  void CreateUsersMediaTable(){
	try{
     //statement object to store action
     Statement stmt = conn.createStatement();
        //creating the table
		stmt.execute("CREATE TABLE MEDIA(Username VARCHAR(20),Type VARCHAR(6),Title VARCHAR(100),Author VARCHAR(50) ,publish INT,Genre VARCHAR(25) ,Rating INT, Tag1 VARCHAR(15) , Tag2 VARCHAR(15),Tag3 VARCHAR(15) , ISBN CHAR(13) , Runtime DECIMAL(8,2), Seasons INTEGER , Series VARCHAR(5), Platform VARCHAR(20) , Replayability VARCHAR(5) , Airing VARCHAR(5) )");	 
		System.out.println("Media Table created.");
	}
	catch (SQLException ex)
  {
     System.out.println("ERROR: " + ex.getMessage());
  }
}
/**
 * method to drop any table 
 * @param tableToDrop name of the table to drop 
 */
public  void dropTable(String tableToDrop){
	System.out.println("Checking for existing tables.");
	try{
		//statement object
		Statement stmt  = conn.createStatement();;
         //drop the table
         stmt.execute("DROP TABLE "+ tableToDrop);
			System.out.println(tableToDrop +" Table dropped.");
		}
	   //if the table didn't exist
		catch(SQLException ex){	
		}
}
/**
 * method to add account param is account information to be added
 * @param name
 * @param email
 * @param user
 * @param pass
 * @param admin
 */
 void AddAccount(String name, String email, String user,String pass, Boolean admin)  {

	   //adding data to table (had to be done this way because we are using variables)
		try {
			//make our statement object to store variables
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ACCOUNTS VALUES('"+name+"','"+email+"','"+user+"','"+pass+"','"+admin+"',"+"'Go To Setings to Edit Bio!'"+")");
	
			//update the table with row data
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
