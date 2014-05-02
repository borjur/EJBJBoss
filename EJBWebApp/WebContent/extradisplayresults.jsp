<%@ page import='com.entity.*'%>
<%@ page import='java.util.*'%>
<h1>Search results</h1>

<% 
List<PhoneNumber> numbers = (List<PhoneNumber>) request.getAttribute("numbers");
if(numbers != null) {
	Iterator<PhoneNumber> i = numbers.iterator();
	while(i.hasNext()) {
		PhoneNumber p = i.next();
		out.println(p + "<br>");
	}
} 
List<BankAccount> accounts = (List<BankAccount>) request.getAttribute("accounts");
if(accounts != null) 
{
	Iterator<BankAccount> i = accounts.iterator();
	while(i.hasNext()) {
		BankAccount ba =  i.next();
		out.println(ba + "<br>");	
	}
}
List<PhoneNumber> phoneNumbers = (List<PhoneNumber>) request.getAttribute("phoneNumbers");
if(phoneNumbers != null) 
{
	Iterator<PhoneNumber> i = phoneNumbers.iterator();
	while(i.hasNext()) {
		PhoneNumber p =  i.next();
		out.println(p + "<br>");
	}
}

%>