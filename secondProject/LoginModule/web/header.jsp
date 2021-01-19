<%-- 
    Document   : header
    Created on : Jun 14, 2019, 2:16:01 PM
    Author     : Nitesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <style>
        #header{
            margin-left: auto;
            margin-right: auto;
            width: 80%;
            background: black;
            color: white;
            height: 10%;
        }
        .header{
            text-decoration: none;
            color: white;
        }
    </style>
</head>
<body>
    <div id="header">
        <center>
            <a class="header" href="http://localhost:8084/LoginModule/homePage.jsp">Home Page</a> &nbsp;&nbsp;|
            <a class="header" href="http://localhost:8084/LoginModule/module/index.jsp">Module Details</a> &nbsp;&nbsp;|
            <a class="header" href="http://localhost:8084/LoginModule/batch/index.jsp">Batch Details</a> &nbsp;&nbsp;|
            <a class="header" href="http://localhost:8084/LoginModule/teacher/index.jsp">Teacher Details</a> &nbsp;&nbsp;|
            <%
                Object usr = session.getAttribute("User");
                if(usr==null){
            %>
            <a class="header" href="http://localhost:8084/LoginModule/login.jsp">Login</a>
            <%
                }
                else{
            %>    
                <a id="logo" class="header" href="http://localhost:8084/LoginModule/Logout">Logout</a>
            <%
                }
            %>
        </center>
    </div>
</body>
