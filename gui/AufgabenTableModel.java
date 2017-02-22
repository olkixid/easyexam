package easyexam.gui;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.ArbeitEvent;
import easyexam.core.ArbeitListener;
import easyexam.core.Aufgabe;

@SuppressWarnings("serial")
public class AufgabenTableModel extends AbstractTableModel implements ArbeitListener {
	
	private Arbeit arbeit;
	
	public AufgabenTableModel(Arbeit arbeit) {
		this.arbeit = arbeit;
		arbeit.addArbeitListener(this);
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return arbeit.getNumberOfAufgabenKeys();
	}

	@Override
	public Object getValueAt(int row, int col) {
		String aKey = arbeit.getAufgabenKeyAt(row);
		Aufgabe a = arbeit.getAufgabeForKey(aKey);
		
		switch (col) {
		case 0:
			return aKey;
		case 1:
			if (a == null) {
				return null;
			}
			return a.getBeschreibung();
		case 2:
			if (a == null) {
				return null;
			}
			return a.getMaxPunkte();
		default:
			return "ERROR";
		}
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Key";
		case 1:
			return "Beschreibung";
		case 2:
			return "max. Punkte";
		default:
			return "ERROR";
		}
	}

	@Override
	public void arbeitChanged(ArbeitEvent e) {
		if (e.aufgabeKeysDidChange() || e.aufgabenDidChange()) {
			fireTableDataChanged();
		}
	}

}
