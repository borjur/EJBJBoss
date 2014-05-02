<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.bank.*'%>
<%@ page import='com.entity.*'%>

<HTML>
<BODY>
<FORM action="createaccountv2.jsp">
<h1>Create A Bank Account</h1>
Name: <INPUT type="text" name="ownername" size="20" value=""><BR>
Address: <INPUT type="text" name="address" size="20" value=""><BR>
Date of Birth: <INPUT type="text" name="dob" size="20" value=""><BR>
Balance: <INPUT type="text" name="balance" size="20" value=""><BR>
<INPUT type="submit" name="todo" value="Add Account"><br>
</FORM>

<%
	String ownername = request.getParameter("ownername");
	String dob = request.getParameter("dob");
	String address = request.getParameter("address");
	String balanceString = request.getParameter("balance");
	
	
	if(ownername != null) {
		try {
			
		InitialContext ctx = new InitialContext();
		TellerLocal teller = (TellerLocal) 
			ctx.lookup("EJBEARProject/Teller/local");
		int balance = new Integer(balanceString).intValue();
		Owner owner = new Owner();
		owner.setAddress(address);
		owner.setName(ownername);
		owner.setDateOfBirth(dob);
		int id = teller.createAccount(owner, balance);
		out.println("Account created.  It has id " + id);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		
	}

%>
</BODY>
</HTML>
