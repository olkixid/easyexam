package easyexam.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import easyexam.core.Arbeit;
import easyexam.core.Schueler;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class MainTabbedPane extends JTabbedPane {
	public MainTabbedPane() {
		super();
		buildUI();
	}
	
	private void buildUI() {
		//Nicht in build ui machen
		Arbeit arbeit = new Arbeit();
		arbeit.addSchueler(new Schueler("Jung", "Lukas", LocalDate.of(1994,6,12)));
		addTab("Aufgaben", null, new AufgabenPanel(), null);
		addTab("Schüler", null, new SchuelerPanel(arbeit), null);
		
		JPanel auswertungsPanel = new JPanel();
		addTab("Auswertung", null, auswertungsPanel, null);
	}
}
