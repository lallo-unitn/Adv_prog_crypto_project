<%@ page import="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advisor Choice</title>
</head>
<body>
<h2>Advisory Choice</h2>
<jsp:useBean id="studentDto"
             scope="request"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.StudentDto"/>
<ul>
    <li>Name: ${studentDto.userAccount.name}</li>
    <li>Surname: ${studentDto.userAccount.surname}</li>
    <li>Matriculation: ${studentDto.id}</li>
</ul>
<hr>
<h3>Teachers</h3>
<ul>

    <jsp:useBean id="teacherDtoList"
                 type="java.util.List"
                 scope="request"/>

    <% for (TeacherDto t :
            (List<TeacherDto>) teacherDtoList) {%>
    <li>
        <%=t.getUserAccount().getName() + " "%>  <%=t.getUserAccount().getSurname()%>
    </li>
    <%}%>
</ul>
</body>
</html>
