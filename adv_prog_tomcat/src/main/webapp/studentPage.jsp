<%@ page import="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO" %>
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
             class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO"/>
<ul>
    <li>Name: ${studentDTO.name}</li>
    <li>Surname: ${studentDTO.surname}</li>
    <li>Matriculation: ${studentDTO.id}</li>
</ul>
<h3>Courses</h3>
<ul>
<jsp:useBean id="enrollmentDTO"
             scope="request"
             class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO"/>

<jsp:useBean id="enrollmentDTOList"
             type="java.util.List"
             scope="request"/>

<% for (EnrollmentDTO e :
        (List<EnrollmentDTO>)enrollmentDTOList){%>
<li>
    Course name: <%=e.getCourseName()%> Grade: <%=e.getGrade()%>
</li>
<%}%>
</ul>
</body>
</html>
