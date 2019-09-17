package de.ollie.archimedes.alexandrian.service.impl;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import de.ollie.archimedes.alexandrian.service.ModelService;
import de.ollie.archimedes.alexandrian.service.so.DatabaseSO;
import de.ollie.archimedes.alexandrian.service.so.SchemeSO;
import de.ollie.archimedes.alexandrian.service.so.TableSO;

/**
 * An implementation of the ModelService interface.
 *
 * @author ollie (15.09.2019)
 */
@Service
public class ModelServiceImpl implements ModelService {

	static Logger log = Logger.getLogger(ModelServiceImpl.class);

	private DatabaseSO model = null;

	@PostConstruct
	void postConstruct() {
		SchemeSO scheme = new SchemeSO().setName("Scheme");
		this.model = new DatabaseSO().setName("Model").addScheme(scheme);
		log.info("service initialized.");
	}

	@Override
	public boolean addTable(TableSO table) {
		this.model.getSchemes().get(0).addTable(table);
		log.info("table added: " + table.getName());
		return true;
	}

	@Override
	public DatabaseSO getModel() {
		return this.model;
	}

}