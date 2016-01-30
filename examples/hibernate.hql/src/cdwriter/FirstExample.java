package cdwriter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class FirstExample {
	public static void main(String[] args) {
		Session session = null;
		 Transaction tx = null;
		try{
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			 session =sessionFactory.openSession();
			 tx = session.beginTransaction();
				//Create new instance of Contact and set values in it by reading them from form object
			 	System.out.println("Inserting Record");
				Contact contact = new Contact();
				contact.setId(5);
				contact.setFirstName("vishwanath");
				contact.setLastName("b ramachandra rao");
				contact.setEmail("vishymails@gmail.com");
				session.save(contact);
				 tx.commit();
				System.out.println("Done");
		}catch(Exception e){
			System.out.println(e.getMessage());
			tx.rollback();
		}finally{
			// Actual contact insertion will happen at this step
			try
			{
			session.flush();
			session.close();
			}catch(HibernateException e)
			{
		System.out.println(e);		
			}
			}
		
	}
}
