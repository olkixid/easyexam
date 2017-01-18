package easyexam.gui;

import java.util.TreeSet;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.ArbeitEvent;
import easyexam.core.ArbeitListener;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class SchuelerTableModel extends AbstractTableModel implements ArbeitListener {
	
	private Arbeit arbeit;
	private TreeSet<Schueler> sortedSchueler;
	
	public SchuelerTableModel(Arbeit arbeit) {
		super();
		this.arbeit = arbeit;
		arbeit.addArbeitListener(this);
		sortedSchueler = new TreeSet<>(arbeit.getAllSchueler());
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return arbeit.getAllSchueler().size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		//nicht optimal: stattdessen gleich array als member
		Schueler s = (Schueler) sortedSchueler.toArray()[row];
		
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
		if (e.schuelerDidChange()) {
			sortedSchueler = new TreeSet<>(arbeit.getAllSchueler());
			//genauer moeglich?
			fireTableDataChanged();
		}
	}
}
