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
import javax.swing.JList;

public class DisplayMultiPers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel dismulti; 
	public Persona[] chara; 

	/**
	 * Create the panel.
	 */
	public DisplayMultiPers(JInternalFrame internalFrame, DndDatabase data, Player user) {

		try {
			chara = data.findPlayPersonas(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		dismulti = new JPanel();
		dismulti.setBackground(new Color(240, 255, 255));
		dismulti.setBounds(10, 10, 502, 348);
		dismulti.setLayout(null);
		
		
		JLabel PlayerIn = new JLabel("Current Characters");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(155, 10, 246, 50);
		dismulti.add(PlayerIn);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setText(user.toString());
		txtrHello.setBounds(10, 10, 121, 41);
		dismulti.add(txtrHello);
		
		internalFrame.getContentPane().add(dismulti);
		
		JList<Persona> list = new JList<>(chara);
		list.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		list.setBounds(134, 91, 316, 202);
		dismulti.add(list);
		
		JTextArea txtrSelectACharacter = new JTextArea();
		txtrSelectACharacter.setWrapStyleWord(true);
		txtrSelectACharacter.setLineWrap(true);
		txtrSelectACharacter.setText("Select a character and click below to see more information");
		txtrSelectACharacter.setBounds(10, 106, 104, 112);
		dismulti.add(txtrSelectACharacter);
		
		JButton btnNewButton = new JButton("SELECT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona going = list.getSelectedValue(); 
				DisplaySingPers dis = new DisplaySingPers(internalFrame, data, user, going); 
				dismulti.setVisible(false);
			}
		});
		btnNewButton.setBounds(20, 228, 85, 31);
		dismulti.add(btnNewButton);
		
	}
}


