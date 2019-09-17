package de.ollie.archimedes.alexandrian.gui;

import lombok.Generated;

/**
 * An interface for a class which exits the application.
 *
 * @author ollie (12.09.2019)
 */
@Generated // To get it away from coverage computing.
public interface SystemExiter {

	/**
	 * Performs a system exit.
	 *
	 * @param returnCode The code which is to return to the OS.
	 */
	default void exit(int returnCode) {
		System.exit(returnCode);
	}

}