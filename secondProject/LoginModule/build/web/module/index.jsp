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
        <title>All Module's Report</title>
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
                select * from Module
            </sql:query>
            <center>
                <table style="outline: 1px solid;">
                    <caption style="border: 2px solid #eeeeee;">Module's Information</caption>
                    <c:forEach items="${rs.rows}" var="row">
                        <tr>
                            <td>${row.Module_ID}</td>
                            <td>${row.Module_Name}</td>
                            <td>${row.Module_Description}</td>
                            <td><a href="http://localhost:8084/LoginModule/module/deleteModule.jsp?module_id=${row.Module_ID}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br><br><br>
                <a href="http://localhost:8084/LoginModule/module/addModule.jsp">Add New Module</a>
                <h3 style="color:red;">${param.status}</h3>
            </center>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
