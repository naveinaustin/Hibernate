package com;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TablePerSubClassTester {

	public static void main(String[] args) {
		createBaggages();
		System.out.println("BAGGAGES CREATED");
		HibernateUtil.sessionFactory.close();
	}

	private static void createBaggages() {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		/* Bad practice. Creation of objects should be done outside the transaction. */
		CheckinBaggage chek = new CheckinBaggage("bvrrag",23);
		HandBaggage hb = new HandBaggage("sreenath",5);
		session.save(chek);
		session.save(hb);
		tx.commit();
		HibernateUtil.closeSession();
	}
}
