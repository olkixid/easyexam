package easyexam.gui;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//TODO: andere Namen, initialisierungsfunktion
public class SchulDingens {
	private JFrame frame = new JFrame();

	public static void main(String[] args) {
		
		SchulDingens ding = new SchulDingens();
		SwingUtilities.invokeLater(() -> {
			ding.frame.setContentPane(new MainTabbedPane());
			ding.frame.setMinimumSize(new Dimension(500,300));
			ding.frame.pack();
			ding.frame.setVisible(true);
		});
	}

}
