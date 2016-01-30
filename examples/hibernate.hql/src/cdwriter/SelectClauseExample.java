package cdwriter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.*;

public class SelectClauseExample {
	public static void main(String[] args) {
	Session session = null;

	try{
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session =sessionFactory.openSession();
		 
		//Create Select Clause HQL
	 	String SQL_QUERY ="Select insurance.lngInsuranceId,insurance.insuranceName," + 
		 "insurance.investementAmount,insurance.investementDate from Insurance insurance";
	 	Query query = session.createQuery(SQL_QUERY);
	 	for(Iterator it=query.iterate();it.hasNext();){
	 		Object[] row = (Object[]) it.next();
	 		System.out.println("ID: " + row[0]);
	 		System.out.println("Name: " + row[1]);
	 		System.out.println("Amount: " + row[2]);
	 	}
	 	
	 	System.out.println("done");
	 	
        session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		}
	}
}
