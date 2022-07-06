package de.ollie.archimedes.alexandrian.gui.diagram;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseClickEvent;
import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseMovedEvent;

/**
 * A component which shows the diagram and allows to edit it.
 *
 * @author ollie (12.09.2019)
 */
public class DiagramComponent extends JComponent implements MouseListener, MouseMotionListener {

	static Logger log = LogManager.getLogger(DiagramComponent.class);

	private DiagramComponentMode mode = DiagramComponentMode.INSERT;
	private List<DiagramComponentListener> listeners = new ArrayList<>();

	public DiagramComponent() {
		super();
		setName("DiagramComponent");
		addMouseListener(this);
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

	void fireMouseClickEvent(MouseClickEvent event) {
		for (DiagramComponentListener l : this.listeners) {
			try {
				l.mouseClicked(event);
			} catch (Exception e) {
				log.warn("Error occured while firing MouseClickEvent: " + event + ", listener: " + l + ", exception: "
						+ e);
			}
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

	/**
	 * Returns the current diagram component mode.
	 *
	 * @return The current diagram component mode.
	 */
	public DiagramComponentMode getDiagramComponentMode() {
		return this.mode;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			fireMouseClickEvent(new MouseClickEvent(this.getDiagramComponentMode(), e.getX(), e.getY()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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

	/**
	 * Sets a new mode for the diagram component.
	 *
	 * @param mode The new component diagram mode for the diagram component.
	 * @return The diagram component.
	 */
	public DiagramComponent setDiagramComponentMode(DiagramComponentMode mode) {
		this.mode = mode;
		return this;
	}

}