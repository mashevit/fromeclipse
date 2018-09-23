<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="com.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<h1>Bank Account was updated</h1>
	<%
		BankAccount ba = (BankAccount) request.getAttribute("account");
		if (ba == null) {
			String id = (String) request.getAttribute("closed");
			out.println("Account " + id + " was closed");
		} else {
			out.println(ba + ".");
			/* 		out.println(
							"account " + ba.getId() + " owned by " + ba.getOwnerName() + " has balance " + ba.getBalance());
				 */}
	%>
</body>
</html>