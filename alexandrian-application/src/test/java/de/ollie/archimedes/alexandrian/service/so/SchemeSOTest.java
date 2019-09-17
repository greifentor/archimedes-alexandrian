package de.ollie.archimedes.alexandrian.service.so;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for class "SchemeSO".
 *
 * @author ollie (16.09.2019)
 */
@ExtendWith(MockitoExtension.class)
public class SchemeSOTest {

	@InjectMocks
	private SchemeSO unitUnderTest;

	@DisplayName("Tests for method addTable.")
	@Nested
	class TestsForMethodAddTable {

		@DisplayName("Passing a null value is not added to the list.")
		@Test
		void addTable_PassANullPointer_AddsNothing() {
			// Run
			unitUnderTest.addTable(null);
			// Check
			assertThat(unitUnderTest.getTables().size(), equalTo(0));
		}

		@DisplayName("Passing a table service object adds it to the list.")
		@Test
		void addTable_PassATableSO_AddsThePassedTableSO() {
			// Prepare
			TableSO expected = new TableSO();
			// Run
			unitUnderTest.addTable(expected);
			// Check
			assertThat(unitUnderTest.getTables().get(0), equalTo(expected));
		}

	}

}