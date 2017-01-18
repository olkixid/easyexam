package easyexam.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import easyexam.core.Arbeit;

@SuppressWarnings("serial")
public class SchuelerPanel extends JPanel implements ActionListener {
	private JTable schuelerTable = new JTable();
	private JButton addSchuelerButton;
	private JButton removeSchuelerButton;

	private Arbeit arbeit;
	
	public SchuelerPanel(Arbeit arbeit) {
		super();
		this.arbeit = arbeit;
		
		buildUI();
		
		SchuelerTableModel model = new SchuelerTableModel(arbeit);
		schuelerTable.setModel(model);

		addSchuelerButton.addActionListener(this);
		removeSchuelerButton.addActionListener(this);
	}
	
	private void buildUI() {
		SpringLayout sl_schuelerPanel = new SpringLayout();
		setLayout(sl_schuelerPanel);
		
		JButton importSchuelerButton = new JButton("Import");
		sl_schuelerPanel.putConstraint(SpringLayout.WEST, importSchuelerButton, 10, SpringLayout.WEST, this);
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, importSchuelerButton, -10, SpringLayout.SOUTH, this);
		add(importSchuelerButton);
		
		JButton exportSchuelerButton = new JButton("Export");
		sl_schuelerPanel.putConstraint(SpringLayout.WEST, exportSchuelerButton, 10, SpringLayout.EAST, importSchuelerButton);
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, exportSchuelerButton, -10, SpringLayout.SOUTH, this);
		add(exportSchuelerButton);
		
		addSchuelerButton = new JButton("Add");
		sl_schuelerPanel.putConstraint(SpringLayout.EAST, addSchuelerButton, -10, SpringLayout.EAST, this);
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, addSchuelerButton, -10, SpringLayout.SOUTH, this);
		add(addSchuelerButton);
		
		removeSchuelerButton = new JButton("Remove");
		sl_schuelerPanel.putConstraint(SpringLayout.EAST, removeSchuelerButton, -10, SpringLayout.WEST, addSchuelerButton);
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, removeSchuelerButton, -10, SpringLayout.SOUTH, this);
		add(removeSchuelerButton);
		
		//table
		JScrollPane tableScrollPane = new JScrollPane(schuelerTable);
		sl_schuelerPanel.putConstraint(SpringLayout.NORTH, tableScrollPane, 10, SpringLayout.NORTH, this);
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, tableScrollPane, -10, SpringLayout.NORTH, addSchuelerButton);
		sl_schuelerPanel.putConstraint(SpringLayout.EAST, tableScrollPane, -10, SpringLayout.EAST, this);
		sl_schuelerPanel.putConstraint(SpringLayout.WEST, tableScrollPane, 10, SpringLayout.WEST, this);
		add(tableScrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addSchuelerButton) {
			AddSchuelerDialog dialog = new AddSchuelerDialog(SwingUtilities.windowForComponent(this) , arbeit);
			System.out.println("add");	
		} else if (ae.getSource() == removeSchuelerButton) {
			System.out.println("remove");
		}
	}
}
