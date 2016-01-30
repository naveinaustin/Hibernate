package bag;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.collection.PersistentBag;

import com.HibernateUtil;

public class PersistantBagTest
{
	public static void main(String args[])
	{

		BagOwner parent = new BagOwner( "root" );
		BagOwner child = new BagOwner( "c1" );
		parent.getChildren().add( child );
		child.setParent( parent );
		BagOwner otherChild = new BagOwner( "c2" );

		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		session.save( parent );
		session.flush();
		// at this point, the list on parent has now been replaced with a PersistentBag...
		PersistentBag children = ( PersistentBag ) parent.getChildren();

		/*assertFalse( children.remove( otherChild ) );
		assertFalse( children.isDirty() );*/

		ArrayList otherCollection = new ArrayList();
		otherCollection.add( child );
		/*assertFalse( children.retainAll( otherCollection ) );
		assertFalse( children.isDirty() );
*/
		otherCollection = new ArrayList();
		otherCollection.add( otherChild );
		/*assertFalse( children.removeAll( otherCollection ) );
		assertFalse( children.isDirty() );*/

		children.clear();
		session.delete( child );
		//assertTrue( children.isDirty() );

		session.flush();

		children.clear();
		//assertFalse( children.isDirty() );

		session.delete( parent );
		session.getTransaction().commit();
		session.close();
	
	}
}
