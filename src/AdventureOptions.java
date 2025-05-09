import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdventureOptions extends JPanel {
	private JInternalFrame fr;
	private DndDatabase db;
	private Player user;
	private JPanel jp;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdventureOptions(JInternalFrame fr, DndDatabase db, Player user, int advID, String advName) {
		this.fr = fr;
		this.db = db;
		this.user = user;

		jp = new JPanel();
		jp.setBackground(new Color(153, 193, 241));
		jp.setBounds(10, 10, 502, 348);
		jp.setLayout(null);
		fr.getContentPane().add(jp);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(151, 204, 200, 50);
		jp.add(btnBack);
		
		JButton btnAdventureCharacters = new JButton("Edit Aventure Characters");
		btnAdventureCharacters.setBounds(151, 98, 200, 50);
		jp.add(btnAdventureCharacters);

		
		JButton btnAddRemoveGameSession = new JButton("Edit Adventure Events");
		btnAddRemoveGameSession.setBounds(151, 151, 200, 50);
		jp.add(btnAddRemoveGameSession);
		
		JLabel lblCurrentAdventure = new JLabel("Current Adventure:");
		lblCurrentAdventure.setBounds(12, 12, 121, 17);
		jp.add(lblCurrentAdventure);
		
		JLabel lblNewLabel = new JLabel(advName);
		lblNewLabel.setBounds(132, 12, 325, 17);
		jp.add(lblNewLabel);

		btnAddRemoveGameSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdventureGameSession(fr, db, user, advID, advName);
				jp.setVisible(false);
			}
		});

		btnAdventureCharacters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditAdventure(fr, db, user, advID, advName);
				jp.setVisible(false);
			}
		});
	btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewAdventures(fr, db, user);
				jp.setVisible(false);
			}
		});
	}

}
