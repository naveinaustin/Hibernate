package set;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentSet;

import com.HibernateUtil;

public class SetTest {

	public static void main(String[] args) 
	{
		Parent parent = new Parent( "p1" );
		Child child = new Child( "c1" );
		parent.getChildren().add( child );
		child.setParent( parent );
		Child otherChild = new Child( "c2" );
		//otherChild.setParent(parent); // for more than one child

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save( parent );
		session.flush();
		// at this point, the set on parent has now been replaced with a PersistentSet...
		PersistentSet children = ( PersistentSet ) parent.getChildren();

		HashSet otherSet = new HashSet();
		otherSet.add( child );

		otherSet = new HashSet();
		otherSet.add( otherChild );
		
		//parent.setChildren(otherSet); // for more than one child
		children.clear();
		session.delete( child );

		session.flush();

		children.clear();

		session.delete( parent );
		tx.commit();
		session.close();
	}

}
