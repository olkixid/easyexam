package easyexam.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import easyexam.core.Arbeit;

@SuppressWarnings("serial")
public class AufgabenPanel extends JPanel implements ActionListener {
	private JTable aufgabenTable;
	private JButton addAufgabeButton;
	private JButton removeAufgabeButton;
	
	private Arbeit arbeit;
	
	public AufgabenPanel(Arbeit arbeit) {
		this.arbeit = arbeit;
		buildUI();
		
		AufgabenTableModel model = new AufgabenTableModel(arbeit);
		aufgabenTable.setModel(model);
		
		addAufgabeButton.addActionListener(this);
		removeAufgabeButton.addActionListener(this);
	}
	
	private void buildUI() {
		SpringLayout sl_aufgabenPanel = new SpringLayout();
		setLayout(sl_aufgabenPanel);
		
		addAufgabeButton = new JButton("Add Aufgabe");
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, addAufgabeButton, -10, SpringLayout.SOUTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, addAufgabeButton, -10, SpringLayout.EAST, this);
		add(addAufgabeButton);
		
		removeAufgabeButton = new JButton("Remove Aufgabe");
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, removeAufgabeButton, -10, SpringLayout.SOUTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, removeAufgabeButton, -10, SpringLayout.WEST, addAufgabeButton);
		add(removeAufgabeButton);
		
		aufgabenTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(aufgabenTable);
		sl_aufgabenPanel.putConstraint(SpringLayout.NORTH, tableScrollPane, 10, SpringLayout.NORTH, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.WEST, tableScrollPane, 10, SpringLayout.WEST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.EAST, tableScrollPane, -10, SpringLayout.EAST, this);
		sl_aufgabenPanel.putConstraint(SpringLayout.SOUTH, tableScrollPane, -10, SpringLayout.NORTH, addAufgabeButton);
		add(tableScrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addAufgabeButton) {
			AddAufgabeDialog dialog = new AddAufgabeDialog(SwingUtilities.windowForComponent(this), arbeit);
			dialog.setVisible(true);
		} else if (e.getSource() == removeAufgabeButton) {
			int i = aufgabenTable.getSelectedRow();
			if (i != -1) {
				arbeit.removeAufgabenKeyAt(i);
			}
		}
	}
}
