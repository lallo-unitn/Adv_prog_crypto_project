<%@ page import="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.EnrollmentDto" %>
<%@ page import="java.util.List" %>
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
<h3>Courses</h3>
<ul>

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

<a href="LoginServlet">Logout</a>

</body>
</html>
