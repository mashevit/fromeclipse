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
		List<PhoneNumber> numbers = (List<PhoneNumber>) request.getAttribute("numbers");
		List<PhoneNumber> phoneNumbers = (List<PhoneNumber>) request.getAttribute("phoneNumbers");
		List<BankAccount> accounts = (List<BankAccount>) request.getAttribute("accounts");

		if (numbers != null) {
			out.println("<h1>phone number results</h1><br>");
			Iterator<PhoneNumber> i = numbers.iterator();
			while (i.hasNext()) {
				PhoneNumber pn = i.next();
				out.println(pn + "<br>");
		}}
			if (phoneNumbers != null) {
				out.println("<h1>telemarketers phone number results</h1><br>");
				Iterator<PhoneNumber> i = phoneNumbers.iterator();
				while (i.hasNext()) {
					PhoneNumber pn = i.next();
					out.println(pn + "<br>");
				
			}}
		if (accounts != null) {
					Iterator<BankAccount> i = accounts.iterator();
					while (i.hasNext()) {
						BankAccount ba = i.next();
						out.println(ba + "<br>");
					}
	
		}
	%>
</body>
</html>