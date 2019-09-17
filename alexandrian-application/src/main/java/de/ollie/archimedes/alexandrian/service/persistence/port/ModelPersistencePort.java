package de.ollie.archimedes.alexandrian.service.persistence.port;

import de.ollie.archimedes.alexandrian.service.exception.PersistenceException;
import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;

/**
 * An interface for classes which are able to persist a model.
 *
 * @author ollie (17.09.2019)
 */
public interface ModelPersistencePort {

	/**
	 * Persists the mode in relation to the passed persistence mode.
	 * 
	 * @param model The model service object to persist.
	 * @param mode  The persistence mode.
	 * @throws PersistenceException In case of an error occurs while persisting the database model.
	 */
	void persist(DatabaseSO model, PersistenceMode mode) throws PersistenceException;

}