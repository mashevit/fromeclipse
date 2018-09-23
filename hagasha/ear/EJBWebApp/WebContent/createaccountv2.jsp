<%@page import="simple.bank.TellerLocal"%>
<%@page import="com.entity.*"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="javax.naming.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="simple.stateful.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<h1>Create A Bank Account</h1>
	<br>

	<form action="createaccountv2.jsp">
		Name: &nbsp; &nbsp;<INPUT type="text" name="ownername" size="20"><br>
		Address: &nbsp; &nbsp;<INPUT type="text" name="address" size="20"><br>
		Date of Birth: &nbsp; &nbsp;<INPUT type="text" name="dob" size="20"><br>
		Balance: &nbsp; &nbsp;<INPUT type="text" name="balance"><br>
		<INPUT type="submit" name="todo" value="create"> <br>
	</form>
	<%
		String ownername = request.getParameter("ownername");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String balanceStr = request.getParameter("balance");
		if (ownername != null) {
			try {
				final Properties jndiProps = new Properties();

				//jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
				/* 	
					InitialContext ctx = new InitialContext();
					TellerLocal teller = (TellerLocal) ctx.lookup("EJBEARProject/Teller/local"); 
					 */
				//Context ejbCtx = (Context) iniCtx.lookup("java:comp/env/ejb");

				InitialContext iniCtx = new InitialContext();
				TellerLocal teller = (TellerLocal) iniCtx
						.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");

				int balance = Integer.parseInt(balanceStr);
				Owner owner = new Owner();
				owner.setAddress(address);
				owner.setName(ownername);
				owner.setDateOfBirth(dob);
				int id = teller.createAccount(owner, balance);
				out.println("account created it has id: " + id);
			} catch (NamingException ex) {
				ex.printStackTrace();
			}
		}
	%>
</body>
</html>

<%-- 	<form action="createaccount.jsp">
		Enter a to do item: &nbsp; &nbsp;<INPUT type="text" name="item"><br>
		<INPUT type="submit" name="todo" value="AddItem"> <INPUT
			type="submit" name="todo" value="ListItems"> <INPUT
			type="submit" name="todo" value="ClearList">
			<br>
		<%
			String action = request.getParameter("todo");
			ToDoRemote td = (ToDoRemote) session.getAttribute("todo");
			if (td == null) {
				try {
					final Properties jndiProps = new Properties();

					jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
					InitialContext ctx = new InitialContext(jndiProps);
					td = (ToDoRemote) ctx.lookup("ejb:EJBEARProject/SimpleEJBProject/ToDo!simple.stateful.ToDoRemote?stateful");
					session.setAttribute("todo", td);
				} catch (NamingException ex) {
					ex.printStackTrace();
				}
			}
			if (action != null) {
				if (action.equals("AddItem")) {
					String item = request.getParameter("item");
					if (!item.equals("")) {
						td.addItem(item);
						out.println("item " + item + " added");
					}
				} else if (action.equals("ListItems")) {
					Vector<String> items = td.listItems();
					out.println("items: ");
					for (int x = 0; x != items.size(); x++) {
						out.println("<br>" + (x + 1) + ". " + items.elementAt(x));
					}
				} else {
					td.clearItems();
					out.println("items Cleared");
				}
			}
		%>
	</form> --%>
