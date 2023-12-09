<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Dashboard</title>
</head>
<body onLoad="init()">
<h2>Teacher Detail</h2>
<jsp:useBean id="teacherDto"
             scope="session"
             class="it.unitn.disi.advprog.gennaro.adv_prog_project.dto.TeacherDto"/>
<ul>
    <li>Name: ${teacherDto.userAccount.name}</li>
    <li>Surname: ${teacherDto.userAccount.surname}</li>
    <li>Id: ${teacherDto.id}</li>
</ul>

<label for="inputfile"></label>
<input type="file" id="inputfile" placeholder="Enter item...">

<br>
<br>

<%--<button onclick="add()">Add Item <i class="fa-solid fa-plus"></i></button>
<button onclick="del()">Clear all <i class="fa-solid fa-ban"></i></button>--%>
<div id="output"></div>
<div id="outputKey"></div>
<br>
<br>

<form action="AssignGradeServlet" method="post">
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
<script src="js/signature_functions.js" defer></script>
</body>
</html>
