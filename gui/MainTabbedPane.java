package easyexam.gui;

import javax.swing.JTabbedPane;

import easyexam.core.Arbeit;

@SuppressWarnings("serial")
public class MainTabbedPane extends JTabbedPane {
	public MainTabbedPane() {
		super();
		buildUI();
	}
	
	private void buildUI() {
		//Nicht in build ui machen
		Arbeit arbeit = new Arbeit();
		addTab("Aufgaben", null, new AufgabenPanel(arbeit), null);
		addTab("Schüler", null, new SchuelerPanel(arbeit), null);
		addTab("Auswertung", null, new AuswertungsPanel(arbeit), null);
	}
}
