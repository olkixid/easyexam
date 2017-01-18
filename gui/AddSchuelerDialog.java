package easyexam.gui;



import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import easyexam.core.Arbeit;
import easyexam.core.Schueler;

//TODO: blockieren wenn leere eingabe!
@SuppressWarnings("serial")
public class AddSchuelerDialog extends JDialog implements ActionListener {

	private Arbeit arbeit;
	
	private JTextField nameTextField;
	private JTextField vornameTextField;
	private JButton addButton;
	private JButton cancelButton;
	
	public AddSchuelerDialog(Window window, Arbeit arbeit) {
		super(window);
		
		this.arbeit = arbeit;
		buildUI();
		addButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setVisible(true);
	}
	
	private void buildUI() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		JLabel nameLabel = new JLabel("Name");
		JLabel vornameLabel = new JLabel("Vorname");
		nameTextField = new JTextField(15);
		vornameTextField = new JTextField(15);
		addButton = new JButton("Add");
		cancelButton = new JButton("Cancel");
		add(nameLabel);
		add(vornameLabel);
		add(nameTextField);
		add(vornameTextField);
		add(addButton);
		add(cancelButton);
		
		Spring maxLabelWidth = Spring.max(Spring.width(nameLabel), Spring.width(vornameLabel));
		Spring eastOfLabels = Spring.sum(maxLabelWidth, Spring.constant(10));
		
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, this);
		layout.getConstraints(nameLabel).setConstraint(SpringLayout.EAST, eastOfLabels);

		layout.putConstraint(SpringLayout.NORTH, vornameLabel, 10, SpringLayout.SOUTH, nameLabel);
		layout.getConstraints(vornameLabel).setConstraint(SpringLayout.EAST, eastOfLabels);
		
		layout.putConstraint(SpringLayout.BASELINE, nameTextField, 0, SpringLayout.BASELINE, nameLabel);
		layout.putConstraint(SpringLayout.WEST, nameTextField, 10, SpringLayout.EAST, nameLabel);
		
		layout.putConstraint(SpringLayout.BASELINE, vornameTextField, 0, SpringLayout.BASELINE, vornameLabel);
		layout.putConstraint(SpringLayout.WEST, vornameTextField, 10, SpringLayout.EAST, vornameLabel);

		layout.putConstraint(SpringLayout.NORTH, addButton, 10, SpringLayout.SOUTH, vornameTextField);
		layout.putConstraint(SpringLayout.EAST, addButton, 0, SpringLayout.EAST, vornameTextField);
		
		layout.putConstraint(SpringLayout.NORTH, cancelButton, 10, SpringLayout.SOUTH, vornameTextField);
		layout.putConstraint(SpringLayout.EAST, cancelButton, -10, SpringLayout.WEST, addButton);
		
		layout.putConstraint(SpringLayout.SOUTH, getContentPane(), 10, SpringLayout.SOUTH, addButton);
		layout.putConstraint(SpringLayout.EAST, getContentPane(), 10, SpringLayout.EAST, addButton);
		
		setResizable(false);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == addButton) {
			Schueler s = new Schueler(nameTextField.getText(), vornameTextField.getText(), LocalDate.now());
			arbeit.addSchueler(s);
			dispose();
		} else if (ae.getSource() == cancelButton) {
			dispose();
		}
	}
	
	
}
