package cdwriter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;


public class IdIncrementExample {
	public static void main(String[] args) {
		Session session = null;

		try{
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session =sessionFactory.openSession();
			 
			Transaction tx = session.beginTransaction();
			 
			//Create new instance of Contact and set values in it by reading them from form object
		 	System.out.println("Inserting Book object into database..");
			Book book = new Book();
			book.setStrBookName("BVR Hibernate Tutorial");
			session.save(book);
			System.out.println("Book object persisted to the database.");
	        tx.commit();
	        session.flush();
	        session.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			}
		
	}
}
