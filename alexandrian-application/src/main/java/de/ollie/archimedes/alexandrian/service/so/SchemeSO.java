package de.ollie.archimedes.alexandrian.service.so;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container class for database schemes in the service environment.
 * 
 * @author ollie
 *
 */
@Generated
@Data
@Accessors(chain = true)
public class SchemeSO {

	private String name;
	private List<TableSO> tables = new ArrayList<>();

	/**
	 * Adds the passed table to the tables of the scheme service object.
	 * 
	 * @param table The table to add ("null" values will not be added).
	 * @return The scheme service object.
	 */
	public SchemeSO addTable(TableSO table) {
		if (table != null) {
			this.tables.add(table);
		}
		return this;
	}

}