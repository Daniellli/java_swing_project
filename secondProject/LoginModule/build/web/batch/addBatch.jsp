<%-- 
    Document   : addTeacher
    Created on : Jun 16, 2019, 4:33:23 PM
    Author     : Nitesh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Batch</title>
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
        <div id="mainContent">
            <span id="loginName">Welcome ${sessionScope.User.userName}</span>
            <br><br><br><br><br>
            <center>
                <form action="http://localhost:8084/LoginModule/batch/insertBatch.jsp" method="GET">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Add New Batch Form</caption>
                        <tr>
                            <td>Batch Start</td>
                            <td><input type="date" name="batch_start" required/></td>
                        </tr>
                        <tr>
                            <td>Module Name</td>
                            <td><select name="module_id">
                                    <sql:query dataSource="${ds}" var="rs" scope="session">
                                        select * from Module
                                    </sql:query>
                                    <c:forEach items="${rs.rows}" var="row">
                                        <option value="${row.Module_ID}:${row.Module_Name}">${row.Module_ID}:${row.Module_Name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Teacher Name</td>
                            <td><select name="teacher_id">
                                    <sql:query dataSource="${ds}" var="rs" scope="session">
                                        select * from Teacher
                                    </sql:query>
                                    <c:forEach items="${rs.rows}" var="row">
                                        <option value="${row.Teacher_ID}:${row.Teacher_Name}">${row.Teacher_ID}:${row.Teacher_Name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Batch Description</td>
                            <td><input type="text" name="batch_description" placeholder="Batch Description"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Add Batch"/></td>
                            <td><input type="reset" value="    Reset    "/></td>
                        </tr>
                    </table>
                </form>
                <h3>${param.status}</h3>
            </center>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
