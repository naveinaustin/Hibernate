package sample.namedquery;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class ManageStudent {
	   private static SessionFactory factory; 
	   public static void main(String[] args) {
	      try{
	         factory = new AnnotationConfiguration().
	                   configure().
	                   buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      ManageStudent MS = new ManageStudent();

	      /* Add few student records in database */
	      Integer empID1 = MS.addStudent("Zara", "Ali");
	      Integer empID2 = MS.addStudent("Daisy", "Das");
	      Integer empID3 = MS.addStudent("John", "Paul");

	      /* List down all the students */
	      MS.listStudents();

	      /* Update student's records */
	      MS.updateStudent(empID1);

	      /* Delete an student from the database */
	      MS.deleteStudent(empID2);

	      /* List down new list of the students */
	      MS.listStudents();
	   }
	   /* Method to CREATE an student in the database */
	   public Integer addStudent(String fname, String lname){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer studentID = null;
	      try{
	         tx = session.beginTransaction();
	         Student student = new Student();
	         student.setFirstName(fname);
	         student.setLastName(lname);
	         studentID = (Integer) session.save(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return studentID;
	   }
	   /* Method to  READ all the students */
	   public void listStudents( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List students = session.createQuery("FROM Student").list(); 
	         for (Iterator iterator = 
	                           students.iterator(); iterator.hasNext();){
	            Student student = (Student) iterator.next(); 
	            System.out.print("First Name: " + student.getFirstName()); 
	            System.out.print("  Last Name: " + student.getLastName()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to UPDATE salary for an student */
	   public void updateStudent(Integer StudentID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Student student = 
	                    (Student)session.get(Student.class, StudentID); 
			 session.update(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to DELETE an student from the records */
	   public void deleteStudent(Integer StudentID){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Student student = 
	                   (Student)session.get(Student.class, StudentID); 
	         session.delete(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	}
