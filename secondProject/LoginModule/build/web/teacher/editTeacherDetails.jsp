<%-- 
    Document   : insertTeacher
    Created on : Jun 16, 2019, 4:43:34 PM
    Author     : Nitesh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Teacher's Record</title>
    </head>
    <body>
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <sql:update dataSource="${ds}" scope="session" var="result">
            update Teacher set Teacher_Name=?, Teacher_Phone=? where Teacher_ID=?
            <sql:param value="${param.teacher_name}"/>
            <sql:param value="${param.teacher_phone}"/>
            <sql:param value="${param.teacher_id}"/>
        </sql:update>
        <c:if test="${result > 0}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:if test="${result <= 0}">
            <jsp:forward page="index.jsp">
                <jsp:param name="status" value="Could not change teacher's details !!" />
            </jsp:forward>
        </c:if>
    </body>
</html>
