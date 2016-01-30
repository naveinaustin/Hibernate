package p1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	private static String CONFIG_FILE_LOCATION = "p1/hibernate.cfg.xml";
	private static final ThreadLocal threadLocal = new ThreadLocal();
	private static final Configuration cfg = new Configuration();
	private static SessionFactory sessionFactory;
	
	
	public static Session currentSession() throws HibernateException
	{
		Session session = (Session)threadLocal.get();
		
		if( session == null || !session.isOpen())
		{
			if( null == sessionFactory)
			{
				try
				{
					cfg.configure(CONFIG_FILE_LOCATION);
					sessionFactory = cfg.buildSessionFactory();
				}
				catch(Exception e)
				{
					System.out.println("Error creating session factory " + e);
					e.printStackTrace();
				}
			}
			
			session = (null != sessionFactory) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		
		return session;
	}
	
	public static void closeSession() throws HibernateException
	{
		Session session = (Session)threadLocal.get();
		threadLocal.set(null);
		
		if(session != null)
		{
			session.close();
		}
	}
}
