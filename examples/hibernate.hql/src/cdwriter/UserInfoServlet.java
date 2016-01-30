package cdwriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserInfoServlet extends HttpServlet {
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		Session session = null;
		try{
			// This step will read hibernate.cfg.xml and prepare hibernate for use
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			 session =sessionFactory.openSession();
				//Create new instance of Contact and set values in it by reading them from form object
			 	System.out.println("Inserting Record");
				Contact contact = new Contact();
	contact.setId(Integer.parseInt
			(request.getParameter("id")));
	contact.setFirstName(request.getParameter("first"));
contact.setLastName(request.getParameter("last"));
contact.setEmail(request.getParameter("email"));
				session.save(contact);
				out.println("Inserted Successfully");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			// Actual contact insertion will happen at this step
			try
			{
			session.flush();
			session.close();
			}catch(HibernateException e)
			{
		System.out.println(e);		
			}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	}
}
