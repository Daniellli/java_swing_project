<%-- 
    Document   : index
    Created on : Jun 16, 2019, 4:49:02 PM
    Author     : Nitesh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Batch's Report</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <%
            Object usr = session.getAttribute("User");
                if(usr==null){
                    request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
        %>
        <jsp:include page="/header.jsp"/>
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <sql:query dataSource="${ds}" var="rs" scope="session">
            SELECT Batch.Batch_ID, Batch.Batch_Start, Batch.Batch_Close, Batch.Module_ID, Batch.Teacher_ID, 
            Batch.Batch_Description, Module.Module_Name, Teacher.Teacher_Name, Teacher.Teacher_Gender, 
            Teacher.Teacher_Phone, Module.Module_Description FROM Batch INNER JOIN Module ON 
            Batch.Module_ID = Module.Module_ID INNER JOIN Teacher ON Batch.Teacher_ID = Teacher.Teacher_ID
        </sql:query>
        <div id="mainContent">
            <span id="loginName">Welcome ${sessionScope.User.userName}</span>
            <br><br><br><br><br>
            <center>
                <table style="outline: 1px solid;">
                    <caption style="border: 2px solid #eeeeee;">Batch's Information</caption>
                    <tr>
                        <td>Batch ID</td><td>Batch Start</td><td>Batch Close</td><td>Batch Description</td><td>Module ID</td>
                        <td>Module Name</td><td>Module Description</td><td>Teacher ID</td><td>Teacher Name</td><td>Gender</td>
                    </tr>
                    <c:forEach items="${rs.rows}" var="row">
                        <tr>
                            <td>${row.Batch_ID}</td>
                            <td>${row.Batch_Start}</td>
                            <td>${row.Batch_Close}</td>
                            <td>${row.Batch_Description}</td>
                            <td>${row.Module_ID}</td>
                            <td>${row.Module_Name}</td>
                            <td>${row.Module_Description}</td>
                            <td>${row.Teacher_ID}</td>
                            <td>${row.Teacher_Name}</td>
                            <td>${row.Teacher_Gender}</td>
                            <td><a href="http://localhost:8084/LoginModule/batch/editBatch.jsp?batch_id=${row.Batch_ID}&batch_start=${row.Batch_Start}&module_id=${row.Module_ID}&teacher_id=${row.Teacher_ID}&batch_description=${row.Batch_Description}">Edit</a></td>
                            <td><a href="http://localhost:8084/LoginModule/batch/closeBatch.jsp?batch_id=${row.Batch_ID}">Close</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br><br><br>
                <a href="http://localhost:8084/LoginModule/batch/addBatch.jsp">Add New Batch</a>
                <h3>${param.status}</h3>
            </center>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
