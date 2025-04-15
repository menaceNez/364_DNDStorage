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
		//create.setVisible(false);
		
		
		JLabel PlayerIn = new JLabel("Player Info");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(154, 42, 186, 50);
		info.add(PlayerIn);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setText(user.toString());
		txtrHello.setBounds(194, 147, 121, 27);
		info.add(txtrHello);
		
		internalFrame.getContentPane().add(info); 
		
	}
	
}


