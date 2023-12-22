<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Page</title>
</head>
<body>
<h2>Student Info</h2>
<jsp:useBean id="studentDto"
             scope="request"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto"/>
<ul>
    <li>Name: ${studentDto.userAccount.name}</li>
    <li>Surname: ${studentDto.userAccount.surname}</li>
    <li>Matriculation: ${studentDto.id}</li>
</ul>

<a href="LoginServlet">Logout</a>


</body>
</html>
