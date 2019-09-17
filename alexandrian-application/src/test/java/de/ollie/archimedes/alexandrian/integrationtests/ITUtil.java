package de.ollie.archimedes.alexandrian.integrationtests;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.operators.JMenuOperator;

/**
 * Some utility method for integration tests.
 *
 * @author ollie (12.09.2019)
 */
public class ITUtil {

	public static void clickQuitMenuItem(JFrameOperator frame) {
		JMenuOperator menuFile = new JMenuOperator((JMenu) findComponent("Menu:File", frame.getWindow()));
		menuFile.doClick();
		JMenuItemOperator menuItemQuit = new JMenuItemOperator(
				(JMenuItem) findComponent("MenuItem:File|Quit", menuFile.getComponent()));
		menuItemQuit.doClick();
	}

	/**
	 * Returns the component with the passed name.
	 *
	 * @param name The component name which is to search for.
	 * @param root The component which acts as root.
	 * @return The component with the passed name or a "null" value.
	 */
	public static Component findComponent(String cn, Component root) {
		Component result = null;
		Container cont = null;
		int i = 0;
		int leni = 0;
		JMenu menu = null;
		JMenuBar menuBar = null;
		JToolBar toolBar = null;
		String n = root.getName();
		if ((n != null) && n.equals(cn) && (root != null)) {
			return root;
		}
		if (root instanceof JMenuBar) {
			menuBar = (JMenuBar) root;
			for (i = 0, leni = menuBar.getMenuCount(); i < leni; i++) {
				if (menuBar.getMenu(i) != null) {
					result = findComponent(cn, menuBar.getMenu(i));
					if (result != null) {
						break;
					}
				}
			}
		} else if (root instanceof JMenu) {
			menu = (JMenu) root;
			for (i = 0, leni = menu.getMenuComponentCount(); i < leni; i++) {
				if (menu.getMenuComponent(i) != null) {
					result = findComponent(cn, menu.getMenuComponent(i));
					if (result != null) {
						break;
					}
				}
			}
			if (result != null) {
				for (i = 0, leni = menu.getItemCount(); i < leni; i++) {
					if (menu.getItem(i) != null) {
						result = findComponent(cn, menu.getItem(i));
						if (result != null) {
							break;
						}
					}
				}
			}
		} else if (root instanceof JToolBar) {
			toolBar = (JToolBar) root;
			for (i = 0, leni = toolBar.getComponentCount(); i < leni; i++) {
				if (toolBar.getComponentAtIndex(i) != null) {
					result = findComponent(cn, toolBar.getComponentAtIndex(i));
					if (result != null) {
						break;
					}
				}
			}
		} else if (root instanceof Container) {
			cont = (Container) root;
			for (i = 0, leni = cont.getComponentCount(); i < leni; i++) {
				if (cont.getComponent(i) != null) {
					result = findComponent(cn, cont.getComponent(i));
					if (result != null) {
						break;
					}
				}
			}
		}
		return result;
	}

}