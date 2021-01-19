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
        <title>Add New Batch</title>
    </head>
    <body>
        <%
            String module = request.getParameter("module_id");
            int pos = module.indexOf(':');
            int module_id = Integer.parseInt(module.substring(0, pos));
            String teacher = request.getParameter("teacher_id");
            pos = teacher.indexOf(':');
            int teacher_id = Integer.parseInt(teacher.substring(0, pos));
            System.out.println(module_id + ":" + teacher_id);
        %>
        <sql:setDataSource  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" 
                            url="jdbc:sqlserver://localhost;databaseName=master"
                            user="sa"
                            password="niit1234" 
                            var="ds" 
                            scope="session" />
        <sql:update dataSource="${ds}" scope="session" var="result">
            insert into Batch(Batch_Start, Module_ID, Teacher_ID, Batch_Description) values(?, ?, ?, ?)
            <sql:param value="${param.batch_start}"/>
            <sql:param value="<%=module_id %>"/>
            <sql:param value="<%=teacher_id %>"/>
            <sql:param value="${param.batch_description}"/>
        </sql:update>
        <c:if test="${result > 0}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:if test="${result <= 0}">
            <jsp:forward page="addBatch.jsp">
                <jsp:param name="status" value="Could not add new Batch !!" />
            </jsp:forward>
        </c:if>
    </body>
</html>
