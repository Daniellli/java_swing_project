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
        <title>Add Module</title>
    </head>
    <body>
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <sql:update dataSource="${ds}" scope="session" var="result">
            insert into Module(Module_Name, Module_Description) values(?, ?)
            <sql:param value="${param.module_name}"/>
            <sql:param value="${param.module_description}"/>
        </sql:update>
        <c:if test="${result > 0}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:if test="${result <= 0}">
            <jsp:forward page="addModule.jsp">
                <jsp:param name="status" value="Could not add new module !!" />
            </jsp:forward>
        </c:if>
    </body>
</html>
