package de.ollie.archimedes.alexandrian.gui.diagram;

import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseMovedEvent;

/**
 * An interface to observe events on a DiagramComponent object.
 *
 * @author ollie (13.09.2019)
 */
public interface DiagramComponentListener {

	/**
	 * Is called if the mouse position has been changed.
	 * 
	 * @param event An event with the new mouse position.
	 */
	void mouseMoved(MouseMovedEvent event);

}