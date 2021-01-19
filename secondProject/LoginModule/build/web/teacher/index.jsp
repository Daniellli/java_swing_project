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
        <title>All Teacher's Report</title>
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
        <div id="mainContent">
            <span id="loginName">Welcome ${sessionScope.User.userName}</span>
            <br><br><br><br><br>
            <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                                url="jdbc:sqlserver://localhost;databaseName=master"
                                user="sa"
                                password="niit1234" 
                                var="ds" 
                                scope="session" />
            <sql:query dataSource="${ds}" var="rs" scope="session">
                select * from Teacher
            </sql:query>
            <center>
                <table style="outline: 1px solid;">
                    <caption style="border: 2px solid #eeeeee;">Teacher's Information</caption>
                    <c:forEach items="${rs.rows}" var="row">
                        <tr>
                            <td>${row.Teacher_ID}</td>
                            <td>${row.Teacher_Name}</td>
                            <td>${row.Teacher_Gender}</td>
                            <td>${row.Teacher_Phone}</td>
                            <td><a href="http://localhost:8084/LoginModule/teacher/editTeacher.jsp?teacher_id=${row.Teacher_ID}&teacher_name=${row.Teacher_Name}&teacher_gender=${row.Teacher_Gender}&teacher_phone=${row.Teacher_Phone}">Edit</a></td>
                            <td><a href="http://localhost:8084/LoginModule/teacher/deleteTeacher.jsp?teacher_id=${row.Teacher_ID}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br><br><br>
                <a href="http://localhost:8084/LoginModule/teacher/addTeacher.jsp">Add New Teacher's Record</a>
                <h3 style="color:red;">${param.status}</h3>
            </center>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
