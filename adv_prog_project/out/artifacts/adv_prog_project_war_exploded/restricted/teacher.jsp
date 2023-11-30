<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Dashboard</title>
</head>
<body>
<h2>Teacher Detail</h2>
<jsp:useBean id="teacherDto"
             scope="request"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto"/>
<ul>
    <li>Name: ${teacherDto.userAccount.name}</li>
    <li>Surname: ${teacherDto.userAccount.surname}</li>
    <li>Matriculation: ${teacherDto.id}</li>
</ul>
</body>
</html>
