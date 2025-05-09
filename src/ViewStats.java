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
import javax.swing.JTable;

public class ViewStats extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel info; 
	private JTable TopCla;
	private JTable TopRac;

	/**
	 * Create the panel.
	 */
	public ViewStats(JInternalFrame internalFrame, DndDatabase data) {
		info = new JPanel();
		info.setBackground(new Color(240, 255, 255));
		info.setBounds(10, 10, 502, 348);
		info.setLayout(null);
		
		
		JLabel PlayerIn = new JLabel("STATISTICS");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(112, 10, 280, 50);
		info.add(PlayerIn);
		
		
		
		internalFrame.getContentPane().add(info);
;
		
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
		
		
		String[][] top5R = data.top5races();
		String[][] top5C = data.top5classes();
		String[] labs = new String[2];
		labs[0] = "Total";
		labs[1] = "Race";
		
		TopCla = new JTable(top5R, labs);
		//TopCla = new JTable();
		TopCla.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		TopCla.setEnabled(false);
		TopCla.setFillsViewportHeight(true);
		TopCla.setBounds(40, 90, 157, 82);
		info.add(TopCla);
		
		TopRac = new JTable(top5C, labs); 
		//TopRac = new JTable();
		TopRac.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		TopRac.setEnabled(false);
		TopRac.setFillsViewportHeight(true);
		TopRac.setBounds(40, 238, 134, 82);
		info.add(TopRac);
		
		
		
		JLabel TopClaLab = new JLabel("TOP 5 CHOSEN CLASSES");
		TopClaLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		TopClaLab.setHorizontalAlignment(SwingConstants.CENTER);
		TopClaLab.setBounds(10, 57, 196, 13);
		info.add(TopClaLab);
		
		JLabel TopRacLab = new JLabel("TOP 5 CHOSEN RACES");
		TopRacLab.setHorizontalAlignment(SwingConstants.CENTER);
		TopRacLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		TopRacLab.setBounds(10, 205, 196, 13);
		info.add(TopRacLab);
		
		JLabel totLabC = new JLabel("TOTAL\r\n");
		totLabC.setBounds(40, 78, 45, 13);
		info.add(totLabC);
		
		JLabel claLab = new JLabel("CLASS");
		claLab.setHorizontalAlignment(SwingConstants.RIGHT);
		claLab.setBounds(129, 78, 45, 13);
		info.add(claLab);
		
		JLabel totLabR = new JLabel("TOTAL\r\n");
		totLabR.setBounds(40, 227, 45, 13);
		info.add(totLabR);
		
		JLabel racLab = new JLabel("RACE");
		racLab.setHorizontalAlignment(SwingConstants.RIGHT);
		racLab.setBounds(129, 227, 45, 13);
		info.add(racLab);
		
		double ave = data.avgChartoPlay();
		
		JTextPane AvePlaLab = new JTextPane();
		AvePlaLab.setBackground(new Color(240, 255, 255));
		AvePlaLab.setText("AVERAGE # OF CHARACTERS PER PLAYER");
		AvePlaLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		AvePlaLab.setBounds(243, 57, 134, 50);
		info.add(AvePlaLab);
		
		JTextPane txtpnSet = new JTextPane();
		txtpnSet.setText("           "+ ave);
		txtpnSet.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		txtpnSet.setBounds(243, 111, 134, 21);
		info.add(txtpnSet);
		
	}
}


