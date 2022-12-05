/*
 * @author el0034
 */
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class Settings {

	/**
	 * Create the panel.
	 */
	JPanel settingsPanel;
	private JPanel changeInfoPanel;
	private JPanel applyAdminPanel;
	private JPanel deactivatePanel;
	private JPanel deactivateAdminPanel;
	private JTextField tfNewName;
	private JTextField tfNewPWD;
	private JTextField tfUsername;
	private JTextField tfName;
	
	
	public Settings() {
		// Create panel
		settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 0, 800, 600);
		settingsPanel.setBackground(new Color(216, 191, 216));
		settingsPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(45, 63, 686, 2);
		settingsPanel.add(separator);
		
		// Create top panel that holds the combo box
		JPanel panel = new JPanel();
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(45, 76, 686, 44);
		settingsPanel.add(panel);
		panel.setLayout(null);
		// Label for settings
		JLabel lblSettingsAction = new JLabel("Select Settings Action:");
		lblSettingsAction.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSettingsAction.setBounds(56, 11, 240, 22);
		panel.add(lblSettingsAction);
		// Invoke all methods that will create the panels
		setChangeInfo();
		setApplyForAdmin();
		setDeactivateAccount();
		setDeactivateAdmin();
		
		//allows easy switching from panel to panel
		CardLayout typePage = new CardLayout();
				
		//creating a main panel to switch child panels on
		JPanel settingsSpace = new JPanel();
		settingsSpace.setBounds(45, 143, 686, 380);
		settingsSpace.setLayout(typePage);
		settingsPanel.add(settingsSpace);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		settingsSpace.add(panel_1,"null");
		panel_1.setLayout(null);
		settingsSpace.add(changeInfoPanel, "Change Info");
		settingsSpace.add(applyAdminPanel,"Apply for Admin");
		settingsSpace.add(deactivatePanel,"Deactivate Account");
		settingsSpace.add(deactivateAdminPanel,"Deactivate Admin Account");
		
		//Creating the dropdown media selection menu
		String[] types = {"Select Action","Change Info","Apply for Admin", "Deactivate Account","Deactivate Admin Account"};
		JComboBox comboBox = new JComboBox(types);
		comboBox.setBackground(new Color(211, 211, 211));
		//One a selection is chosen show appropriate display
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					String  selectedMedia =comboBox.getSelectedItem().toString();
					if(selectedMedia.equals(types[0])) {
						typePage.show(settingsSpace,"null");
					}
					
					else if(selectedMedia.equals(types[1])) {
						typePage.show(settingsSpace,"Change Info");
					} 
					else if(selectedMedia.equals(types[2])) {
						typePage.show(settingsSpace,"Apply for Admin");
					}
					else if(selectedMedia.equals(types[3])) {
						typePage.show(settingsSpace,"Deactivate Account");
						
					}
					else if(selectedMedia.equals(types[4])) {
						typePage.show(settingsSpace,"Deactivate Admin Account");
					}
				}
			}
		});
		comboBox.setBounds(292, 14, 284, 22);
		panel.add(comboBox);
		
		// Create panels and labels for organization
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(45, 16, 164, 39);
		settingsPanel.add(panel_2);
		
		JLabel settingsLabel = new JLabel("Settings");
		panel_2.add(settingsLabel);
		settingsLabel.setForeground(Color.WHITE);
		settingsLabel.setBackground(new Color(255, 255, 255));
		settingsLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		//button to return to my page
		JButton btnReturn = new JButton("Return to MyPage");
		btnReturn.setBackground(new Color(211, 211, 211));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home.switchToHome();
				Home.modelLeaderboard.setRowCount(0);
				Home.populateLeaderboardTable();
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReturn.setBounds(565, 10, 178, 44);
		settingsPanel.add(btnReturn);
		
		
	}
	// Method to create Change Info panel
	public void setChangeInfo(){
		// Create panel
		changeInfoPanel = new JPanel();
		changeInfoPanel.setForeground(Color.WHITE);
		changeInfoPanel.setBounds(45, 143, 686, 411);
		changeInfoPanel.setBackground(new Color(128, 128, 128));
		changeInfoPanel.setLayout(null);
		// Create labels
		JLabel lbChangeName = new JLabel("Change Name");
		lbChangeName.setForeground(Color.WHITE);
		lbChangeName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbChangeName.setBounds(34, 34, 160, 20);
		changeInfoPanel.add(lbChangeName);
		
		JLabel lblChangePWD = new JLabel("Change Password");
		lblChangePWD.setForeground(Color.WHITE);
		lblChangePWD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChangePWD.setBounds(488, 34, 183, 20);
		changeInfoPanel.add(lblChangePWD);
		
		JLabel lblChangeBio = new JLabel("Change Biography");
		lblChangeBio.setForeground(Color.WHITE);
		lblChangeBio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChangeBio.setBounds(269, 34, 194, 20);
		changeInfoPanel.add(lblChangeBio);
		
		JLabel lblNewName = new JLabel("Enter New Name: ");
		lblNewName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewName.setForeground(Color.WHITE);
		lblNewName.setBounds(34, 107, 172, 20);
		changeInfoPanel.add(lblNewName);
		
		JLabel lblNewPWD = new JLabel("Enter New Password:");
		lblNewPWD.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewPWD.setForeground(Color.WHITE);
		lblNewPWD.setBounds(488, 107, 184, 20);
		changeInfoPanel.add(lblNewPWD);
		
		JLabel lblNewBiography = new JLabel("Enter Biography:");
		lblNewBiography.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewBiography.setForeground(Color.WHITE);
		lblNewBiography.setBounds(286, 107, 166, 20);
		changeInfoPanel.add(lblNewBiography);
		
		// Create textfields and text areas
		tfNewName = new JTextField();
		tfNewName.setBounds(34, 143, 146, 26);
		changeInfoPanel.add(tfNewName);
		tfNewName.setColumns(10);
		
		tfNewPWD = new JTextField();
		tfNewPWD.setBounds(499, 143, 172, 26);
		changeInfoPanel.add(tfNewPWD);
		tfNewPWD.setColumns(10);
		
		JTextPane tpNewBio = new JTextPane();
		tpNewBio.setBounds(258, 134, 194, 121);
		tpNewBio.setBackground(Color.WHITE);
		// get old bio
				String oldBio="";
				try {
					ResultSet query;
					Statement stmt = MyLoginPage.connectTest.conn.createStatement();
					query =	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+"')");
					if(query.next()) {
					oldBio=query.getString("Bio");
					tpNewBio.setText(oldBio);
					}
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		changeInfoPanel.add(tpNewBio);
		// Create buttons
		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make sure text field is not empty
				if(tfNewName.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Field Blank");
				}
				else if(tfNewName.getText().trim().contains(",")){
					showMessageDialog(null,"Field cannot contain ','");
				}
				else {
					//Changing name of user
					try {
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//make our statement change name
						PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("UPDATE ACCOUNTS SET NAME= '"+tfNewName.getText().replaceAll("'", "''")+"'WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+ "') ");
				
						//change the name
						stmt.executeUpdate();
						
						tfNewName.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String name="";
					try {
						ResultSet query;
						MyLoginPage.connectTest.conn.setAutoCommit(false);
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						query =	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+"')");
						if(query.next()) {
						name=query.getString("Name");
						
						Home.lblpfpName.setText("Name: "+name);
						showMessageDialog(null,"Name change was succesful");
						}
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btnChangeName.setBounds(35, 215, 145, 29);
		changeInfoPanel.add(btnChangeName);
		
		
		JButton btnChangeBio = new JButton("Change Biography");
		btnChangeBio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make sure text field is not empty
				if(tpNewBio.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Field Blank");
				}
				else {
					//Changing bio of user
					try {
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						//make our statement change name
						PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("UPDATE ACCOUNTS SET Bio= '"+tpNewBio.getText().replaceAll("'", "''")+"' WHERE Lower(Username)=Lower('"+MyLoginPage.getActiveUser()+ "') ");
				
						//change the biography
						stmt.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String bio="";
					//try findinge exisiting accounts bio
					try {
						ResultSet query;
						Statement stmt = MyLoginPage.connectTest.conn.createStatement();
						query =	stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+"')");
						MyLoginPage.connectTest.conn.setAutoCommit(false);
						if(query.next()) {
						bio=query.getString("Bio");
						
						Home.tpBio.setText(bio);
						showMessageDialog(null,"Biography change was succesful");
						}
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnChangeBio.setBounds(269, 277, 167, 29);
		changeInfoPanel.add(btnChangeBio);
		
		//Button to change password
		JButton btnChangePWD = new JButton("Change Password");
		btnChangePWD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make sure text field is not empty
				if(tfNewPWD.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Field Blank");
				}
				else if(MyLoginPage.validatePassword(tfNewPWD.getText())){
					try {
						MyLoginPage.connectTest.conn.setAutoCommit(false);
						//make our statement change password
						PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("UPDATE ACCOUNTS SET Password= '"+tfNewPWD.getText().replaceAll("'", "''")+"'WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+ "') ");
				
						//change the password
						stmt.executeUpdate();
						
						MyLoginPage.connectTest.conn.setAutoCommit(true);
						tfNewPWD.setText("");
						
						showMessageDialog(null,"Password  change was successful");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnChangePWD.setBounds(499, 215, 172, 29);
		changeInfoPanel.add(btnChangePWD);
	}
	// Create method for apply for admin panel
	public void setApplyForAdmin(){
		// Create panel
		applyAdminPanel = new JPanel();
		applyAdminPanel.setForeground(Color.WHITE);
		applyAdminPanel.setBounds(45, 143, 686, 411);
		applyAdminPanel.setBackground(new Color(128, 128, 128));
		applyAdminPanel.setLayout(null);
		// Create description for admin
		JTextArea taDescription = new JTextArea();
		taDescription.setFont(new Font("Monospaced", Font.PLAIN, 14));
		taDescription.setEditable(false);
		taDescription.setBounds(15, 16, 656, 164);
		taDescription.setText("Admins have the power to monitors users' accounts.\n" + 
				"They are able to delete users' accounts based on necessity.\n" + 
				"Admins can still enjoy the features of creating media lists and interacting with"+"\nother users."+
				"\n"+
				"\nPlease fill out the form below if you wish to become an admin."+
				"\nThe system administrator will approve users based on previous activties on\nthe application.");
		applyAdminPanel.add(taDescription);
		// Create labels
		JLabel lblusername = new JLabel("Username: ");
		lblusername.setForeground(Color.WHITE);
		lblusername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblusername.setBounds(25, 196, 104, 20);
		applyAdminPanel.add(lblusername);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(352, 196, 80, 20);
		applyAdminPanel.add(lblName);
		// Create textfields
		tfUsername = new JTextField();
		tfUsername.setBounds(132, 196, 146, 26);
		applyAdminPanel.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(433, 193, 146, 26);
		applyAdminPanel.add(tfName);
		tfName.setColumns(10);
		// Create label and text area for entry
		JLabel lblNewLabel = new JLabel("Briefly describe why you should become an admin:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 243, 420, 30);
		applyAdminPanel.add(lblNewLabel);
		
		JTextArea taAdminReason = new JTextArea();
		taAdminReason.setBounds(25, 276, 411, 88);
		applyAdminPanel.add(taAdminReason);
		// Create submission button
		JButton btnSubmitForm = new JButton("Submit Form");
		btnSubmitForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfUsername.getText().trim().equals("") || tfName.getText().trim().equals("") || taAdminReason.getText().trim().equals("")) {
					showMessageDialog(null,"Cannot Leave Fields Blank");
				}
				// check to see is name and username are in the system. This was we know they entered a valid name and username
			}
			
		});
		btnSubmitForm.setBounds(523, 335, 135, 29);
		applyAdminPanel.add(btnSubmitForm);
	}
	// Method to create deactivate account panel
	public void setDeactivateAccount() {
		// Create panel
		deactivatePanel = new JPanel();
		deactivatePanel.setForeground(Color.WHITE);
		deactivatePanel.setBounds(45, 143, 686, 411);
		deactivatePanel.setBackground(new Color(128, 128, 128));
		deactivatePanel.setLayout(null);
		// Create label
		JLabel lblWarning = new JLabel("WARNING:");
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWarning.setBounds(282, 16, 171, 20);
		deactivatePanel.add(lblWarning);
		// Create text area
		JTextArea taWarning = new JTextArea();
		taWarning.setEditable(false);
		taWarning.setFont(new Font("Monospaced", Font.PLAIN, 14));
		taWarning.setBounds(30, 47, 629, 85);
		taWarning.setText("Deactivating your account will erase all media lists you have created.\nThere is no account recovery once deactivation goes through."+
						"\n"+"\nPlease think carefully before deactivating your account.");
		deactivatePanel.add(taWarning);
		// Create deactivate button
		JButton btnDeactivate = new JButton("Deactivate Account");
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyLoginPage.connectTest.conn.setAutoCommit(false);
					//make our statement to delete account
					PreparedStatement stmt = MyLoginPage.connectTest.conn.prepareStatement("DELETE FROM ACCOUNTS WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+ "') ");
					PreparedStatement stmt2 = MyLoginPage.connectTest.conn.prepareStatement("DELETE FROM MEDIA WHERE Lower(Username)=Lower('" +MyLoginPage.getActiveUser()+ "') ");
					
					Home.modelUser.setRowCount(0);
					Home.populateUserTable();
					
					//delete account
					stmt.executeUpdate();
					stmt2.executeUpdate();
					
					// close out frames
					Home.frame1.dispose();
					
					CreateAndLoginPage.CreateAndLoginRun();
					MyLoginPage.setActiveUser(null);
					
					MyLoginPage.connectTest.conn.setAutoCommit(true);
					
					showMessageDialog(null,"Account was deleted.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDeactivate.setBounds(222, 203, 219, 48);
		deactivatePanel.add(btnDeactivate);
		
	}
	// Method to create deactivate admin account panel
	public void setDeactivateAdmin(){
		// Create panel
		deactivateAdminPanel = new JPanel();
		deactivateAdminPanel.setForeground(Color.WHITE);
		deactivateAdminPanel.setBounds(45, 143, 686, 411);
		deactivateAdminPanel.setBackground(new Color(128, 128, 128));
		deactivateAdminPanel.setLayout(null);
		// Create label
		JLabel lblWarning = new JLabel("WARNING:");
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWarning.setBounds(282, 16, 171, 20);
		deactivateAdminPanel.add(lblWarning);
		// Create text area
		JTextArea taWarning = new JTextArea();
		taWarning.setEditable(false);
		taWarning.setFont(new Font("Monospaced", Font.PLAIN, 14));
		taWarning.setBounds(30, 47, 629, 85);
		taWarning.setText("Deactivating your admin account will turn your account into a basic user.\nYou will not have the ability to delete users or monitor accounts.\nIf you wish to become an admin again, you will have to fill out the\nrequest form again.");
		deactivateAdminPanel.add(taWarning);
		// Create deactivate button
		JButton btnDeactivateAdmin = new JButton("Deactivate Admin Account");
		btnDeactivateAdmin.setBounds(222, 203, 219, 48);
		deactivateAdminPanel.add(btnDeactivateAdmin);
	}
}