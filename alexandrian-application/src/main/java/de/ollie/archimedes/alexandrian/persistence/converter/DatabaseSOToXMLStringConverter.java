package de.ollie.archimedes.alexandrian.persistence.converter;

import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;
import de.ollie.archimedes.alexandrian.service.so.SchemeSO;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * A converter which transfers database service object to an XML string.
 *
 * @author ollie (16.09.2019)
 */
public class DatabaseSOToXMLStringConverter {

	private final TableSOToXMLStringConverter tableConverter;

	public DatabaseSOToXMLStringConverter(TableSOToXMLStringConverter tableConverter) {
		super();
		this.tableConverter = tableConverter;
	}

	/**
	 * Converts the passed database service object into an XML string.
	 * 
	 * @param modelSO The database service object to convert.
	 * @return An XML string which represents the passed database service object.
	 */
	public String convert(DatabaseSO database) {
		if (database == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder("<database name=\"" + database.getName() + "\">\n");
		for (SchemeSO scheme : database.getSchemes()) {
			sb.append("\t<scheme name=\"").append(scheme.getName()).append("\">\n");
			for (TableSO table : scheme.getTables()) {
				sb.append(this.tableConverter.convert(table, "\t\t"));
			}
			sb.append("\t</scheme>\n");
		}
		return sb.append("</database>").toString();
	}

}