package version;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateUtil;

public class VersionTest 
{
	public static void main(String args[])
	{
		testCollectionVersion();
		
		testVersionShortCircuitFlush();
		
		testCollectionNoVersion();
	}
	
	public static void testVersionShortCircuitFlush() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Person gavin = new Person("Gavin");
		new Thing("Passport", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		Thing passp = (Thing) s.get(Thing.class, "Passport");
		passp.setLongDescription("blah blah blah");
		s.createQuery("from Person").list();
		s.createQuery("from Person").list();
		s.createQuery("from Person").list();
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		s.createQuery("delete from Thing").executeUpdate();
		s.createQuery("delete from Person").executeUpdate();
		t.commit();
		s.close();
	}
	
	public static void testCollectionVersion() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Person gavin = new Person("gavin");
		new Thing("Passport", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		new Thing("Laptop", gavin);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		gavin.getThings().clear();
		t.commit();
		s.close();
		

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		s.delete(gavin);
		t.commit();
		s.close();
	}
	
	public static void testCollectionNoVersion() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Person gavin = new Person("Gavin");
		new Task("Code", gavin);
		s.persist(gavin);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		new Task("Document", gavin);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		gavin = (Person) s.createCriteria(Person.class).uniqueResult();
		gavin.getTasks().clear();
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		s.delete(gavin);
		t.commit();
		s.close();
	}

}

