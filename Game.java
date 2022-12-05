/*
 * @author hag0009
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Game extends Media {
	
	private String replayAbility; // Gives True/false if replayable
	private String platform;// Gives type of platform

	public Game() {
		
	}
	
	//Media construction with attributes type, title, author, year, rating, tags, replayAbility, & platform 
	public Game(String type, String title, String genre,  String author, int year, int rating, String tags, String replayAbility, String platform) {
		super(type,title, genre, author,year,rating,tags);
		this.replayAbility = replayAbility;
		this.platform = platform; 
	
	}
	
	
	//Sets platform type
	public void platform(String platform) {
		this.platform = platform; 
	}
	
	//Gets platform type	
	public String getPlatform() {
		return platform;
	}
	
	//Sets true or false if has replay ability
	public void setAbility(String replayAbility) {
		this.replayAbility = replayAbility;
	}
	
	//Returns true/false
	public String getAblity() {
		return replayAbility;
	}
	// To String method
	public String toString() {
		return super.toString() + "," + platform + "," + replayAbility;	
	}
	// Add to Media Table
	public void addMedia() {
		try {
			String tagsarr[] = super.getTags().split(",");
		
			
			PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("INSERT INTO MEDIA VALUES('"+MyLoginPage.getActiveUser()+"','"+super.getType()+"','"+super.getTitle()+"','"+super.getAuthor()+"',"+super.getYear()+",'"+super.getGenre()+"',"+super.getRating()+",'"+tagsarr[0]+"','"+tagsarr[1]+"','"+tagsarr[2]+"',"+"'NULL'"+","+"0"+","+"0"+","+"'NULL'"+",'"+platform+"',"+replayAbility+","+"'NULL'"+")");
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
