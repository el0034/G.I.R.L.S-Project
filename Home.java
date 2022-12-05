/*
 * @author hag0009 el0034 cnt0009 al0001
 */


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Home {
	// Declare variables
	private JPanel contentPane;
	public static JFrame frame1;
	private JTextField txtSearch;
	private static JPanel pages;
	private static CardLayout contentPages;
	static JLabel lblpfpName;
	static JTextPane tpBio;
	private Settings addSettings;
	private AddMediaPage addmediap;
	private ViewList addViewList;
	static DefaultTableModel modelLeaderboard;
	static DefaultTableModel modelUser;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void Homerun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame1.setVisible(true);
					frame1.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Home() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	private void initialize(){
		// Create new panels of each class
		addmediap = new AddMediaPage();
		ViewEditUpdate vieweditupdatepanel = new ViewEditUpdate();
	    addSettings = new Settings();
	    addViewList = new ViewList();
		
	    // Create frame to hold content
		frame1 = new JFrame();
		frame1.setResizable(false);
		frame1.setTitle("G.I.R.L.S");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create cardlayout
		contentPages = new CardLayout();
		
		// Create a main panel to switch child panels on
		pages = new JPanel();
		pages.setBounds(100, 100, 800, 600);
		pages.setLayout(contentPages);
		frame1.getContentPane().add(pages);
		pages.add(addmediap.addMediaPanel, "AddMedia");
		pages.add(vieweditupdatepanel.viewEditUpdatePanel, "ViewEdit");
		pages.add(addSettings.settingsPanel,"Settings");
		pages.add(addViewList.viewListPanel,"ViewList");
		frame1.setBounds(100, 100, 800, 600);
		
		// Create Welcome Panel
		JPanel welcome = new JPanel();
		welcome.setBounds(0, 0, 10, 10);
		welcome.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Add Welcome Panel to card layout
		pages.add(welcome, "Welcome");
		welcome.setLayout(null);
		// Create tabbed pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 136, 764, 414);
		welcome.add(tabbedPane);
		JPanel panel_HomePage = new JPanel();
		tabbedPane.addTab("Homepage", null, panel_HomePage, null);
		panel_HomePage.setLayout(null);
		// Create label for About Us
		JLabel lblNewLabel_2 = new JLabel("About Us");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(328, 40, 100, 22);
		panel_HomePage.add(lblNewLabel_2);
		
		// Panel that goes is below leaderboard
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(15, 79, 210, 288);
		panel_HomePage.add(panel_1);
		
		// Panel that is below Users
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(540, 79, 209, 288);
		panel_HomePage.add(panel_2);
		panel_2.setLayout(null);
		// Create scroll panel for username table
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 209, 288);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane_1);
		// Create table for usernames
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		/*
		 * @author Caitlyn 
		 */
		// Set table properties
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(210);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.BLACK);
		table.setFillsViewportHeight(true);
		table.setBackground(new Color(245, 245, 245));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table);
		panel_1.setLayout(null);
		modelUser=(DefaultTableModel)table.getModel();
		// Create scrollpane for Leaderboard
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 198, 288);
		panel_1.add(scrollPane);
		
		// Create table for displaying leaderboard
		JTable tableLeader = new JTable();
		tableLeader.setForeground(new Color(0, 0, 0));
		tableLeader.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLeader.setBackground(new Color(245, 245, 245));
		tableLeader.setSurrendersFocusOnKeystroke(true);
		tableLeader.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableLeader);
		tableLeader.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		tableLeader.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Media Count"
			}
		) {
			boolean[] columnEditable = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditable[column];
			}
		});
		tableLeader.getColumnModel().getColumn(0).setResizable(false);
		tableLeader.getColumnModel().getColumn(0).setPreferredWidth(110);
		tableLeader.getColumnModel().getColumn(0).setMinWidth(100);
		tableLeader.getColumnModel().getColumn(1).setResizable(false);
		tableLeader.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableLeader.getColumnModel().getColumn(1).setMinWidth(80);
		modelLeaderboard = (DefaultTableModel)tableLeader.getModel();
		
		// Panel that is behind leaderboard
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(51, 51, 51));
		panel_3.setBounds(15, 40, 210, 28);
		panel_HomePage.add(panel_3);
		populateLeaderboardTable();
		populateUserTable();
		
		// Leaderboard label
		JLabel lblLeaderB = new JLabel("Leaderboard");
		panel_3.add(lblLeaderB);
		lblLeaderB.setForeground(new Color(255, 255, 255));
		lblLeaderB.setBackground(new Color(0, 0, 0));
		lblLeaderB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		// Panel that is behind Users
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(51, 51, 51));
		panel_4.setForeground(new Color(51, 51, 51));
		panel_4.setBounds(540, 40, 209, 28);
		panel_HomePage.add(panel_4);
		
		// Users label
		JLabel lblNewLabel_1 = new JLabel("Users");
		panel_4.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel_MyPage = new JPanel();
		tabbedPane.addTab("MyPage", null, panel_MyPage, null);
		panel_MyPage.setLayout(null);
		
		// Getting user's username and name
		String username="";
		String name="";
		try {
			ResultSet query;
			Statement stmt = MyLoginPage.connectTest.conn.createStatement();
			query =	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+"')");
			if(query.next()) {
			username=query.getString("Username");
			name=query.getString("Name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Getting biography
		String bio="";
		try {
			ResultSet query;
			Statement stmt = MyLoginPage.connectTest.conn.createStatement();
			query =	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+"')");
			if(query.next()) {
			bio=query.getString("Bio");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Label for name
	    lblpfpName = new JLabel("Name: "+name);
		lblpfpName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblpfpName.setBounds(151, 70, 212, 20);
		panel_MyPage.add(lblpfpName);

		// Label for username
		JLabel lblpfpUsername = new JLabel("Username: "+username);
		lblpfpUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblpfpUsername.setBounds(151, 25, 288, 29);
		panel_MyPage.add(lblpfpUsername);
		// Label for email
		JLabel lblpfpBio = new JLabel("Bio:");
		lblpfpBio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblpfpBio.setBounds(151, 111, 288, 20);
		panel_MyPage.add(lblpfpBio);
		// Label for Actions
		JLabel lblpfpActions = new JLabel("Actions:");
		lblpfpActions.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpfpActions.setBounds(608, 43, 69, 20);
		panel_MyPage.add(lblpfpActions);
		// Button for View List
		JButton btnEditDeleteMedia = new JButton("Edit/Delete Media");
		btnEditDeleteMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPages.show(pages,"ViewEdit");
				ViewEditUpdate.setCount();
				ViewEditUpdate.populateTable(MyLoginPage.getActiveUser());
			}
		});
		btnEditDeleteMedia.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEditDeleteMedia.setBounds(565, 126, 169, 29);
		panel_MyPage.add(btnEditDeleteMedia);
		
		// Button for Settings
		JButton btnSettings = new JButton("Settings");
		btnSettings.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSettings.setBounds(565, 211, 169, 29);
		panel_MyPage.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPages.show(pages,"Settings");
			}
		});
		
		// Button for add media
		JButton btnAddMedia = new JButton("Add Media");
		btnAddMedia.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddMedia.setBounds(565, 171, 169, 29);
		panel_MyPage.add(btnAddMedia);
		
		JButton btnViewList = new JButton("View List");
		btnViewList.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewList.setBounds(565, 86, 169, 29);
		panel_MyPage.add(btnViewList);
		
		// text area for biography
		tpBio = new JTextPane();
		tpBio.setEditable(false);
		tpBio.setBounds(205, 115, 324, 161);
		tpBio.setBackground(new Color(240, 240, 240));
		tpBio.setFont(new Font("Tahoma", Font.BOLD, 15));
		tpBio.setText(bio);
		panel_MyPage.add(tpBio);
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPages.show(pages,"ViewList");
				ViewList.resetPopulate();
			}
		});
		btnAddMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPages.show(pages,"AddMedia");
			}
		});
		// Panel that goes behind the Main header label
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 11, 764, 73);
		welcome.add(panel);
		
		// Main header label
		JLabel lblNewLabel = new JLabel("Welcome to G.I.R.L.S!");
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		
		// Log out and login button
		JButton btnInOut = new JButton("Login/Log out");
		btnInOut.setForeground(new Color(204, 102, 204));
		btnInOut.setBounds(620, 92, 106, 34);
		welcome.add(btnInOut);
		btnInOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAndLoginPage.CreateAndLoginRun();
				frame1.dispose();
				MyLoginPage.setActiveUser(null);
			}
		});
		btnInOut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		// Search box
		txtSearch = new JTextField();
		txtSearch.setBounds(455, 91, 155, 26);
		welcome.add(txtSearch);
		txtSearch.setColumns(10);
		// Search label
		JLabel lblNewLabel_4 = new JLabel("Search:");
		lblNewLabel_4.setBounds(395, 92, 75, 25);
		welcome.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		// Panel behind the About Us Paragraph
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(235, 91, 295, 233);
		panel_HomePage.add(panel_5);
		panel_5.setBackground(new Color(216, 191, 216));
		panel_5.setLayout(null);
		
		// Text Panel for About Us paragraph
		JTextPane txtpnInTodaysWorld = new JTextPane();
		txtpnInTodaysWorld.setBounds(10, 11, 275, 212);
		panel_5.add(txtpnInTodaysWorld);
		txtpnInTodaysWorld.setBackground(SystemColor.control);
		txtpnInTodaysWorld.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnInTodaysWorld.setEditable(false);
		txtpnInTodaysWorld.setText("In today\u2019s world, consumers engross themselves in various types of media for hours on end. This includes but is not limited to video games, books, movies, and TV shows. In response to this, many fandoms or clubs have been created to support one another in their favorite media. This is a place where a community can be built from people who have similar interests.");
		
		// Pane for How To Use Panel
		JPanel panel_HowToUse = new JPanel();
		tabbedPane.addTab("How To Use", null, panel_HowToUse, null);
		panel_HowToUse.setLayout(null);
		
		// Label for instructions
		JLabel lblInstructions = new JLabel("Instructions:");
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInstructions.setBounds(37, 16, 115, 32);
		panel_HowToUse.add(lblInstructions);
		
		// Text area for the instructions text
		JTextArea taInstructions = new JTextArea();
		taInstructions.setEditable(false);
		taInstructions.setFont(new Font("Tahoma", Font.BOLD, 13));
		taInstructions.setBackground(SystemColor.control);
		taInstructions.setBounds(25, 61, 683, 322);
		taInstructions.setText("This application allows you to create Media Lists that are filled with your favorite books, moives, etc."
				+ "				\nTo add a media to your list, click the MyPage tab and click the Add Media button located on the right."
				+ "				\nA window will pop up and you will be able to select the media type you want to add and then are able to "
				+"              \nfill in the information for the media."
				+ "				\n"
				+ "				\nYou can edit and view your list through the MyPage tab."
				+"              \nYou can edit your account information by clicking the Settings button on the MyPage tab."
				+"              \n"
				+"              \nOn the Homepage the leaderboard is displaying all the users in order of who consumed the most media."
				+"				\nThe user list is displaying all the users that have used the application."
				+"              \nIf you ever run into problems, please email system administrator.");
		panel_HowToUse.add(taInstructions);
		
		
		// Purple panel behind search bar and login/logout button
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 88, 764, 45);
		welcome.add(panel_6);
		panel_6.setBackground(new Color(216, 191, 216));
		contentPages.show(pages, "Welcome");
	}
	// Method to switch back to home
	public static void switchToHome() {
		contentPages.show(pages, "Welcome");
	}
	
	// Method to populate the leaderboard from database
	public static void populateLeaderboardTable() {
		//get accounts in order of most media per account
		Statement stmt;
		try {
			stmt = MyLoginPage.connectTest.conn.createStatement();
			ResultSet query= stmt.executeQuery("SELECT USERNAME, COUNT(TYPE) AS MEDIACOUNT FROM MEDIA GROUP BY USERNAME ORDER BY COUNT(TYPE) DESC");
			MyLoginPage.connectTest.conn.setAutoCommit(false);
			while(query.next()) {
				modelLeaderboard.addRow(new Object[] {query.getString("Username"), query.getString("MEDIACOUNT")		
			});
			}
			MyLoginPage.connectTest.conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
}		
	}
	// Method to populate user table display on home
	public static void populateUserTable() {
		
		//getting all usernames in the database
		Statement stmt;
		try {
			stmt = MyLoginPage.connectTest.conn.createStatement();
			ResultSet query= stmt.executeQuery("SELECT USERNAME FROM ACCOUNTS GROUP BY USERNAME");
			MyLoginPage.connectTest.conn.setAutoCommit(false);
			while(query.next()) {
		
				modelUser.addRow(new Object[] {query.getString("Username")	
			});
			}
			MyLoginPage.connectTest.conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	}
}