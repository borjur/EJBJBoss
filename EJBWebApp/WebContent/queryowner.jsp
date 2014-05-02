<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.bank.*'%>
<%@ page import='com.entity.*'%>

<HTML>
<BODY>
<FORM action="queryowner.jsp">
<h1>Get Owner Info</h1>
Bank account ID: <INPUT type="text" name="id" size="20" value=""><BR>
<INPUT type="submit" name="todo" value="Show Bank Account's Owner"><br>
</FORM>

<%
	String idString = request.getParameter("id");

	
	if(idString != null) {
		try {
			int id = new Integer(idString).intValue();
			
			InitialContext ctx = new InitialContext();
			TellerLocal teller = (TellerLocal)ctx.lookup("EJBEARProject/Teller/local");
			Owner owner = teller.findOwner(id);
			out.println("Located owner " + owner + "<br>");
			
			List<PhoneNumber> phoneNumbers = owner.getPhoneNumbers();
			
			for (PhoneNumber p: phoneNumbers) {
				out.println(p + "<br>");
			}		
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

		
	}

%>
</BODY>
</HTML>
