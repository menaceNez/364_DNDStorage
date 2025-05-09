import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AdventureGameSession extends JPanel {
	private JInternalFrame fr;
	private DndDatabase db;
	private Player user;
	private JPanel jp;
	private JList<Event> notesList;
	private JTextArea textArea;

	private static final long serialVersionUID = 1L;

	private int advID;
	private String advName;
	
	/**
	 * Create the panel.
	 */
	public AdventureGameSession(JInternalFrame fr, DndDatabase db, Player user, int advID, String advName) {
		this.fr = fr;
		this.db = db;
		this.user = user;
		this.advID = advID;
		this.advName = advName;

		jp = new JPanel();
		jp.setBackground(new Color(153, 193, 241));
		jp.setBounds(10, 10, 502, 348);
		jp.setLayout(null);
		fr.getContentPane().add(jp);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(390, 310, 100, 25);
		jp.add(btnBack);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 223, 366, 113);
		jp.add(textArea);
		
		JButton btnSubmit = new JButton("Submit Event");
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 9));
		btnSubmit.setBounds(390, 250, 100, 25);
		jp.add(btnSubmit);
		
		JLabel lblAdventure = new JLabel("Adventure:");
		lblAdventure.setBounds(271, 1, 73, 17);
		jp.add(lblAdventure);
		
		JLabel lblNewLabel = new JLabel(advName);
		lblNewLabel.setBounds(339, 1, 151, 17);
		jp.add(lblNewLabel);
		
		JLabel lblSessionNotes = new JLabel("Adventure Events:");
		lblSessionNotes.setBounds(12, 1, 123, 17);
		jp.add(lblSessionNotes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 19, 480, 200);
		jp.add(scrollPane);
		
		notesList = new JList<>(new DefaultListModel<Event>());
	
		scrollPane.setViewportView(notesList);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEvent();
			}
		});
		btnDeleteEvent.setFont(new Font("Dialog", Font.BOLD, 9));
		btnDeleteEvent.setBounds(390, 280, 100, 25);
		jp.add(btnDeleteEvent);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdventureOptions(fr, db, user, advID, advName);
				jp.setVisible(false);
			}
		});	
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitSessionNote();
			}
		});
		fillNotesList();
	}
	
	public void fillNotesList() {
		AdventureQueries aq = new AdventureQueries(db);
		ArrayList<Event> events = aq.getEvents(advID);
		clearListModel(notesList);
		
		DefaultListModel<Event> dlm = new DefaultListModel<>();
		
		for(Event event : events) {

			dlm.addElement(event);
		}
		
		notesList.setModel(dlm);
	}
	
	private void clearListModel(JList<?> list) {
		DefaultListModel<?> mdl = (DefaultListModel<?>) list.getModel();
		mdl.clear();
	}
	
	public void submitSessionNote() {
		AdventureQueries aq = new AdventureQueries(db);
		String text = textArea.getText().trim();
		if(text.length() == 0) {
			// should error text here
			return;
		}

		int key = aq.insertEvent(advID, text);

		if(key != -1) {
			
			fillNotesList();			
			textArea.setText("");
		}
	}
	
	public void deleteEvent() {
		Event event = notesList.getSelectedValue();
		AdventureQueries aq = new AdventureQueries(db);
		
		aq.deleteEvent(event.id);	
		fillNotesList();
	}
}
