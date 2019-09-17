package de.ollie.archimedes.alexandrian.service;

import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * A service for table access.
 *
 * @author ollie (15.09.2019)
 */
public interface ModelService {

	/**
	 * Adds the table to the model.
	 * 
	 * @param table The table service object to add.
	 */
	boolean addTable(TableSO table);

	/**
	 * Returns the model.
	 * 
	 * @return The the model.
	 */
	DatabaseSO getModel();

}