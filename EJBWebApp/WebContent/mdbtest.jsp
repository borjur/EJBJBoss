<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.bank.*'%>
<%@ page import='com.entity.*'%>
<%@ page import='javax.jms.*' %>
<%@ page import='javax.jms.*' %>

<HTML>
<BODY>
<FORM action="mdbtest.jsp">
<h1>Get Owner Info</h1>
What is your name? <INPUT type="text" name="username" size="20" value=""><BR>
<INPUT type="submit" name="todo" value="Calculate total funds"><br>
</FORM>

<%
	String username = request.getParameter("username");

	
	if(username != null) {
		try {
			Context ctx = new InitialContext(System.getProperties());
			ConnectionFactory factory =(ConnectionFactory)ctx.lookup("XAConnectionFactory");
			javax.jms.Queue testQ = (javax.jms.Queue) ctx.lookup("queue/TestQueue");
			Connection connect = factory.createConnection();      
		    Session qsession = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    
		    MessageProducer sender = qsession.createProducer(testQ);
		    TextMessage message = qsession.createTextMessage();
		    message.setText(username);
		   
		    sender.send(message);
		    
		    out.println("<b>A request to calculate the total funds of the account has been made, " + username);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		
	}

%>
</BODY>
</HTML>
