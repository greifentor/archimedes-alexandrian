package de.ollie.archimedes.alexandrian.gui.diagram.event;

import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponentMode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

/**
 * A container for the mouse click event.
 *
 * @author ollie (17.09.2019)
 */
@Generated
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class MouseClickEvent {

	private DiagramComponentMode mode;
	private int mouseX;
	private int mouseY;

}