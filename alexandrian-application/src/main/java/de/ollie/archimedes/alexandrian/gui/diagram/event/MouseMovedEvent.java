package de.ollie.archimedes.alexandrian.gui.diagram.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A container for the mouse moved event data.
 *
 * @author ollie (13.09.2019)
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class MouseMovedEvent {

	private int mouseX;
	private int mouseY;

}