package iterate;

import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateUtil;

/**
 * @author Gavin King
 */
public class IterateTest 
{

	public void testIterate() throws Exception {
		//getSessions().getStatistics().clear();
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Item i1 = new Item("foo");
		Item i2 = new Item("bar");
		s.persist("Item", i1);
		s.persist("Item", i2);
		t.commit();
		s.close();

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		Iterator iter = s.getNamedQuery("Item.nameDesc").iterate();
		i1 = (Item) iter.next();
		i2 = (Item) iter.next();
		
		i1.getName();
		i2.getName();
		
		Hibernate.initialize(i1);
		
		s.delete(i1);
		s.delete(i2);
		t.commit();
		s.close();
		
	}
	
	public void testScroll() throws Exception {
	
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Item i1 = new Item("foo");
		Item i2 = new Item("bar");
		s.persist("Item", i1);
		s.persist("Item", i2);
		t.commit();
		s.close();

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		ScrollableResults sr = s.getNamedQuery("Item.nameDesc").scroll();
		
		i1 = (Item) sr.get(0);
		
		i2 = (Item) sr.get(0);
		s.delete(i1);
		s.delete(i2);
		t.commit();
		s.close();
	}
}

