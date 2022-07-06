package de.ollie.archimedes.alexandrian.persistence;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import de.ollie.archimedes.alexandrian.persistence.converter.DatabaseSOToXMLStringConverter;
import de.ollie.archimedes.alexandrian.persistence.converter.TableSOToXMLStringConverter;
import de.ollie.archimedes.alexandrian.service.exception.PersistenceException;
import de.ollie.archimedes.alexandrian.service.persistence.port.ModelPersistencePort;
import de.ollie.archimedes.alexandrian.service.persistence.port.PersistenceMode;
import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;

/**
 * An implementation of the model persistence port for XML file storage.
 *
 * @author ollie (17.09.2019)
 */
@Component
public class XMLModelPersistenceAdapter implements ModelPersistencePort {

	static Logger log = LogManager.getLogger(XMLModelPersistenceAdapter.class);

	public DatabaseSOToXMLStringConverter converter = new DatabaseSOToXMLStringConverter(
			new TableSOToXMLStringConverter());

	@Override
	public void persist(DatabaseSO model, PersistenceMode mode) throws PersistenceException {
		String content = this.converter.convert(model);
		try {
			Path path = Paths.get(System.getProperty("java.io.tmpdir"), "unbenannt.xml");
			log.info("writing to XML file: " + path);
			Files.write(path, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (Exception e) {
			log.warn("error while persisting database model to XML file: " + e.getMessage() + ", exception: " + e);
			throw new PersistenceException("error while persisting database model to XML file: " + e.getMessage(), e);
		}
	}

}