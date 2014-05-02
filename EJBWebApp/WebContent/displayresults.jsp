<%@ page import='com.entity.*'%>
<%@ page import='java.util.*'%>
<h1>Search results</h1>

<% 
List<BankAccount> results = (List<BankAccount>) request.getAttribute("results");
if(results==null) {
	BankAccount ba = (BankAccount) request.getAttribute("account");
	out.println(ba + "<br>");
} else {
	Iterator<BankAccount> i = results.iterator();
	while(i.hasNext()) {
		BankAccount ba = i.next();
		out.println(ba + "<br>");		
	}
}

%>