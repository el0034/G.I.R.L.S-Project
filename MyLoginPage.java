/*
 * @author al0001 el0034 hag0009
 */

import static javax.swing.JOptionPane.showMessageDialog;


import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MyLoginPage  {
	private static String activeUser;
	static ConnectDB connectTest;
	public static void main(String[] args){
		
	
		
	//creating database from/to system
	 connectTest = new ConnectDB();
	   
	//Commented code for database testing (please do not touch)    
	// connectTest.dropTable("ACCOUNTS");
	// connectTest.dropTable("MEDIA");
    //  connectTest.displayInfo();  
    //   connectTest.LoadMediaData("userAsh");
    //    AccountsList.printer(list);

	 //if there is no connection already, create a connection
		try {
	    connectTest.CreateDBUsers();
	    connectTest.CreateUsersMediaTable();
		}
		catch(NullPointerException e){
			System.exit(0);
		}
	 
		//Setting environment for login page
		CreateAndLoginPage.CreateAndLoginRun();
		
		}
	//method for creating account
	public static void createAccount( String name, String username, String email, String passw, String admin) throws IOException {
		String returnString="";
		
		//Add the unique profile if it doesnt already exist
		try {
			ResultSet query;
			Statement stmt = connectTest.conn.createStatement();
		     query=	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)= Lower('"+username+"') AND Lower(Email) = Lower('"+email+"')");
		     while(query.next()) {
					//Allows traversing of database
		    	 	connectTest.conn.setAutoCommit(false);
		    	 	//checks requirements
					if(query.getString("Username").equalsIgnoreCase(username)) {
						returnString = "Username Taken";
						break;
					}
					if(query.getString("Email").equalsIgnoreCase(email)) {
						returnString+="Email Already registered";
						break;
					}
					
				}
		     query.close();
		     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//More Create account requirement checks
		if(username.equals("") || passw.equals("") || email.equals("")) {
			showMessageDialog(null,"Email, Username, and Password fields cannot be empty");	
		}
		//Prevents spaces
		else if(username.contains(" ")||passw.contains(" ")||email.contains(" ")) {
			showMessageDialog(null,"Email, Username, and Password fields cannot contain spaces");	
		}
		//no commas anywhere
		else if(email.contains(",")||passw.contains(",")||name.contains(",")) {
			showMessageDialog(null,"Text Fields cannot contain ',' ");
		}
		//no special characters for username
		else if(hasSpecial(username)) {
			showMessageDialog(null,"Username can only contain letters and numbers");
		}
		// check to see if password is valid
		else if(!validatePassword(passw)) {
			//error message in method		
		}
		 else if(returnString.equals("")) {
			 //Create the account, add to database, create users media table
			 try {
				 connectTest.conn.setAutoCommit(true);
				 connectTest.AddAccount(name,  email,  username, passw,  false);
				
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// connectTest.CreateUsersMediaTable(username);
			 CreateAndLoginPage.clearFields();
	
			 showMessageDialog(null,"Account Created");	 
		 }
		//Let user know if username/email is taken
		 else {
			 showMessageDialog(null,returnString); 
		 }	
	}
	//method for logging in
	public static void login( String username, char passw[]) {
		//checks with system to see if credentials are valid
		
		Boolean isValidLogin = false;
		
		try {
			//Seeing if account exists
			Statement stmt = connectTest.conn.createStatement();
		    connectTest.conn.setAutoCommit(false);
		    ResultSet query=	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +username+"')");
		    //If account exists and creds are right, authorize login
		    while(query.next()) {
		    	 if(query.getString("Username").equalsIgnoreCase(username) && query.getString("Password").equals(new String(passw))) {
				   	 isValidLogin = true;
				   	 break;	
				    }
		     }
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//change to homescreen
		if(isValidLogin) {
			CreateAndLoginPage.loginFrame.dispose();
			Home.Homerun();
			setActiveUser(username.toLowerCase());	
		}
		else {	
		   showMessageDialog(null,"Login Failed");   
		}
	}	

	//sets logged in user
	public static void setActiveUser(String accountUsername) {
		activeUser = accountUsername;
	}
	//gets logged in user
	public static String getActiveUser() {
		return activeUser;
	}
	//helper method for checking requirements
	public static boolean hasSpecial(String user) {
		for(int i = 0;i<user.length();i++) {
			if(Character.isLetterOrDigit(user.charAt(i))==false) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param passw
	 * @return if password is valid
	 * checks if password does not pass the requirement check
	 * @author el0034
	 */
	public static boolean validatePassword(String passw) {
		// Check is password is at least 8 characters long
		if(passw.length() < 8) {
			showMessageDialog(null, "Password must be at least 8 charaters long");
			return false;
		}
		// Check is password contains a special character
		else if (!(passw.contains("!") || passw.contains("~") || passw.contains("#") || passw.contains("$") || passw.contains("?") || passw.contains("@"))) {
			showMessageDialog(null,"Password must contain at least one special charcter !,,$,~,#,?, or @");
			return false;
    	}
		// Check if password contains 4 letters and 3 numbers
		else {
			int countNum=0;
			int countLetter=0;
			for(int i=0; i<passw.length(); i++) {
				char ch = passw.charAt(i);
				
				if(Character.isDigit(ch)) {
					countNum++;
				}
				if(Character.isLetter(ch)) {
					countLetter++;
				}
		}
			if (countLetter<4 || countNum<3) {
				showMessageDialog(null, "Password must contain at least 4 letters and 3 numbers");
				return false;		
		}
		return true;
		}
	}
}