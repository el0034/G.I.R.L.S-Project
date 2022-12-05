import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author el0034
 * Class for creating Movie objects
 */
public class Movie extends Media {
	// Create variables
	private double runtime;
	
	// Create no args constructor 
	public Movie() {
	
	}
	
	/**
	 * 
	 * @param type -
	 * @param title
	 * @param genre
	 * @param author - the creator 
	 * @param year - the year the media was created
	 * @param runtime - runtime of movie in minutes
	 * @param rating
	 * @param tags
	 * 
	 * Construct media object with all attributes
	 */
	public Movie(String type, String title, String genre, String author, int year,Double runtime, int rating, String tags) {
		super(type, title, genre, author, year, rating, tags);
		this.runtime=runtime;
	}
	
	// Create setter and getter for runtime
	public void setRuntime(Double runtime) {
		this.runtime=runtime;
	}
	// Get Runtime
	public Double getRuntime() {
		return runtime;
	}
	
	// Override toString method
	public String toString() {
		return super.toString() + ","+ runtime;
	}
	// Add to Media Table
	public void addMedia() {
		String tagsarr[] = super.getTags().split(",");
		PreparedStatement stmt;
		try {
			stmt = MyLoginPage.connectTest.conn.prepareStatement("INSERT INTO MEDIA VALUES('"+MyLoginPage.getActiveUser()+"','"+super.getType()+"','"+super.getTitle()+"','"+super.getAuthor()+"',"+super.getYear()+",'"+super.getGenre()+"',"+super.getRating()+",'"+tagsarr[0]+"','"+tagsarr[1]+"','"+tagsarr[2]+"',"+"'NULL'"+","+runtime+","+"0"+",'"+"NULL"+"','"+"NULL"+"',"+"'NULL'"+","+"'NULL'"+")");
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}