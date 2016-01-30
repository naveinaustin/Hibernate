//$Id: IdClassTest.java 10976 2006-12-12 23:22:26Z steve.ebersole@jboss.com $
package idclass;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateUtil;

/**
 * @author Gavin King
 */
public class IdClassTest 
{
	public static void main(String args[]) {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Customer cust = new FavoriteCustomer("JBoss", "RouteOne", "Detroit");
		s.persist(cust);
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		CustomerId custId = new CustomerId("JBoss", "RouteOne");
		t = s.beginTransaction();
		cust = (Customer) s.get(Customer.class, custId);
		/*assertEquals( "Detroit", cust.getAddress() );
		assertEquals( cust.getCustomerName(), custId.getCustomerName() );
		assertEquals( cust.getOrgName(), custId.getOrgName() );*/
		t.commit();
		HibernateUtil.closeSession();		

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		cust = (Customer) s.createQuery("from Customer where id.customerName = 'RouteOne'").uniqueResult();
		/*assertEquals( "Detroit", cust.getAddress() );
		assertEquals( cust.getCustomerName(), custId.getCustomerName() );
		assertEquals( cust.getOrgName(), custId.getOrgName() );*/
		t.commit();
		HibernateUtil.closeSession();		

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		cust = (Customer) s.createQuery("from Customer where customerName = 'RouteOne'").uniqueResult();
		/*assertEquals( "Detroit", cust.getAddress() );
		assertEquals( cust.getCustomerName(), custId.getCustomerName() );
		assertEquals( cust.getOrgName(), custId.getOrgName() );*/
		
		s.createQuery( "delete from Customer" ).executeUpdate();
		
		t.commit();
		HibernateUtil.closeSession();		
	}

}

