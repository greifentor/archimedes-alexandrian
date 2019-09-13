package de.ollie.archimedes.alexandrian.integrationtests;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;

import de.ollie.archimedes.alexandrian.gui.ApplicationFrame;
import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponent;

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

	@DisplayName("Position field on status bar shows the mouse position on the DiagramComponent.")
	@Test
	public void statusBar_ShowsTheCurrentMousePositionInTheDiagramComponentOnPositionField() throws Exception {
		ApplicationFrame.systemExiter = new TestApplicationCloser();
		ApplicationFrame.main(new String[0]);
		JFrameOperator frameOp = new JFrameOperator();
		JComponentOperator diagramComponent = new JComponentOperator(
				(DiagramComponent) ITUtil.findComponent("DiagramComponent", frameOp.getWindow()));
		diagramComponent.moveMouse(100, 100);
		Thread.sleep(1000);
		JLabel l = (JLabel) ITUtil.findComponent("StatusBar:MousePosition", frameOp.getWindow());
		assertThat(l.getText(), equalTo("(100, 100)"));
	}

}