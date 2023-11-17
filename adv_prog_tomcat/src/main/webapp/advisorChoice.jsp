<%@ page import="it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.DTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advisor Choice</title>
</head>
<body>
<h2>Advisory Choice</h2>
<jsp:useBean id="studentDTO"
             scope="request"
             class="it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.StudentDto"/>
<ul>
    <li>Name: ${studentDTO.name}</li>
    <li>Surname: ${studentDTO.surname}</li>
    <li>Matriculation: ${studentDTO.id}</li>
</ul>
<hr>
<h3>Teachers</h3>
<ul>
    <jsp:useBean id="teacherDTO"
                 scope="request"
                 class="it.unitn.disi.advprog.gennaro.adv_prog_wildfly.DTO.TeacherDto"/>

    <jsp:useBean id="teacherDTOList"
                 type="java.util.List"
                 scope="request"/>

    <% for (TeacherDto t :
            (List<TeacherDto>)teacherDTOList){%>
    <li>
        <%=t.getName() + " "%>  <%=t.getSurname()%>
    </li>
    <%}%>
</ul>
</body>
</html>
