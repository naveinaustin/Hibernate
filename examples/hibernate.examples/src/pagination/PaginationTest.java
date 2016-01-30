package pagination;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.HibernateUtil;

/**
 * @author Gavin King
 */
public class PaginationTest
{
	
	public static void main(String args[])
	{
		Session s = HibernateUtil.currentSession();
		Transaction t = s.beginTransaction();		
		/*for ( int i=0; i<10; i++ ) {
			DataPoint dp = new DataPoint();
			dp.setX( new BigDecimal(i * 0.1d).setScale(19, BigDecimal.ROUND_DOWN) );
			dp.setY( new BigDecimal( Math.cos( dp.getX().doubleValue() ) ).setScale(19, BigDecimal.ROUND_DOWN) );
			s.persist(dp);
		}
		t.commit();
		s.close();*/
		
		s = HibernateUtil.currentSession();
		t = s.beginTransaction();
		int size = s.createSQLQuery("select id, xval, yval, description from DataPoint order by xval, yval")
			.addEntity(DataPoint.class)
			.setMaxResults(5)
			.list().size();
		System.out.println("Size: " + size);
		size = s.createQuery("from DataPoint order by x, y")
			.setFirstResult(5)
			.setMaxResults(2)
			.list().size();
		System.out.println("Size: " + size);
		size = s.createCriteria(DataPoint.class)
			.addOrder( Order.asc("x") )
			.addOrder( Order.asc("y") )
			.setFirstResult(8)
			.list().size();
		System.out.println("Size: " + size);
		t.commit();
		s.close();
	}
}

