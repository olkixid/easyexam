package easyexam.gui;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.ArbeitEvent;
import easyexam.core.ArbeitListener;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class AuswertungsTableModel extends AbstractTableModel implements ArbeitListener {

	private Arbeit arbeit;
	
	public AuswertungsTableModel(Arbeit arbeit) {
		this.arbeit = arbeit;
		arbeit.addArbeitListener(this);
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return arbeit.getNumberOfSchueler();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			Schueler s = arbeit.getSchuelerAt(row);
			return s.getName() + ", " + s.getVorname() + " - " + s.getGebDatum();
		case 1:
			return 0.5;
		default:
			return "Can't be! Error!";
		}
	}
	
	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Schueler";
		case 1:
			return "Progress";
		default:
			return "ERROR";
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 1) {
			return Double.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public void arbeitChanged(ArbeitEvent e) {
		if (e.schuelerDidChange()) {
			fireTableDataChanged();
		}
	}

}
