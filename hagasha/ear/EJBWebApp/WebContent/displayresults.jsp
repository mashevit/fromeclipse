<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import='com.entity.*'%>
<%@page import='java.util.*'%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<h1>Search results</h1>
	<%
		List<BankAccount> results = (List<BankAccount>) request.getAttribute("results");
		if (results == null) {
			BankAccount ba = (BankAccount) request.getAttribute("account");
			out.println(ba + "<br>");
			/* 
			out.println("Account " + ba.getId() + " Owned by " + ba.getOwnerName() + " has a balance of "
					+ ba.getBalance());
			*/
		} else {
			Iterator<BankAccount> i = results.iterator();
			while (i.hasNext()) {
				BankAccount ba = i.next();
				out.println(ba + "<br>");
				/* out.println("Account " + ba.getId() + " Owned by " + ba.getOwnerName() + " has a balance of "
						+ ba.getBalance()+"<br>");
				*/}
		}
	%>
</body>
</html>