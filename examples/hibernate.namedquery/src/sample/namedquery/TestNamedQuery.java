package sample.namedquery;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class TestNamedQuery {
	public static void main(String[] args) {
		try {
			SessionFactory sessionFactory = new AnnotationConfiguration()
					.configure().buildSessionFactory();
			Session session = sessionFactory.openSession();

			Query query = session.getNamedQuery("findAllEmployees");
			//query.setString("name", "John");
			List<Employee> employees = query.list();
			printEmployees(employees);
			
			query = session.getNamedQuery("findEmployeeByName");
			query.setString("name", "John");
			employees = query.list();
			printEmployees(employees);
			
			query = session.getNamedQuery("findStudentByName");
			query.setString("name", "John");
			List<Student> students = query.list();
			printStudent(students);

			session.close();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static void printEmployees(List<Employee> employees) {
		Iterator<Employee> itr = employees.iterator();
		while (itr.hasNext()) {
			Employee e = itr.next();
			System.out.println(e);
		}
	}
	
	public static void printStudent(List<Student> students) {
		Iterator<Student> itr = students.iterator();
		while (itr.hasNext()) {
			Student e = itr.next();
			System.out.println(e);
		}
	}
}
