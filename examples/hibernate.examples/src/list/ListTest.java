package list;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentList;

import flight.HibernateUtil;

public class ListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ListOwner parent = new ListOwner( "root" );
		ListOwner child = new ListOwner( "c1" );
		parent.getChildren().add( child );
		child.setParent( parent );
		ListOwner otherChild = new ListOwner( "c2" );

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		session.save( parent );
		session.flush();
		// at this point, the list on parent has now been replaced with a PersistentList...
		PersistentList children = ( PersistentList ) parent.getChildren();


		ArrayList otherCollection = new ArrayList();
		otherCollection.add( child );

		otherCollection = new ArrayList();
		otherCollection.add( otherChild );

		children.clear();
		session.delete( child );

		session.flush();

		children.clear();

		session.delete( parent );
		tx.commit();
		session.close();
	}

}
