import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.SwingConstants;

public class CreateAdventure extends JPanel {

	private static final long serialVersionUID = 1L;

	private JInternalFrame fr;
	private DndDatabase db;
	private Player player;
	public JPanel jp;
	private JTextField campaignField;
	private ArrayList<Campaign> cmpList;
	private JTextField adventureNameField;
	private JLabel lblOneOrMore;
	private JList<Campaign> campJList;
	private JList<String> playerJList;

	/**
	 * Create the panel.
	 * 
	 * @param data
	 * @param internalFrame
	 */
	public CreateAdventure(JInternalFrame internalFrame, DndDatabase db, Player user) {
		this.fr = internalFrame;
		this.db = db;
		this.player = user;

		jp = new JPanel();
		jp.setBackground(new Color(153, 193, 241));
		jp.setBounds(10, 10, 502, 348);
		jp.setLayout(null);
		fr.getContentPane().add(jp);

		JLabel lblCampaignName = new JLabel("Select A Campaign");
		lblCampaignName.setBounds(270, 17, 122, 17);
		jp.add(lblCampaignName);

		campaignField = new JTextField();
		campaignField.setBounds(270, 237, 220, 21);
		jp.add(campaignField);
		campaignField.setColumns(10);

		JButton btnCampaign = new JButton("Create Campaign");
		btnCampaign.setFont(new Font("Dialog", Font.BOLD, 9));
		btnCampaign.setBounds(270, 265, 122, 27);
		jp.add(btnCampaign);

		JLabel lblPlayerNames = new JLabel("Select A Dungeon Master");
		lblPlayerNames.setBounds(18, 17, 170, 17);
		jp.add(lblPlayerNames);

		JScrollPane CampaignJScroll = new JScrollPane();
		CampaignJScroll.setBounds(270, 40, 220, 180);
		jp.add(CampaignJScroll);

		campJList = new JList<Campaign>();
		CampaignJScroll.setViewportView(campJList);
		fillCampaignList(campJList);

		JScrollPane PlayerJScroll = new JScrollPane();
		PlayerJScroll.setBounds(12, 40, 220, 180);
		jp.add(PlayerJScroll);

		playerJList = new JList<>();
//		 // add this to other screen

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(415, 313, 75, 25);
		jp.add(btnBack);

		adventureNameField = new JTextField();
		adventureNameField.setToolTipText("AdventureName");
		adventureNameField.setBounds(12, 237, 220, 21);
		jp.add(adventureNameField);
		adventureNameField.setColumns(10);

		JButton btnCreateAdventure = new JButton("Create Adventure");
		btnCreateAdventure.setFont(new Font("Dialog", Font.BOLD, 9));
		btnCreateAdventure.setBounds(12, 265, 122, 27);
		jp.add(btnCreateAdventure);

		PlayerJScroll.setViewportView(playerJList); // This is what sets the current list to the scroll pane,
		fillPlayerList(playerJList);		
		
		/* ################################ Events and listeners are here ################################ */

		lblOneOrMore = new JLabel("Invalid PlayerID or Missing Adventure Name");
		lblOneOrMore.setForeground(new Color(237, 51, 59));
		lblOneOrMore.setBounds(140, 317, 281, 17);
		jp.add(lblOneOrMore);
		
		JLabel lblEnterAdventureName = new JLabel("Enter Adventure Name");
		lblEnterAdventureName.setBounds(12, 218, 162, 17);
		jp.add(lblEnterAdventureName);
		
		JLabel lblEnterCampaignName = new JLabel("Enter Campaign Name");
		lblEnterCampaignName.setBounds(270, 218, 162, 17);
		jp.add(lblEnterCampaignName);
		lblOneOrMore.setVisible(false);

		btnCampaign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdventureQueries aq = new AdventureQueries(db);

				String input = campaignField.getText().trim();

				aq.insertCampaign(new Campaign(input));

				fillCampaignList(campJList);
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PlayerInfo(internalFrame, db, user);
				jp.setVisible(false);
			}
		});

		btnCreateAdventure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constructAdventure();
			}
		});

		/* ################################ Events and listeners end here ################################ */
	}

	/*
	 * ###################################### Methods for create adventure start
	 * here ######################################
	 */

	private void fillCampaignList(JList<Campaign> list) {
		CampaignQueries cmp = new CampaignQueries(db);

		cmpList = cmp.getAllCampaigns();
		DefaultListModel<Campaign> cmpModel = new DefaultListModel<>();

		for (Campaign camp : cmpList) {
			cmpModel.addElement(camp);
		}

		list.setModel(cmpModel);
	}

	private void fillPlayerList(JList<String> list) {
		AdventureQueries qu = new AdventureQueries(db);

		ArrayList<Player> playerList = qu.getAllPlayer();

		DefaultListModel<String> plyModel = new DefaultListModel<>();

		for (Player player : playerList) {
			String playerStr = player.getPlayID() +" "+ player.getFirstName() + " " + player.getLastName();
			plyModel.addElement(playerStr);
		}

		list.setModel(plyModel);
	}

//	private void clearListModel(JList<?> list) {
//		DefaultListModel<?> mdl = (DefaultListModel<?>) list.getModel();
//		mdl.clear();
//	}
// Add this to the next window after createAdventure
//	private void fillPlayerCharacters(JList<String> list, String playerID, JList<String> charList, JScrollPane scrollPane) {
//		AdventureQueries q = new AdventureQueries(db);
//		ArrayList<Persona> characters = q.getPlayerCharacter(playerID);
//
//		DefaultListModel<String> it2 = (DefaultListModel<String>) charList.getModel();
//
//		clearListModel(list);
//		clearListModel(charList);
//		scrollPane.setViewportView(charList);
//
//		for (Persona p : characters) {
//			// convert to string here
//			String charaStr = p.getCharID() + " " + p.getPFirstName() + " " + p.getPLastName();
//			it2.addElement(charaStr);
//		}
//	}
	
	private void constructAdventure() {
		// collect input fields, validate integer and exist in table, insert adventure and add characters to PersonaPlaysIn
		try {	
			AdventureQueries aq = new AdventureQueries(db);
			String advName = adventureNameField.getText().trim();
			Campaign selectedCamp = campJList.getSelectedValue();
			String selectPlayer = playerJList.getSelectedValue();
			boolean isAdvNameTaken = aq.validateAdventureName(advName);
			
			if(advName.length() > 0 && selectedCamp != null && selectPlayer != null && !isAdvNameTaken) { // ensure all needed input is present
				lblOneOrMore.setVisible(false);	

				int advID = aq.insertAdventure(selectedCamp.getCampId(), Integer.parseInt(selectPlayer.split(" ")[0]), advName);
				if(advID == -1) {
					System.out.println("Error in constructAdventure@CreateAdventure");
					return;
				}

				new EditAdventure(fr, db, player, advID, advName);
				jp.setVisible(false);
			}
			else { // display error message for invalid input
				lblOneOrMore.setVisible(true);
			}
		}
		catch(NumberFormatException error) {
			System.out.print("Error parsing number: "+ error);
				lblOneOrMore.setVisible(true);
		}
	}
}
