package de.ollie.archimedes.alexandrian.integrationtests;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.ollie.archimedes.alexandrian.gui.ApplicationFrame;

/**
 * Integration tests for the applications status bar.
 *
 * @author ollie (12.09.2019)
 */
public class StatusBarTest {

	@DisplayName("Status bar is initialized correctly")
	@Test
	public void statusBar_IsInitializedCorrectly() {
		ApplicationFrame.systemExiter = new TestApplicationCloser();
		JFrame frame = new ApplicationFrame();
		JLabel l = (JLabel) ITUtil.findComponent("StatusBar:StatusMessage", frame);
		assertThat(l.getText(), equalTo("Null problemo!"));
	}

}