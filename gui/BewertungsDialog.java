package easyexam.gui;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import easyexam.core.Arbeit;
import easyexam.core.Schueler;

@SuppressWarnings("serial")
public class BewertungsDialog extends JDialog {
	private JTable bewertungsTable;

	//private Arbeit arbeit;
	
	public BewertungsDialog(Window window, Arbeit arbeit, Schueler schueler) {
		super(window);
		
		//this.arbeit = arbeit;
		
		buildUI();
		
		BewertungsTableModel model = new BewertungsTableModel(arbeit, schueler);
		bewertungsTable.setModel(model);
		
		setTitle(schueler.getName());
		
		arbeit.addArbeitListener(model);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				arbeit.removeArbeitListener(model);
			}
		});
	}
	
	private void buildUI() {
		SpringLayout sl_schuelerPanel = new SpringLayout();
		setLayout(sl_schuelerPanel);
		
		bewertungsTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(bewertungsTable);
		sl_schuelerPanel.putConstraint(SpringLayout.NORTH, tableScrollPane, 10, SpringLayout.NORTH, this.getContentPane());
		sl_schuelerPanel.putConstraint(SpringLayout.WEST, tableScrollPane, 10, SpringLayout.WEST, this.getContentPane());
		sl_schuelerPanel.putConstraint(SpringLayout.SOUTH, this.getContentPane(), 10, SpringLayout.SOUTH, tableScrollPane);
		sl_schuelerPanel.putConstraint(SpringLayout.EAST, this.getContentPane(), 10, SpringLayout.EAST, tableScrollPane);
		add(tableScrollPane);
		
		pack();
	}
}
