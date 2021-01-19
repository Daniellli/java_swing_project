<%-- 
    Document   : insertTeacher
    Created on : Jun 16, 2019, 4:43:34 PM
    Author     : Nitesh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Close Batch</title>
    </head>
    <body>
        <c:set var="now" value="<%=new java.util.Date()%>" />
        <fmt:formatDate var="today" type="date" value="${now}" pattern="yyyy-MM-dd" />
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <sql:update dataSource="${ds}" scope="session" var="result">
            update Batch set Batch_Close=? where Batch_ID=?
            <sql:param value="${today}"/>
            <sql:param value="${param.batch_id}"/>
        </sql:update>
        <c:if test="${result > 0}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:if test="${result <= 0}">
            <jsp:forward page="index.jsp">
                <jsp:param name="status" value="Could not close the batch !!" />
            </jsp:forward>
        </c:if>
    </body>
</html>
