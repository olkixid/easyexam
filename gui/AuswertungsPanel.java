package easyexam.gui;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import easyexam.core.Arbeit;

@SuppressWarnings("serial")
public class AuswertungsPanel extends JPanel {
	private JTable auswertungsTable;
	
	Arbeit arbeit;
	
	public AuswertungsPanel(Arbeit arbeit) {
		this.arbeit = arbeit;
		buildUI();
		
		//mymodel model = new mzmodel(arbeit);
		//aufgabenTable.setModel(model);
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
