<%@ page import='com.entity.exceptions.*'%>
<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.bank.*'%>
<%@ page import='com.entity.*'%>

<HTML>
<BODY>
<FORM action="updateowner.jsp">Enter an owner's ID: <INPUT
	type="text" name="id" size="20" value=""><BR>
Enter a number to add: <INPUT type="text" name="areacode" size="3"
	value=""> <INPUT type="text" name="number" size="7" value=""><br>
<INPUT type="submit" name="todo" value="Add Number"> <br>
</FORM>

<%
	String idString = request.getParameter("id");
	if (idString != null) {
		TellerLocal teller = null;
		int id = new Integer(idString).intValue();
		String areacodeString = request.getParameter("areacode");
		int areacode = new Integer(areacodeString).intValue();
		String numberString = request.getParameter("number");
		int number = new Integer(numberString).intValue();

		try {
			//We really should lookup the teller only once and cache it
			//on the session.  This exercise is left to the student
			InitialContext ctx = new InitialContext();
			teller = (TellerLocal) ctx
					.lookup("EJBEARProject/Teller/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setAreaCode(areacode);
		phoneNumber.setNumber(number);
		try {
			Owner o = teller.addNumber(id, phoneNumber);
			out.println("Phone number added to " + o);
		} catch (OwnerNotFoundException ex) {
			out.println("No owner found for id " + id);
		}
	}
%>




</BODY>
</HTML>
