/*
 * ApplicationFrame.java
 *
 * (c) by Ollie
 *
 * 12.09.2019
 */
package de.ollie.archimedes.alexandrian.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponent;
import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponentListener;
import de.ollie.archimedes.alexandrian.gui.diagram.DiagramComponentMode;
import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseClickEvent;
import de.ollie.archimedes.alexandrian.gui.diagram.event.MouseMovedEvent;
import de.ollie.archimedes.alexandrian.gui.statusbar.StatusBar;
import de.ollie.archimedes.alexandrian.service.ModelService;
import de.ollie.archimedes.alexandrian.service.exception.PersistenceException;
import de.ollie.archimedes.alexandrian.service.persistence.port.ModelPersistencePort;
import de.ollie.archimedes.alexandrian.service.persistence.port.PersistenceMode;
import de.ollie.archimedes.alexandrian.service.so.TableGUIInfo;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * The main frame of the application.
 *
 * @author ollie (12.09.2019)
 *
 */
@ComponentScan("de.ollie")
public class ApplicationFrame extends JFrame implements ActionListener, DiagramComponentListener {

	public static SystemExiter systemExiter = new SystemExiter() {
	};

	static Logger log = LogManager.getLogger(ApplicationFrame.class);

	private static final int HGAP = 3;
	private static final int VGAP = 3;

	private static ConfigurableApplicationContext ctx = null;

	private DiagramComponent diagramComponent = new DiagramComponent();
	private JPanel panelMain = new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem menuItemCreateTable = null;
	private JMenuItem menuItemQuit = null;
	private JMenuItem menuItemSave = null;
	private StatusBar statusBar = null;

	public static void main(String[] args) {
		ctx = new SpringApplicationBuilder(ApplicationFrame.class).headless(false).run(args);
	}

	@Autowired
	private ModelService modelService;
	@Autowired
	private ModelPersistencePort modelPersistencePort;

	/**
	 * Creates and opens a new application main frame.
	 */
	public ApplicationFrame() {
		super("Archimedes Alexandrian");
		centerAndResize();
		addMenuBar();
		this.panelMain = createPanelMain();
		addStatusBar("Null problemo!");
		setContentPane(this.panelMain);
		setVisible(true);
	}

	private void centerAndResize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(dim.width - 100, dim.height - 100));
		this.setSize((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight());
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void addMenuBar() {
		this.menuBar = new JMenuBar();
		JMenu menuFile = createMenu("File");
		menuFile.setName("Menu:File");
		this.menuBar.add(menuFile);
		this.menuItemSave = createMenuItem("Save");
		this.menuItemSave.setName("MenuItem:File|Save");
		menuFile.add(this.menuItemSave);
		menuFile.add(new JSeparator(JSeparator.HORIZONTAL));
		this.menuItemQuit = createMenuItem("Quit");
		this.menuItemQuit.setName("MenuItem:File|Quit");
		menuFile.add(this.menuItemQuit);
		JMenu menuDiagram = createMenu("Diagram");
		menuDiagram.setName("Menu:Diagram");
		this.menuBar.add(menuDiagram);
		this.menuItemCreateTable = createMenuItem("Create Table");
		this.menuItemCreateTable.setName("MenuItem:Diagram|CreateTable");
		menuDiagram.add(this.menuItemCreateTable);
		this.setJMenuBar(this.menuBar);
	}

	private JPanel createPanelMain() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		p.add(this.diagramComponent);
		this.diagramComponent.addDiagramComponentListener(this);
		return p;
	}

	private JMenu createMenu(String label) {
		return new JMenu(label);
	}

	private JMenuItem createMenuItem(String label) {
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.addActionListener(this);
		return menuItem;
	}

	private void addStatusBar(String statusMessage) {
		this.statusBar = new StatusBar(statusMessage);
		this.panelMain.add(this.statusBar, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.menuItemCreateTable) {
			this.diagramComponent.setDiagramComponentMode(DiagramComponentMode.INSERT);
			this.statusBar.updateDiagramComponentMode(this.diagramComponent.getDiagramComponentMode());
		} else if (e.getSource() == this.menuItemQuit) {
			ApplicationFrame.systemExiter.exit(0);
		} else if (e.getSource() == this.menuItemSave) {
			try {
				this.modelPersistencePort.persist(this.modelService.getModel(), PersistenceMode.XML);
			} catch (PersistenceException pe) {
				log.error("error persisting database model: " + pe.getMessage() + ", cause: " + pe.getCause());
			}
		}
	}

	@Override
	public void mouseClicked(MouseClickEvent event) {
		if (event.getMode() == DiagramComponentMode.INSERT) {
			TableGUIInfo guiInfo = new TableGUIInfo().setX(event.getMouseX()).setY(event.getMouseY());
			TableSO table = new TableSO().setName("Table0").setGuiInfo(guiInfo);
			this.modelService.addTable(table);
		}
	}

	@Override
	public void mouseMoved(MouseMovedEvent event) {
		this.statusBar.updateMousePosition(event.getMouseX(), event.getMouseY());
	}

}