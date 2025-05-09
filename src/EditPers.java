import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Color;
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

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EditPers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel pers; 
	private JTextField FirNaTxt;
	private JTextField MidNaTxt;
	private JTextField LasNaTxt;
	private JTextField selLev;

	/**
	 * Create the panel.
	 */
	public EditPers(JInternalFrame internalFrame, DndDatabase data, Player user, Persona chara) {
		pers = new JPanel();
		pers.setBackground(new Color(240, 255, 255));
		pers.setBounds(10, 10, 502, 348);
		pers.setLayout(null);
		
		
		
		JLabel PlayerIn = new JLabel("Edit Character");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(144, 10, 284, 50);
		pers.add(PlayerIn);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setEditable(false);
		txtrHello.setText("Player Info\r\n" + user.toString());
		txtrHello.setBounds(10, 10, 121, 41);
		pers.add(txtrHello);
		
		
		internalFrame.getContentPane().add(pers); 
		
		FirNaTxt = new JTextField();
		String first = chara.getPFirstName(); 
		FirNaTxt.setText(first);
		FirNaTxt.setBounds(119, 134, 96, 19);
		pers.add(FirNaTxt);
		FirNaTxt.setColumns(10);
		
		MidNaTxt = new JTextField();
		String middle = chara.getMiddName(); 
		MidNaTxt.setText(middle);
		MidNaTxt.setBounds(119, 171, 96, 19);
		pers.add(MidNaTxt);
		MidNaTxt.setColumns(10);
		
		LasNaTxt = new JTextField();
		String last = chara.getPLastName();
		LasNaTxt.setText(last);
		LasNaTxt.setBounds(119, 208, 96, 19);
		pers.add(LasNaTxt);
		LasNaTxt.setColumns(10);
		
		Class[] classes = data.getAllClasses(data);
		int cla = chara.getClassID();
		JTextArea DisClass = new JTextArea();
		DisClass.setText(classes[cla].getClassName());
		DisClass.setEditable(false);
		DisClass.setBounds(289, 131, 109, 21);
		pers.add(DisClass);
		
		Race[] races = data.getAllRace(data);
		int ra = chara.getRaceID();
		JTextArea DisRace = new JTextArea();
		DisRace.setText(races[ra].getRaceName());
		DisRace.setEditable(false);
		DisRace.setBounds(289, 198, 109, 21);
		pers.add(DisRace);
		
		selLev = new JTextField();
		int lev = chara.getLev(); 
		String slev = "" + lev; 
		selLev.setText(slev);
		pers.add(selLev); 
		selLev.setBounds(44, 287, 96, 19);
		selLev.setColumns(10);
		
		JLabel charLevLab = new JLabel("Level ");
		charLevLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		charLevLab.setBounds(44, 264, 67, 13);
		pers.add(charLevLab);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		lblNewLabel.setBounds(20, 137, 67, 13);
		pers.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		lblLastName.setBounds(20, 211, 67, 13);
		pers.add(lblLastName);
		
		JRadioButton MidNaOpt = new JRadioButton("Middle Name");
		MidNaOpt.setBackground(new Color(240, 255, 255));
		MidNaOpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pers.add(MidNaTxt);
				pers.repaint();
			}
		});

		MidNaOpt.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		MidNaOpt.setBounds(10, 170, 103, 21);
		pers.add(MidNaOpt);
		
		
		JButton SaveBut = new JButton("SAVE");
		SaveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = FirNaTxt.getText(); 
				System.out.println(first); 
				String middle = MidNaTxt.getText(); 
				System.out.println(middle); 
				String last = LasNaTxt.getText(); 
				System.out.println(last); 
				int lev; 
				String levs = selLev.getText(); 
				lev = Integer.valueOf(levs); 
				System.out.println(lev); 
				
				
				Persona adding = new Persona(user.getPlayID(), first, middle, last, lev); 
				chara.charFirstName = first; 
				chara.charMiddleName = middle; 
				chara.charLastName = last; 
				chara.lev = lev; 
				try {
					data.updatePersona(chara, adding); 
					//data.getAndsetClasName(adding); 
					//data.getAndsetRaceName(adding); 
					//data.getAndSetCharID(adding); 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DisplaySingPers dis = new DisplaySingPers(internalFrame, data, user, chara); 
				pers.setVisible(false);

				
				
				
				
				
			}
		});
		SaveBut.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		SaveBut.setBounds(382, 272, 85, 21);
		pers.add(SaveBut);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerInfo info = new PlayerInfo(internalFrame, data, user); 
				pers.setVisible(false);
			}
		});
		btnExit.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		btnExit.setBounds(307, 272, 67, 21);
		pers.add(btnExit);
		
		JLabel PickClaLab = new JLabel("Select Class");
		PickClaLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		PickClaLab.setBounds(289, 110, 85, 13);
		pers.add(PickClaLab);
		
		JLabel PickRacLab = new JLabel("Select Race");
		PickRacLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		PickRacLab.setBounds(289, 177, 85, 13);
		pers.add(PickRacLab);
		
		JLabel CharIDLab = new JLabel("Character ID");
		CharIDLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		CharIDLab.setBounds(10, 89, 79, 21);
		pers.add(CharIDLab);
		
		JTextArea charID = new JTextArea();
		String chaiD = "" + chara.getCharID(); 
		charID.setText(chaiD);
		charID.setEditable(false);
		charID.setBounds(94, 86, 37, 21);
		pers.add(charID);
		
		JLabel CreaDateLab = new JLabel("Creation Date");
		CreaDateLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		CreaDateLab.setBounds(235, 82, 89, 21);
		pers.add(CreaDateLab);
		
		JTextArea creaDateDis = new JTextArea();
		Date da = chara.getCal(); 
		creaDateDis.setText(da.toString());
		creaDateDis.setEditable(false);
		creaDateDis.setBounds(319, 82, 109, 21);
		pers.add(creaDateDis);
	
		
		
	}
}



