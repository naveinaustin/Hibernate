package cdwriter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import java.util.*;

public class SelectHQLExample {

	public static void main(String[] args) {
	Session session = null;

	try{
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		 session =sessionFactory.openSession();
		 
		 
			//Using from Clause
		 	String SQL_QUERY ="from Insurance insurance";
		 	Query query = session.createQuery(SQL_QUERY);
		 	for(Iterator it=query.iterate();it.hasNext();){
		 		Insurance insurance=(Insurance)it.next();
		 		System.out.println("ID: " + insurance.getLngInsuranceId());
		 		System.out.println("First Name: " + insurance.getInsuranceName());
		 	}
		 	
	        session.close();
	}catch(Exception e){
		System.out.println(e.getMessage());
	}finally{
		}

	}	
}
