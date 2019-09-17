package de.ollie.archimedes.alexandrian.service.exception;

/**
 * An exception for persistence errors.
 *
 * @author ollie (17.09.2019)
 */
public class PersistenceException extends Exception {

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}