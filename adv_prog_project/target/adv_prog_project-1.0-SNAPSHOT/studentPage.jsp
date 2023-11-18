<%@ page import="it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.EnrollmentDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Page</title>
</head>
<body>
<h2>Student Info</h2>
<jsp:useBean id="studentDTO"
             scope="request"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.StudentDto"/>
<ul>
    <li>Name: ${studentDTO.name}</li>
    <li>Surname: ${studentDTO.surname}</li>
    <li>Matriculation: ${studentDTO.id}</li>
</ul>
<h3>Courses</h3>
<ul>
    <jsp:useBean id="enrollmentDto"
                 scope="request"
                 class="it.unitn.disi.advprog.gennaro.adv_prog_project.DTO.EnrollmentDto"/>

    <jsp:useBean id="enrollmentDtoList"
                 type="java.util.List"
                 scope="request"/>

    <% for (EnrollmentDto e :
            (List<EnrollmentDto>) enrollmentDtoList) {%>
    <li>
        Course name: <%=e.getCourseName()%> Grade: <%=e.getGrade()%>
    </li>
    <%}%>
</ul>
</body>
</html>
