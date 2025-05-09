
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
//import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JInternalFrame;


public class DnDDatabaseGUI {

	private JFrame frame;
	private JInternalFrame internalFrame; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DndDatabase data = new DndDatabase(); 
					data.connect();
					DnDDatabaseGUI window = new DnDDatabaseGUI(data);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DnDDatabaseGUI(DndDatabase data) {
		initialize();
		WelcomeScreenGUI welcomesc = new WelcomeScreenGUI(internalFrame, data);  
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		internalFrame = new JInternalFrame("DnD Datebase");
		internalFrame.getContentPane().setEnabled(false);
		internalFrame.setBackground(new Color(0, 250, 154));
		frame.getContentPane().add(internalFrame, BorderLayout.CENTER);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
	}



}
