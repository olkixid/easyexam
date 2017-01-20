package easyexam.gui;

import javax.swing.table.AbstractTableModel;

import easyexam.core.Arbeit;
import easyexam.core.ArbeitEvent;
import easyexam.core.ArbeitListener;

@SuppressWarnings("serial")
public class AufgabenTableModel extends AbstractTableModel {
	
	private Arbeit arbeit;
	
	public AufgabenTableModel(Arbeit arbeit) {
		this.arbeit = arbeit;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return arbeit.getAllAufgabenKeys().size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return "test";
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

}
