<html>

<% 
   double freeMem = Runtime.getRuntime().freeMemory();
   double totlMem = Runtime.getRuntime().totalMemory();
   double percent = freeMem/totlMem;
   if (percent < 0.5) { 
%>

<jsp:forward page="one.jsp"/>

<% } else { %>

<jsp:forward page="two.html"/>

<% } %>

</html>

