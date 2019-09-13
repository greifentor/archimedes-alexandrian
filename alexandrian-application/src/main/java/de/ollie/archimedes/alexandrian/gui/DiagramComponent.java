package de.ollie.archimedes.alexandrian.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * A component which shows the diagram and allows to edit it.
 *
 * @author ollie (12.09.2019)
 */
public class DiagramComponent extends JComponent {

	public DiagramComponent() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Hier kommt dann das Diagramm :o)", 100, 100);
	}

}