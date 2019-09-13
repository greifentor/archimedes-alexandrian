package de.ollie.archimedes.alexandrian.gui.diagram;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.apache.log4j.Logger;

import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseMovedEvent;

/**
 * A component which shows the diagram and allows to edit it.
 *
 * @author ollie (12.09.2019)
 */
public class DiagramComponent extends JComponent implements MouseMotionListener {

	static Logger log = Logger.getLogger(DiagramComponent.class);

	private List<DiagramComponentListener> listeners = new ArrayList<>();

	public DiagramComponent() {
		super();
		setName("DiagramComponent");
		addMouseMotionListener(this);
	}

	/**
	 * Adds the passed DiagrammComponentListener to the observing listeners. A passed "null" value will add nothing.
	 * 
	 * @param listener The listener to add.
	 */
	public void addDiagramComponentListener(DiagramComponentListener listener) {
		if (listener != null) {
			this.listeners.add(listener);
		}
	}

	void fireMouseMovedEvent(MouseMovedEvent event) {
		for (DiagramComponentListener l : this.listeners) {
			try {
				l.mouseMoved(event);
			} catch (Exception e) {
				log.warn("Error occured while firing MouseMovedEvent: " + event + ", listener: " + l + ", exception: "
						+ e);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		log.info("mouseDragged called.");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		fireMouseMovedEvent(new MouseMovedEvent(e.getX(), e.getY()));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Hier kommt dann das Diagramm :o)", 100, 100);
	}

}