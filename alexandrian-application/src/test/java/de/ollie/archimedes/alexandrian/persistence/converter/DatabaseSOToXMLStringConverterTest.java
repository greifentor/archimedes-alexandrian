package de.ollie.archimedes.alexandrian.persistence.converter;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;
import de.ollie.archimedes.alexandrian.service.so.SchemeSO;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * Unit tests for class "DatabaseSOToXMLStringConverter".
 *
 * @author ollie (16.09.2019)
 */
@ExtendWith(MockitoExtension.class)
public class DatabaseSOToXMLStringConverterTest {

	private static final String DATABASE_NAME = "databaseName";
	private static final String SCHEME_NAME = "schemeName";
	private static final String TABLE_NAME = "tableName";

	private DatabaseSOToXMLStringConverter unitUnderTest = new DatabaseSOToXMLStringConverter(
			new TableSOToXMLStringConverter());

	@DisplayName("DatabaseSO to XML string")
	@Nested
	class DatabaseSOToXMLStringConversionTests {

		@DisplayName("Passing a null value returns a null value.")
		@Test
		void convert_PassANullValue_ReturnsANullValue() {
			// Prepare
			String expected = null;
			DatabaseSO passed = null;
			// Run
			String returned = unitUnderTest.convert(passed);
			// Check
			assertThat(returned, equalTo(expected));
		}

		@DisplayName("Passing a database without scheme or tables.")
		@Test
		void convert_PassADatabaseWithNoSchemeOrTable_ReturnsAnEmptyDatabaseString() {
			// Prepare
			String expected = "<database name=\"" + DATABASE_NAME + "\">\n" //
					+ "</database>";
			DatabaseSO passed = new DatabaseSO().setName(DATABASE_NAME);
			// Run
			String returned = unitUnderTest.convert(passed);
			// Check
			assertThat(returned, equalTo(expected));
		}

		@DisplayName("Passing a database with a scheme without tables.")
		@Test
		void convert_PassADatabaseWithSchemeButNoTables_ReturnsCorrectString() {
			// Prepare
			String expected = "<database name=\"" + DATABASE_NAME + "\">\n" //
					+ "\t<scheme name=\"" + SCHEME_NAME + "\">\n" //
					+ "\t</scheme>\n" //
					+ "" + "</database>";
			SchemeSO scheme = new SchemeSO().setName(SCHEME_NAME);
			DatabaseSO passed = new DatabaseSO().setName(DATABASE_NAME).addScheme(scheme);
			// Run
			String returned = unitUnderTest.convert(passed);
			// Check
			assertThat(returned, equalTo(expected));
		}

		@DisplayName("Passing a database with a scheme and table.")
		@Test
		void convert_PassADatabaseWithSchemeAndTable_ReturnsCorrectString() {
			// Prepare
			String expected = "<database name=\"" + DATABASE_NAME + "\">\n" //
					+ "\t<scheme name=\"" + SCHEME_NAME + "\">\n" //
					+ "\t\t<table name=\"" + TABLE_NAME + "\">\n" //
					+ "\t\t</table>\n" //
					+ "\t</scheme>\n" //
					+ "" + "</database>";
			TableSO table = new TableSO().setName(TABLE_NAME);
			SchemeSO scheme = new SchemeSO().setName(SCHEME_NAME).addTable(table);
			DatabaseSO passed = new DatabaseSO().setName(DATABASE_NAME).addScheme(scheme);
			// Run
			String returned = unitUnderTest.convert(passed);
			// Check
			assertThat(returned, equalTo(expected));
		}

	}

}