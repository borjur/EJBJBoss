<%@ page import='javax.naming.*'%>
<%@ page import='java.io.*'%>
<%@ page import='java.util.*'%>
<%@ page import='simple.stateful.*'%>

<HTML>
<BODY>
<FORM action="index.jsp">
Enter a todo item: <INPUT type="text" name="item" size="20"	value=""><BR>
<INPUT type="submit" name="todo" value="Add Item"> 
<INPUT type="submit" name="todo" value="List Items">
<INPUT type="submit" name="todo" value="Clear list">
</FORM>

<%
	
//INSERT CODE HERE
String action = request.getParameter("todo");
ToDoRemote td = (ToDoRemote) session.getAttribute("todo");
if (td == null) {
    try {
         InitialContext ctx = new InitialContext();
         td = (ToDoRemote) ctx.lookup("ToDo");
         session.setAttribute("todo", td);
    } catch (NamingException e) {
	e.printStackTrace();
    }
}
if (action != null) {
    if (action.equals("Add Item")) {
         String item = request.getParameter("item");
	if(!(item.equals(""))) {
	     td.addItem(item);
	     out.println("Added item " + item);
	}
    } else if (action.equals("List Items")) {
	     Vector<String> items = td.listItems();
	     out.println("Items:");
	     for (int x = 0; x != items.size(); x++) {
	          out.println("<br>" + (x + 1) + ". " + 				items.elementAt(x));
	     }
    } else {
	td.clearItems();
	out.println("Items cleared");
    }
}



%>
</BODY>
</HTML>
