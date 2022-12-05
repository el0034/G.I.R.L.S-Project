/**
 * @author al00001
 */

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;


public class ViewEditUpdate {
		//Panels and models to be accessed statically
	  JPanel viewEditUpdatePanel;
	  private static JTable table;
	  private static DefaultTableModel model;
	  private static JPanel update;
	  private static JLabel lblPrompt;
	  private static CardLayout update_delete;
	  private static JPanel content;
	  private static JTable updateRowTable;
	  private static boolean[] columnEditables2;
	  private static Object rowtoadd[];
	/**
	 * Create the application.
	 */
	public ViewEditUpdate() {
	/**
	 * Initialize the contents of the panel
	 */
		
		viewEditUpdatePanel = new JPanel();
		viewEditUpdatePanel.setBounds(100, 100, 787, 600);
		viewEditUpdatePanel.setBackground(new Color(216, 191, 216));
		viewEditUpdatePanel.setLayout(null);
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(45, 63, 525, 2);
		viewEditUpdatePanel.add(separator);
		//Main panel to display content
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(45, 76, 695, 44);
		viewEditUpdatePanel.add(panel);
		panel.setLayout(null);
		
		//LabelPrompt /w background
		lblPrompt = new JLabel("Select a Media to Update or Delete");
		lblPrompt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrompt.setBounds(10, 11, 361, 22);
		panel.add(lblPrompt);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(44, 26, 525, 39);
		viewEditUpdatePanel.add(panel_2);
		panel_2.setLayout(null);
		//Display userslist name
		JLabel viewMediaLabel = new JLabel("Editing "+MyLoginPage.getActiveUser() +"'s List");
		viewMediaLabel.setBounds(10, 0, 667, 34);
		panel_2.add(viewMediaLabel);
		viewMediaLabel.setForeground(Color.WHITE);
		viewMediaLabel.setBackground(new Color(255, 255, 255));
		viewMediaLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		//Carlayout to isolate the data we want when updating
		 update_delete= new CardLayout();	
		//creating a main panel to switch child panels on
		content = new JPanel();
		content.setBounds(10, 120, 764, 441);
		content.setLayout(update_delete);
		viewEditUpdatePanel.add(content);
		//Two panels to display our tables
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 120, 764, 441);
		panel_3.setLayout(null);
		content.add(panel_3, "SelectMedia");
		update_delete.show(content,"SelectMedia");
		update = new JPanel();
		update.setBounds(10, 120, 764, 441);
		update.setLayout(null);
		content.add(update, "UpdateMedia");
		
	
		//Main Table gui and definitions
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 744, 361);
		panel_3.add(scrollPane);
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(245, 245, 245));
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.getTableHeader().setReorderingAllowed(false);
		//initializing the view only and select table
		table.setModel(new DefaultTableModel(
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setMinWidth(114);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(118);
		table.getColumnModel().getColumn(2).setMinWidth(118);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setMinWidth(75);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setMinWidth(75);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setMinWidth(75);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setMinWidth(75);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(8).setMinWidth(75);
		table.getColumnModel().getColumn(9).setResizable(false);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setMinWidth(100);
		table.getColumnModel().getColumn(10).setResizable(false);
		table.getColumnModel().getColumn(10).setPreferredWidth(65);
		table.getColumnModel().getColumn(10).setMinWidth(65);
		table.getColumnModel().getColumn(11).setResizable(false);
		table.getColumnModel().getColumn(11).setPreferredWidth(56);
		table.getColumnModel().getColumn(11).setMinWidth(56);
		table.getColumnModel().getColumn(12).setResizable(false);
		table.getColumnModel().getColumn(12).setMinWidth(75);
		table.getColumnModel().getColumn(13).setResizable(false);
		table.getColumnModel().getColumn(13).setPreferredWidth(90);
		table.getColumnModel().getColumn(13).setMinWidth(90);
		table.getColumnModel().getColumn(14).setResizable(false);
		table.getColumnModel().getColumn(14).setPreferredWidth(88);
		table.getColumnModel().getColumn(14).setMinWidth(88);
		table.getColumnModel().getColumn(15).setResizable(false);
		table.getColumnModel().getColumn(15).setPreferredWidth(84);
		table.getColumnModel().getColumn(15).setMinWidth(84);
		 model = (DefaultTableModel) table.getModel();
		
		 
	 	//Table2/update table definitions and scrollpane
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBounds(10, 11, 744, 148);
		update.add(scrollPane2);
		 updateRowTable= new JTable();
		updateRowTable.setForeground(new Color(0, 0, 0));
		updateRowTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		updateRowTable.setBackground(new Color(245, 245, 245));
		updateRowTable.setSurrendersFocusOnKeystroke(true);
		updateRowTable.setFillsViewportHeight(true);
		updateRowTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		updateRowTable.getTableHeader().setReorderingAllowed(false);
		scrollPane2.setViewportView(updateRowTable);
		
		
			
		//To delete the selected media
		JButton btnDeleteMedia = new JButton("Delete Selected Media");
		btnDeleteMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get selected media
				int rowSelected = table.getSelectedRow();
				if(rowSelected>=0) {
				
				String title = table.getModel().getValueAt(rowSelected, 1).toString();
				String author=table.getModel().getValueAt(rowSelected, 2).toString();
				String type=table.getModel().getValueAt(rowSelected, 0).toString();
				String year=table.getModel().getValueAt(rowSelected, 3).toString();
				
				//comfirmation
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the "+type +" "+title+" "+year+" By "+ author);
				
				//delete from database
				if(input==0) {
					try {
						PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("DELETE FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND Type= '"+type.replaceAll("'","''")+"' AND Author='"+author.replaceAll("'","''")+"' AND Title='"+title.replaceAll("'","''")+"' AND publish="+year.replaceAll("'","''"));
						stmt.executeUpdate();
						model.removeRow(rowSelected);
					} catch (SQLException exc) {
						// TODO Auto-generated catch block
						exc.printStackTrace();
					}
				}
			}
				}
		});
		//delete button gui css
		btnDeleteMedia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteMedia.setBackground(new Color(211, 211, 211));
		btnDeleteMedia.setBounds(93, 385, 195, 44);
		panel_3.add(btnDeleteMedia);
		
		
		//update selected media
		JButton btnUpdateSelectedMedia = new JButton("Update Selected Media");
		btnUpdateSelectedMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//get the selected media	
			int rowSelected = table.getSelectedRow();
			if(rowSelected>=0) {
				//array to pass into the initialization	
				 rowtoadd= model.getDataVector().elementAt(rowSelected).toArray();
			
			
			
			//Changes what the user can modify based on media type
			if(rowtoadd[0].toString().equals("book")) {
				 columnEditables2 = new boolean[] {false, true, true, true, true, true, true, true, true, true, false, false, true, false, false, false};
			}
			else if(rowtoadd[0].toString().equals("movie")) {
				columnEditables2 = new boolean[] {false, true, true, true, true, true, true, true, true, false, true, false, false, false, false, false};
			}
			else if(rowtoadd[0].toString().equals("tvshow")) {
				columnEditables2 = new boolean[] {false, true, true, true, true, true, true, true, true, false, false, true, false, false, false, true};
			}
			else {
				columnEditables2 = new boolean[] {false, true, true, true, true, true, true, true, true, false, false, false, false, true, true, };
			}
			//defines our new table to update the data
			updateRowTable.setModel(new DefaultTableModel(
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

					public boolean isCellEditable(int row, int column) {
						return columnEditables2[column];
					}
				});
			//update table GUI and CSS
			updateRowTable.getColumnModel().getColumn(0).setResizable(false);
			updateRowTable.getColumnModel().getColumn(0).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(1).setResizable(false);
			updateRowTable.getColumnModel().getColumn(1).setPreferredWidth(114);
			updateRowTable.getColumnModel().getColumn(1).setMinWidth(114);
			updateRowTable.getColumnModel().getColumn(2).setResizable(false);
			updateRowTable.getColumnModel().getColumn(2).setPreferredWidth(118);
			updateRowTable.getColumnModel().getColumn(2).setMinWidth(118);
			updateRowTable.getColumnModel().getColumn(3).setResizable(false);
			updateRowTable.getColumnModel().getColumn(3).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(4).setResizable(false);
			updateRowTable.getColumnModel().getColumn(4).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(5).setResizable(false);
			updateRowTable.getColumnModel().getColumn(5).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(6).setResizable(false);
			updateRowTable.getColumnModel().getColumn(6).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(7).setResizable(false);
			updateRowTable.getColumnModel().getColumn(7).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(8).setResizable(false);
			updateRowTable.getColumnModel().getColumn(8).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(9).setResizable(false);
			updateRowTable.getColumnModel().getColumn(9).setPreferredWidth(100);
			updateRowTable.getColumnModel().getColumn(9).setMinWidth(100);
			updateRowTable.getColumnModel().getColumn(10).setResizable(false);
			updateRowTable.getColumnModel().getColumn(10).setPreferredWidth(65);
			updateRowTable.getColumnModel().getColumn(10).setMinWidth(65);
			updateRowTable.getColumnModel().getColumn(11).setResizable(false);
			updateRowTable.getColumnModel().getColumn(11).setPreferredWidth(56);
			updateRowTable.getColumnModel().getColumn(11).setMinWidth(56);
			updateRowTable.getColumnModel().getColumn(12).setResizable(false);
			updateRowTable.getColumnModel().getColumn(12).setMinWidth(75);
			updateRowTable.getColumnModel().getColumn(13).setResizable(false);
			updateRowTable.getColumnModel().getColumn(13).setPreferredWidth(90);
			updateRowTable.getColumnModel().getColumn(13).setMinWidth(90);
			updateRowTable.getColumnModel().getColumn(14).setResizable(false);
			updateRowTable.getColumnModel().getColumn(14).setPreferredWidth(88);
			updateRowTable.getColumnModel().getColumn(14).setMinWidth(88);
			updateRowTable.getColumnModel().getColumn(15).setResizable(false);
			updateRowTable.getColumnModel().getColumn(15).setPreferredWidth(84);
			updateRowTable.getColumnModel().getColumn(15).setMinWidth(84);
			DefaultTableModel model2 = (DefaultTableModel) updateRowTable.getModel();
			//Clears any data from possible previous actions
			model2.setRowCount(0);
			//initializes our update method
			initializeupdate( model2);
				//
				panel.add(lblPrompt);
				update_delete.show(content,"UpdateMedia");
		
				}
			}
		});
		btnUpdateSelectedMedia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdateSelectedMedia.setBackground(new Color(211, 211, 211));
		btnUpdateSelectedMedia.setBounds(474, 385, 195, 45);
		panel_3.add(btnUpdateSelectedMedia);


		
		//button to return to my page
		JButton btnNewButton = new JButton("Return to MyPage");
		btnNewButton.setBounds(581, 11, 178, 44);
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_delete.show(content,"SelectMedia");
				Home.switchToHome();
				Home.modelLeaderboard.setRowCount(0);
				Home.populateLeaderboardTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		viewEditUpdatePanel.add(btnNewButton);
	}
	/**
	 * 
	 * @param username of user
	 * populates the view table with users media
	 */
	public static void populateTable(String username) {
		try {
			//gets all from users media table
			Statement stmt = MyLoginPage.connectTest.conn.createStatement();
			ResultSet query= stmt.executeQuery("SELECT * FROM MEDIA WHERE Lower(Username)='"+username.toLowerCase()+"'");
		 
			MyLoginPage.connectTest.conn.setAutoCommit(false);
			//while user has media add it to the row in specific format
			while(query.next()) {
			
			if(query.getString("Type").equals("book")) {
				model.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),query.getString("ISBN"),"","",query.getString("Series"),"","",""
				});
			}
			else if(query.getString("Type").equals("movie")) {
				model.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"",query.getString("Runtime"),"","","","",""		
				});					
			}
			else if(query.getString("Type").equals("tvshow")) {
				model.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"","",query.getString("Seasons"),"","","",query.getString("Airing")	
				});	
				
			}
			else if(query.getString("Type").equals("game")) {
				model.addRow(new Object[] {query.getString("Type"),query.getString("Title"),query.getString("Author"),query.getString("Publish"),query.getString("Genre"),query.getString("Rating"),query.getString("Tag1"),query.getString("Tag2"),query.getString("Tag3"),"","","","",query.getString("Platform"),query.getString("Replayability"),""		
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
	/**
	 * 
	 * @param rowtoadd
	 * @param model2
	 * method initializes the update pane (pane 2)
	 */
	public static void initializeupdate(DefaultTableModel model2) {
			lblPrompt.setText("Double Click a Cell to Edit the Value");
			//import our isolated data 
			model2.addRow(rowtoadd);
			
			//When the user commits to updating data
			 JButton btnUpdateRow = new JButton("Update Row");
			 btnUpdateRow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//closes input from the table
						if (updateRowTable.isEditing())
						     updateRowTable.getCellEditor().stopCellEditing();
						int rowSelected = updateRowTable.getSelectedRow();
						//if they have selected the row in the update table
						if(rowSelected>=0) {
							try {
								//delete the old info
								MyLoginPage.connectTest.conn.setAutoCommit(true);
								PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("DELETE FROM MEDIA WHERE Lower(Username)='"+MyLoginPage.getActiveUser().toLowerCase()+"' AND Type= '"+rowtoadd[0].toString().replaceAll("'","''")+"' AND Author='"+rowtoadd[2].toString().replaceAll("'","''")+"' AND Title='"+rowtoadd[1].toString().replaceAll("'","''")+"' AND publish="+Integer.parseInt(rowtoadd[3].toString()));
								
								stmt.executeUpdate();
				
							} catch (SQLException exc) {
								// TODO Auto-generated catch block
								exc.printStackTrace();
							}
						
						//insert new data into users database
						Object rowtoChange[]= new String[16];
						for(int i=0;i<16;i++) {
							rowtoChange[i]= updateRowTable.getValueAt(rowSelected, i).toString();
						
							
						//properly formats the data not applicable to type	
						}
						if(rowtoChange[0].equals("game")) {
							Game editedGame = new Game(rowtoChange[0].toString().replaceAll("'", "''"),  rowtoChange[1].toString().replaceAll("'", "''"),  rowtoChange[4].toString().replaceAll("'", "''"), rowtoChange[2].toString().replaceAll("'", "''"),  Integer.parseInt(rowtoChange[3].toString()),  Integer.parseInt(rowtoChange[5].toString()),  rowtoChange[6].toString().replaceAll("'", "''")+","+rowtoChange[7].toString().replaceAll("'", "''")+","+rowtoChange[8].toString().replaceAll("'", "''"), rowtoChange[14].toString().replaceAll("'", "''"),rowtoChange[13].toString().replaceAll("'", "''"));
							editedGame.addMedia();
						}
						else if(rowtoChange[0].equals("book")){
							Book editedBook = new Book(rowtoChange[0].toString(),  rowtoChange[1].toString(),  rowtoChange[4].toString(), rowtoChange[2].toString(),  Integer.parseInt(rowtoChange[3].toString()),  Integer.parseInt(rowtoChange[5].toString()),  rowtoChange[6].toString()+","+rowtoChange[7].toString()+","+rowtoChange[8].toString(), rowtoChange[12].toString(),rowtoChange[9].toString());
							editedBook.addMedia();
						}
						else if(rowtoChange[0].equals("movie")) {
							Movie editedMovie = new Movie(rowtoChange[0].toString(),  rowtoChange[1].toString(),  rowtoChange[4].toString(), rowtoChange[2].toString(),  Integer.parseInt(rowtoChange[3].toString()),Double.parseDouble(rowtoChange[10].toString()),  Integer.parseInt(rowtoChange[5].toString()),  rowtoChange[6].toString()+","+rowtoChange[7].toString()+","+rowtoChange[8].toString());
							editedMovie.addMedia();
						}
						else if(rowtoChange[0].equals("tvshow")) {
							TVShow editedTVShow = new TVShow(rowtoChange[0].toString(),  rowtoChange[1].toString(),  rowtoChange[4].toString(), rowtoChange[2].toString(),  Integer.parseInt(rowtoChange[3].toString()),Integer.parseInt(rowtoChange[11].toString()), rowtoChange[15].toString(), Integer.parseInt(rowtoChange[5].toString()),  rowtoChange[6].toString()+","+rowtoChange[7].toString()+","+rowtoChange[8].toString());
							editedTVShow.addMedia();
						}
					
						System.out.println(rowtoadd[2]);
						
						
						//clean up and refresh view table and go back to view
						update_delete.show(content,"SelectMedia");	
						model.setRowCount(0);
						model2.setRowCount(0);
						populateTable(MyLoginPage.getActiveUser());
						lblPrompt.setText("Select a Media to Update or Delete");
					}
					}
				});
			 	// update button css
				btnUpdateRow.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnUpdateRow.setBackground(new Color(211, 211, 211));
				btnUpdateRow.setBounds(137, 263, 178, 44);
				update.add(btnUpdateRow);
				
				//Button if user decides not to update
				JButton btnGoBackTo = new JButton("Go Back to List");
				btnGoBackTo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						lblPrompt.setText("Select a Media to Update or Delete");
						update_delete.show(content,"SelectMedia");
						if(model2.getRowCount()!=0) {
							model2.setRowCount(0);
						}
						
					}
				});
				//cancel button css
				btnGoBackTo.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnGoBackTo.setBackground(new Color(211, 211, 211));
				btnGoBackTo.setBounds(447, 263, 178, 44);
				update.add(btnGoBackTo);
		
	}
	public static void setCount() {
		model.setRowCount(0);
	}
}