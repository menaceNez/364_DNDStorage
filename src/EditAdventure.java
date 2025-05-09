import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;

public class EditAdventure extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalFrame fr;
	private DndDatabase db;
	private Player user;
	private int advID;
	private JPanel jp;
	private JList<String> playerJList;
	private JList<Adventure> personasAddedJList;
	private JList<Persona> personaJList;
	private JScrollPane scrollPanePlayerCharacters;
	private JTextField changeNameTF;
	ArrayList<Adventure> adventures;

	/**
	 * Create the panel.
	 */
	public EditAdventure(JInternalFrame fr, DndDatabase db, Player user, int advID, String advName) {
		this.fr = fr;
		this.db = db;
		this.advID = advID;

		System.out.println(advID + " " + advName);

		jp = new JPanel();
		jp.setBackground(new Color(153, 193, 241));
		jp.setBounds(10, 10, 502, 348);
		jp.setLayout(null);
		fr.getContentPane().add(jp);

		scrollPanePlayerCharacters = new JScrollPane();
		scrollPanePlayerCharacters.setBounds(12, 60, 220, 180);
		jp.add(scrollPanePlayerCharacters);

		playerJList = new JList<>(new DefaultListModel<String>());
		scrollPanePlayerCharacters.setViewportView(playerJList);
		personaJList = new JList<>(new DefaultListModel<Persona>());

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(389, 7, 101, 27);
		jp.add(btnBack);

		JButton btnAddPersona = new JButton("Add Character");
		btnAddPersona.setFont(new Font("Dialog", Font.BOLD, 9));

		btnAddPersona.setBounds(12, 250, 114, 27);
		jp.add(btnAddPersona);

		JScrollPane scrollPanePersonas = new JScrollPane();
		scrollPanePersonas.setBounds(270, 60, 220, 180);
		jp.add(scrollPanePersonas);

		personasAddedJList = new JList<>(new DefaultListModel<Adventure>());
		scrollPanePersonas.setViewportView(personasAddedJList);

		JLabel lblCurrentlyAddedPersonas = new JLabel("Currently Added Characters:");
		lblCurrentlyAddedPersonas.setBounds(269, 38, 178, 17);
		jp.add(lblCurrentlyAddedPersonas);

		JLabel lblPlayerList = new JLabel("Find Characters:");
		lblPlayerList.setBounds(12, 38, 114, 17);
		jp.add(lblPlayerList);

		fillPlayerList();
		fillPersonasList();

		JButton btnBackToPlayers = new JButton("Back To Players");
		btnBackToPlayers.setFont(new Font("Dialog", Font.BOLD, 8));
		btnBackToPlayers.setBounds(132, 36, 100, 20);
		jp.add(btnBackToPlayers);

		JButton btnRemovePers = new JButton("Remove Character");
		btnRemovePers.setFont(new Font("Dialog", Font.BOLD, 9));
		btnRemovePers.setBounds(376, 250, 114, 27);
		jp.add(btnRemovePers);

		changeNameTF = new JTextField(advName);
		changeNameTF.setBounds(201, 283, 114, 21);
		jp.add(changeNameTF);
		changeNameTF.setColumns(10);

		JLabel lblChangeName = new JLabel("Change Adventure Name");
		lblChangeName.setBounds(181, 266, 163, 17);
		jp.add(lblChangeName);

		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.setFont(new Font("Dialog", Font.BOLD, 9));
		btnChangeName.setBounds(201, 309, 114, 27);
		jp.add(btnChangeName);
		
		JLabel lblCurrentAdventure = new JLabel("Current Adventure:");
		lblCurrentAdventure.setBounds(12, 12, 127, 17);
		jp.add(lblCurrentAdventure);
		
		JLabel lblAdventureNameDyn = new JLabel(advName+" ");
		lblAdventureNameDyn.setBounds(143, 12, 251, 17);
		jp.add(lblAdventureNameDyn);

		/*
		 * ########################### Event Listeners HERE ###########################
		 */
		btnAddPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdventureQueries aq = new AdventureQueries(db);
					Persona persona = personaJList.getSelectedValue();
					System.out.println("Got back: " + persona+" \n"+"CharID: "+persona.getCharID());

					if (aq.validatePersonaID(persona.getCharID())) {
						aq.insertPersonaPlaysIn(advID, persona.getCharID());
						fillPersonasList();
					}
				} catch (NumberFormatException error) {
					System.out.println("Error btnAddPersona@AdventurePlayers: " + error);
				}
			}
		});

		btnBackToPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearListModel(personaJList);
				fillPlayerList();
				scrollPanePlayerCharacters.setViewportView(playerJList);
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewAdventures(fr, db, user);
				jp.setVisible(false);
			}
		});

		playerJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = playerJList.locationToIndex(e.getPoint());

				if (index != -1) {
					String selectedVal = playerJList.getModel().getElementAt(index);
					String playerID = selectedVal.split(" ")[0];

					fillPlayerCharacters(playerID);
				}
			}
		});
		
//		personasAddedJList.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int index = personasAddedJList.locationToIndex(e.getPoint());
//				
//				if(index != -1) {
//					Adventure selectedVal = personasAddedJList.getModel().getElementAt(index);	
//					
//				}
//			}
//		});
//		
		btnRemovePers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdventureQueries aq = new AdventureQueries(db);
					Adventure selected = personasAddedJList.getSelectedValue();

					if(aq.validatePersonaID(selected.getPersona().charID)) {
						aq.deletePersonaPlaysIn(advID, selected.getPersona().charID);
					}	
					else {
						// display error here.						
					}

					clearListModel(personasAddedJList);
					fillPersonasList();
					
				}
				catch(NumberFormatException error) {
					System.out.println("Error in btnRemovePers@EditAdventure : "+ error);
				}	
			}
		});
		
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdventureQueries aq = new AdventureQueries(db);
				String updatedName = changeNameTF.getText();
				
				if(updatedName.length() != 0) {
					if(aq.updateAdventureName(updatedName, advID)) {
						// error for 
					}
				}
				else {
					// show error
				}
			}
		});
	}
	
	private void fillPersonasList() {
		// use advID to pull all adventures with the id associated
		clearListModel(personasAddedJList);
		AdventureQueries aq = new AdventureQueries(db);
		adventures = aq.getAdventurePersonas(advID);
		if (adventures == null)
			return;

		DefaultListModel<Adventure> lm = new DefaultListModel<>();

		for (Adventure a : adventures) {
			lm.addElement(a);
		}
		personasAddedJList.setModel(lm);
	}

	private void fillPlayerList() {
		clearListModel(playerJList);

		AdventureQueries qu = new AdventureQueries(db);

		ArrayList<Player> playerList = qu.getAllPlayer();

		DefaultListModel<String> plyModel = new DefaultListModel<>();

		for (Player player : playerList) {
			String playerStr = player.getPlayID() + " " + player.getFirstName() + " " + player.getLastName();
			plyModel.addElement(playerStr);
		}

		playerJList.setModel(plyModel);
	}

	private void clearListModel(JList<?> list) {
		DefaultListModel<?> mdl = (DefaultListModel<?>) list.getModel();
		mdl.clear();
	}

// Add this to the next window after createAdventure
	private void fillPlayerCharacters(String playerID) {
		clearListModel(playerJList);
		clearListModel(personaJList);

		AdventureQueries q = new AdventureQueries(db);
		ArrayList<Persona> characters = q.getPlayerCharacter(playerID);
		System.out.println("Got Chracters: "+ characters.toString());

		DefaultListModel<Persona> it2 = (DefaultListModel<Persona>) personaJList.getModel();

		

		for (Persona p : characters) {
			// convert to string here
//			String charaStr = p.getCharID() + " " + p.getPFirstName() + " " + p.getPLastName();
			it2.addElement(p);
		}

		scrollPanePlayerCharacters.setViewportView(personaJList);
		personaJList.setModel(it2);
	}
}
