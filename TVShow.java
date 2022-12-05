import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Tv Shows class to gather information
 * @author Caitlyn Taylor
 */
public class TVShow extends Media {
	
	//Variable declaration
	
	private Integer seasons; 
	private String current; //if the show is still adding new seasons or not


	public TVShow() {
		
	}
	
	//Media construction with attributes type, title, author, year,seasons, current, rating, and tags
	
	public TVShow(String type, String title, String genre, String author, int year, int seasons, String current, int rating,String tags) {
		super(type,title,genre,author,year,rating,tags);
		this.seasons = seasons;
		this.current = current; 
	
		
	}
	
	
	//Sets number of seasons
	public void setSeasons(Integer seasons) {
		this.seasons = seasons; 
	}
	
	//Gets seasons
	
	public Integer getSeasons() {
		return seasons;
	}
	
	//Sets if stilling running true or false
	public void setCurrent(String current) {
		this.current = current;
	}
	
	public String getCurrent() {
		return current;
	}
	
	public String toString() {
		return super.toString() + "," + seasons + "," + current;
	
	}
	public void addMedia() {
		
		try {
			String tagsarr[] = super.getTags().split(",");
		
		
			PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("INSERT INTO MEDIA VALUES('"+MyLoginPage.getActiveUser()+"','"+super.getType()+"','"+super.getTitle()+"','"+super.getAuthor()+"',"+super.getYear()+",'"+super.getGenre()+"',"+super.getRating()+",'"+tagsarr[0]+"','"+tagsarr[1]+"','"+tagsarr[2]+"','"+"NULL"+"',"+"0"+","+seasons+","+"'NULL'"+","+"'NULL'"+","+"'NULL'"+",'"+current+"')");
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}