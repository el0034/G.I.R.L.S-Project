import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

/**
 * 
 * @author el0034 and hag0009
 *Creates the login and create account page
 */
public class CreateAndLoginPage{
	// Declare variables
	public  static JFrame loginFrame;
	private JTextField tfUsername;
	private JPasswordField userPassword;
	private static  JTextField tfCrName;
	private static JTextField tfCrEmail;
	private static JTextField tfCrUsername;
	private  static JTextField tfCrPassword;
	private JLabel lblnewName;
	
	// Create the window view
	public static void CreateAndLoginRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAndLoginPage window = new CreateAndLoginPage();
					window.loginFrame.setVisible(true);
					loginFrame.revalidate();
					loginFrame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateAndLoginPage() {
		initialize();
	}

	// Constructor for creating the page
	public void initialize() {
		// Create new JFrame
		loginFrame = new JFrame();
		loginFrame.setTitle("G.I.R.L.S");
		loginFrame.setBounds(100, 100, 673, 417);
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 160, 122));
		panel.setBackground(new Color(216, 191, 216));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//adding panel to the frame
		loginFrame.setContentPane(panel);
		panel.setLayout(null);
		
		// Create the login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(230, 230, 250));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		//Authenticating login on click
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyLoginPage.login(tfUsername.getText(), userPassword.getPassword());
			}
		});
		btnLogin.setBounds(179, 266, 113, 23);
		panel.add(btnLogin);
		
		// Create Login User name text field
		tfUsername = new JTextField();
		tfUsername.setBackground(new Color(255, 240, 245));
		tfUsername.setBounds(135, 152, 157, 20);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		// Title on page
		JLabel lblLogo = new JLabel("Graphical Interface for Rating Literature and Such");
		lblLogo.setBackground(new Color(169, 169, 169));
		lblLogo.setForeground(new Color(0, 0, 0));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLogo.setBounds(82, 11, 524, 38);
		panel.add(lblLogo);
		
		// Create Login Header
		JLabel lblReturning = new JLabel("Login");
		lblReturning.setForeground(new Color(255, 255, 255));
		lblReturning.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReturning.setBounds(117, 80, 69, 38);
		panel.add(lblReturning);
		
		// Create Password field for login password
		userPassword = new JPasswordField();
		userPassword.setBackground(new Color(255, 240, 245));
		userPassword.setBounds(135, 208, 157, 20);
		panel.add(userPassword);
		
		// Create Account header
		JLabel lblNewUser = new JLabel("Create an Account");
		lblNewUser.setBackground(new Color(128, 128, 128));
		lblNewUser.setForeground(new Color(255, 255, 255));
		lblNewUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewUser.setBounds(413, 80, 198, 38);
		panel.add(lblNewUser);
		
		// Create text field for creating name
		tfCrName = new JTextField();
		tfCrName.setColumns(10);
		tfCrName.setBackground(new Color(255, 240, 245));
		tfCrName.setBounds(469, 133, 157, 20);
		panel.add(tfCrName);
		
		// Create text field for creating email
		tfCrEmail = new JTextField();
		tfCrEmail.setColumns(10);
		tfCrEmail.setBackground(new Color(255, 240, 245));
		tfCrEmail.setBounds(469, 183, 157, 20);
		panel.add(tfCrEmail);
		
		// Create text field for creating user name
		tfCrUsername = new JTextField();
		tfCrUsername.setColumns(10);
		tfCrUsername.setBackground(new Color(255, 240, 245));
		tfCrUsername.setBounds(469, 235, 157, 20);
		panel.add(tfCrUsername);
		
		// Create text field for creating password
		tfCrPassword = new JTextField();
		tfCrPassword.setColumns(10);
		tfCrPassword.setBackground(new Color(255, 240, 245));
		tfCrPassword.setBounds(469, 280, 157, 20);
		panel.add(tfCrPassword);
		
		// Button creates account on click
		JButton btnCreateAcct = new JButton("Create Account");
		btnCreateAcct.setForeground(Color.BLACK);
		btnCreateAcct.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCreateAcct.setBackground(new Color(230, 230, 250));
		btnCreateAcct.setBounds(458, 324, 167, 23);
		btnCreateAcct.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 try {
							MyLoginPage.createAccount( tfCrName.getText().trim(), tfCrUsername.getText().trim(),  tfCrEmail.getText().trim(), tfCrPassword.getText().trim(), "false");
							tfCrPassword.setText("");
						 } catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
	    );
		panel.add(btnCreateAcct);
		
		// Vertical separator to keep neat
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(188, 143, 143));
		separator_1.setBackground(new Color(255, 240, 245));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(328, 80, 18, 244);
		panel.add(separator_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(169, 169, 169));
		panel_1.setBounds(10, 80, 300, 220);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_2.setBounds(10, 73, 100, 31);
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Create Label for Login username
		JLabel lblLogin = new JLabel("Username:");
		panel_2.add(lblLogin);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setBackground(new Color(0, 0, 0));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Create panel for organization
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(SystemColor.controlDkShadow);
		panel_2_1.setBounds(10, 126, 100, 31);
		panel_1.add(panel_2_1);
		panel_2_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Create label for the login password
		JLabel lblPassword = new JLabel("Password:");
		panel_2_1.add(lblPassword);
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Create panels and separator for organization 
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(128, 128, 128));
		separator_2.setBackground(new Color(105, 105, 105));
		separator_2.setBounds(91, 32, 76, 23);
		panel_1.add(separator_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setForeground(new Color(255, 255, 255));
		panel_1_1.setBackground(new Color(128, 128, 128));
		panel_1_1.setBounds(347, 80, 300, 278);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(10, 50, 100, 26);
		panel_1_1.add(panel_2_1_1);
		panel_2_1_1.setBackground(new Color(211, 211, 211));
		panel_2_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Label for creating name
		lblnewName = new JLabel("Name:");
		lblnewName.setBackground(new Color(0, 0, 0));
		panel_2_1_1.add(lblnewName);
		lblnewName.setForeground(new Color(0, 0, 0));
		lblnewName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Panel for organization
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(new Color(211, 211, 211));
		panel_2_1_1_1.setBounds(10, 101, 100, 26);
		panel_1_1.add(panel_2_1_1_1);
		panel_2_1_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Label for creating email
		JLabel lblnewEmail = new JLabel("Email:");
		panel_2_1_1_1.add(lblnewEmail);
		lblnewEmail.setForeground(new Color(0, 0, 0));
		lblnewEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Panel for organization
		JPanel panel_2_1_1_2 = new JPanel();
		panel_2_1_1_2.setBackground(new Color(211, 211, 211));
		panel_2_1_1_2.setBounds(10, 152, 100, 26);
		panel_1_1.add(panel_2_1_1_2);
		panel_2_1_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Label for creating name
		JLabel lblnewUsername = new JLabel("Username:");
		panel_2_1_1_2.add(lblnewUsername);
		lblnewUsername.setForeground(new Color(0, 0, 0));
		lblnewUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Panel for organization
		JPanel panel_2_1_1_3 = new JPanel();
		panel_2_1_1_3.setBackground(new Color(211, 211, 211));
		panel_2_1_1_3.setBounds(10, 198, 100, 26);
		panel_1_1.add(panel_2_1_1_3);
		panel_2_1_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Label for creating password
		JLabel lblnewPassword = new JLabel("Password:");
		panel_2_1_1_3.add(lblnewPassword);
		lblnewPassword.setForeground(new Color(0, 0, 0));
		lblnewPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// Panel and separator for organization
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(128, 128, 128));
		separator_2_1.setBounds(63, 31, 157, 23);
		panel_1_1.add(separator_2_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(82, 11, 529, 38);
		panel.add(panel_3);
		
	}
	/**
	 * helper method to clear any fields
	 */
		public static void clearFields(){
			 tfCrUsername.setText("");
			 tfCrEmail.setText("");
			 tfCrName.setText("");
	
		}
}
