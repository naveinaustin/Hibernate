package flight;

import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.*;

public class UniDirectionalAscTester {
	
	public static void main(String[] args) 
	{
		//listFlights();
		createFlight();
		listFlights();
		HibernateUtil.sessionFactory.close();
	}

	private static void createFlight() {
	
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Flight fl = new Flight();

		fl.setFlightNo("DN207");
		fl.setAirlines("Air Deccan");
		fl.setFrom("Bangalore");
		fl.setTo("Chennai");
	
		session.save(fl);
		tx.commit();
		HibernateUtil.closeSession();
	}

	private static void listFlights(){

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		List result = session.createQuery("from Flight").list();
		

		for (int i = 0; i < result.size(); i++) {
			Flight fl = (Flight) result.get(i);
			System.out.println("FlightNo: " + fl.getFlightNo() + " Airline: " + fl.getAirlines());
			Map pass = fl.getPassengers();
			System.out.println("Passengers on this flight:");
			Iterator it = pass.keySet().iterator();
			while(it.hasNext()){
				String name = (String)it.next();
				System.out.println(name);
			}
		}
		tx.commit();
		session.close();
	}
}

