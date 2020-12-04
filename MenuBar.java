import javax.swing.*;
import backend.FontLoader;

import java.awt.Color;
import java.awt.event.*;

public class MenuBar extends JMenuBar implements ActionListener{
	private JMenu file; 
	private JMenuItem createBackup, restoreBackup, printList;
	private JButton completedActionItems, quit;
	private JFileChooser fileChooser; 
	final static Color BAR_COLOR = Color.decode("#56997F");
	
	MenuBar(JPanel mainScreen){
		this.setBackground(BAR_COLOR);
		file = new JMenu("File");
		file.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 15));
		file.setBackground(BAR_COLOR);
		file.setForeground(Color.white);
		
		createBackup = new JMenuItem("Create Backup");
		createBackup.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 12));
		restoreBackup = new JMenuItem("Restore Backup...");
		restoreBackup.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 12));
		printList = new JMenuItem("Print List");
		printList.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 12));
		file.add(createBackup);
		file.add(restoreBackup);
		file.add(printList);
		
		completedActionItems = new JButton("Completed Action Items");
		completedActionItems.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 15));
		completedActionItems.setBackground(BAR_COLOR);
		completedActionItems.setActionCommand("Completed Action Items");
		completedActionItems.addActionListener(this);
		quit = new JButton("Quit");
		quit.setFont(FontLoader.loadFont("src/fonts/Chivo-Regular.ttf", 10));
		quit.setBackground(BAR_COLOR);
		quit.setActionCommand("Quit");
		quit.addActionListener(this); 
		
		mainScreen.add(file);
		mainScreen.add(completedActionItems);
		mainScreen.add(quit);
	
	}
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals("Completed Action Items")) {
			/* quit button becomes back, visbility for other buttons false
			 * screens change from main to cai screen
			 */
		} else if (eventName.equals("Quit")) {
			
		}
		else
	}
	public static void main (String[] args) {
		JFrame frame = new JFrame();
		MenuBar bar = new MenuBar(new MainScreen(frame));
		frame.setContentPane(bar);
		frame.pack();
		frame.setVisible(true);
	}
}
