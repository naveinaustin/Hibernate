//$Id: OrderByTest.java 10976 2006-12-12 23:22:26Z steve.ebersole@jboss.com $
package ordered;

import java.util.Iterator;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateUtil;

/**
 * @author Gavin King
 */
public class OrderByTest 	
{
	public static void main(String args[]) 
	{
		Search s = new Search("Hibernate");
		s.getSearchResults().add("jboss.com");
		s.getSearchResults().add("hibernate.org");
		s.getSearchResults().add("HiA");
		
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		sess.persist(s);
		sess.flush();
		
		sess.clear();
		s = (Search) sess.createCriteria(Search.class).uniqueResult();
		Iterator iter = s.getSearchResults().iterator();
		System.out.println("**************************");
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		/*assertEquals( iter.next(), "HiA" );
		assertEquals( iter.next(), "hibernate.org" );
		assertEquals( iter.next(), "jboss.com" );
		assertFalse( iter.hasNext() );*/
		
		sess.clear();
		s = (Search) sess.createCriteria(Search.class)
				.setFetchMode("searchResults", FetchMode.JOIN)
				.uniqueResult();
		//assertTrue( Hibernate.isInitialized( s.getSearchResults() ) );
		iter = s.getSearchResults().iterator();
		System.out.println("**************************");
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		/*assertEquals( iter.next(), "HiA" );
		assertEquals( iter.next(), "hibernate.org" );
		assertEquals( iter.next(), "jboss.com" );
		assertFalse( iter.hasNext() );*/
		
		sess.clear();
		s = (Search) sess.createQuery("from Search s left join fetch s.searchResults")
				.uniqueResult();
		//assertTrue( Hibernate.isInitialized( s.getSearchResults() ) );
		iter = s.getSearchResults().iterator();
		System.out.println("**************************");
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		/*assertEquals( iter.next(), "HiA" );
		assertEquals( iter.next(), "hibernate.org" );
		assertEquals( iter.next(), "jboss.com" );
		assertFalse( iter.hasNext() );
		*/
		/*sess.clear();
		s = (Search) sess.createCriteria(Search.class).uniqueResult();
		assertFalse( Hibernate.isInitialized( s.getSearchResults() ) );
		iter = sess.createFilter( s.getSearchResults(), "").iterate();
		assertEquals( iter.next(), "HiA" );
		assertEquals( iter.next(), "hibernate.org" );
		assertEquals( iter.next(), "jboss.com" );
		assertFalse( iter.hasNext() );*/
		
		sess.delete(s);
		tx.commit();
		sess.close();
	}

}

