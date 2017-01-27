package easyexam.gui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import easyexam.core.Arbeit;
import easyexam.core.Aufgabe;

@SuppressWarnings("serial")
public class AddAufgabeDialog extends JDialog implements ActionListener{

	private Arbeit arbeit;
	
	private JTextField keyTextField;
	private JTextField maxPunkteTextField;
	private JButton addButton;
	private JButton cancelButton;
	
	public AddAufgabeDialog(Window window, Arbeit arbeit) {
		super(window);
		
		this.arbeit = arbeit;
		buildUI();
		addButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	
	private void buildUI() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		JLabel keyLabel = new JLabel("Nummer");
		JLabel maxPunkteLabel = new JLabel("max. Punkte");
		keyTextField = new JTextField(15);
		maxPunkteTextField = new JTextField(15);
		addButton = new JButton("Add");
		cancelButton = new JButton("Cancel");
		add(keyLabel);
		add(maxPunkteLabel);
		add(keyTextField);
		add(maxPunkteTextField);
		add(addButton);
		add(cancelButton);
		
		Spring maxLabelWidth = Spring.max(Spring.width(keyLabel), Spring.width(maxPunkteLabel));
		Spring eastOfLabels = Spring.sum(maxLabelWidth, Spring.constant(10));
		
		layout.putConstraint(SpringLayout.NORTH, keyLabel, 10, SpringLayout.NORTH, this);
		layout.getConstraints(keyLabel).setConstraint(SpringLayout.EAST, eastOfLabels);

		layout.putConstraint(SpringLayout.NORTH, maxPunkteLabel, 10, SpringLayout.SOUTH, keyLabel);
		layout.getConstraints(maxPunkteLabel).setConstraint(SpringLayout.EAST, eastOfLabels);
		
		layout.putConstraint(SpringLayout.BASELINE, keyTextField, 0, SpringLayout.BASELINE, keyLabel);
		layout.putConstraint(SpringLayout.WEST, keyTextField, 10, SpringLayout.EAST, keyLabel);
		
		layout.putConstraint(SpringLayout.BASELINE, maxPunkteTextField, 0, SpringLayout.BASELINE, maxPunkteLabel);
		layout.putConstraint(SpringLayout.WEST, maxPunkteTextField, 10, SpringLayout.EAST, maxPunkteLabel);

		layout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, maxPunkteTextField);
		layout.putConstraint(SpringLayout.EAST, addButton, 0, SpringLayout.EAST, maxPunkteTextField);
		
		layout.putConstraint(SpringLayout.NORTH, cancelButton, 10, SpringLayout.SOUTH, maxPunkteTextField);
		layout.putConstraint(SpringLayout.EAST, cancelButton, -10, SpringLayout.WEST, addButton);
		
		layout.putConstraint(SpringLayout.SOUTH, getContentPane(), 10, SpringLayout.SOUTH, addButton);
		layout.putConstraint(SpringLayout.EAST, getContentPane(), 10, SpringLayout.EAST, addButton);
		
		setResizable(false);
		
		pack();
	}

	private void addAufgabeToArbeit() {
		String aKey = keyTextField.getText();
		arbeit.addAufgabenKey(aKey);
		double maxPunkte = Double.parseDouble(maxPunkteTextField.getText());
		
		arbeit.setAufgabeForKey(aKey, new Aufgabe(maxPunkte, null) );
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addButton) {
			addAufgabeToArbeit();
			dispose();
		} else if (ae.getSource() == cancelButton) {
			dispose();
		}
	}
	
	
}
