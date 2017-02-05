package easyexam.gui;


import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.TableCellRenderer;

import easyexam.core.Arbeit;

@SuppressWarnings("serial")
public class AuswertungsPanel extends JPanel {
	private JTable auswertungsTable;
	
	TableCellRenderer pBarRenderer = new TableCellRenderer() {
		private JProgressBar pBar = new JProgressBar(0, 100);
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			//System.out.println("hallo");
			if (value.getClass() != Double.class ) {
				return null;
			}
			Double dValue = (Double)value;
			pBar.setValue( (int)(dValue.doubleValue() * 100.0) );
			return pBar;
		}
	};
	
	Arbeit arbeit;
	
	public AuswertungsPanel(Arbeit arbeit) {
		this.arbeit = arbeit;
		buildUI();
		
		AuswertungsTableModel model = new AuswertungsTableModel(arbeit);
		auswertungsTable.setModel(model);
		
		auswertungsTable.setDefaultRenderer(Double.class, pBarRenderer);
	}
	
	private void buildUI() {
		SpringLayout sl_aufgabenPanel = new SpringLayout();
		setLayout(sl_aufgabenPanel);
		
		auswertungsTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(auswertungsTable);
		sl_aufgabenPanel.putConstraint(SpringLayout.NORTH, tableScrollPane, 10, SpringLayout.NORTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.WEST, tableScrollPane, 10, SpringLayout.WEST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, tableScrollPane, -10, SpringLayout.EAST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, tableScrollPane, -10, SpringLayout.SOUTH, this);
		add(tableScrollPane);
	}
}
