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

public class CreatePers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel pers; 
	private JTextField FirNaTxt;
	private JTextField MidNaTxt;
	private JTextField LasNaTxt;
	private JTextField selLev;

	/**
	 * Create the panel.
	 */
	public CreatePers(JInternalFrame internalFrame, DndDatabase data, Player user) {
		pers = new JPanel();
		pers.setBackground(new Color(240, 255, 255));
		pers.setBounds(10, 10, 502, 348);
		pers.setLayout(null);
		
		
		
		JLabel PlayerIn = new JLabel("Create New Character");
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
		FirNaTxt.setBounds(119, 134, 96, 19);
		pers.add(FirNaTxt);
		FirNaTxt.setColumns(10);
		
		MidNaTxt = new JTextField();
		MidNaTxt.setBounds(119, 171, 96, 19);
		//pers.add(MidNaTxt);
		MidNaTxt.setColumns(10);
		
		LasNaTxt = new JTextField();
		LasNaTxt.setBounds(119, 208, 96, 19);
		pers.add(LasNaTxt);
		LasNaTxt.setColumns(10);
		
		Class[] classes = data.getAllClasses(data); 
		JComboBox<Class> classPick = new JComboBox<>(classes);
		classPick.setBounds(289, 133, 139, 21);
		pers.add(classPick);
		
		Race[] races = data.getAllRace(data); 
		JComboBox<Race> racePick = new JComboBox<>(races);
		racePick.setBounds(289, 200, 139, 21);
		pers.add(racePick);
		
		selLev = new JTextField();
		selLev.setBounds(88, 299, 96, 19);
		selLev.setColumns(10);
		
		JRadioButton SetLevOpt = new JRadioButton("Set Level");
		SetLevOpt.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		SetLevOpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pers.add(selLev);
				pers.repaint();
				
			}
		});
		
		SetLevOpt.setBackground(new Color(240, 255, 255));
		SetLevOpt.setBounds(44, 272, 87, 21);
		pers.add(SetLevOpt);
		
		JRadioButton DeftLevOpt = new JRadioButton("Default Level (1)");
		DeftLevOpt.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		DeftLevOpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selLev.setVisible(false);
				pers.repaint();
			}
		});
		DeftLevOpt.setBackground(new Color(240, 255, 255));
		DeftLevOpt.setBounds(150, 272, 128, 21);
		pers.add(DeftLevOpt);
		
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
		
		
		ButtonGroup levl = new ButtonGroup(); 
		levl.add(SetLevOpt);
		levl.add(DeftLevOpt);
		
		JTextPane SaveErrLab = new JTextPane();
		SaveErrLab.setForeground(new Color(255, 0, 0));
		SaveErrLab.setEditable(false);
		SaveErrLab.setBackground(new Color(240, 255, 255));
		SaveErrLab.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		SaveErrLab.setText("ERROR: Insufficent Informaton Entered");
		SaveErrLab.setBounds(20, 68, 195, 41);
		SaveErrLab.setVisible(false);
		//pers.add(SaveErrLab);
		
		
		JButton SaveBut = new JButton("SAVE");
		SaveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pers.add(SaveErrLab);
				if(SaveErrLab.isVisible()) {
					SaveErrLab.setVisible(false); 
				}
				String first = FirNaTxt.getText(); 
				System.out.println(first); 
				String middle = MidNaTxt.getText(); 
				System.out.println(middle); 
				String last = LasNaTxt.getText(); 
				System.out.println(last); 
				int lev; 
				if(selLev.isVisible()) {
					String levs = selLev.getText(); 
					lev = Integer.valueOf(levs); 
				} else {
					lev = 1; 
				}
				System.out.println(lev); 
				int clapic = classPick.getSelectedIndex(); 
				Class clasPicked = classes[clapic]; 
				int racpic = racePick.getSelectedIndex(); 
				Race racPicked = races[racpic]; 
				clapic = clasPicked.getClassID(); 
				System.out.println(clapic + " class" + clasPicked.getClassName()); 
				racpic = racPicked.getRaceID(); 
				System.out.println(racpic + " race" + racPicked.getRaceName()); 
				//java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				Date date = Date.valueOf(LocalDate.now());
				System.out.println(date); 
				
				
				Persona adding = new Persona(user.getPlayID(), first, middle, last, clapic, racpic, lev, date); 
				try {
					data.insertPersona(adding);
					data.getAndsetClasName(adding); 
					data.getAndsetRaceName(adding); 
					data.getAndSetCharID(adding); 
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					SaveErrLab.setVisible(true);
					pers.repaint();
					e1.printStackTrace();
				} /*catch (SQLException j) {
					SaveErrLab.setVisible(true);
					pers.repaint();
					j.printStackTrace();
				}*/
				DisplaySingPers dis = new DisplaySingPers(internalFrame, data, user, adding); 
				pers.setVisible(false);

				
				
				
				
				
			}
		});
		SaveBut.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		SaveBut.setBounds(382, 272, 85, 21);
		pers.add(SaveBut);
		
		JLabel PickClaLab = new JLabel("Select Class");
		PickClaLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		PickClaLab.setBounds(289, 110, 85, 13);
		pers.add(PickClaLab);
		
		JLabel PickRacLab = new JLabel("Select Race");
		PickRacLab.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		PickRacLab.setBounds(289, 177, 85, 13);
		pers.add(PickRacLab);
		
		JButton ExitBut = new JButton("EXIT");
		ExitBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PlayerInfo info = new PlayerInfo(internalFrame, data,  user); 
				 pers.setVisible(false);
			}
		});
		ExitBut.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		ExitBut.setBounds(289, 272, 85, 21);
		pers.add(ExitBut);
		
		
	}
}


