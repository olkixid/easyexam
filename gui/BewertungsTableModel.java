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
		return 4;
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
			if (b == null) return null;
			return b.getPunkte();
		case 3:
			if (b == null) return null;
			return b.getHinweis();
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
			return "Punkte";
		case 3:
			return "Hinweis";
		default:
			return "ERROR";
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex >= 2) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		String aKey = arbeit.getAufgabenKeyAt(row);
		Bewertung bOld = arbeit.getBewertungFor(schueler, aKey);
		
		String hinweis = "";
		double punkte = 0;
		
		if (bOld != null) {
			hinweis = bOld.getHinweis();
			punkte = bOld.getPunkte();
		}
		
		switch (col) {
		case 2:
			punkte = Double.parseDouble((String)value);
			break;
		case 3:
			hinweis = (String)value;
			break;
		}
		
		Bewertung b = null;
		if ( punkte != 0 || !(hinweis.length() == 0) ) {
			b = new Bewertung(punkte, hinweis);
		}
		arbeit.setBewertungFor(schueler, aKey, b);
	}

}
