package assign1;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.HibernateUtil;

public class TestProgram {

	public static void main(String[] args) 
	{
		UnionEmployee emp1 = new UnionEmployee("001","name1", new Date(), "address1");
		NonUnionEmployee emp2 = new NonUnionEmployee("002","name2", new Date(), "address2");
		
		Set employees = new HashSet();
		employees.add(emp1);
		employees.add(emp2);
		
		Company company = new Company();
		company.setName("SunGard");
		company.setAddress("Langford Road");
		company.setEmployee(employees);
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.save(company);
		tx.commit();
		HibernateUtil.closeSession();
	}
}
