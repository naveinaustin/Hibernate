package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Table per class hierarchy
 * @author Navein.Fernandes
 *
 */
public class SingleTableTester {

	public static void main(String[] args) {
		createAirports();
		System.out.println("AIRPORTS CREATED");
		HibernateUtil.sessionFactory.close();
	}

	private static void createAirports() {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		DomesticAirport dom = new DomesticAirport("RAJIV GANDHI", "Hyderabad");
		InternationalAirport intl = new InternationalAirport("INDIRA GANDHI", "Bangalore");
		session.save(dom);
		session.save(intl);
		tx.commit();
		HibernateUtil.closeSession();
	}

}
