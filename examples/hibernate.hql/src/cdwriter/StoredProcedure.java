package cdwriter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.PreparedStatement;
import java.util.*;
public class StoredProcedure {
	public static void main(String[] args) {
		Session session = null;
		Transaction tx= null;
		try {
			
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			//Simple Stored procedure Example
			tx = session.beginTransaction();
			PreparedStatement st = session.connection().prepareStatement("{call procedure_test(?,?)}");
					    
					    st.setString(1, "12");
					    st.setString(2, "34");
					    st.execute();
					    tx.commit();
			session.close();
		} catch (Exception e) {
			try{
				tx.rollback();
			}
			catch(Exception e1)
			{
				System.out.println(e);
			}
			System.out.println(e.getMessage());
		} finally {
			System.out.println("End of Demo ");
		}
	}
}
