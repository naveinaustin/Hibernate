package basic;

import java.util.Date;
import java.util.List;

import list.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;

/**
 * @author Gavin King
 */
public class ComponentTest 
{
	public static void main(String args[])
	{
		System.out.println("********************** USER **********************");
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin", "secret", new Person("Gavin King", new Date(), "Karbarook Ave") );
		s.persist(u);
		s.flush();
		u.getPerson().changeAddress("Phipps Place");
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "gavin");
		u.setPassword("$ecret");
		
		t.commit();
		s.close();

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "gavin");
		s.delete(u);
		t.commit();
		s.close();
		System.out.println("********************** USER **********************\n");
		
		System.out.println("********************** EMP **********************");
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		Employee emp = new Employee();
		emp.setHireDate( new Date() );
		emp.setPerson( new Person() );
		emp.getPerson().setName( "steve" );
		emp.getPerson().setDob( new Date() );
		s.save( emp );

		s.createQuery( "from Employee e where e.person = :p and 1 = 1 and 2=2" ).setParameter( "p", emp.getPerson() ).list();
		s.createQuery( "from Employee e where :p = e.person" ).setParameter( "p", emp.getPerson() ).list();
		List list = s.createQuery( "from Employee e where e.person = ('steve', current_timestamp)" ).list();
		Employee emp1 = (Employee)list.get(0);
		System.out.println(emp1.getPerson().getAddress());

		s.delete( emp );
		t.commit();
		s.close();
		
		System.out.println("********************** EMP **********************");
	}
	
	public void testUpdateFalse() {
		//getSessions().getStatistics().clear();
		
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin", "secret", new Person("Gavin King", new Date(), "Karbarook Ave") );
		s.persist(u);
		s.flush();
		u.getPerson().setName("XXXXYYYYY");
		t.commit();
		s.close();
		
		/*assertEquals( 1, s.getStatistics().getEntityInsertCount() );
		assertEquals( 0, s.getStatistics().getEntityUpdateCount() );*/

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "gavin");
		//assertEquals( u.getPerson().getName(), "Gavin King" );
		s.delete(u);
		t.commit();
		s.close();
		
		//assertEquals( 1, getSessions().getStatistics().getEntityDeleteCount() );
	}
	
	public void testComponent() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		User u = new User( "gavin", "secret", new Person("Gavin King", new Date(), "Karbarook Ave") );
		s.persist(u);
		s.flush();
		u.getPerson().changeAddress("Phipps Place");
		t.commit();
		s.close();
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "gavin");
		/*assertEquals( u.getPerson().getAddress(), "Phipps Place" );
		assertEquals( u.getPerson().getPreviousAddress(), "Karbarook Ave" );
		assertEquals( u.getPerson().getYob(), u.getPerson().getDob().getYear()+1900 );*/
		u.setPassword("$ecret");
		t.commit();
		s.close();

		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "gavin");
		/*assertEquals( u.getPerson().getAddress(), "Phipps Place" );
		assertEquals( u.getPerson().getPreviousAddress(), "Karbarook Ave" );
		assertEquals( u.getPassword(), "$ecret" );*/
		s.delete(u);
		t.commit();
		s.close();
	}

	public void testComponentStateChangeAndDirtiness() {
		// test for HHH-2366
		Session s = HibernateUtil.currentSession();
		s.beginTransaction();
		User u = new User( "steve", "hibernater", new Person( "Steve Ebersole", new Date(), "Main St") );
		s.persist( u );
		s.flush();
		//long intialUpdateCount = sfi().getStatistics().getEntityUpdateCount();
		u.getPerson().setAddress( "Austin" );
		s.flush();
		//assertEquals( intialUpdateCount + 1, sfi().getStatistics().getEntityUpdateCount() );
		//intialUpdateCount = sfi().getStatistics().getEntityUpdateCount();
		u.getPerson().setAddress( "Cedar Park" );
		s.flush();
		//assertEquals( intialUpdateCount + 1, sfi().getStatistics().getEntityUpdateCount() );
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}

	public void testComponentQueries() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		Employee emp = new Employee();
		emp.setHireDate( new Date() );
		emp.setPerson( new Person() );
		emp.getPerson().setName( "steve" );
		emp.getPerson().setDob( new Date() );
		s.save( emp );

		s.createQuery( "from Employee e where e.person = :p and 1 = 1 and 2=2" ).setParameter( "p", emp.getPerson() ).list();
		s.createQuery( "from Employee e where :p = e.person" ).setParameter( "p", emp.getPerson() ).list();
		s.createQuery( "from Employee e where e.person = ('steve', current_timestamp)" ).list();

		s.delete( emp );
		t.commit();
		s.close();
	}
	
	public void testComponentFormulaQuery() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		s.createQuery("from User u where u.person.yob = 1999").list();
		s.createCriteria(User.class)
			.add( Property.forName("person.yob").between( new Integer(1999), new Integer(2002) ) )
			.list();
		/*if ( getDialect().supportsRowValueConstructorSyntax() ) {
			s.createQuery("from User u where u.person = ('gavin', :dob, 'Peachtree Rd', 'Karbarook Ave', 1974, 'Peachtree Rd')")
				.setDate("dob", new Date("March 25, 1974")).list();
			s.createQuery("from User where person = ('gavin', :dob, 'Peachtree Rd', 'Karbarook Ave', 1974, 'Peachtree Rd')")
				.setDate("dob", new Date("March 25, 1974")).list();
		}*/
		t.commit();
		s.close();
	}

	public void testNamedQuery() {
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();
		s.getNamedQuery("userNameIn")
			.setParameterList( "nameList", new Object[] {"1ovthafew", "turin", "xam"} )
			.list();
		t.commit();
		s.close();
	}

}

