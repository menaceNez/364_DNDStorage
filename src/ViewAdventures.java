import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ViewAdventures extends JPanel {
	private JInternalFrame fr;
	private DndDatabase db;
	private Player user;
	private JPanel advView;
	private JList<Adventure> list;
	ArrayList<Adventure> adventures;

	private static final long serialVersionUID = 1L;
	private JButton btnBack;

	/*
	 * this View will have the ability to delete or add players to a given adventure
	 * List to show all adventures Select an adventure, brings up an edit adventure
	 * view\ Edit adventure view: Almost a copy of createAdventure, removing
	 * creating campaign
	 */

	/**
	 * Create the panel.
	 */
	public ViewAdventures(JInternalFrame fr, DndDatabase db, Player user) {
		this.fr = fr;
		this.db = db;
		this.user = user;

		advView = new JPanel();
		advView.setBackground(new Color(153, 193, 241));
		advView.setBounds(10, 10, 502, 348);
		advView.setLayout(null);

		fr.getContentPane().add(advView);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 50, 370, 240);
		advView.add(scrollPane);

		list = new JList<>(new DefaultListModel<Adventure>());
		scrollPane.setViewportView(list);

		JLabel lblSelectAnAdventure = new JLabel("Select an Adventure to Edit");
		lblSelectAnAdventure.setBounds(12, 12, 179, 17);
		advView.add(lblSelectAnAdventure);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(389, 7, 101, 27);
		advView.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PlayerInfo(fr, db, user);
				advView.setVisible(false);
			}
		});

		fillAdventure();

		MouseListener clickEvent = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());

				if (index != -1) {
					Adventure selectedValue = list.getModel().getElementAt(index);
					System.out.println("WOW: "+ selectedValue);
					new AdventureOptions(fr, db, user, selectedValue.getAdventureID(), selectedValue.getAdvName());
					advView.setVisible(false);
				}
			}
		};

		list.addMouseListener(clickEvent);
	}

	public void fillAdventure() {
		AdventureQueries aq = new AdventureQueries(db);
		adventures = aq.getAdventures();
		DefaultListModel<Adventure> dlm = new DefaultListModel<>();

		for (Adventure adventure : adventures) {
			dlm.addElement(adventure);
		}

		list.setModel(dlm);
	}
}
