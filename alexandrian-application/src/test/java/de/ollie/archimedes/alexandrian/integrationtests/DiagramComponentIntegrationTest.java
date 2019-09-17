package de.ollie.archimedes.alexandrian.integrationtests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.equalTo;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.assertj.core.util.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.operators.JMenuOperator;

import de.ollie.archimedes.alexandrian.gui.ApplicationFrame;
import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponent;
import de.ollie.archimedes.alexandrian.persistence.converter.TableSOToXMLStringConverter;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * Integration tests for the diagram component.
 *
 * @author ollie (15.09.2019)
 */
public class DiagramComponentIntegrationTest {

	private String fileName = System.getProperty("java.io.tmpdir") + "/unbenannt.xml";
	private TableSOToXMLStringConverter tableConverter = new TableSOToXMLStringConverter();

	@BeforeEach
	void setUp() {
		new File(this.fileName).delete();
	}

	@DisplayName("Changes to insert mode after clicked Diagram|CreateTable.")
	@Test
	void changeToInsertMode() {
		System.setProperty("archimedes.alexandrian.save.path", fileName);
		ApplicationFrame.main(new String[0]);
		JFrameOperator frameOp = new JFrameOperator();
		changeToInsertMode(frameOp);
		JLabel labelDiagramComponentMode = (JLabel) ITUtil.findComponent("StatusBar:DiagramComponentMode",
				frameOp.getWindow());
		assertThat(labelDiagramComponentMode.getText(), equalTo("INSERT"));
	}

	private void changeToInsertMode(JFrameOperator frameOp) {
		JMenuOperator menuDiagram = new JMenuOperator(
				(JMenu) ITUtil.findComponent("Menu:Diagram", frameOp.getWindow()));
		menuDiagram.doClick();
		JMenuItemOperator menuItemCreateTable = new JMenuItemOperator(
				(JMenuItem) ITUtil.findComponent("MenuItem:Diagram|CreateTable", menuDiagram.getComponent()));
		menuItemCreateTable.doClick();
	}

	@DisplayName("Create a new table on diagram component.")
	@Test
	void createANewTableOnDiagramComponent() {
		System.setProperty("archimedes.alexandrian.save.path", fileName);
		ApplicationFrame.main(new String[0]);
		JFrameOperator frameOp = new JFrameOperator();
		changeToInsertMode(frameOp);
		clickOnTheDiagramComponent(frameOp);
		saveDiagram(frameOp);
		String fileContent = Files.contentOf(new File(fileName), Charset.forName("UTF-8"));
		assertTrue(fileContent.contains(this.tableConverter.convert(new TableSO().setName("Table0"), "\t\t")));
	}

	private void clickOnTheDiagramComponent(JFrameOperator frameOp) {
		JComponentOperator diagramComponent = new JComponentOperator(
				(DiagramComponent) ITUtil.findComponent("DiagramComponent", frameOp.getWindow()));
		diagramComponent.moveMouse(100, 100);
		diagramComponent.clickMouse(2);
	}

	private void saveDiagram(JFrameOperator frameOp) {
		JMenuOperator menuFile = new JMenuOperator((JMenu) ITUtil.findComponent("Menu:File", frameOp.getWindow()));
		menuFile.doClick();
		JMenuItemOperator menuItemSave = new JMenuItemOperator(
				(JMenuItem) ITUtil.findComponent("MenuItem:File|Save", menuFile.getComponent()));
		menuItemSave.doClick();
	}

}