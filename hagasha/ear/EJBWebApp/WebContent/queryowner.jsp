<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="javax.naming.*"%>
<%@page import="simple.bank.TellerLocal"%>
<%@page import='com.entity.*'%>
<%@page import='java.util.*' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<form action="queryowner.jsp">
		id: &nbsp; &nbsp;<INPUT type="text" name="id"><br> <INPUT
			type="submit" name="todo" value="Show Bank Accounts Owner"> <br>
	</form>
	<%
		String idStr = request.getParameter("id");

		if (idStr != null) {
			try {
				int id = Integer.parseInt(idStr);
				InitialContext iniCtx = new InitialContext();
				TellerLocal teller = (TellerLocal) iniCtx
						.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");
				Owner owner = teller.findOwner(id);
				out.println("Located owner "+ owner+"<BR>");
				List<PhoneNumber> phoneNumbers=owner.getPhoneNumbers();
				for(PhoneNumber p:phoneNumbers){
					out.println(p+"<br>");
				}
			} catch (NamingException e) {
				// 
				e.printStackTrace();
			}
		}
	%>
</body>
</html>