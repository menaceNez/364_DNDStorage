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

public class ViewOption extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel info; 

	/**
	 * Create the panel.
	 */
	public ViewOption(JInternalFrame internalFrame, DndDatabase data) {
		info = new JPanel();
		info.setBackground(new Color(240, 255, 255));
		info.setBounds(10, 10, 502, 348);
		info.setLayout(null);
		
		
		JLabel PlayerIn = new JLabel("select an item to view");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(112, 10, 280, 50);
		info.add(PlayerIn);
		
		
		
		internalFrame.getContentPane().add(info);
		
		JButton viewPer = new JButton("View");
		viewPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPers pers = new ViewPers(internalFrame, data); 
				//WelcomeScreenGUI wel = new WelcomeScreenGUI(internalFrame, data); 
				info.setVisible(false);
			}
		});
;
		viewPer.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		viewPer.setBounds(73, 128, 102, 41);
		info.add(viewPer);
		
		JButton viewAdv = new JButton("View");
		viewAdv.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		viewAdv.setBounds(314, 128, 102, 41);
		info.add(viewAdv);
		
		JLabel viewPerLab = new JLabel("View Characters");
		viewPerLab.setHorizontalAlignment(SwingConstants.CENTER);
		viewPerLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		viewPerLab.setBounds(48, 91, 156, 27);
		info.add(viewPerLab);
		
		JLabel viewAdvLab = new JLabel("View Adventures");
		viewAdvLab.setHorizontalAlignment(SwingConstants.CENTER);
		viewAdvLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		viewAdvLab.setBounds(288, 91, 156, 27);
		info.add(viewAdvLab);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeScreenGUI wel = new WelcomeScreenGUI(internalFrame, data); 
				info.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		btnNewButton.setBounds(407, 317, 85, 21);
		info.add(btnNewButton);
		
		JButton viewStats = new JButton("View");
		viewStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStats vi = new ViewStats(internalFrame, data);
				info.setVisible(false);
			}
		});
		viewStats.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		viewStats.setBounds(195, 238, 102, 41);
		info.add(viewStats);
		
		JLabel viewStatsLab = new JLabel("View Stats");
		viewStatsLab.setHorizontalAlignment(SwingConstants.CENTER);
		viewStatsLab.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 10));
		viewStatsLab.setBounds(168, 197, 156, 27);
		info.add(viewStatsLab);
		
	}
}


