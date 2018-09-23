package com.bank;

import java.io.IOException;
import java.util.List;

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
import com.entity.SavingsAccount;

import simple.bank.TellerLocal;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName = "Teller")
	TellerLocal teller;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
//		try {
			// final Properties jndiProps = new Properties();

			// jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

			// Context ctx = new InitialContext();
			// teller = (TellerLocal)
			// ctx.lookup("ejb:EJBEARProject/SimpleEJBProject/Teller!simple.bank.TellerLocal");

//			InitialContext iniCtx = new InitialContext();
//			 teller = (TellerLocal) iniCtx.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");

			// InitialContext iniCtx = new InitialContext();
			// Context ejbCtx = (Context) iniCtx.lookup("java:comp/env/ejb");
			// teller = (TellerLocal)
			// ejbCtx.lookup("java:app/SimpleEJBProject/Teller!simple.bank.TellerLocal");
			// InitialContext ctx = new InitialContext();
			// TellerLocal teller = (TellerLocal) ctx.lookup("EJBEARProject/Teller/local");
//		} catch (NamingException e) {
//			// 
//			e.printStackTrace();
//		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("todo");
		if ("ID Search".equals(action)) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			BankAccount ba = teller.findAccount(id);
			request.setAttribute("account", ba);
			request.setAttribute("results", null);
		}
		if ("Balance Search".equals(action)) {
			String amountString = request.getParameter("amount");
			int amount = Integer.parseInt(amountString);
			List<BankAccount> results = teller.findWithBalance(amount);
			request.setAttribute("results", results);
		}
		if ("List All Accounts".equals(action)) {
			List<BankAccount> results = teller.listAllAccounts();
			request.setAttribute("results", results);
		}
		if ("List All Savings Accounts".equals(action)) {
			List<SavingsAccount> results = teller.listAllsAvingAccounts();
			request.setAttribute("results", results);
		}
		request.getRequestDispatcher("displayresults.jsp").forward(request, response);
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
