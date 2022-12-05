/*
 * @author: hag0009
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Book extends Media {
	
	//Declaring variables for books
	private String bolSeries; // Gives True/false if series
	private String isbn;// Gives number of page numbers


	public Book() {
		
	}
	
	//Media construction with attributes type, title, author, year, rating, tags, bolSeries, & ISBN 
	public Book(String type, String title, String genre, String author,int year, int rating, String tags, String bolSeries, String iSBN) {
		super(type,title,genre,author,year,rating,tags);
		this.bolSeries = bolSeries;
		this.isbn = iSBN; 
		
		
	}
	
	//Sets page numbers
	public void ISBN(String iSBN) {
		this.isbn = iSBN; 
	}
	
	//Gets page numbers type	
	public String getISBN() {
		return isbn;
	}
	
	//Sets true or false if has series
	public void setSeries(String bolSeries) {
		this.bolSeries = bolSeries;
	}
	
	//Returns true/false
	public String getSeries() {
		return bolSeries;
	}
	// ToString method
	public String toString() {
		return super.toString() + "," + isbn + "," + bolSeries;	
	}	
	// Add to Media Table
	public void addMedia() {
		PreparedStatement stmt;
		try {
			String tagsarr[] = super.getTags().split(",");
			String series;
		
			stmt = MyLoginPage.connectTest.conn.prepareStatement("INSERT INTO MEDIA"+" VALUES('"+MyLoginPage.getActiveUser()+"','"+super.getType()+"','"+super.getTitle()+"','"+super.getAuthor()+"',"+super.getYear()+",'"+super.getGenre()+"',"+super.getRating()+",'"+tagsarr[0]+"','"+tagsarr[1]+"','"+tagsarr[2]+"','"+isbn+"',"+"0"+","+"0"+","+bolSeries+","+"'NULL'"+","+"'NULL'"+","+"'NULL'"+")");
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}