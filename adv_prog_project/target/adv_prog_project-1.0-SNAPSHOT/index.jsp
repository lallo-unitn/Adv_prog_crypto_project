<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h2>Student Page</h2>
<form action="${pageContext.request.contextPath}/StudentServlet" method="GET">
    <input type="number"
           required="required"
           name="matriculation">
    <input type="submit" value="Submit">
</form>
<%= request.getAttribute("messageStudent")%>
<br>
<hr>
<h2>Advisor Choice Page</h2>
<form action="${pageContext.request.contextPath}/AdvisorChoiceServlet" method="GET">
    <input type="number"
           required="required"
           name="matriculation">
    <input type="submit" value="Submit">
</form>
<%= request.getAttribute("messageAdvisor")%>
</body>
</html>