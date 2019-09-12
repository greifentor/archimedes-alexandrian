package de.ollie.archimedes.alexandrian;

import org.apache.log4j.Logger;

import de.ollie.archimedes.alexandrian.gui.ApplicationFrame;

/**
 * Starts the Archimedes Alexandrian application.
 *
 * @author ollie (12.09.2019)
 *
 *
 */
public class ArchimedesAlexandrianStarter {

	static final Logger LOG = Logger.getLogger(ArchimedesAlexandrianStarter.class);

	public static void main(String[] args) {
		LOG.info("Starting Archimedes Alexandrian ...");
		new ApplicationFrame();
	}

}