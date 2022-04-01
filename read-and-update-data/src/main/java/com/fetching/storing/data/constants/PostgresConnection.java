package com.fetching.storing.data.constants;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.fetching.storing.data.dto.CellDto;
import com.fetching.storing.data.model.BMS;
import com.fetching.storing.data.model.Cell;
import com.fetching.storing.data.model.Ewaste;
import com.fetching.storing.data.model.EwasteBatch;
import com.fetching.storing.data.model.Manufacturer;
import com.fetching.storing.data.model.PhysicalCondition;

public class PostgresConnection {
	public static final Configuration CONFIGURATION = new Configuration().configure().addAnnotatedClass(PhysicalCondition.class).addAnnotatedClass(EwasteBatch.class).addAnnotatedClass(Manufacturer.class).addAnnotatedClass(Ewaste.class).addAnnotatedClass(BMS.class).addAnnotatedClass(Cell.class);
	public static final SessionFactory SF = CONFIGURATION.buildSessionFactory();
	public static final Session SESSION = SF.openSession();
	public static final Transaction TRANSACTION = SESSION.beginTransaction();
	
	public void closeConnections() {
		this.TRANSACTION.commit();
		this.SESSION.close();
	}
}
