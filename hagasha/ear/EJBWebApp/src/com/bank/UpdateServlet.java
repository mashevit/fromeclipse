package com.bank;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.BankAccount;

import simple.bank.TellerLocal;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	@EJB(beanName = "Teller")
	TellerLocal teller;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		try {
//			final Properties jndiProps = new Properties();

			// jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			// jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			// jndiProps.put(Context.URL_PKG_PREFIXES, "jboss.naming:org.jnp.interfaces");

			// Context ctx = new InitialContext();

			// teller = (TellerLocal) ctx.lookup("ejb/Teller");

//			InitialContext iniCtx = new InitialContext();
//			 teller = (TellerLocal) iniCtx.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");

			// InitialContext iniCtx = new InitialContext();
			// Context ejbCtx = (Context) iniCtx.lookup("java:comp/env/ejb");
			// teller = (TellerLocal)
			// ejbCtx.lookup("java:app/SimpleEJBProject/Teller!simple.bank.TellerLocal");

			// InitialContext ctx = new InitialContext();
			// TellerLocal teller = (TellerLocal) ctx.lookup("EJBEARProject/Teller/local");

			// teller = (TellerLocal) ejbCtx.lookup("Tellerlocal");
			// teller = (TellerLocal)
			// ctx.lookup("java:app/SimpleEJBProject/Teller!simple.bank.TellerLocal");
			// teller=InitialContext.doLookup("java:app/SimpleEJBProject/Teller!simple.bank.TellerLocal");
//		} catch (NamingException e) {
//			// 
//			e.printStackTrace();
//		}
		// Context ctx;
		// try {
		// ctx = new InitialContext();
		// teller = (TellerLocal) ctx.lookup("ejb/Teller");
		// } catch (NamingException e) {
		// // 
		// e.printStackTrace();
		// }

		String action = request.getParameter("todo");
		String idstring = request.getParameter("id");
		String amountstring = request.getParameter("amount");
		int id = Integer.parseInt(idstring);
		int amount = Integer.parseInt(amountstring);
		BankAccount ba;
		if ("deposit".equals(action)) {
			ba = teller.deposit(id, amount);
			request.setAttribute("account", ba);
		}
		if ("withdraw".equals(action)) {
			ba = teller.withdraw(id, amount);
			request.setAttribute("account", ba);
		}
		if ("close account".equals(action)) {
			teller.closeAccount(id);
			request.setAttribute("closed", "" + id);
		}
		request.getRequestDispatcher("displayaccount.jsp").forward(request, response);
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
