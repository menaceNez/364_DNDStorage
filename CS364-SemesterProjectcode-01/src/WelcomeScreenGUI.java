import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreenGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel welcome; 
	JFrame frameWelcome; 
	
	/**
	 * Create the panel.
	 */
	public WelcomeScreenGUI(JInternalFrame internalFrame, DndDatabase data) {
		welcome = new JPanel();
		welcome.setBackground(new Color(255, 0, 255));
		welcome.setBounds(10, 10, 502, 348);
		welcome.setLayout(null);	
		
		JLabel welcomeLabel = new JLabel("WELCOME TO DATABASE");
		welcomeLabel.setBounds(91, 10, 294, 68);
		welcomeLabel.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		welcome.add(welcomeLabel); 
		
		JButton findButton = new JButton("go to Find");
		findButton.setBounds(329, 162, 85, 21);
		welcome.add(findButton);
		
		JButton createButton = new JButton("go to Create");
		createButton.setBounds(32, 162, 115, 21);
		welcome.add(createButton);
		
		
		createButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { 
				CreateUser user = new CreateUser(internalFrame, data); 
				welcome.setVisible(false);   
				
			}
		}
		);
		
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
	//frameWelcome = new JFrame(); 
	//frameWelcome.add(welcome); 
	internalFrame.getContentPane().add(welcome); 
		
	}
	
	}


