<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.bank.*'%>
<%@ page import='com.entity.*'%>

<HTML>
<BODY>
<FORM action="createsavingsaccount.jsp">
<h1>Create A Bank Account</h1>
Name: <INPUT type="text" name="ownername" size="20" value=""><BR>
Address: <INPUT type="text" name="address" size="20" value=""><BR>
Date of Birth: <INPUT type="text" name="dob" size="20" value=""><BR>
Balance: <INPUT type="text" name="balance" size="20" value=""><BR>
Interest Rate: <INPUT type="text" name="interestRate" size="20" value=""><BR>
Phone number : <INPUT type="text" name="areacode" size="3" value="">
<INPUT type="text" name="number" size="7" value=""><br>
<INPUT type="submit" name="todo" value="Add Account"><br>
</FORM>

<%
	String ownername = request.getParameter("ownername");
	String dob = request.getParameter("dob");
	String address = request.getParameter("address");
	String balanceString = request.getParameter("balance");
	
	
	if(ownername != null) {
		String areacodeString = request.getParameter("areacode");
		int areacode = new Integer(areacodeString).intValue();
		String numberString = request.getParameter("number");
		int number = new Integer(numberString).intValue();

		try {
			
		InitialContext ctx = new InitialContext();
		TellerLocal teller = (TellerLocal)ctx.lookup("EJBEARProject/Teller/local");
		int balance = new Integer(balanceString).intValue();
		String interestString = request.getParameter("interestRate");
		int interestRate = new Integer(interestString).intValue();
		Owner owner = new Owner();
		owner.setAddress(address);
		owner.setName(ownername);
		owner.setDateOfBirth(dob);
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setAreaCode(areacode);
		phoneNumber.setNumber(number);
		owner.addNumber(phoneNumber);
		
		int id = teller.createSavingsAccount(owner, balance, 		interestRate);
		
		out.println("Account created.  It has id " + id);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		
	}

%>
</BODY>
</HTML>
