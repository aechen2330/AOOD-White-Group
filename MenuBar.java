import javax.swing.*;

import backend.ActionItem;
import backend.FontLoader;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MenuBar extends JMenuBar implements ActionListener{
	private JMenu file; 
	private JMenuItem createBackup, restoreBackup, printList;
	private JButton completedActionItems, quit, back;
	private JFileChooser fileChooser; 
	private Stack<JPanel> prevPanels = new Stack<JPanel>();
	private JFrame frame;
	private MainScreen main;
	private List<ActionItem> passCompletedActionItems;
	final static Color BAR_COLOR = Color.decode("#56997F");
	
	MenuBar(JFrame frame, MainScreen main){
		this.frame = frame;
		this.main = main;
		setBackground(BAR_COLOR);
		file = new JMenu("File");
		file.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 12));
		file.setBackground(BAR_COLOR);
		file.setForeground(Color.white);
		
		createBackup = new JMenuItem("Create Backup");
		createBackup.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 10));
		createBackup.setActionCommand("Create");
		restoreBackup = new JMenuItem("Restore Backup...");
		restoreBackup.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 10));
		restoreBackup.setActionCommand("Restore");
		printList = new JMenuItem("Print List");
		printList.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 10));
		printList.setActionCommand("Print");
		file.add(createBackup);
		file.add(restoreBackup);
		file.add(printList);
		
		completedActionItems = new JButton("Completed Action Items");
		completedActionItems.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 12));
		completedActionItems.setBackground(BAR_COLOR);
		completedActionItems.setForeground(Color.white);
		completedActionItems.setOpaque(true);
		completedActionItems.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		completedActionItems.setActionCommand("Completed Action Items");
		completedActionItems.addActionListener(this);
		quit = new JButton("Quit");
		quit.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Bold.ttf", 12));
		quit.setBackground(BAR_COLOR);
		quit.setOpaque(true);
		quit.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		quit.setForeground(Color.white);
		quit.setActionCommand("Quit");
		quit.addActionListener(this); 
		
		add(file);
		add(completedActionItems);
		add(quit);
		
		passCompletedActionItems = new ArrayList<ActionItem>();
		
		//frame = new JFrame();
		//MainScreen main = new MainScreen(frame);
		//frame.setJMenuBar(this);
		//frame.setContentPane(main);
		//frame.pack();
		//frame.setVisible(true);
	
	}
	public void changeBar() {
		remove(file);
		remove(completedActionItems);
		remove(quit); 
		back = new JButton("Back");
		add(back);
		back.setActionCommand("Back");
		back.addActionListener(this);
		back.setFont(FontLoader.loadFont("src/res/Chivo/Chivo-Regular.ttf", 12));
		back.setBackground(BAR_COLOR);
		back.setForeground(Color.white);
		back.setOpaque(true);
		back.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}
	public void resetBar() {
		remove(back);
		add(file);
		add(completedActionItems);
		add(quit);
	}
	public void addPrevPanel(JPanel panel) {
		prevPanels.push(panel);
	}
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals("Create")) {
			fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(new JPanel());
		} else if (eventName.equals("Restore")) {
			//Restore?
		} else if (eventName.equals("Print")) {
			//TBD
		} else if (eventName.equals("Back")) {
			if (prevPanels.empty()) {
				frame.setContentPane(main);
				resetBar();
				// refresh list
			} else {
				frame.setContentPane(prevPanels.pop());
			}
			frame.revalidate();
			frame.repaint();
		} else if (eventName.equals("Completed Action Items")) {
			changeBar();
			for(int i=0; i<main.getToDoList().getNumCompleteItems(); i++) {
				passCompletedActionItems.add(main.getToDoList().getCompleteItemAtIndex(i));
			}
			ClosedActionItemsScreen completed = new ClosedActionItemsScreen(passCompletedActionItems);
			frame.setContentPane(completed);
			frame.revalidate();
			frame.repaint();
		} else if (eventName.equals("Quit")) {
			System.exit(0);
		}
	}
	public static void main (String[] args) {
		//MenuBar bar = new MenuBar();
	}
}
