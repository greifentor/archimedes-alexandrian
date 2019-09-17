package de.ollie.archimedes.alexandrian.gui.diagram;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseMovedEvent;

/**
 * Unit tests for class "DiagramComponent".
 *
 * @author ollie (13.09.2019)
 */
@ExtendWith(MockitoExtension.class)
public class DiagramComponentTest {

	@InjectMocks
	private DiagramComponent unitUnderTest;

	private Logger log = null;;

	@BeforeEach
	void setUp() {
		this.log = DiagramComponent.log;
		DiagramComponent.log = mock(Logger.class);
	}

	@AfterEach
	void tearDown() {
		DiagramComponent.log = this.log;
	}

	@DisplayName("DiagramComponentListener tests.")
	@Nested
	class DiagramComponentListenerTests {

		@DisplayName("\"null\" values will not be added to the Listeners.")
		@Test
		@SuppressWarnings("unchecked")
		void addDiagramComponentListener_NullValuePassed_AddsNothingToList() {
			// Prepare
			DiagramComponentListener listener = null;
			// Run
			unitUnderTest.addDiagramComponentListener(listener);
			// Check
			List<DiagramComponentListener> l = (List<DiagramComponentListener>) ReflectionTestUtils
					.getField(unitUnderTest, "listeners");
			assertThat(l.isEmpty(), equalTo(true));
		}

		@DisplayName("Listener will be added to the Listeners.")
		@Test
		@SuppressWarnings("unchecked")
		void addDiagramComponentListener_ListenerPassed_ListenerWillBeAddedToTheList() {
			// Prepare
			DiagramComponentListener listener = mock(DiagramComponentListener.class);
			// Run
			unitUnderTest.addDiagramComponentListener(listener);
			// Check
			List<DiagramComponentListener> l = (List<DiagramComponentListener>) ReflectionTestUtils
					.getField(unitUnderTest, "listeners");
			assertThat(l.size(), equalTo(1));
			assertThat(l.get(0), equalTo(listener));
		}

		@DisplayName("An error while firing a MouseMovedEvent causes a warning in the log.")
		@Test
		void fireMouseMovedEvent_ExceptionThrown_CreatesAWarningOnLog() {
			// Prepare
			MouseMovedEvent event = mock(MouseMovedEvent.class);
			DiagramComponentListener listener = mock(DiagramComponentListener.class);
			RuntimeException exception = new RuntimeException();
			doThrow(exception).when(listener).mouseMoved(event);
			unitUnderTest.addDiagramComponentListener(listener);
			// Run
			unitUnderTest.fireMouseMovedEvent(event);
			// Check
			verify(DiagramComponent.log, times(1)).warn("Error occured while firing MouseMovedEvent: " + event
					+ ", listener: " + listener + ", exception: " + exception);
		}

	}

	@DisplayName("DiagramComponentMode tests.")
	@Nested
	class DiagramComponentModeTests {

		@DisplayName("Accepts a null value as diagram component mode.")
		@Test
		void setDiagramComponentMode_PassANullValue_SetsNullAsMode() {
			// Prepare
			DiagramComponentMode expected = null;
			// Run
			unitUnderTest.setDiagramComponentMode(expected);
			// Check
			assertThat(unitUnderTest.getDiagramComponentMode(), equalTo(expected));
		}

		@DisplayName("Set the diagram component mode.")
		@Test
		void setDiagramComponentMode_PassADiagramComponentMode_SetsThePassedMode() {
			// Prepare
			DiagramComponentMode expected = DiagramComponentMode.INSERT;
			// Run
			unitUnderTest.setDiagramComponentMode(expected);
			// Check
			assertThat(unitUnderTest.getDiagramComponentMode(), equalTo(expected));
		}

		@DisplayName("Returns the diagram component.")
		@Test
		void setDiagramComponentMode_ReturnsTheDiagramComponent() {
			// Prepare
			DiagramComponentMode expected = DiagramComponentMode.INSERT;
			// Run
			DiagramComponent returned = unitUnderTest.setDiagramComponentMode(expected);
			// Check
			assertThat(returned, sameInstance(unitUnderTest));
		}

	}
}