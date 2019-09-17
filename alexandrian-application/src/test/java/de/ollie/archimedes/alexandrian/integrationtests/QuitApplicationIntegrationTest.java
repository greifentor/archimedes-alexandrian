package de.ollie.archimedes.alexandrian.integrationtests;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.netbeans.jemmy.operators.JFrameOperator;

import de.ollie.archimedes.alexandrian.gui.ApplicationFrame;
import de.ollie.archimedes.alexandrian.gui.SystemExiter;

/**
 * A test which checks closing the application.
 *
 * @author ollie (12.09.2019)
 */
public class QuitApplicationIntegrationTest {

	@DisplayName("Opens the application and selects the quit item of the file menu. The application closes.")
	@Test
	public void clickTheQuitItem_CallsTheApplicationCloser() {
		ApplicationFrame.systemExiter = new TestApplicationCloser();
		new ApplicationFrame();
		ITUtil.clickQuitMenuItem(new JFrameOperator());
		assertThat(((TestApplicationCloser) ApplicationFrame.systemExiter).calls, equalTo(1));
	}

}

class TestApplicationCloser implements SystemExiter {

	int calls = 0;

	@Override
	public void exit(int returnCode) {
		this.calls++;
	}

}