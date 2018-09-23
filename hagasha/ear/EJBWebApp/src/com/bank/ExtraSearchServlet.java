package com.bank;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.BankAccount;
import com.entity.PhoneNumber;

import simple.bank.TellerLocal;

/**
 * Servlet implementation class ExtraSearchServlet
 */
@WebServlet("/ExtraSearchServlet")
public class ExtraSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(beanName = "Teller")
	TellerLocal teller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtraSearchServlet() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		try {
//			InitialContext iniCtx = new InitialContext();
//			 teller = (TellerLocal) iniCtx.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");
//		} catch (NamingException e) {
//			// 
//			e.printStackTrace();
//		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("todo");
		if ("Phone Number Search".equals(action)) {
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			List<PhoneNumber> numbers = teller.findNumbersForOwner(id);
			request.setAttribute("numbers",numbers); 
		}
		if ("Account Search".equals(action)) {
			String areaCodeString = request.getParameter("areacode");
			int areacode = Integer.parseInt(areaCodeString);
			List<BankAccount> accounts = teller.findAccountsForAreaCode(areacode);
			request.setAttribute("accounts", accounts);
		}
		if ("Telemarketers Click Here".equals(action)) {
			String amountString=request.getParameter("amount");
			int amount= Integer.parseInt(amountString);
			List<PhoneNumber> phoneNumbers = teller.findNumbersForAmount(amount);
			request.setAttribute("phoneNumbers",phoneNumbers);
		}
		request.getRequestDispatcher("extradisplayresults.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
