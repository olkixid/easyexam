package easyexam.gui;


import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import easyexam.core.Arbeit;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class AuswertungsPanel extends JPanel {
	private JTable auswertungsTable;
	
	private TableCellRenderer pBarRenderer = new TableCellRenderer() {
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
	
	private MouseListener doubleClickRowListener = new MouseAdapter() {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent me) {
			if (me.getClickCount() != 2) {
				return;
			}
			
			JTable table = (JTable) me.getSource();
			int row = table.rowAtPoint(me.getPoint());
			
			if (row == -1) {
				return;
			}
			
			Schueler s = arbeit.getSchuelerAt(row);
			Window window = SwingUtilities.windowForComponent(AuswertungsPanel.this);
			
			BewertungsDialog bd = new BewertungsDialog(window, arbeit, s);
			bd.setVisible(true);
		};
	};
	
	private Arbeit arbeit;
	
	public AuswertungsPanel(Arbeit arbeit) {
		this.arbeit = arbeit;
		buildUI();
		
		AuswertungsTableModel model = new AuswertungsTableModel(arbeit);
		auswertungsTable.setModel(model);
		
		auswertungsTable.setDefaultRenderer(Double.class, pBarRenderer);
		auswertungsTable.addMouseListener(doubleClickRowListener);
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
