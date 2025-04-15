import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUser extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JFrame creaUser; 
	public JPanel create; 
	public boolean exit; 

	/**
	 * Create the panel.
	 */
	public CreateUser(JInternalFrame internalFrame, DndDatabase data) {
		create = new JPanel();
		create.setBackground(new Color(240, 255, 255));
		create.setBounds(10, 10, 502, 348);
		create.setLayout(null);
		//create.setVisible(false);
		
		JButton exitCreate = new JButton("Exit");
		exitCreate.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		exitCreate.setBounds(439, 317, 53, 21);
		create.add(exitCreate);
		
		JLabel PlayerLogLabel = new JLabel("Player Login");
		PlayerLogLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerLogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerLogLabel.setBounds(154, 42, 186, 50);
		create.add(PlayerLogLabel);
		
		JTextArea NewPlayerLog = new JTextArea();
		NewPlayerLog.setWrapStyleWord(true);
		NewPlayerLog.setEditable(false);
		NewPlayerLog.setLineWrap(true);
		NewPlayerLog.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 8)); 
		NewPlayerLog.setText("Enter First and Last Names into the spaces below to create a new Player");
		NewPlayerLog.setBounds(54, 132, 119, 40);
		create.add(NewPlayerLog);
		
		JTextArea ExiPlayerLog = new JTextArea();
		ExiPlayerLog.setWrapStyleWord(true);
		ExiPlayerLog.setEditable(false);
		ExiPlayerLog.setBounds(361, 132, 96, 40);
		ExiPlayerLog.setLineWrap(true);
		ExiPlayerLog.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 8)); 
		ExiPlayerLog.setText("Enter First and Last Names into the spaces below to access an existing Player");
		create.add(ExiPlayerLog); 
		
		JTextField UserInNewFN = new JTextField();
		UserInNewFN.setBounds(77, 185, 96, 19);
		UserInNewFN.requestFocusInWindow(); 
		create.add(UserInNewFN);
		UserInNewFN.setColumns(10);
		
		
		JTextField UserInNewLN = new JTextField();
		UserInNewLN.setBounds(77, 222, 96, 19);
		create.add(UserInNewLN);
		UserInNewLN.setColumns(10);
		
		JLabel LogLabNewFN = new JLabel("First Name");
		LogLabNewFN.setFont(new Font("Constantia", Font.PLAIN, 10));
		LogLabNewFN.setBounds(10, 186, 57, 16);
		create.add(LogLabNewFN);
		
		JLabel LogLabNewLN = new JLabel("Last Name");
		LogLabNewLN.setFont(new Font("Constantia", Font.PLAIN, 10));
		LogLabNewLN.setBounds(10, 225, 57, 13);
		create.add(LogLabNewLN);
		
		JTextField UserInFN = new JTextField();
		UserInFN.setBounds(361, 185, 96, 19);
		create.add(UserInFN);
		UserInFN.setColumns(10);
		
		JTextField UserInLN = new JTextField();
		UserInLN.setBounds(361, 222, 96, 19);
		create.add(UserInLN);
		UserInLN.setColumns(10);
		
		JLabel LogLabFN = new JLabel("First Name");
		LogLabFN.setFont(new Font("Constantia", Font.PLAIN, 10));
		LogLabFN.setBounds(300, 188, 57, 13);
		create.add(LogLabFN);
		
		JLabel LogLabLN = new JLabel("Last Name");
		LogLabLN.setFont(new Font("Constantia", Font.PLAIN, 10));
		LogLabLN.setBounds(300, 225, 57, 13);
		create.add(LogLabLN);
		
	
		
		
		JButton SaveNewPlayer = new JButton("CREATE");
		SaveNewPlayer.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		SaveNewPlayer.setBounds(88, 251, 85, 21);
		create.add(SaveNewPlayer);
		
		JButton LoginUser = new JButton("LOGIN");
		LoginUser.setFont(new Font("Sitka Text", Font.PLAIN, 10));
		LoginUser.setBounds(372, 251, 85, 21);
		create.add(LoginUser);
		
		
		internalFrame.getContentPane().add(create);
		
		JTextArea ErrNwPlay = new JTextArea();
		ErrNwPlay.setBackground(new Color(240, 255, 255));
		ErrNwPlay.setForeground(new Color(255, 0, 0));
		ErrNwPlay.setWrapStyleWord(true);
		ErrNwPlay.setLineWrap(true);
		ErrNwPlay.setText("ERROR: Player Has Already Been Created\r\nLogin with First and Last Name");
		ErrNwPlay.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		ErrNwPlay.setBounds(178, 182, 119, 79);
		//create.add(ErrNwPlay);
		
		JLabel lblNewLabel = new JLabel("Create New Player");
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 13));
		lblNewLabel.setBounds(24, 99, 149, 23);
		create.add(lblNewLabel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 13));
		lblLogin.setBounds(411, 102, 46, 23);
		create.add(lblLogin);
		
		JTextArea ErrPlayLog = new JTextArea();
		ErrPlayLog.setWrapStyleWord(true);
		ErrPlayLog.setText("ERROR: Player Information is Incorrect, Try Again or Create New Player");
		ErrPlayLog.setLineWrap(true);
		ErrPlayLog.setForeground(Color.RED);
		ErrPlayLog.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		ErrPlayLog.setBackground(new Color(240, 255, 255));
		ErrPlayLog.setBounds(178, 182, 119, 79);
		//create.add(ErrPlayLog);
		
		
		
		
		LoginUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = UserInFN.getText(); 
				String last = UserInLN.getText(); 
				Player user = new Player(first, last); 
				boolean test = false; 
				try {
					test = data.playerExists(user); 
					//gets and sets playerID
					ResultSet results = data.playerLookup(user); 
					while(results.next()){
						int playID = results.getInt("PlayID");
						System.out.println(playID); 
						user.setPlayID(playID);
					}
					System.out.println("Login:" + user); 
					PlayerInfo info = new PlayerInfo(internalFrame, data, user); 
					create.setVisible(false);
				} catch (SQLException e1) {
					create.add(ErrPlayLog); 
					create.repaint();
					e1.printStackTrace();
				} 
				
				//find user in database
				//remove this panel, add new panel with PlayerInfo
				
			}
		});
		
		SaveNewPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("press create"); 
				String first = UserInNewFN.getText(); 
				String last = UserInNewLN.getText(); 
				Player user = new Player(first, last); 
				try {
					
					boolean test = data.playerExists(user); 
					System.out.println(test); 
					if(test) {
						create.add(ErrNwPlay); 
						create.repaint(); 
					}
					
				} catch (SQLException e1) {
					try {
					System.out.println("gets into here"); 
					data.insertPlayer(user);
					ResultSet results = data.playerLookup(user); 
					while(results.next()){
						int playID = results.getInt("PlayID");
						System.out.println(playID); 
						user.setPlayID(playID);
					}
					
					System.out.println("Create: " + user); 
					PlayerInfo pi = new PlayerInfo(internalFrame, data, user); 
					create.setVisible(false);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} 
				
				
				
				//add user to database 
				//remove this panel, add new panel PlayerInfo
				
			}
		});
		
		exitCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreenGUI welcome = new WelcomeScreenGUI(internalFrame, data); 
				//welcome.setVis(true);
				create.setVisible(false);
			}
		});
	}
	
	public JPanel returnCreate() {
		return create; 
	}
	
	public void setVis(boolean vis) {
		create.setVisible(vis);
	}
	
	public JFrame returnCreaUser() {
		return creaUser; 
	}
	
	public boolean exit(){
		return exit; 
	}
	
}
