<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.jms.*"%>
<%@ page import="javax.jms.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<h1>Get Owner Info</h1>
	<form action="mdbtest.jsp">
		what is your name? <input type="text" name="name"><BR> <input
			type="submit" name="todo" value="Calculate Total Funds"><BR>
	</form>
	<%String username = request.getParameter("name");
	if (username!=null){
		try {
			Context ctx = new InitialContext(System.getProperties());
			ConnectionFactory factory = (ConnectionFactory) ctx.lookup("java:/ConnectionFactory");
			javax.jms.Queue testQ = (javax.jms.Queue) ctx.lookup("java:jboss/exported/jms/queue/example");
			Connection connect = factory.createConnection();
			Session qsession = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer sender = qsession.createProducer(testQ);
			TextMessage message = qsession.createTextMessage();
			message.setText(username);
			
			sender.send(message);
			out.println("<b> a request to calculate the total funds of the account has eeen made, "+username);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	%>
</body>
</html>