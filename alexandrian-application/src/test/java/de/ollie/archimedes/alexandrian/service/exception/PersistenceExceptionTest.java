package de.ollie.archimedes.alexandrian.service.exception;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for class "PersistenceException".
 *
 * @author ollie (17.09.2019)
 */
public class PersistenceExceptionTest {

	private static final RuntimeException CAUSE = new RuntimeException();
	private static final String MESSAGE = "message";

	private PersistenceException unitUnderTest = new PersistenceException(MESSAGE, CAUSE);

	@Test
	public void constructorSetCauseCorrectly() {
		assertThat(this.unitUnderTest.getCause(), equalTo(CAUSE));
	}

	@Test
	public void constructorSetMessageCorrectly() {
		assertThat(this.unitUnderTest.getMessage(), equalTo(MESSAGE));
	}

}