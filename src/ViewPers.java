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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewPers extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JPanel viewPer; 
	public Persona[] chara; 
	private JTextField startRange;
	private JTextField endRange;

	/**
	 * Create the panel.
	 */
	public ViewPers(JInternalFrame internalFrame, DndDatabase data) {
		
		viewPer = new JPanel();
		viewPer.setBackground(new Color(240, 255, 255));
		viewPer.setBounds(10, 10, 641, 388);
		viewPer.setLayout(null);

		
		
		JLabel PlayerIn = new JLabel("View Characters");
		PlayerIn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		PlayerIn.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerIn.setBounds(155, 10, 246, 50);
		viewPer.add(PlayerIn);
		
		internalFrame.getContentPane().add(viewPer);
		internalFrame.repaint();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(259, 76, 372, 302);
		viewPer.add(scrollPane);
		
		
		JList<Persona> list = new JList<>();
		scrollPane.setViewportView(list);
		list.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 10));
		
		JTextArea txtrSelectACharacter = new JTextArea();
		txtrSelectACharacter.setWrapStyleWord(true);
		txtrSelectACharacter.setLineWrap(true);
		txtrSelectACharacter.setText("Select attributes to sort by");
		txtrSelectACharacter.setBounds(10, 10, 149, 50);
		viewPer.add(txtrSelectACharacter);
		

		
		
		
		JTextArea txtrClickSelectTo = new JTextArea();
		txtrClickSelectTo.setFont(new Font("Modern No. 20", Font.PLAIN, 11));
		txtrClickSelectTo.setWrapStyleWord(true);
		txtrClickSelectTo.setText("click select to see all characters with chose attributes");
		txtrClickSelectTo.setLineWrap(true);
		txtrClickSelectTo.setBounds(10, 245, 85, 43);
		viewPer.add(txtrClickSelectTo);
		
		
		
		JTextArea txtrClearSelectedCharaters = new JTextArea();
		txtrClearSelectedCharaters.setWrapStyleWord(true);
		txtrClearSelectedCharaters.setText("Clear Selected Characters");
		txtrClearSelectedCharaters.setLineWrap(true);
		txtrClearSelectedCharaters.setFont(new Font("Modern No. 20", Font.PLAIN, 11));
		txtrClearSelectedCharaters.setBounds(116, 255, 85, 31);
		viewPer.add(txtrClearSelectedCharaters);
		
		Race[] race = data.getAllRace(data); 
		JComboBox<Race> RaceSel = new JComboBox<Race>(race);
		RaceSel.setBounds(10, 147, 103, 21);
		//viewPer.add(RaceSel);
		
		Class[] clas = data.getAllClasses(data); 
		JComboBox<Class> ClassSel = new JComboBox<Class>(clas);
		ClassSel.setBounds(155, 147, 103, 21);
		//viewPer.add(ClassSel);
		
		
		
		JLabel RacSelLab = new JLabel("Select a Race");
		RacSelLab.setBounds(10, 124, 103, 13);
		//viewPer.add(RacSelLab);
		
		JLabel ClaSelLab = new JLabel("Select a Class");
		ClaSelLab.setBounds(155, 124, 103, 13);
		//viewPer.add(ClaSelLab);
		
		JRadioButton RaceSelect = new JRadioButton("Race");
		RaceSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPer.add(RaceSel);
				viewPer.add(RacSelLab);
				viewPer.repaint();
			}
		});
		RaceSelect.setBackground(new Color(240, 255, 255));
		RaceSelect.setBounds(10, 68, 103, 21);
		viewPer.add(RaceSelect);
		
		JRadioButton ClassSelect = new JRadioButton("Class");
		ClassSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					viewPer.add(ClassSel);
					viewPer.add(ClaSelLab); 
					viewPer.repaint();
			}
		});
		ClassSelect.setBackground(new Color(240, 255, 255));
		ClassSelect.setBounds(132, 66, 103, 21);
		viewPer.add(ClassSelect);
		
		startRange = new JTextField();
		startRange.setBounds(10, 216, 103, 19);
		startRange.setColumns(10);
		
		endRange = new JTextField();
		endRange.setColumns(10);
		endRange.setBounds(155, 216, 103, 19);
		endRange.setVisible(false);
		
		
		JLabel LevLab1 = new JLabel("Enter a Level");
		LevLab1.setBounds(10, 188, 85, 13);
		
		JLabel RangLevLab1 = new JLabel("Between");
		RangLevLab1.setBounds(10, 203, 65, 13);
		
		
		JLabel RangLevLab2 = new JLabel("AND");
		RangLevLab2.setBounds(123, 219, 36, 13);
		
		
		JLabel RangLevLab = new JLabel("Enter a Range of Levels");
		RangLevLab.setBounds(10, 188, 149, 13);
		//viewPer.add(RangLevLab);
		
		JRadioButton LevRange = new JRadioButton("Range of Levels");
		LevRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPer.remove(LevLab1);
				viewPer.add(RangLevLab);
				viewPer.add(RangLevLab2);	
				viewPer.add(RangLevLab1);
				viewPer.add(endRange);
				endRange.setVisible(true);
				viewPer.repaint();
			}
		});
		LevRange.setBackground(new Color(240, 255, 255));
		LevRange.setBounds(134, 184, 119, 21);
		//viewPer.add(LevRange);
		
		JRadioButton LevelSelect = new JRadioButton("Level");
		LevelSelect.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			viewPer.add(LevLab1);
			viewPer.add(startRange);
			viewPer.add(LevRange);
			viewPer.repaint();
			}
		});
		LevelSelect.setBackground(new Color(240, 255, 255));
		LevelSelect.setBounds(10, 91, 103, 21);
		viewPer.add(LevelSelect);

		
		
		//Button Groups
		ButtonGroup rac = new ButtonGroup(); 
		rac.add(RaceSelect);
		
		ButtonGroup cla = new ButtonGroup();
		cla.add(ClassSelect);
		
		ButtonGroup le = new ButtonGroup(); 
		le.add(LevelSelect);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		textArea.setBounds(259, 51, 283, 22);
		
		JButton btnNewButton = new JButton("FIND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int racFir = 0; 
				int racSec = 20; 
				int claFir = 0; 
				int claSec = 20; 
				int levFir = 0; 
				int levSec = 100000; 
				int hold = 0; 
				if(rac.getSelection() != null && rac.getSelection().equals(RaceSelect.getModel())){
					System.out.println(RaceSel.getSelectedIndex()); 
					if(RaceSel.getSelectedIndex() != -1) {
					hold = RaceSel.getSelectedIndex(); 
					racFir = hold; 
					racSec = hold; 
					} else {
						racFir = 0; 
						racSec = 20;
					}
				}
				if(cla.getSelection() != null && cla.getSelection().equals(ClassSelect.getModel())) {
					System.out.println(ClassSel.getSelectedIndex()); 
					if(ClassSel.getSelectedIndex() != -1) {
					hold = ClassSel.getSelectedIndex(); 
					claFir = hold; 
					claSec = hold; 
					} else {
						claFir = 0; 
						claSec = 20; 
					}
				}
				if(le.getSelection() != null && le.getSelection().equals(LevelSelect.getModel())) {
					int fir = 0; 
					int las = 1000000; 
					String fi = startRange.getText(); 
					if(!fi.equals("")) {
						System.out.println(fi); 
						fir = Integer.valueOf(fi); 
						System.out.println(fir); 
						if(endRange.isVisible()) {
							fi = endRange.getText(); 
							las = Integer.valueOf(fi); 
						} else {
							las = fir; 
						}
					}
					levFir = fir; 
					levSec = las; 
				}
				
				Persona[] sele = data.Find(racFir, racSec, claFir, claSec, levFir, levSec); 
				list.setListData(sele);
				textArea.setText("Characters Found: " + sele.length);
				viewPer.add(textArea);
				viewPer.repaint();
				
				
				
			}
		});
		btnNewButton.setBounds(10, 298, 85, 31);
		viewPer.add(btnNewButton);
		

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona[] empty = new Persona[1]; 
				textArea.setText("Characters Found: ");
				list.setListData(empty);
				viewPer.repaint();
			}
		});
		btnClear.setBounds(116, 298, 85, 31);
		viewPer.add(btnClear);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewOption vi = new ViewOption(internalFrame, data); 
				viewPer.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 357, 85, 21);
		viewPer.add(btnNewButton_1);
		
		

		
	}
}



