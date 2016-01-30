package example.products;
//import HibernateSessionFactory;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CDTest {

  public static void main(String [] args) {
	  Transaction tx = null;
    try {
    	
		//Session session = new Configuration().buildSessionFactory().openSession();
		Session session=HibernateSessionFactory.currentSession();
	
			tx = session.beginTransaction();
       

     // CD cd = new CD("Grace Under Pressure", "Rush", new Date(), 9.99);
      SpecialEditionCD secd = new SpecialEditionCD("Grace Under Pressure", "Rush", new Date(), 9.99, "Widescreen");
      InternationalCD icd = new InternationalCD("Grace Under Pressure", "Rush", new Date(), 9.99, "Spanish", 4);

     // session.save(cd);
      session.save(secd);
      session.save(icd);
      tx.commit();
      session.flush();
     
      session.close();
		
		
	} catch (HibernateException e) {
		e.printStackTrace();
		
	//	if (tx != null && ((Object) tx).isActive())
			tx.rollback();

	}
	}}
    
