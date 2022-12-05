// author@el0034

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ViewList {
	
	JPanel viewListPanel;
	private static JLabel lblPrompt;
	private static CardLayout viewPages;
	private static String allData;
	private static JPanel content;
	private static JPanel comparePanel;
	private static JTable userOneTable;
	private static JTable userTwoTable;
	private static JTable viewTable;
    private static DefaultTableModel viewModel;
    private static DefaultTableModel userOneModel;
    private static DefaultTableModel userTwoModel;
	private JTextField tfSearchTitle;
	private JTextField tfSearchType;
	private JTextField tfUsernameCompare;
	private String user2;
	
	public ViewList() {
		// Create Panel
		viewListPanel = new JPanel();
		viewListPanel.setBounds(100, 100, 787, 600);
		viewListPanel.setBackground(new Color(216, 191, 216));
		viewListPanel.setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(45, 63, 525, 2);
		viewListPanel.add(separator);
		
		//Main panel to display content
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(45, 76, 695, 44);
		viewListPanel.add(panel);
		panel.setLayout(null);
		
		// DELETE THIS EVENTUALLY UNLESS ASHLEY CAN FIGURE IT OUT
		//setComparePanel();
		
		//LabelPrompt /w background
		lblPrompt = new JLabel("Sort, Search, or Compare Media");
		lblPrompt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrompt.setBounds(10, 11, 361, 22);
		panel.add(lblPrompt);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(44, 26, 525, 39);
		viewListPanel.add(panel_2);
		panel_2.setLayout(null);
		//Display userslist name
		JLabel viewMediaLabel = new JLabel("Editing "+MyLoginPage.getActiveUser() +"'s List");
		viewMediaLabel.setBounds(10, 0, 667, 34);
		panel_2.add(viewMediaLabel);
		viewMediaLabel.setForeground(Color.WHITE);
		viewMediaLabel.setBackground(new Color(255, 255, 255));
		viewMediaLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		// Create sql statment
		allData="SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"'";
		
		
		//Cardlayout to isolate the data we want when updating
		 viewPages = new CardLayout();	
		//creating a main panel to switch child panels on
		content = new JPanel();
		content.setBounds(10, 120, 764, 441);
		content.setLayout(viewPages);
		viewListPanel.add(content);
		//Two panels to display our tables
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 120, 764, 441);
		panel_3.setLayout(null);
		content.add(panel_3, "SortMedia");
		viewPages.show(content,"SortMedia");

	//	content.add(comparePanel, "CompareMedia");

		
		//Main Table gui and definitions
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 39, 744, 260);
		panel_3.add(scrollPane);
		viewTable = new JTable();
		viewTable.setForeground(new Color(0, 0, 0));
		viewTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		viewTable.setBackground(new Color(245, 245, 245));
		viewTable.setSurrendersFocusOnKeystroke(true);
		viewTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(viewTable);
		viewTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		viewTable.getTableHeader().setReorderingAllowed(false);
		//initializing the view only and select table
		viewTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Title", "Author/Developer", "Publish Year", "Genre", "Rating", "Tag 1", "Tag 2", "Tag 3", "ISBN", "Runtime", "Seasons", "Series ", "Platform", "Has Replayability", "Currently Airing"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//shouldn't be able to edit this table with this
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//more GUI assignments for the column dimensions for view table
		viewTable.getColumnModel().getColumn(0).setResizable(false);
		viewTable.getColumnModel().getColumn(0).setMinWidth(75);
		viewTable.getColumnModel().getColumn(1).setResizable(false);
		viewTable.getColumnModel().getColumn(1).setPreferredWidth(114);
		viewTable.getColumnModel().getColumn(1).setMinWidth(114);
		viewTable.getColumnModel().getColumn(2).setResizable(false);
		viewTable.getColumnModel().getColumn(2).setPreferredWidth(118);
		viewTable.getColumnModel().getColumn(2).setMinWidth(118);
		viewTable.getColumnModel().getColumn(3).setResizable(false);
		viewTable.getColumnModel().getColumn(3).setMinWidth(75);
		viewTable.getColumnModel().getColumn(4).setResizable(false);
		viewTable.getColumnModel().getColumn(4).setMinWidth(75);
		viewTable.getColumnModel().getColumn(5).setResizable(false);
		viewTable.getColumnModel().getColumn(5).setMinWidth(75);
		viewTable.getColumnModel().getColumn(6).setResizable(false);
		viewTable.getColumnModel().getColumn(6).setMinWidth(75);
		viewTable.getColumnModel().getColumn(7).setResizable(false);
		viewTable.getColumnModel().getColumn(7).setMinWidth(75);
		viewTable.getColumnModel().getColumn(8).setResizable(false);
		viewTable.getColumnModel().getColumn(8).setMinWidth(75);
		viewTable.getColumnModel().getColumn(9).setResizable(false);
		viewTable.getColumnModel().getColumn(9).setPreferredWidth(100);
		viewTable.getColumnModel().getColumn(9).setMinWidth(100);
		viewTable.getColumnModel().getColumn(10).setResizable(false);
		viewTable.getColumnModel().getColumn(10).setPreferredWidth(65);
		viewTable.getColumnModel().getColumn(10).setMinWidth(65);
		viewTable.getColumnModel().getColumn(11).setResizable(false);
		viewTable.getColumnModel().getColumn(11).setPreferredWidth(56);
		viewTable.getColumnModel().getColumn(11).setMinWidth(56);
		viewTable.getColumnModel().getColumn(12).setResizable(false);
		viewTable.getColumnModel().getColumn(12).setMinWidth(75);
		viewTable.getColumnModel().getColumn(13).setResizable(false);
		viewTable.getColumnModel().getColumn(13).setPreferredWidth(90);
		viewTable.getColumnModel().getColumn(13).setMinWidth(90);
		viewTable.getColumnModel().getColumn(14).setResizable(false);
		viewTable.getColumnModel().getColumn(14).setPreferredWidth(88);
		viewTable.getColumnModel().getColumn(14).setMinWidth(88);
		viewTable.getColumnModel().getColumn(15).setResizable(false);
		viewTable.getColumnModel().getColumn(15).setPreferredWidth(84);
		viewTable.getColumnModel().getColumn(15).setMinWidth(84);
		viewModel = (DefaultTableModel) viewTable.getModel();
		
		viewTable.setAutoCreateRowSorter(true);
		
		// Create label for direction
		JLabel lblSearchTitle = new JLabel("Search List by Title:");
		lblSearchTitle.setBounds(174, 309, 151, 14);
		panel_3.add(lblSearchTitle);
		// Create button to search title
		JButton btnSearchTitle = new JButton("Search");
		btnSearchTitle.setBounds(174, 379, 96, 20);
		panel_3.add(btnSearchTitle);
		btnSearchTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewModel.setRowCount(0);
				String searchTitle="SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND LOWER(Title) LIKE '%"+tfSearchTitle.getText().toLowerCase().replaceAll("'", "''")+"%'";
				populateTable(searchTitle, viewModel);
				tfSearchTitle.setText("");
			}
		});
		// create text field to get title
		tfSearchTitle = new JTextField();
		tfSearchTitle.setBounds(174, 334, 96, 20);
		panel_3.add(tfSearchTitle);
		tfSearchTitle.setColumns(10);
		// create label for search type
		JLabel lblSearchType = new JLabel("Search List by Type:");
		lblSearchType.setBounds(10, 309, 138, 14);
		panel_3.add(lblSearchType);
		// create text field for search type
		tfSearchType = new JTextField();
		tfSearchType.setColumns(10);
		tfSearchType.setBounds(14, 334, 96, 20);
		panel_3.add(tfSearchType);
		// create button for searching type
		JButton btnSearchType = new JButton("Search");
		btnSearchType.setBounds(14, 379, 96, 20);
		panel_3.add(btnSearchType);
		btnSearchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewModel.setRowCount(0);
				String searchType="SELECT * FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"'AND LOWER(Type)='"+tfSearchType.getText().toLowerCase().replaceAll("'", "''")+"'";
				populateTable(searchType, viewModel);
				tfSearchType.setText("");
			}
		});
		// button to compare lists
		JButton btnCompare = new JButton("Compare");
		btnCompare.setBounds(561, 378, 89, 23);
		panel_3.add(btnCompare);
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUsernameCompare.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Fields Blank");
				}
				else {
					// Create compare panel
					setComparePanel();
					content.add(comparePanel,"CompareMedia");
					viewPages.show(content,"CompareMedia");
					user2= tfUsernameCompare.getText().replaceAll("'", "''");
					tfUsernameCompare.setText("");
				}
				
			}
		});
		// directions for comparing
		JLabel lblCompare = new JLabel("Enter username of user you can to compare lists with:");
		lblCompare.setBounds(430, 300, 324, 33);
		panel_3.add(lblCompare);
		// get user name to compare with
		tfUsernameCompare = new JTextField();
		tfUsernameCompare.setBounds(561, 334, 96, 20);
		panel_3.add(tfUsernameCompare);
		tfUsernameCompare.setColumns(10);
		// reset the sorting
		JButton btnReset = new JButton("Reset Sort");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPopulate();
			}
		});
		
		btnReset.setBounds(320, 377, 105, 23);
		panel_3.add(btnReset);
		
		JLabel lblClickAColumn = new JLabel("Click a column to sort by it!");
		lblClickAColumn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClickAColumn.setBounds(243, 7, 361, 22);
		panel_3.add(lblClickAColumn);
		
		//button to return to my page
		JButton btnReturnMyPage = new JButton("Return to MyPage");
		btnReturnMyPage.setBounds(581, 11, 178, 44);
		btnReturnMyPage.setBackground(new Color(211, 211, 211));
		btnReturnMyPage.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
					viewPages.show(content,"SortMedia");
					Home.switchToHome();
					Home.modelLeaderboard.setRowCount(0);
					Home.populateLeaderboardTable();
					}
				});
		btnReturnMyPage.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewListPanel.add(btnReturnMyPage);
	}
	
	// Method to create the Compare Panel
	public void setComparePanel() {
		comparePanel = new JPanel();
		comparePanel.setBounds(10, 120, 764, 441);
		comparePanel.setLayout(null);
		lblPrompt.setText("Compare Media Lists");
		// Create labels
		JLabel lblUserTwoList = new JLabel(tfUsernameCompare.getText()+"'s List");
		lblUserTwoList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserTwoList.setBounds(10, 231, 261, 26);
		comparePanel.add(lblUserTwoList);
		
		JLabel lblUserOneList = new JLabel(MyLoginPage.getActiveUser()+"'s List");
		lblUserOneList.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserOneList.setBounds(10, 25, 231, 14);
		comparePanel.add(lblUserOneList);
		// Create back button
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPages.show(content, "SortMedia");
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(563, 11, 154, 23);
		comparePanel.add(btnBack);
		
	
		//Main Table gui and definitions
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 50, 744, 170);
		comparePanel.add(scrollPane);
		userOneTable = new JTable();
		userOneTable.setForeground(new Color(0, 0, 0));
		userOneTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userOneTable.setBackground(new Color(245, 245, 245));
		userOneTable.setSurrendersFocusOnKeystroke(true);
		userOneTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(userOneTable);
		userOneTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		userOneTable.getTableHeader().setReorderingAllowed(false);
		//initializing the view only and select table
		userOneTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Title", "Author/Developer", "Publish Year", "Genre", "Rating", "Tag 1", "Tag 2", "Tag 3", "ISBN", "Runtime", "Seasons", "Series ", "Platform", "Has Replayability", "Currently Airing"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//shouldn't be able to edit this table with this
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//more GUI assignments for the column dimensions for view table
		userOneTable.getColumnModel().getColumn(0).setResizable(false);
		userOneTable.getColumnModel().getColumn(0).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(1).setResizable(false);
		userOneTable.getColumnModel().getColumn(1).setPreferredWidth(114);
		userOneTable.getColumnModel().getColumn(1).setMinWidth(114);
		userOneTable.getColumnModel().getColumn(2).setResizable(false);
		userOneTable.getColumnModel().getColumn(2).setPreferredWidth(118);
		userOneTable.getColumnModel().getColumn(2).setMinWidth(118);
		userOneTable.getColumnModel().getColumn(3).setResizable(false);
		userOneTable.getColumnModel().getColumn(3).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(4).setResizable(false);
		userOneTable.getColumnModel().getColumn(4).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(5).setResizable(false);
		userOneTable.getColumnModel().getColumn(5).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(6).setResizable(false);
		userOneTable.getColumnModel().getColumn(6).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(7).setResizable(false);
		userOneTable.getColumnModel().getColumn(7).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(8).setResizable(false);
		userOneTable.getColumnModel().getColumn(8).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(9).setResizable(false);
		userOneTable.getColumnModel().getColumn(9).setPreferredWidth(100);
		userOneTable.getColumnModel().getColumn(9).setMinWidth(100);
		userOneTable.getColumnModel().getColumn(10).setResizable(false);
		userOneTable.getColumnModel().getColumn(10).setPreferredWidth(65);
		userOneTable.getColumnModel().getColumn(10).setMinWidth(65);
		userOneTable.getColumnModel().getColumn(11).setResizable(false);
		userOneTable.getColumnModel().getColumn(11).setPreferredWidth(56);
		userOneTable.getColumnModel().getColumn(11).setMinWidth(56);
		userOneTable.getColumnModel().getColumn(12).setResizable(false);
		userOneTable.getColumnModel().getColumn(12).setMinWidth(75);
		userOneTable.getColumnModel().getColumn(13).setResizable(false);
		userOneTable.getColumnModel().getColumn(13).setPreferredWidth(90);
		userOneTable.getColumnModel().getColumn(13).setMinWidth(90);
		userOneTable.getColumnModel().getColumn(14).setResizable(false);
		userOneTable.getColumnModel().getColumn(14).setPreferredWidth(88);
		userOneTable.getColumnModel().getColumn(14).setMinWidth(88);
		userOneTable.getColumnModel().getColumn(15).setResizable(false);
		userOneTable.getColumnModel().getColumn(15).setPreferredWidth(84);
		userOneTable.getColumnModel().getColumn(15).setMinWidth(84);
		userOneModel = (DefaultTableModel) userOneTable.getModel();
		
		userOneTable.setAutoCreateRowSorter(true);
		
		//Main Table gui and definitions
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBounds(10, 260, 744, 170);
		comparePanel.add(scrollPane2);
		userTwoTable = new JTable();
		userTwoTable.setForeground(new Color(0, 0, 0));
		userTwoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTwoTable.setBackground(new Color(245, 245, 245));
		userTwoTable.setSurrendersFocusOnKeystroke(true);
		userTwoTable.setFillsViewportHeight(true);
		scrollPane2.setViewportView(userTwoTable);
		userTwoTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		userTwoTable.getTableHeader().setReorderingAllowed(false);
		//initializing the view only and select table
		userTwoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type", "Title", "Author/Developer", "Publish Year", "Genre", "Rating", "Tag 1", "Tag 2", "Tag 3", "ISBN", "Runtime", "Seasons", "Series ", "Platform", "Has Replayability", "Currently Airing"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//shouldn't be able to edit this table with this
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//more GUI assignments for the column dimensions for view table
			userTwoTable.getColumnModel().getColumn(0).setResizable(false);
			userTwoTable.getColumnModel().getColumn(0).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(1).setResizable(false);
			userTwoTable.getColumnModel().getColumn(1).setPreferredWidth(114);
			userTwoTable.getColumnModel().getColumn(1).setMinWidth(114);
			userTwoTable.getColumnModel().getColumn(2).setResizable(false);
			userTwoTable.getColumnModel().getColumn(2).setPreferredWidth(118);
			userTwoTable.getColumnModel().getColumn(2).setMinWidth(118);
			userTwoTable.getColumnModel().getColumn(3).setResizable(false);
			userTwoTable.getColumnModel().getColumn(3).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(4).setResizable(false);
			userTwoTable.getColumnModel().getColumn(4).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(5).setResizable(false);
			userTwoTable.getColumnModel().getColumn(5).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(6).setResizable(false);
			userTwoTable.getColumnModel().getColumn(6).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(7).setResizable(false);
			userTwoTable.getColumnModel().getColumn(7).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(8).setResizable(false);
			userTwoTable.getColumnModel().getColumn(8).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(9).setResizable(false);
			userTwoTable.getColumnModel().getColumn(9).setPreferredWidth(100);
			userTwoTable.getColumnModel().getColumn(9).setMinWidth(100);
			userTwoTable.getColumnModel().getColumn(10).setResizable(false);
			userTwoTable.getColumnModel().getColumn(10).setPreferredWidth(65);
			userTwoTable.getColumnModel().getColumn(10).setMinWidth(65);
			userTwoTable.getColumnModel().getColumn(11).setResizable(false);
			userTwoTable.getColumnModel().getColumn(11).setPreferredWidth(56);
			userTwoTable.getColumnModel().getColumn(11).setMinWidth(56);
			userTwoTable.getColumnModel().getColumn(12).setResizable(false);
			userTwoTable.getColumnModel().getColumn(12).setMinWidth(75);
			userTwoTable.getColumnModel().getColumn(13).setResizable(false);
			userTwoTable.getColumnModel().getColumn(13).setPreferredWidth(90);
			userTwoTable.getColumnModel().getColumn(13).setMinWidth(90);
			userTwoTable.getColumnModel().getColumn(14).setResizable(false);
			userTwoTable.getColumnModel().getColumn(14).setPreferredWidth(88);
			userTwoTable.getColumnModel().getColumn(14).setMinWidth(88);
			userTwoTable.getColumnModel().getColumn(15).setResizable(false);
			userTwoTable.getColumnModel().getColumn(15).setPreferredWidth(84);
			userTwoTable.getColumnModel().getColumn(15).setMinWidth(84);
			userTwoModel = (DefaultTableModel) userTwoTable.getModel();
			
			userTwoTable.setAutoCreateRowSorter(true);
			
			// populate the tables
			populateTable(allData,userOneModel);
			String secondUserData="SELECT * FROM MEDIA WHERE Lower(Username)='"+tfUsernameCompare.getText().replaceAll("'", "''")+"'";
			populateTable(secondUserData,userTwoModel);
			
			JButton btnCommonMedia = new JButton("Common Media");
			btnCommonMedia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					userOneModel.setRowCount(0);
					userTwoModel.setRowCount(0);
					String cmQueryUser1 ="SELECT * FROM MEDIA WHERE Lower(Username) ='"+MyLoginPage.getActiveUser()+"' AND Lower(TYPE) IN (SELECT Lower(TYPE) FROM MEDIA WHERE Lower(Username) = '"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(TYPE) HAVING COUNT(*)>1 ) AND Lower(TITLE) IN (SELECT Lower(TITLE) FROM MEDIA WHERE Lower(Username) = '"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(TITLE) HAVING COUNT(*)>1 ) AND Lower(AUTHOR) IN (SELECT Lower(AUTHOR) FROM MEDIA WHERE Lower(Username) ='"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(AUTHOR) HAVING COUNT(*)>1 ) AND PUBLISH IN (SELECT PUBLISH FROM MEDIA WHERE Lower(Username) ='"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY PUBLISH  HAVING COUNT(*)>1 )";
					String cmQueryUser2 ="SELECT * FROM MEDIA WHERE Lower(Username) ='"+user2.toLowerCase()+"' AND Lower(TYPE) IN (SELECT Lower(TYPE) FROM MEDIA WHERE Lower(Username) = '"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(TYPE) HAVING COUNT(*)>1 ) AND Lower(TITLE) IN (SELECT Lower(TITLE) FROM MEDIA WHERE Lower(Username) = '"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(TITLE) HAVING COUNT(*)>1 ) AND Lower(AUTHOR) IN (SELECT Lower(AUTHOR) FROM MEDIA WHERE Lower(Username) ='"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY Lower(AUTHOR) HAVING COUNT(*)>1 ) AND PUBLISH IN (SELECT PUBLISH FROM MEDIA WHERE Lower(Username) ='"+MyLoginPage.getActiveUser()+"' OR Lower(Username) ='"+user2.toLowerCase()+"' GROUP BY PUBLISH  HAVING COUNT(*)>1 )";
					populateTable(cmQueryUser1,userOneModel);
					populateTable(cmQueryUser2,userTwoModel);
					
					if(userOneTable.getRowCount()==0||userTwoTable.getRowCount()==0) {
						showMessageDialog(null, "No Common Media");
						viewPages.show(content, "SortMedia");
					}
					
				}
			});
			btnCommonMedia.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCommonMedia.setBounds(297, 13, 200, 26);
			comparePanel.add(btnCommonMedia);

}
	
	// populate table
	public static void populateTable(String sqlQuery, DefaultTableModel tModel) {
		try {
			//gets all from users media table
			Statement stmt = MyLoginPage.connectTest.conn.createStatement();
			ResultSet query= stmt.executeQuery(sqlQuery);
		 
			MyLoginPage.connectTest.conn.setAutoCommit(false);
			//while user has media add it to the row in specific format
			
			while(query.next()) {
				System.out.println(query.getString("Type")+" "+ query.getString("Title"));
			if(query.getString("Type").equals("book")) {
				tModel.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),query.getString("ISBN"),"","",query.getString("Series"),"","",""
				});
			}
			else if(query.getString("Type").equals("movie")) {
				tModel.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"",query.getString("Runtime"),"","","","",""		
				});					
			}
			else if(query.getString("Type").equals("tvshow")) {
				tModel.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"","",query.getString("Seasons"),"","","",query.getString("Airing")	
				});	
				
			}
			else if(query.getString("Type").equals("game")) {
				tModel.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"","","","",query.getString("Platform"),query.getString("Replayability"),""		
				});
			}
			}
			
		
			//lets the database be affected after a read of info
			MyLoginPage.connectTest.conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
	// reset and populate the table
	public static void resetPopulate() {
		viewModel.setRowCount(0);
		populateTable(allData,viewModel);
	}
}
