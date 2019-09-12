package de.ollie.archimedes.alexandrian.gui.statusbar;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A component which represents the status bar at the application windows bottom.
 *
 * @author ollie (12.09.2019)
 */
public class StatusBar extends JPanel {

	private JLabel labelStatusMessage = new JLabel("");

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	/**
	 * Creates a new status bar with the passed message.
	 * 
	 * @param statusMessage The initial status message.
	 */
	public StatusBar(String statusMessage) {
		super(new GridLayout(1, 2, HGAP, VGAP));
		add(new JLabel(""));
		add(createStatusMessagePanel(statusMessage));
	}

	private JPanel createStatusMessagePanel(String statusMessage) {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, HGAP, VGAP));
		p.add(this.labelStatusMessage);
		this.labelStatusMessage.setName("StatusBar:StatusMessage");
		this.labelStatusMessage.setText(statusMessage);
		return p;
	}

}