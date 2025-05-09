import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DisplaySingPers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JFrame displChara; 
	public JPanel displ; 
	public boolean exit; 
	private JLabel FirNaLab; 
	private JTextField MidNaTxt;
	private JLabel MidNaLab; 
	private JLabel LasNaLab; 
	private JTextField selLev;

	/**
	 * Create the panel.
	 */
	public DisplaySingPers(JInternalFrame internalFrame, DndDatabase data, Player user, Persona chara) {
		displ = new JPanel();
		displ.setBackground(new Color(240, 255, 255));
		displ.setBounds(10, 10, 502, 348);
		displ.setLayout(null);
		
		
		
		JLabel PlayerIn = new JLabel("Display Character");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(144, 10, 284, 50);
		displ.add(PlayerIn);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setText("Player Info\r\n" + user.toString());
		txtrHello.setBounds(10, 10, 121, 41);
		displ.add(txtrHello);
		
		
		internalFrame.getContentPane().add(displ);
		
		
		MidNaTxt = new JTextField();
		MidNaTxt.setBounds(119, 171, 96, 19);
		//displ.add(MidNaTxt);
		MidNaTxt.setColumns(10);
		
		Class[] classes = data.getAllClasses(data);
		
		Race[] races = data.getAllRace(data);
		
		selLev = new JTextField();
		selLev.setBounds(88, 299, 96, 19);
		selLev.setColumns(10);
		
		JLabel FirNamLab = new JLabel("First Name");
		FirNamLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		FirNamLab.setBounds(20, 137, 67, 13);
		displ.add(FirNamLab);
		
		JLabel LasNamLab = new JLabel("Last Name");
		LasNamLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		LasNamLab.setBounds(20, 211, 67, 13);
		displ.add(LasNamLab);
		
		
		ButtonGroup levl = new ButtonGroup();
		
		JButton EditBut = new JButton("EDIT");
		EditBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPers per = new EditPers(internalFrame, data, user, chara); 
				displ.setVisible(false);
			}
		});
		EditBut.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		EditBut.setBounds(322, 317, 85, 21);
		displ.add(EditBut);
		
		JLabel MidNamLab = new JLabel("Middle Name");
		MidNamLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		MidNamLab.setBounds(10, 174, 89, 13);
		displ.add(MidNamLab);
		
		JLabel charLevLab = new JLabel("Level ");
		charLevLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		charLevLab.setBounds(44, 264, 67, 13);
		displ.add(charLevLab);
		
		JTextArea charFirNa = new JTextArea();
		charFirNa.setText(chara.charFirstName);
		charFirNa.setEditable(false);
		charFirNa.setBounds(94, 133, 139, 21);
		displ.add(charFirNa);
		
		JTextArea charMidNam = new JTextArea();
		charMidNam.setText(chara.charMiddleName);
		charMidNam.setEditable(false);
		charMidNam.setBounds(94, 167, 139, 21);
		displ.add(charMidNam);
		
		JTextArea charLasNam = new JTextArea();
		charLasNam.setText(chara.charLastName);
		charLasNam.setEditable(false);
		charLasNam.setBounds(94, 204, 139, 21);
		displ.add(charLasNam);
		
		JTextArea charCla = new JTextArea();
		charCla.setText(chara.getClaName());
		charCla.setEditable(false);
		charCla.setBounds(289, 133, 139, 21);
		displ.add(charCla);
		
		JLabel ClaLab = new JLabel("Class");
		ClaLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		ClaLab.setBounds(289, 113, 67, 13);
		displ.add(ClaLab);
		
		JLabel RacLab = new JLabel("Race");
		RacLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		RacLab.setBounds(289, 182, 67, 13);
		displ.add(RacLab);
		
		JTextArea charRac = new JTextArea();
		charRac.setText(chara.getRacName());
		charRac.setEditable(false);
		charRac.setBounds(289, 204, 139, 21);
		displ.add(charRac);
		
		JTextArea charLev = new JTextArea();
		String lev = "" + chara.getLev(); 
		charLev.setText(lev);
		charLev.setEditable(false);
		charLev.setBounds(44, 284, 45, 21);
		displ.add(charLev);
		
		JLabel CharIDLab = new JLabel("Character ID");
		CharIDLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		CharIDLab.setBounds(10, 89, 79, 21);
		displ.add(CharIDLab);
		
		JTextArea charID = new JTextArea();
		String chaiD = "" + chara.getCharID(); 
		charID.setText(chaiD);
		charID.setEditable(false);
		charID.setBounds(94, 86, 37, 21);
		displ.add(charID);
		
		JLabel CreaDateLab = new JLabel("Creation Date");
		CreaDateLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		CreaDateLab.setBounds(235, 82, 89, 21);
		displ.add(CreaDateLab);
		
		JTextArea creaDateDis = new JTextArea();
		Date da = chara.creationDate; 
		creaDateDis.setText(da.toString());
		creaDateDis.setEditable(false);
		creaDateDis.setBounds(319, 82, 109, 21);
		displ.add(creaDateDis);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerInfo info = new PlayerInfo(internalFrame, data, user); 
				displ.setVisible(false);
			}
		});
		btnExit.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		btnExit.setBounds(407, 317, 85, 21);
		displ.add(btnExit);
		
		JButton EditBut_1 = new JButton("DELETE");
		EditBut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.deletePers(chara);
					DisplayMultiPers dis = new DisplayMultiPers(internalFrame, data, user); 
					displ.setVisible(false); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		EditBut_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		EditBut_1.setBounds(182, 317, 85, 21);
		displ.add(EditBut_1);
	}
}

