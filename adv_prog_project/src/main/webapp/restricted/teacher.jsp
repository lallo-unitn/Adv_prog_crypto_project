<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Dashboard</title>
</head>
<body>
<h2>Teacher Detail</h2>
<jsp:useBean id="teacherDto"
             scope="session"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto"/>
<ul>
    <li>Name: ${teacherDto.userAccount.name}</li>
    <li>Surname: ${teacherDto.userAccount.surname}</li>
    <li>Id: ${teacherDto.id}</li>
</ul>
<form action="TeacherServlet" method="post">
    <label for="studentId"></label>
    <input type="text"
           placeholder="studentId"
           name="studentId"
           id="studentId"
           required="required">
    <label for="grade"></label>
    <input type="number"
           placeholder="grade"
           name="grade"
           id="grade"
           required="required">
    <input type="submit" value="Submit">
</form>
</body>
</html>
