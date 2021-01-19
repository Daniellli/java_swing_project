<%-- 
    Document   : homePage
    Created on : Jun 14, 2019, 2:14:25 PM
    Author     : Nitesh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page - Welcome</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <%
            Object usr = session.getAttribute("User");
                if(usr==null){
                    request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
        %>
        <jsp:include page="header.jsp"/>
        <div id="mainContent">
            <span id="loginName">Welcome ${sessionScope.User.userName}</span>
            <br><br><br><br><br>
            <center>
                <table style="outline: 1px solid;">
                    <caption style="border: 2px solid #eeeeee;">Your Details:</caption>
                    <tr>
                        <td>Login Id</td>
                        <td>${sessionScope.User.loginId}</td>
                    </tr>
                    <tr>
                        <td>Full Name</td>
                        <td>${sessionScope.User.userName}</td>
                    </tr>
                    <tr>
                        <td>Mobile Number</td>
                        <td>${sessionScope.User.userPhone}</td>
                    </tr>
                </table>
            </center>
            <br><br><br><br><br>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>

