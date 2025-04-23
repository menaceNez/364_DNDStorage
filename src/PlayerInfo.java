import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;
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

public class PlayerInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel info; 

	/**
	 * Create the panel.
	 */
	public PlayerInfo(JInternalFrame internalFrame, DndDatabase data, Player user) {
		info = new JPanel();
		info.setBackground(new Color(240, 255, 255));
		info.setBounds(10, 10, 502, 348);
		info.setLayout(null);
		
		
		JLabel PlayerIn = new JLabel("Player Info");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(154, 10, 186, 50);
		info.add(PlayerIn);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setText(user.toString());
		txtrHello.setBounds(10, 10, 121, 41);
		info.add(txtrHello);
		
		internalFrame.getContentPane().add(info); 
		
		JButton createPer = new JButton("Create");
		createPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatePers per = new CreatePers(internalFrame, data, user); 
				info.setVisible(false);
				
			}
		});
		createPer.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		createPer.setBounds(74, 118, 102, 41);
		info.add(createPer);
		
		JButton viewPer = new JButton("View");
		viewPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayMultiPers dis = new DisplayMultiPers(internalFrame, data, user); 
				info.setVisible(false);
			}
		});
		viewPer.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		viewPer.setBounds(74, 227, 102, 41);
		info.add(viewPer);
		
		JButton createAdv = new JButton("Create");
		createAdv.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		createAdv.setBounds(319, 118, 102, 41);
		info.add(createAdv);
		
		JButton viewAdv = new JButton("View");
		viewAdv.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		viewAdv.setBounds(319, 227, 102, 41);
		info.add(viewAdv);
		
		JLabel createPreLab = new JLabel("Create New Character");
		createPreLab.setHorizontalAlignment(SwingConstants.CENTER);
		createPreLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		createPreLab.setBounds(44, 81, 156, 27);
		info.add(createPreLab);
		
		JLabel viewPerLab = new JLabel("View Current Characters");
		viewPerLab.setHorizontalAlignment(SwingConstants.CENTER);
		viewPerLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		viewPerLab.setBounds(44, 190, 156, 27);
		info.add(viewPerLab);
		
		JLabel viewAdvLab = new JLabel("View Current Adventures");
		viewAdvLab.setHorizontalAlignment(SwingConstants.CENTER);
		viewAdvLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		viewAdvLab.setBounds(292, 190, 156, 27);
		info.add(viewAdvLab);
		
		JLabel createAdvLab = new JLabel("Create New Adventure");
		createAdvLab.setHorizontalAlignment(SwingConstants.CENTER);
		createAdvLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		createAdvLab.setBounds(292, 81, 156, 27);
		info.add(createAdvLab);
		
	}
	
}

