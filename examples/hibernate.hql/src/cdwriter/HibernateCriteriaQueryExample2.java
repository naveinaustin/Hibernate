package cdwriter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;



import java.util.*;
public class HibernateCriteriaQueryExample2 {
	public static void main(String[] args) {
		Session session = null;
		try {
			
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
			session = sessionFactory.openSession();
			//Criteria Query Example
			Criteria crit = session.createCriteria(Insurance.class);			
			crit.add(Restrictions.like("insuranceName", "%a%")); //Like condition
			crit.setMaxResults(5); //Restricts the max rows to 5

			List insurances = crit.list();
			for(Iterator it = insurances.iterator();it.hasNext();){
				Insurance insurance = (Insurance) it.next();
				System.out.println("ID: " + insurance.getLngInsuranceId());
				System.out.println("Name: " + insurance.getInsuranceName());
				
			}
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
		}		
	}
}
