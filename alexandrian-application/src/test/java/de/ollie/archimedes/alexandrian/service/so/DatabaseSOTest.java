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
 * Unit tests for class "DatabaseSO".
 *
 * @author ollie (16.09.2019)
 */
@ExtendWith(MockitoExtension.class)
public class DatabaseSOTest {

	@InjectMocks
	private DatabaseSO unitUnderTest;

	@DisplayName("Tests for method addScheme.")
	@Nested
	class TestsForMethodAddScheme {

		@DisplayName("Passing a null value is not added to the list.")
		@Test
		void addScheme_PassANullPointer_AddsNothing() {
			// Run
			unitUnderTest.addScheme(null);
			// Check
			assertThat(unitUnderTest.getSchemes().size(), equalTo(0));
		}

		@DisplayName("Passing a scheme service object adds it to the list.")
		@Test
		void addScheme_PassASchemeSO_AddsThePassedSchemeSO() {
			// Prepare
			SchemeSO expected = new SchemeSO();
			// Run
			unitUnderTest.addScheme(expected);
			// Check
			assertThat(unitUnderTest.getSchemes().get(0), equalTo(expected));
		}

	}

}