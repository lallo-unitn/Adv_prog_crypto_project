<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="AuthServlet" method="POST">
    <label for="username"></label>
    <input type="text"
           placeholder="Username"
           name="username"
           id="username"
           required="required">
    <label for="password"></label>
    <input type="password"
           placeholder="Password"
           name="password"
           id="password"
           required="required">
    <input type="submit" value="Login">
    <input type="hidden" value="<%= request.getAttribute("destination")%>">

    <br>
    <br>
    <% if (request.getAttribute("message") != null) { %>
        <%= request.getAttribute("message")%>
    <% } %>
</form>
TESTING CREDENTIALS:
    user1,password1,student
    jane_smith,pass456,teacher
    bob_johnson,secret789,student
    admin,admin,admin
</body>
</html>
