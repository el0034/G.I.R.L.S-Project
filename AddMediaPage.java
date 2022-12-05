import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.plaf.SliderUI;

/**
 * 
 * @author al0001, el0034, hag0009 cnt0009
 * Creates the Add media page
 */
public class AddMediaPage {
	// Declare all variables
	private static String isSeries ;
	private static String hasReplayAbility;
	private int trueRating;
	private static String airing;

	private JPanel bookPanel;
	private JPanel moviePanel;
	private JPanel tvShowPanel;
	private JPanel gamePanel;
	 JPanel addMediaPanel;
	

	// Create panel and initialize all panels
	public AddMediaPage() {
		
		// Create page layout 
		addMediaPanel = new JPanel();
		addMediaPanel.setBounds(100, 100, 800, 600);
		addMediaPanel.setBackground(new Color(216, 191, 216));
		addMediaPanel.setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 63, 686, 2);
		separator.setForeground(new Color(255, 255, 255));
		addMediaPanel.add(separator);
		JPanel panel = new JPanel();
		panel.setBounds(45, 76, 691, 44);
		panel.setBackground(new Color(169, 169, 169));
		addMediaPanel.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Select A Type of Media to Add:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 272, 22);
		panel.add(lblNewLabel);
		
        // Initializes special panels for media types
		setBookPanel() ;
		setMoviePanel() ;
		setTVShowPanel() ;
		setGamePanel() ;
		
		// Create card layout to allow easy switching from panel to panel
		CardLayout typePage = new CardLayout();
		
		// Create a main panel to switch child panels on
		JPanel mediaSpace = new JPanel();
		mediaSpace.setBounds(50, 143, 686, 380);
		mediaSpace.setLayout(typePage);
		addMediaPanel.add(mediaSpace);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		mediaSpace.add(panel_1,"null");
		panel_1.setLayout(null);
		
		// Add panels to a "deck" to shuffle through
		mediaSpace.add(bookPanel,"Book");
		mediaSpace.add(moviePanel,"Movie");
		mediaSpace.add(tvShowPanel,"TVShow");
		mediaSpace.add(gamePanel,"Game");
		
		// Create the drop down media selection menu
		String[] types = {"Select Media","Book","Movie", "TV Show","Game"};
		JComboBox comboBox = new JComboBox(types);
		comboBox.setBackground(new Color(211, 211, 211));
		
		// Once a selection is chosen show appropriate display
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// If an item is selected, pick appropriate panel to flip to
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String  selectedMedia =comboBox.getSelectedItem().toString();
					if(selectedMedia.equals(types[0])) {
						typePage.show(mediaSpace,"null");
					}
					else if(selectedMedia.equals(types[1])) {
						typePage.show(mediaSpace,"Book");
					} 
					else if(selectedMedia.equals(types[2])) {
						typePage.show(mediaSpace,"Movie");
					}
					else if(selectedMedia.equals(types[3])) {
						typePage.show(mediaSpace,"TVShow");
						
					}
					else if(selectedMedia.equals(types[4])) {
						typePage.show(mediaSpace,"Game");
					}
				}
			}
		});
		
		//More gui CSS and Background panels
		comboBox.setBounds(292, 14, 118, 22);
		panel.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(44, 26, 164, 39);
		panel_2.setBackground(Color.DARK_GRAY);
		addMediaPanel.add(panel_2);
		
		JLabel addMediaLabel = new JLabel("Add Media");
		panel_2.add(addMediaLabel);
		addMediaLabel.setForeground(Color.WHITE);
		addMediaLabel.setBackground(new Color(255, 255, 255));
		addMediaLabel.setFont(new Font("Tahoma", Font.BOLD, 28));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 120, 764, 441);
		addMediaPanel.add(panel_3);
		panel_3.setLayout(null);
		
		
	}
	
	
	/**
	 * @author al00001
	 * This creates the Book panel, and as data pertaining to adding the media
	 */
	public void setBookPanel() {
		
		// Create textfields to get info
		 JTextField tfTitle;
		 JTextField tfAuthor;
		 JTextField tfYear;
		 JTextField tfTag1;
		 JTextField tfISBN;
		 JTextField tfTag2;
		 JTextField tfTag3;
		 JTextField tfGenre;
		
		// Initialize book panel 
		 bookPanel = new JPanel();
		 bookPanel.setForeground(new Color(0, 0, 0));
		 bookPanel.setBounds(55, 143, 686, 380);
		 bookPanel.setBackground(new Color(128, 128, 128));
		 bookPanel.setLayout(null);
		
		 
		// Create label and text for genre
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setForeground(new Color(255, 255, 255));
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenre.setBackground(Color.WHITE);
		lblGenre.setBounds(302, 85, 61, 35);
		bookPanel.add(lblGenre);
		tfGenre = new JTextField();
		tfGenre.setColumns(10);
		tfGenre.setBounds(279, 117, 111, 20);
		bookPanel.add(tfGenre);
			
		// Create labels for all the entries 
		JLabel lblTitle = new JLabel("Book Title:");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(55, 46, 97, 35);
		bookPanel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Book Author:");
		lblAuthor.setForeground(new Color(255, 255, 255));
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthor.setBackground(Color.WHITE);
		lblAuthor.setBounds(50, 139, 122, 35);
		bookPanel.add(lblAuthor);
		
		JLabel lblYear = new JLabel("Book Publish Year:");
		lblYear.setForeground(new Color(255, 255, 255));
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYear.setBackground(Color.WHITE);
		lblYear.setBounds(33, 231, 157, 35);
		bookPanel.add(lblYear);
		
		JLabel lblRating = new JLabel("Your Rating");
		lblRating.setForeground(new Color(255, 255, 255));
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRating.setBackground(Color.WHITE);
		lblRating.setBounds(268, 165, 122, 35);
		bookPanel.add(lblRating);
		
		JLabel lblTags = new JLabel("Book Tags:");
		lblTags.setForeground(new Color(255, 255, 255));
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTags.setBackground(Color.WHITE);
		lblTags.setBounds(521, 46, 97, 35);
		bookPanel.add(lblTags);
		
		JLabel lblSeries = new JLabel("Is this a Series?");
		lblSeries.setForeground(new Color(255, 255, 255));
		lblSeries.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSeries.setBackground(Color.WHITE);
		lblSeries.setBounds(507, 149, 144, 35);
		bookPanel.add(lblSeries);
		
		JLabel lblNumberOfPages = new JLabel("ISBN:");
		lblNumberOfPages.setForeground(new Color(255, 255, 255));
		lblNumberOfPages.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumberOfPages.setBackground(Color.WHITE);
		lblNumberOfPages.setBounds(539, 241, 54, 35);
		bookPanel.add(lblNumberOfPages);
		
		// Create Rating Slider
		JSlider ratingSlider = new JSlider();
		ratingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// gets value of what the slider is at
				setRating(ratingSlider.getValue());
			}
		});
		// more css for slider
		ratingSlider.setPaintTicks(true);
		ratingSlider.setPaintLabels(true);
		ratingSlider.setSnapToTicks(true);
		ratingSlider.setValue(0);
		ratingSlider.setToolTipText("");
		ratingSlider.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		ratingSlider.setMajorTickSpacing(5);
		ratingSlider.setMinorTickSpacing(1);
		ratingSlider.setForeground(new Color(0, 0, 0));
		ratingSlider.setBackground(new Color(216, 191, 216));
		ratingSlider.setMaximum(5);
		ratingSlider.setBounds(226, 202, 209, 42);
		bookPanel.add(ratingSlider);
		
		// Create text fields for entries
		tfTitle = new JTextField();
		tfTitle.setBounds(45, 95, 111, 20);
		bookPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		tfAuthor = new JTextField();
		tfAuthor.setColumns(10);
		tfAuthor.setBounds(50, 175, 111, 20);
		bookPanel.add(tfAuthor);
		
		tfYear = new JTextField();
		tfYear.setColumns(10);
		tfYear.setBounds(50, 265, 111, 20);
		bookPanel.add(tfYear);
		
		tfTag1 = new JTextField();
		tfTag1.setColumns(10);
		tfTag1.setBounds(478, 91, 54, 20);
		bookPanel.add(tfTag1);
		
		tfISBN = new JTextField();
		tfISBN.setColumns(10);
		tfISBN.setBounds(514, 275, 111, 20);
		bookPanel.add(tfISBN);
		
	   // Create RadioButton that is user to tell if a book is a part of a series
		JRadioButton radioBtnSeries = new JRadioButton("Yes");
		radioBtnSeries.setForeground(new Color(255, 255, 255));
		radioBtnSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioBtnSeries.isSelected()) {
					isSeries="true";
				}
				else {
					isSeries="false";
				}
			}
		});
		radioBtnSeries.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioBtnSeries.setBackground(new Color(128, 128, 128));
		radioBtnSeries.setBounds(539, 191, 54, 23);
		bookPanel.add(radioBtnSeries);
		
		// More text fields for tags
		tfTag2 = new JTextField();
		tfTag2.setColumns(10);
		tfTag2.setBounds(545, 91, 54, 20);
		bookPanel.add(tfTag2);
		
		tfTag3 = new JTextField();
		tfTag3.setColumns(10);
		tfTag3.setBounds(611, 91, 54, 20);
		bookPanel.add(tfTag3);
		
		// Button to add the book
		JButton addBookBtn = new JButton("Add Book");
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// Text fields cannot be null
				if(tfTitle.getText().trim().equals("")||tfAuthor.getText().trim().equals("")||tfYear.getText().trim().equals("")||tfGenre.getText().trim().equals("") ||tfTag1.getText().trim().equals("")||tfTag2.getText().trim().equals("")||tfTag3.getText().trim().equals("")||tfISBN.getText().trim().equals("")||tfTag3.getText().trim().equals("")||tfGenre.getText().trim().equals("")){
					showMessageDialog(null,"Cannot Leave Fields Blank");
				}
				// No commas
				else if(tfTitle.getText().trim().contains(",")||tfAuthor.getText().trim().contains(",")||tfYear.getText().trim().contains(",")||tfGenre.getText().trim().contains(",") ||tfTag1.getText().trim().contains(",")||tfTag2.getText().trim().contains(",")||tfTag3.getText().trim().contains(",")||tfISBN.getText().trim().contains(",")) {
					showMessageDialog(null,"Fields cannot contain ','");
				}
				// Year must be greater than 0 and less than 10000
				else if(isANumber(tfYear.getText())==false|| tfYear.getText().length()>4||tfYear.getText().length()<1) {
					showMessageDialog(null,"Publish Year must be a valid number less than 5 digits long");
				}
				// ISBN must be 13 digits
				else if(isANumber(tfISBN.getText())==false|| tfISBN.getText().length()!=13) {
					showMessageDialog(null,"ISBN Year must be a valid 13 digit number");
				}
				// Make the book and add to account
				else {
					String ultimateTag = tfTag1.getText().trim()+","+tfTag2.getText().trim()+","+tfTag3.getText().trim();
					Book newBook = new Book("book",tfTitle.getText().trim().replaceAll("'","''"),tfGenre.getText().trim().replaceAll("'","''"),tfAuthor.getText().trim().replaceAll("'","''"),Integer.parseInt(tfYear.getText().trim()),trueRating,ultimateTag.replaceAll("'","''"),isSeries,tfISBN.getText().trim().replaceAll("'","''"));
			
					try {
						//gets all from users media table
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						ResultSet query= stmt.executeQuery("SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND  Lower(Type)= 'book' AND  Lower(Title)= '"+newBook.getTitle().toLowerCase()+"'AND  Lower(Author)= '"+newBook.getAuthor().toLowerCase()+"' AND  publish= "+newBook.getYear()+"  ");
					 
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//while user has media add it to the row in specific format
						if(query.next()) {
							showMessageDialog(null, "You already have this media in your library");
							
						}
						else {
							newBook.addMedia();
							//reset values
							tfTitle.setText("");
							tfGenre.setText("");
							tfAuthor.setText("");
							tfYear.setText("");
							tfTag1.setText("");
							tfTag2.setText("");
							tfTag3.setText("");
							tfISBN.setText("");
							ratingSlider.setValue(0);
							radioBtnSeries.setSelected(false);
							showMessageDialog(null,"Book has been Added!");
							;	
						}
			
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		
				}
			}
		});
		addBookBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addBookBtn.setBounds(268, 321, 122, 35);
		bookPanel.add(addBookBtn);
		
		// Create button to return to my page
		JButton btnNewButton = new JButton("Return to MyPage");
		btnNewButton.setBounds(554, 11, 178, 44);
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.switchToHome();
				Home.modelLeaderboard.setRowCount(0);
				Home.populateLeaderboardTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		addMediaPanel.add(btnNewButton);
	}
	/**
	 * method to make the movie panel
	 * @author cnt0009
	 */
	public void setMoviePanel() {
		//initialize panel
		moviePanel = new JPanel();
		moviePanel.setForeground(new Color(255, 255, 255));
		moviePanel.setBounds(55, 143, 686, 380);
		moviePanel.setBackground(new Color(128, 128, 128));
	    moviePanel.setLayout(null);
		
	    //make labels for all the inputs
		JLabel lblMovieTitle = new JLabel("Movie Title:");
		lblMovieTitle.setForeground(new Color(255, 255, 255));
		lblMovieTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMovieTitle.setBackground(Color.WHITE);
		lblMovieTitle.setBounds(34, 61, 97, 35);
		moviePanel.add(lblMovieTitle);
		
		JLabel lblMovieAuthor = new JLabel("Movie Director:");
		lblMovieAuthor.setForeground(new Color(255, 255, 255));
		lblMovieAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMovieAuthor.setBackground(Color.WHITE);
		lblMovieAuthor.setBounds(34, 151, 136, 35);
		moviePanel.add(lblMovieAuthor);
		
		JLabel lblMoviePublishYear = new JLabel("Movie Publish Year:");
		lblMoviePublishYear.setForeground(new Color(255, 255, 255));
		lblMoviePublishYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMoviePublishYear.setBackground(Color.WHITE);
		lblMoviePublishYear.setBounds(21, 240, 170, 35);
		moviePanel.add(lblMoviePublishYear);
		
		JLabel lblMovieGenre = new JLabel("Movie Genre:");
		lblMovieGenre.setForeground(new Color(255, 255, 255));
		lblMovieGenre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMovieGenre.setBackground(Color.WHITE);
		lblMovieGenre.setBounds(514, 240, 111, 35);
		moviePanel.add(lblMovieGenre);
		
		JLabel lblMSlider = new JLabel("Your Rating:");
		lblMSlider.setForeground(new Color(255, 255, 255));
		lblMSlider.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMSlider.setBackground(Color.WHITE);
		lblMSlider.setBounds(270, 128, 127, 35);
		moviePanel.add(lblMSlider);
		
		JLabel lblRuntime = new JLabel("Runtime (in minutes):");
		lblRuntime.setForeground(new Color(255, 255, 255));
		lblRuntime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRuntime.setBackground(Color.WHITE);
		lblRuntime.setBounds(484, 151, 176, 35);
		moviePanel.add(lblRuntime);
		
		JLabel lblMovieTags = new JLabel("Movie Tags:");
		lblMovieTags.setForeground(new Color(255, 255, 255));
		lblMovieTags.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMovieTags.setBackground(Color.WHITE);
		lblMovieTags.setBounds(514, 61, 106, 35);
		moviePanel.add(lblMovieTags);
		
		//rating slider 
		JSlider ratingSlider = new JSlider();
		ratingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//gets value of what the slider is at
				setRating(ratingSlider.getValue());
			}
		});
		//rating slider css
		ratingSlider.setPaintTicks(true);
		ratingSlider.setPaintLabels(true);
		ratingSlider.setSnapToTicks(true);
		ratingSlider.setValue(0);
		ratingSlider.setToolTipText("");
		ratingSlider.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		ratingSlider.setMajorTickSpacing(5);
		ratingSlider.setMinorTickSpacing(1);
		ratingSlider.setForeground(new Color(0, 0, 0));
		ratingSlider.setBackground(new Color(216, 191, 216));
		ratingSlider.setMaximum(5);
		ratingSlider.setBounds(228, 173, 201, 57);
		moviePanel.add(ratingSlider);
		
		
		//text fields for all inputs
		JTextField tfMTitle = new JTextField();
		tfMTitle.setColumns(10);
		tfMTitle.setBounds(34, 92, 111, 20);
		moviePanel.add(tfMTitle);
		
		JTextField tfMAuthor = new JTextField();
		tfMAuthor.setColumns(10);
		tfMAuthor.setBounds(34, 186, 111, 20);
		moviePanel.add(tfMAuthor);
		
		JTextField tfMYear = new JTextField();
		tfMYear.setColumns(10);
		tfMYear.setBounds(36, 277, 111, 20);
		moviePanel.add(tfMYear);
		
		JTextField tfRuntime = new JTextField();
		tfRuntime.setColumns(10);
		tfRuntime.setBounds(514, 186, 111, 20);
		moviePanel.add(tfRuntime);
		
		JTextField tfMtags = new JTextField();
		tfMtags.setColumns(10);
		tfMtags.setBounds(458, 96, 80, 20);
		moviePanel.add(tfMtags);
		
		JTextField tfMovieGenre = new JTextField();
		tfMovieGenre.setColumns(10);
		tfMovieGenre.setBounds(514, 277, 111, 20);
		moviePanel.add(tfMovieGenre);
		
		JTextField tfMtags2 = new JTextField();
		tfMtags2.setColumns(10);
		tfMtags2.setBounds(539, 96, 68, 20);
		moviePanel.add(tfMtags2);
		
		JTextField tfMtag3 = new JTextField();
		tfMtag3.setColumns(10);
		tfMtag3.setBounds(608, 96, 68, 20);
		moviePanel.add(tfMtag3);
		
		JButton addMovieBtn = new JButton("Add Movie");
		addMovieBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//no blank boxes
				if(tfMTitle.getText().trim().equals("")||tfMAuthor.getText().trim().equals("")||tfMYear.getText().trim().equals("")||tfMovieGenre.getText().trim().equals("")||tfMtags.getText().trim().equals("")||tfMtags2.getText().trim().equals("")||tfMtag3.getText().trim().equals("")||tfRuntime.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Fields Blank");
				}
				//no commas
				else if(tfMTitle.getText().trim().contains(",")||tfMAuthor.getText().trim().contains(",")||tfMYear.getText().trim().contains(",")||tfMovieGenre.getText().trim().contains(",") ||tfMtags.getText().trim().contains(",")||tfMtags.getText().trim().contains(",")||tfMtag3.getText().trim().contains(",")||tfRuntime.getText().trim().contains(",")) {
					showMessageDialog(null,"Fields cannot contain ','");
				}
				// must have valid year
				else if(isANumber(tfMYear.getText())==false|| tfMYear.getText().length()>4||tfMYear.getText().length()<1) {
					showMessageDialog(null,"Publish Year must be a valid Whole number less than 5 digits long");
				}
				//runtime must be less than 6 digits long
				else if(isANumber(tfRuntime.getText())==false|| tfRuntime.getText().length()>6||tfRuntime.getText().length()<1) {
					showMessageDialog(null,"Runtime Year must be a valid number less than 6 digits long");
				}
				//make the movie and add to database
				else {
					
					String ultimateTag = tfMtags.getText().trim().replaceAll("'", "''")+","+tfMtags2.getText().trim().replaceAll("'", "''")+","+tfMtag3.getText().trim().replaceAll("'", "''");
					Movie newMovie = new Movie("movie",tfMTitle.getText().trim().replaceAll("'", "''"),tfMovieGenre.getText().trim().replaceAll("'", "''"),tfMAuthor.getText().trim().replaceAll("'", "''"),Integer.parseInt(tfMYear.getText().trim()),Double.parseDouble(tfRuntime.getText().trim()),trueRating,ultimateTag);
					
					try {
						//gets all from users media table
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						ResultSet query= stmt.executeQuery("SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND  Lower(Type)= 'movie' AND  Lower(Title)= '"+newMovie.getTitle().toLowerCase()+"'AND  Lower(Author)= '"+newMovie.getAuthor().toLowerCase()+"' AND  publish= "+newMovie.getYear()+"  ");
					 
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//while user has media add it to the row in specific format
						if(query.next()) {
							showMessageDialog(null, "You already have this media in your library");
							
						}
						else {
							newMovie.addMedia();
							//reset text fields
							tfMTitle.setText("");
							tfMovieGenre.setText("");
							tfMAuthor.setText("");
							tfMYear.setText("");
							tfMtags.setText("");
							tfMtags2.setText("");
							tfMtag3.setText("");
							tfRuntime.setText("");
							ratingSlider.setValue(0);
							showMessageDialog(null,"Movie has been Added!");	
						}
			
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			
			}
			}
		});
		addMovieBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addMovieBtn.setBounds(265, 321, 122, 35);
		moviePanel.add(addMovieBtn);
	
	}
	
	/**
	 * @author el0034:  create panel for TV
	 */
	public void setTVShowPanel() {
		// Create panel
		tvShowPanel = new JPanel();
		tvShowPanel.setBounds(55, 143, 686, 380);
		tvShowPanel.setBackground(new Color(128, 128, 128));
		tvShowPanel.setLayout(null);
		
		// Create labels 
		JLabel lblTitle = new JLabel("TV Show Title:");
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(26, 50, 135, 35);
		tvShowPanel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("TV Show Creator:");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setBounds(26, 147, 157, 35);
		tvShowPanel.add(lblAuthor);
		
		JLabel lblYear = new JLabel("Premiere Year:");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYear.setForeground(Color.WHITE);
		lblYear.setBounds(26, 243, 201, 35);
		tvShowPanel.add(lblYear);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenre.setForeground(Color.WHITE);
		lblGenre.setBounds(296, 85, 122, 35);
		tvShowPanel.add(lblGenre);
		
		JLabel lblRating = new JLabel("Your Rating");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRating.setForeground(Color.WHITE);
		lblRating.setBounds(272, 182, 122, 35);
		tvShowPanel.add(lblRating);
		
		JLabel lblTags = new JLabel("TV Show Tags:");
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTags.setForeground(Color.WHITE);
		lblTags.setBounds(500, 50, 166, 35);
		tvShowPanel.add(lblTags);
		
		JLabel lblContinue = new JLabel("Is the show still airing?:");
		lblContinue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContinue.setForeground(Color.WHITE);
		lblContinue.setBounds(467, 158, 209, 35);
		tvShowPanel.add(lblContinue);
		
		JLabel lblNumberOfSeason = new JLabel("Number of seasons:");
		lblNumberOfSeason.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumberOfSeason.setForeground(Color.WHITE);
		lblNumberOfSeason.setBounds(487, 242, 209, 35);
		tvShowPanel.add(lblNumberOfSeason);
		
		//author@alanham: Rating Slider
		JSlider ratingSlider = new JSlider();
		ratingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//gets value of what the slider is at
				setRating(ratingSlider.getValue());
			}
		});
		
		//author@alanham: more css for slider
		ratingSlider.setPaintTicks(true);
		ratingSlider.setPaintLabels(true);
		ratingSlider.setSnapToTicks(true);
		ratingSlider.setValue(0);
		ratingSlider.setToolTipText("");
		ratingSlider.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		ratingSlider.setMajorTickSpacing(5);
		ratingSlider.setMinorTickSpacing(1);
		ratingSlider.setForeground(new Color(0, 0, 0));
		ratingSlider.setBackground(new Color(216, 191, 216));
		ratingSlider.setMaximum(5);
		ratingSlider.setBounds(219, 224, 220, 46);
		tvShowPanel.add(ratingSlider);
		
		// Create text fields for information
		JTextField tfTitle = new JTextField();
		tfTitle.setBounds(26, 85, 122, 20);
		tvShowPanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JTextField tfAuthor = new JTextField();
		tfAuthor.setColumns(10);
		tfAuthor.setBounds(26, 186, 135, 20);
		tvShowPanel.add(tfAuthor);
		
		JTextField tfYear = new JTextField();
		tfYear.setColumns(10);
		tfYear.setBounds(26, 276, 122, 20);
		tvShowPanel.add(tfYear);
		
		JTextField tfTag1 = new JTextField();
		tfTag1.setColumns(10);
		tfTag1.setBounds(453, 85, 54, 20);
		tvShowPanel.add(tfTag1);
		
		JTextField tfTag2 = new JTextField();
		tfTag2.setColumns(10);
		tfTag2.setBounds(532, 85, 54, 20);
		tvShowPanel.add(tfTag2);
		
		JTextField tfTag3 = new JTextField();
		tfTag3.setColumns(10);
		tfTag3.setBounds(612, 85, 54, 20);
		tvShowPanel.add(tfTag3);
		
		JTextField tfNoOfSeasons = new JTextField();
		tfNoOfSeasons.setColumns(10);
		tfNoOfSeasons.setBounds(484, 276, 157, 20);
		tvShowPanel.add(tfNoOfSeasons); 
		
		JTextField tfGenre = new JTextField();
		tfGenre.setColumns(10);
		tfGenre.setBounds(268, 121, 111, 20);
		tvShowPanel.add(tfGenre); 
		
	    // Sees if new episodes are being created
		JRadioButton radioBtnAiring = new JRadioButton("Yes");
		radioBtnAiring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioBtnAiring.isSelected()) {
					airing="true";
				}
				else {
					airing="false";
				}
			}
		});
		radioBtnAiring.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioBtnAiring.setForeground(Color.white);
		radioBtnAiring.setBackground(new Color(128, 128, 128));
		radioBtnAiring.setBounds(529, 189, 122, 35);
		tvShowPanel.add(radioBtnAiring);
		
		//button to add the show
		JButton addShowBtn = new JButton("Add TV Show");
		addShowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			
				//Text fields cannot be null
				if(tfTitle.getText().trim().equals("")||tfAuthor.getText().trim().equals("")||tfYear.getText().trim().equals("")|| tfGenre.getText().trim().equals("") || tfTag1.getText().trim().equals("")||tfTag2.getText().trim().equals("")||tfTag3.getText().trim().equals("")||tfNoOfSeasons.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Fields Blank");
					
				}
				//Text cannot contain commas
				else if(tfTitle.getText().trim().contains(",")||tfAuthor.getText().trim().contains(",")||tfYear.getText().trim().contains(",")||tfGenre.getText().trim().contains(",") ||tfTag1.getText().trim().contains(",")||tfTag2.getText().trim().contains(",")||tfTag3.getText().trim().contains(",")||tfNoOfSeasons.getText().trim().contains(",")) {
					showMessageDialog(null,"Fields cannot contain ','");
				}
				//year must be valid
				else if(isANumber(tfYear.getText())==false|| tfYear.getText().length()>4||tfYear.getText().length()<1) {
					showMessageDialog(null,"Publish Year must be a valid number less than 5 digits long");
					
				}
				//seasons cannot exceed 1000
				else if(isANumber(tfNoOfSeasons.getText())==false|| tfNoOfSeasons.getText().length()>4||tfNoOfSeasons.getText().length()<1|| tfNoOfSeasons.getText().charAt(0)=='0') {
					showMessageDialog(null,"Number of Seasons must be a valid number less than 4 digits long");
					
				}
				//add tvshow to database
				else {
					
					String ultimateTag = tfTag1.getText().trim().replaceAll("'", "''")+","+tfTag2.getText().trim().replaceAll("'", "''")+","+tfTag3.getText().trim().replaceAll("'", "''");
					TVShow newShow = new TVShow("tvshow",tfTitle.getText().trim().replaceAll("'", "''"),tfGenre.getText().trim().replaceAll("'", "''"),tfAuthor.getText().trim().replaceAll("'", "''"),Integer.parseInt(tfYear.getText().trim()),Integer.parseInt(tfNoOfSeasons.getText().trim()),airing, trueRating,ultimateTag);
				 	try {
						//gets all from users media table
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						ResultSet query= stmt.executeQuery("SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND  Lower(Type)= 'tvshow' AND  Lower(Title)= '"+newShow.getTitle().toLowerCase()+"'AND  Lower(Author)= '"+newShow.getAuthor().toLowerCase()+"' AND  publish= "+newShow.getYear()+"  ");
					 
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//while user has media add it to the row in specific format
						if(query.next()) {
							showMessageDialog(null, "You already have this media in your library");
							
						}
						else {
							newShow.addMedia();
							showMessageDialog(null,"Show has been Added!");
							
							//reset text fields
							tfTitle.setText("");
						 	tfGenre.setText("");
							tfAuthor.setText("");
							tfYear.setText("");
							tfTag1.setText("");
							tfTag2.setText("");
							tfTag3.setText("");
							tfNoOfSeasons.setText("");
							ratingSlider.setValue(0);
							radioBtnAiring.setSelected(false);	
						}
						
						
						
					
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			
					
					}
			}
		});
		
		addShowBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addShowBtn.setBounds(257, 329, 137, 35);
		tvShowPanel.add(addShowBtn);		
	}
	/**
	 * @author el0034: Create Game Panel
	 */
	public void setGamePanel() {
		//Initialize game panel
		gamePanel= new JPanel();
		gamePanel.setBounds(55, 143, 686, 380);
		gamePanel.setBackground(new Color(128, 128, 128));
		gamePanel.setLayout(null);
		
		// Create labels 
		JLabel lblTitle = new JLabel("Game Title:");
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(38, 50, 135, 35);
		gamePanel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Developer:");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setBounds(41, 148, 157, 35);
		gamePanel.add(lblAuthor);
		
		JLabel lblYear = new JLabel("Release Year:");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblYear.setForeground(Color.WHITE);
		lblYear.setBounds(26, 243, 201, 35);
		gamePanel.add(lblYear);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenre.setForeground(Color.WHITE);
		lblGenre.setBounds(296, 85, 122, 35);
		gamePanel.add(lblGenre);
		
		JLabel lblRating = new JLabel("Your Rating");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRating.setForeground(Color.WHITE);
		lblRating.setBounds(272, 182, 122, 35);
		gamePanel.add(lblRating);
		
		JLabel lblTags = new JLabel("Game Tags:");
		lblTags.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTags.setForeground(Color.WHITE);
		lblTags.setBounds(500, 50, 166, 35);
		gamePanel.add(lblTags);
		
		JLabel lblContinue = new JLabel("Does it have replayabilty:");
		lblContinue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContinue.setForeground(Color.WHITE);
		lblContinue.setBounds(462, 148, 209, 35);
		gamePanel.add(lblContinue);
		
		JLabel lblPlatformType = new JLabel("Platform Type:");
		lblPlatformType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlatformType.setForeground(Color.WHITE);
		lblPlatformType.setBounds(490, 243, 209, 35);
		gamePanel.add(lblPlatformType);
		
		//Rating Slider
		JSlider ratingSlider = new JSlider();
		ratingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//gets value of what the slider is at
				setRating(ratingSlider.getValue());
			}
		});
		
		//more css for slider
		ratingSlider.setPaintTicks(true);
		ratingSlider.setPaintLabels(true);
		ratingSlider.setSnapToTicks(true);
		ratingSlider.setValue(0);
		ratingSlider.setToolTipText("");
		ratingSlider.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		ratingSlider.setMajorTickSpacing(5);
		ratingSlider.setMinorTickSpacing(1);
		ratingSlider.setForeground(new Color(0, 0, 0));
		ratingSlider.setBackground(new Color(216, 191, 216));
		ratingSlider.setMaximum(5);
		ratingSlider.setBounds(219, 224, 220, 46);
		gamePanel.add(ratingSlider);
		
		// Create text fields for information
		JTextField tfTitle = new JTextField();
		tfTitle.setBounds(26, 85, 122, 20);
		gamePanel.add(tfTitle);
		tfTitle.setColumns(10);
		
		JTextField tfAuthor = new JTextField();
		tfAuthor.setColumns(10);
		tfAuthor.setBounds(26, 186, 135, 20);
		gamePanel.add(tfAuthor);
		
		JTextField tfYear = new JTextField();
		tfYear.setColumns(10);
		tfYear.setBounds(26, 276, 122, 20);
		gamePanel.add(tfYear);
		
		JTextField tfTag1 = new JTextField();
		tfTag1.setColumns(10);
		tfTag1.setBounds(453, 85, 54, 20);
		gamePanel.add(tfTag1);
		
		JTextField tfTag2 = new JTextField();
		tfTag2.setColumns(10);
		tfTag2.setBounds(532, 85, 54, 20);
		gamePanel.add(tfTag2);
		
		JTextField tfTag3 = new JTextField();
		tfTag3.setColumns(10);
		tfTag3.setBounds(612, 85, 54, 20);
		gamePanel.add(tfTag3);
		
		JTextField tfPlatformType = new JTextField();
		tfPlatformType.setColumns(10);
		tfPlatformType.setBounds(474, 276, 157, 20);
		gamePanel.add(tfPlatformType); 
		
		JTextField tfGenre = new JTextField();
		tfGenre.setColumns(10);
		tfGenre.setBounds(268, 121, 111, 20);
		gamePanel.add(tfGenre); 
		
	    // Sees if new episodes are being created
		JRadioButton radioBtnReplay = new JRadioButton("Yes");
		radioBtnReplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioBtnReplay.isSelected()) {
					hasReplayAbility="true";
				}
				else {
					hasReplayAbility="false";
				}
			}
		});
		radioBtnReplay.setFont(new Font("Tahoma", Font.BOLD, 14));
		radioBtnReplay.setForeground(Color.white);
		radioBtnReplay.setBackground(new Color(128, 128, 128));
		radioBtnReplay.setBounds(522, 189, 122, 35);
		gamePanel.add(radioBtnReplay);
		
		//button to add the game
		JButton addGameBtn = new JButton("Add Game");
		addGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				//Text fields cannot be null
				if(tfTitle.getText().trim().equals("")||tfAuthor.getText().trim().equals("")||tfYear.getText().trim().equals("")||tfGenre.getText().trim().equals("") || tfTag1.getText().trim().equals("")||tfTag2.getText().trim().equals("")||tfTag3.getText().trim().equals("")||tfPlatformType.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Fields Blank");
				}
				//Texts cannot have commas
				else if(tfTitle.getText().trim().contains(",")||tfAuthor.getText().trim().contains(",")||tfGenre.getText().trim().contains(",") ||tfYear.getText().trim().contains(",")||tfTag1.getText().trim().contains(",")||tfTag2.getText().trim().contains(",")||tfTag3.getText().trim().contains(",")||tfPlatformType.getText().trim().contains(",")) {
					showMessageDialog(null,"Fields cannot contain ','");
				}
				//year must be a number <1000 and >0
				else if(isANumber(tfYear.getText())==false|| tfYear.getText().length()>4||tfYear.getText().length()<1) {
					showMessageDialog(null,"Publish Year must be a valid number less than 5 digits long");	
				}
				//create the game and add to database
				else {
					
					//add this to the list
				
					String ultimateTag = tfTag1.getText().trim().replaceAll("'", "''")+","+tfTag2.getText().trim().replaceAll("'", "''")+","+tfTag3.getText().trim().replaceAll("'", "''");
					Game newGame = new Game("game",tfTitle.getText().trim().replaceAll("'", "''"),tfGenre.getText().trim().replaceAll("'", "''"),tfAuthor.getText().trim().replaceAll("'", "''"),Integer.parseInt(tfYear.getText().trim()),trueRating,ultimateTag,hasReplayAbility,tfPlatformType.getText().trim());
					
					try {
						//gets all from users media table
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						ResultSet query= stmt.executeQuery("SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND  Lower(Type)= 'game' AND  Lower(Title)= '"+newGame.getTitle().toLowerCase()+"'AND  Lower(Author)= '"+newGame.getAuthor().toLowerCase()+"' AND  publish= "+newGame.getYear()+"  ");
					 
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//while user has media add it to the row in specific format
						if(query.next()) {
							showMessageDialog(null, "You already have this media in your library");
							
						}
						else {
							newGame.addMedia();
							showMessageDialog(null,"Game has been Added!");
							
							//reset text fields
							tfTitle.setText("");
							tfGenre.setText("");
							tfAuthor.setText("");
							tfYear.setText("");
							tfTag1.setText("");
							tfTag2.setText("");
							tfTag3.setText("");
							tfPlatformType.setText("");
							ratingSlider.setValue(0);
							radioBtnReplay.setSelected(false);
							
						}
						
						
						
					
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}	
			
			
				}
			}
		});
		addGameBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addGameBtn.setBounds(257, 329, 137, 35);
		gamePanel.add(addGameBtn);		
	}
	
	//Helper method sets rating for an object
	public void setRating(int value) {
		trueRating = value;
	}
	//Helper method checks 
	public boolean isANumber(String presumedNumber) {
		int decimalCount=0;
		for(int i =0;i<presumedNumber.length();i++ ) {
			if(!Character.isDigit(presumedNumber.charAt(i)) && presumedNumber.charAt(i)!='.') {
				return false;
			}
			if(presumedNumber.charAt(i)=='.') {
				decimalCount++;
			}
			
		}
		if(decimalCount!=1 && decimalCount!=0) {
			return false;
		}
		return true;	
	}
	
}


