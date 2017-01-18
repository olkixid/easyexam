package easyexam.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class AufgabenPanel extends JPanel {
	private JTable aufgabenTable = new JTable();
	
	public AufgabenPanel() {
		super();
		buildUI();
	}
	
	private void buildUI() {
		SpringLayout sl_aufgabenPanel = new SpringLayout();
		setLayout(sl_aufgabenPanel);
		
		JButton btnNewButton = new JButton("Add Aufgabe");
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, this);
		add(btnNewButton);
		
		sl_aufgabenPanel.putConstraint(SpringLayout.NORTH, aufgabenTable, 10, SpringLayout.NORTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.WEST, aufgabenTable, 10, SpringLayout.WEST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, aufgabenTable, -10, SpringLayout.EAST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, aufgabenTable, -10, SpringLayout.NORTH, btnNewButton);
		add(aufgabenTable);
	}
}
