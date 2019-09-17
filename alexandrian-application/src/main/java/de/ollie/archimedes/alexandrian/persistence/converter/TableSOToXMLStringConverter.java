package de.ollie.archimedes.alexandrian.persistence.converter;

import static de.ollie.utils.Check.ensure;

import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * A converter which transfers table service object to an XML string.
 *
 * @author ollie (16.09.2019)
 */
public class TableSOToXMLStringConverter {

	/**
	 * Converts the passed table service object into an XML string.
	 * 
	 * @param tableSO The table service object to convert.
	 * @param indent  A string to indent the XML.
	 * @return An XML string which represents the passed table service object.
	 * @throws IllegalArgumentException Passing a null value as indent.
	 */
	public String convert(TableSO table, String indent) {
		ensure(indent != null, "indent cannot be null.");
		if (table == null) {
			return null;
		}
		return new StringBuilder(indent).append("<table name=\"").append(table.getName()).append("\">\n") //
				.append(indent).append("</table>\n").toString();
	}

}