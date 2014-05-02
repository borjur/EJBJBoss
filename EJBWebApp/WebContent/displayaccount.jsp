<%@ page import='com.entity.*'%>
<h1>Bank Account was updated.</h1>

<% 
BankAccount ba = (BankAccount) request.getAttribute("account");
if(ba==null) {
	String id = (String) request.getAttribute("closed");
	out.println("Account " + id + " was closed");
} else {
	out.println(ba + ".");
}

%>