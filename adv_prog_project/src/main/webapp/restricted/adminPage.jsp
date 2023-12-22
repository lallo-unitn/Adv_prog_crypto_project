<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Add Student</h2>

<form action="${pageContext.request.contextPath}/AddStudentServlet" method="POST">
    <label>
        <input type="text"
               required="required"
               name="newUsername"
               placeholder="Username">
    </label>
    <br>
    <label>
        <input type="text"
               required="required"
               name="newPassword"
               placeholder="Password">
    </label>
    <br>
    <label>
        <input type="text"
               required="required"
               name="newName"
               placeholder="First Name">
    </label>
    <br>
    <label>
        <input type="text"
               required="required"
               name="newSurname"
               placeholder="Surname">
    </label>
    <br>
    <input type="submit" value="Submit">
</form>

<%if (request.getAttribute("message") != null) {%>
    <%=request.getAttribute("message")%>
<%}%>
<br>

<a href="LoginServlet">Logout</a>


</body>
</html>
