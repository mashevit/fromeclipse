<%@page import="com.entity.exceptions.OwnerNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="simple.bank.TellerLocal"%>
<%@page import="com.entity.*"%>
<%@page import="com.entity.exceptions.*"%>
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
	<form action="updateowner.jsp">
		Enter an Owners Id: <INPUT type="text" name="id"><BR>
		Enter owners phone : <INPUT type="text" name="area"><INPUT
			type="text" name="phone"><BR> <INPUT type="submit"
			name="update">
	</form>
	<%
		String areaStr = request.getParameter("area");
		String idStr = request.getParameter("id");
		if (idStr != null) {
			try {
				String numberStr = request.getParameter("phone");
				PhoneNumber phoneNumber = new PhoneNumber();
				InitialContext iniCtx = new InitialContext();
				TellerLocal teller = (TellerLocal) iniCtx
						.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");
				int areaCode = Integer.parseInt(areaStr);
				int id = Integer.parseInt(idStr);
				int number = Integer.parseInt(numberStr);
				phoneNumber.setAreaCode(areaCode);
				phoneNumber.setNumber(number);
				try {
					Owner o = teller.addNumber(id, phoneNumber);
					out.println("phone number added to " + o);
				} catch (OwnerNotFoundException exc) {
					out.println("no owner found for id " + id);
				}
			} catch (NamingException ex) {
				ex.printStackTrace();
			}
		}
	%>

</body>
</html>