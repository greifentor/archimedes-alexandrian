package de.ollie.archimedes.alexandrian.persistence.converter;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * Unit tests for class "TableSOToXMLStringConverter".
 *
 * @author ollie (16.09.2019)
 */
@ExtendWith(MockitoExtension.class)
public class TableSOToXMLStringConverterTest {

	private static final String TABLE_NAME = "tableName";

	@InjectMocks
	private TableSOToXMLStringConverter unitUnderTest;

	@DisplayName("TableSO to XML string")
	@Nested
	class TableSOToXMLStringConversionTests {

		@DisplayName("Throws an IllegalArgumentException passing a null value.")
		@Test
		void convert_PassANullValueAsIndent_ThrowsAnException() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				unitUnderTest.convert(new TableSO(), null);
			});
		}

		@DisplayName("Passing a null value returns a null value.")
		@Test
		void convert_PassANullValue_ReturnsANullValue() {
			// Prepare
			String expected = null;
			TableSO passed = null;
			// Run
			String returned = unitUnderTest.convert(passed, "\t\t");
			// Check
			assertThat(returned, equalTo(expected));
		}

		@DisplayName("Passing a table, returns the correct string.")
		@Test
		void convert_PassATable_ReturnsACorrectString() {
			// Prepare
			String expected = "\t\t<table name=\"" + TABLE_NAME + "\">\n" //
					+ "\t\t</table>\n";
			TableSO passed = new TableSO().setName(TABLE_NAME);
			// Run
			String returned = unitUnderTest.convert(passed, "\t\t");
			// Check
			assertThat(returned, equalTo(expected));
		}

	}

}