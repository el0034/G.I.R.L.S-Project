/**
 * 
 * @author el0034
 * Class for creating Media objects
 */

public class Media {
	// Create variables for media
	private String type;
	private String title;
	private String genre;
	private String author;
	private int year;
	private int rating;
	private String tags;
	
	// Create no-args constructor
	public Media() {
		
	}
	/**
	 * 
	 * @param type -
	 * @param title
	 * @param genre
	 * @param author - the creator 
	 * @param year - the year the media was created
	 * @param rating
	 * @param tags
	 * 
	 * Construct media object with all attributes
	 */
	
	public Media(String type, String title, String genre, String author, int year, int rating, String tags) {
		// Set all variables
		this.type=type;
		this.title=title;
		this.genre=genre;
		this.author=author;
		this.year=year;
		this.rating=rating;
		this.tags=tags;
	}
	
	// Create setter and getter for type
	public void setType(String type) {
		this.type=type;
	}
	public String getType() {
		return type;
	}
	
	// Create setter and getter for title
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;
	}
	
	// Create setter and getter for genre
	public void setGenre(String genre) {
		this.genre=genre;
	}
	
	public String getGenre() {
		return genre;
	}
	
	// Create setter and getter for author
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getAuthor() {
		return author;
	}
	
	// Create setter and getter for year
	public void setYear(int year) {
		this.year=year;
	}
	public int getYear() {
		return year;
	}
	
	// Create setter and getter for rating
	public void setRating(int rating) {
		this.rating=rating;
	}
	// Get ratings
	public int getRating() {
		return rating;
	}
	
	// Create setter and getter for tags
	public void setTags(String tags) {
		this.tags=tags;
	}
	// Get tags
	public String getTags() {
		return tags;
	}
	
	// Create toString Method
	public String toString() {
		return type + "," + author + "," + title+ ","+ genre + ","+ year + ","+ rating + ","+ tags;
	}
}
