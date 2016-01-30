package p1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestHoney {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Honey honey = new Honey();
		honey.setName("Forest Honey");
		honey.setTaste("Good");
		
		createHoney(honey);
	}

	private static void createHoney(Honey honey)
	{
		Transaction tx = null;
		Session session = HibernateSessionFactory.currentSession();
		try 
		{
			tx = session.beginTransaction();
			session.save(honey);
			tx.commit();
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			tx.rollback();
		}
	}
}
