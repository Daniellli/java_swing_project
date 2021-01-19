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
        <title>Delete Teacher</title>
    </head>
    <body>
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <c:set var="exists" value="0" scope="page"/>
        <sql:query dataSource="${ds}" scope="session" var="rs">
            select * from Batch where Teacher_ID=?
            <sql:param value="${param.teacher_id}"/>
        </sql:query>
        <c:forEach items="${rs.rows}" var="row">
            <c:set var="exists" value="1" scope="page"/>
        </c:forEach>
        <c:if test="${exists > 0}">
            <jsp:forward page="index.jsp">
                <jsp:param name="status" value="Can not delete the teacher !! Batch exists for this teacher !!" />
            </jsp:forward>
        </c:if>
        <sql:update dataSource="${ds}" scope="session" var="result">
            delete from Teacher where Teacher_ID=?
            <sql:param value="${param.teacher_id}"/>
        </sql:update>
        <c:if test="${result > 0}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:if test="${result <= 0}">
            <jsp:forward page="index.jsp">
                <jsp:param name="status" value="Could not delete the teacher record !!" />
            </jsp:forward>
        </c:if>
    </body>
</html>
