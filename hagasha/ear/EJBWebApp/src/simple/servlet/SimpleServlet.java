package simple.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple.stateless.GreetingRemote;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	/*	try {
			final Properties jndiProps = new Properties();
			 // jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			//  jndiProps.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
			  // username
			//  jndiProps.put(Context.SECURITY_PRINCIPAL, "au");
			  // password
			//  jndiProps.put(Context.SECURITY_CREDENTIALS, "1234qw@1");
			jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			// create the context
		//	final Context context = new InitialContext(jndiProperties);
			Context ctx = new InitialContext(jndiProps);
			GreetingRemote greeting = (GreetingRemote) ctx.lookup("ejb:EJBEARProject/SimpleEJBProject//Greeting!simple.stateless.GreetingRemote");
			String greet = greeting.sayHello();
			out.println("<br> The EJB said " + greet);
		} catch (NamingException e) {
			// 
			e.printStackTrace();
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
