import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;

public class AdventurePlayers extends JPanel {
	private JInternalFrame fr;
	private DndDatabase db;
	public JPanel jp;
	private int advID;

	private static final long serialVersionUID = 1L;

	private JTextField personaTF;
	private JList<String> playerJList;
	private JList<String> personasAddedJList;
	private JList<String> personaJList;
	private JScrollPane scrollPanePlayerCharacters;
	private ArrayList<Adventure> adventures;

	/**
	 * Create the panel.
	 */
	public AdventurePlayers(JInternalFrame internalFrame, DndDatabase db, Player user, int advID) {
		this.fr = internalFrame;
		this.db = db;
		this.advID = advID;

		jp = new JPanel();
		jp.setBackground(new Color(153, 193, 241));
		jp.setBounds(10, 10, 502, 348);
		jp.setLayout(null);
		fr.getContentPane().add(jp);

		scrollPanePlayerCharacters = new JScrollPane();
		scrollPanePlayerCharacters.setBounds(12, 40, 220, 180);
		jp.add(scrollPanePlayerCharacters);

		playerJList = new JList<>(new DefaultListModel<String>());
		scrollPanePlayerCharacters.setViewportView(playerJList);
		personaJList = new JList<>(new DefaultListModel<String>());

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(389, 309, 101, 27);
		jp.add(btnBack);

		JButton btnAddPersona = new JButton("Add Persona");

		btnAddPersona.setBounds(12, 309, 114, 27);
		jp.add(btnAddPersona);

		personaTF = new JTextField();
		personaTF.setBounds(12, 283, 114, 21);
		jp.add(personaTF);
		personaTF.setColumns(10);

		JLabel lblAddPersona = new JLabel("CharacterID:");
		lblAddPersona.setBounds(12, 264, 101, 17);
		jp.add(lblAddPersona);

		JScrollPane scrollPanePersonas = new JScrollPane();
		scrollPanePersonas.setBounds(270, 40, 220, 180);
		jp.add(scrollPanePersonas);

		personasAddedJList = new JList<>(new DefaultListModel<String>());
		scrollPanePersonas.setViewportView(personasAddedJList);

		JLabel lblCurrentlyAddedPersonas = new JLabel("Currently Added Personas:");
		lblCurrentlyAddedPersonas.setBounds(269, 17, 178, 17);
		jp.add(lblCurrentlyAddedPersonas);

		JLabel lblPlayerList = new JLabel("Find Personas:");
		lblPlayerList.setBounds(12, 17, 119, 17);
		jp.add(lblPlayerList);

		fillPlayerList();
		fillPersonasList();

		JButton btnBackToPlayers = new JButton("Back To Players");
		btnBackToPlayers.setFont(new Font("Dialog", Font.BOLD, 8));
		btnBackToPlayers.setBounds(132, 14, 100, 20);
		jp.add(btnBackToPlayers);

		/*
		 * ########################### Event Listeners HERE ###########################
		 */
		btnAddPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdventureQueries aq = new AdventureQueries(db);
					int personaID = Integer.parseInt(personaTF.getText());

					if(aq.validatePersonaID(personaID)) {
						aq.insertPersonaPlaysIn(advID, personaID);			
						fillPersonasList();
					}
				}
				catch(NumberFormatException error) {
					System.out.println("Error btnAddPersona@AdventurePlayers: " +error);
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
				new CreateAdventure(fr, db, user);
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
	}
	
	private void fillPersonasList() {
		// use advID to pull all adventures with the id associated
		AdventureQueries aq = new AdventureQueries(db);
		adventures = aq.getAdventurePersonas(advID);
		if(adventures == null) return;
		
		DefaultListModel<String> lm = new DefaultListModel<>();
		
		for(Adventure a: adventures) {	
			lm.addElement(a.toString());
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
		AdventureQueries q = new AdventureQueries(db);
		ArrayList<Persona> characters = q.getPlayerCharacter(playerID);

		DefaultListModel<String> it2 = (DefaultListModel<String>) personaJList.getModel();

		clearListModel(playerJList);
		clearListModel(personaJList);
		scrollPanePlayerCharacters.setViewportView(personaJList);

		for (Persona p : characters) {
			// convert to string here
			String charaStr = p.getCharID() + " " + p.getPFirstName() + " " + p.getPLastName();
			it2.addElement(charaStr);
		}
	}
}
