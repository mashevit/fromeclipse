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

	<form action="createsavingsaccount.jsp">
		Name: &nbsp; &nbsp;<INPUT type="text" name="ownername" size="20"><br>
		Address: &nbsp; &nbsp;<INPUT type="text" name="address" size="20"><br>
		Date of Birth: &nbsp; &nbsp;<INPUT type="text" name="dob" size="20"><br>
		Balance: &nbsp; &nbsp;<INPUT type="text" name="balance"><br>
		Interest Rate<INPUT type="text" name="interestRate" size="20" value=""><br>
		Phone - number: &nbsp; &nbsp;<INPUT type="text" name="areacode" size="10"><INPUT type="text" name="number" size="20"><br>
		<INPUT type="submit" name="todo" value="create"> <br>
	</form>
	<%
		String ownername = request.getParameter("ownername");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String balanceStr = request.getParameter("balance");
		String areaStr=request.getParameter("areacode");
		String numberStr=request.getParameter("number");
		if (ownername != null) {
			try {

				InitialContext iniCtx = new InitialContext();
				TellerLocal teller = (TellerLocal) iniCtx
						.lookup("java:app/EJBWebApp/Teller!simple.bank.TellerLocal");
				String interestRateStr = request.getParameter("interestRate");
				int interestRate=Integer.parseInt(interestRateStr);
				int balance = Integer.parseInt(balanceStr);
				int number=Integer.parseInt(numberStr);
				int areacode= Integer.parseInt(areaStr);
				Owner owner = new Owner();
				owner.setAddress(address);
				owner.setName(ownername);
				owner.setDateOfBirth(dob);
				PhoneNumber phoneNumber= new PhoneNumber();
				phoneNumber.setAreaCode(areacode);
				phoneNumber.setNumber(number);
				owner.addNumber(phoneNumber);
				int id = teller.createSavingsAccount(owner, balance, interestRate);
						out.println("account created it has id: " + id);
			} catch (NamingException ex) {
				ex.printStackTrace();
			}
		}
	%>
</body>
</html>


