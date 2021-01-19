<%-- 
    Document   : login
    Created on : Jun 14, 2019, 12:15:16 PM
    Author     : Nitesh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
            <div id="mainContent">
                <span id="loginName">${sessionScope.User.userName}</span>
                <br><br><br><br><br>
                <center>
                    <form action="http://localhost:8084/LoginModule/servlet/Authenticate" method="POST">
                        <table style="outline: 1px solid;">
                            <caption style="border: 2px solid #eeeeee;">Login User Form</caption>
                            <tr>
                                <td>Login ID</td>
                                <td><input type="text" placeholder="Enter your login name" name="loginId" id="loginId" value="${requestScope.loginId}" required/></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type="password" placeholder="Enter Your Password" name="password" id="password" required/></td>
                            </tr>
                            <tr>
                                <td>User Type</td>
                                <td><input type="radio" name="userType" value="admin"/>Admin &nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="userType" value="customer"/>Customer</td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Log In"/></td>
                                <td><input type="reset" value="    Reset    "/></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <a href="http://localhost:8084/LoginModule/register.jsp">Register User</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="http://localhost:8084/LoginModule/resetPassword.jsp">Reset Password</a>
                    <%
                        Object status = request.getAttribute("login");
                        if(status!=null && status.toString().equals("false")){
                    %>
                    <h3 style="color:red;">Authentication failed!! <br>${requestScope.loginId} is not Admin or Does not exist or Password is wrong..</h3>
                    <%
                        }
                    %>
                    <br><br><br><br><br>
                </center>
            </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
