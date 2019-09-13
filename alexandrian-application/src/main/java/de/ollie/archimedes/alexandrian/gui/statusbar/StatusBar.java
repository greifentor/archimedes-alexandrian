package de.ollie.archimedes.alexandrian.gui.statusbar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A component which represents the status bar at the application windows bottom.
 *
 * @author ollie (12.09.2019)
 */
public class StatusBar extends JPanel {

	private JLabel labelMousePosition = new JLabel("");
	private JLabel labelStatusMessage = new JLabel("");

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	/**
	 * Creates a new status bar with the passed message.
	 * 
	 * @param statusMessage The initial status message.
	 */
	public StatusBar(String statusMessage) {
		super(new BorderLayout(HGAP, VGAP));
		add(createMousePositionPanel(), BorderLayout.WEST);
		add(createStatusMessagePanel(statusMessage), BorderLayout.EAST);
	}

	private JPanel createMousePositionPanel() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, HGAP, VGAP));
		p.add(this.labelMousePosition);
		this.labelMousePosition.setName("StatusBar:MousePosition");
		this.labelMousePosition.setText("(0, 0)");
		return p;
	}

	private JPanel createStatusMessagePanel(String statusMessage) {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, HGAP, VGAP));
		p.add(this.labelStatusMessage);
		this.labelStatusMessage.setName("StatusBar:StatusMessage");
		this.labelStatusMessage.setText(statusMessage);
		return p;
	}

	/**
	 * Updates the mouse position on the status bar with the passed values.
	 * 
	 * @param mouseX The x coordinate of the mouse.
	 * @param mouseY The y coordinate of the mouse.
	 */
	public void updateMousePosition(int mouseX, int mouseY) {
		this.labelMousePosition.setText("(" + mouseX + ", " + mouseY + ")");
	}

}