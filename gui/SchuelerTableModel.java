package easyexam.gui;

import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.ArbeitEvent;
import easyexam.core.ArbeitListener;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class SchuelerTableModel extends AbstractTableModel implements ArbeitListener {
	
	private Arbeit arbeit;
	private Schueler[] sortedSchueler = new Schueler[0];
	
	public SchuelerTableModel(Arbeit arbeit) {
		this.arbeit = arbeit;
		arbeit.addArbeitListener(this);
		updateSortedSchueler();
	}
	
	public Schueler getSchuelerAtIndex(int i) {
		return sortedSchueler[i];
	}

	private void updateSortedSchueler() {
		sortedSchueler = arbeit.getAllSchueler().toArray(new Schueler[0]);
		Arrays.sort(sortedSchueler);
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return sortedSchueler.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Schueler s = sortedSchueler[row];
		
		switch (col) {
		case 0:
			return s.getName();
		case 1:
			return s.getVorname();
		case 2:
			return s.getGebDatum().toString();
		default:
			return "Can't be! Error!";
		}
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Name";
		case 1:
			return "Vorname";
		case 2:
			return "Geburtsdatum";
		default:
			return "ERROR";
		}
	}

	@Override
	public void arbeitChanged(ArbeitEvent e) {
		//genauer moeglich?
		if (e.schuelerDidChange()) {
			updateSortedSchueler();
			fireTableDataChanged();
		}

	}
}
