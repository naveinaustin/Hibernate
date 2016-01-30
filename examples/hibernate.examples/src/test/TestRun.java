package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class TestRun
{
	public static void main(String[] args)
	{
		/*Community community = new Community();
		community.setId(1L);
		community.setName("test");
		community.setDescription("");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save( community );
		tx.commit();
		session.close();
		session.flush();*/
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Community tempCommunity = (Community)session.get(Community.class, 1L);
		System.out.println("Id: " + tempCommunity.getId() + " Name: " + tempCommunity.getName() + " Desc: " + tempCommunity.getDescription());
		
		tx.commit();
		session.close();
		//session.flush();
	}
	
	private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new AnnotationConfiguration()
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession()
            throws HibernateException {
        return sessionFactory.openSession();
    }


}
