package easyexam.gui;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.Aufgabe;
import easyexam.core.Bewertung;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class BewertungsTableModel extends AbstractTableModel {

	private Arbeit arbeit;
	private Schueler schueler;
	
	public BewertungsTableModel(Arbeit arbeit, Schueler schueler) {
		this.arbeit = arbeit;
		this.schueler = schueler;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return arbeit.getNumberOfAufgabenKeys();
	}

	@Override
	public Object getValueAt(int row, int col) {
		String aufgabenKey = arbeit.getAufgabenKeyAt(row);
		Aufgabe a = arbeit.getAufgabeForKey(aufgabenKey);
		Bewertung b = arbeit.getBewertungFor(schueler, aufgabenKey);
		
		switch (col) {
		case 0:
			return aufgabenKey;
		case 1:
			if (a == null) return null;
			return a.getMaxPunkte();
		case 2:
			if (a == null) return null;
			return a.getBeschreibung();
		case 3:
			if (b == null) return null;
			return b.punkte;
		case 4:
			if (b == null) return null;
			return b.hinweis;
		default:
			return "ERROR";	
		}
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Aufgabe";
		case 1:
			return "max. Punkte";
		case 2:
			return "Beschreibunf";
		case 3:
			return "Punkte";
		case 4:
			return "Hinweis";
		default:
			return "ERROR";
		}
	}

}
