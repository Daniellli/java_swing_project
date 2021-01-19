<%-- 
    Document   : resetPassword
    Created on : Jun 14, 2019, 12:01:34 AM
    Author     : Nitesh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://localhost:8084/LoginModule/script/resetPassword.js"></script>
        <title>Reset Password Page</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="mainContent">
            <span id="loginName">${sessionScope.User.userName}</span>
            <br><br><br>
            <center>
                <form action="http://localhost:8084/LoginModule/servlet/CheckLoginId">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Step 1</caption>
                        <tr>
                            <td>Login Id</td>
                            <td><td><input type="text" placeholder="enter your login name" name="loginId" id="loginId" value="${requestScope.loginID}" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Check Login ID"/></td>
                        </tr>
                    </table>
                </form>
                <h3 style="color:red;">${requestScope.status}</h3>
                <form action="http://localhost:8084/LoginModule/servlet/CheckAnswer" method="GET">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Step 2</caption>
                        <tr>
                            <td>Login Id</td>
                            <td><td><input type="text" name="loginId" id="loginId2" value="${requestScope.loginID}" required readonly/></td>
                        </tr>
                        <tr>
                            <td>Security Question</td>
                            <td><td><input type="text" name="securityQuestion" id="securityQuestion" value="${requestScope.securityQuestion}" required readonly/></td>
                        </tr>
                        <tr>
                            <td>Security Answer</td>
                            <td><td><input type="text" name="securityAnswer" id="securityAnswer" value="${requestScope.securityAnswer}" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Check Answer"/></td>
                        </tr>
                    </table>
                </form>
                <h3 style="color:red;">${requestScope.status2}</h3>
                <form action="http://localhost:8084/LoginModule/servlet/ResetPassword" method="POST">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Step 3</caption>
                        <tr>
                            <td>Login Id</td>
                            <td><input type="text" name="loginId" id="loginId" value="${requestScope.loginID}" required readonly/></td>
                        </tr>
                        <tr>
                            <td>Security Question</td>
                            <td><input type="text" name="securityQuestion" id="securityQuestion" value="${requestScope.securityQuestion}" required readonly/></td>
                        </tr>
                        <tr>
                            <td>Security Answer</td>
                            <td><input type="text" name="securityAnswer" id="securityAnswer" value="${requestScope.securityAnswer}" required readonly/></td>
                        </tr>
                        <tr>
                            <td>New Password</td>
                            <td><input type="password" placeholder="enter your password" name="password" id="password" required/></td>
                        </tr>
                        <tr>
                            <td>Re-Password</td>
                            <td colspan="3"><input type="password" placeholder="Re-enter your password" name="rePassword" id="rePassword" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Reset Password" onclick="return CheckPassword()"/></td>
                        </tr>
                    </table>
                </form>
                <br/>
                <a href="http://localhost:8084/LoginModule/login.jsp">Login</a> &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="http://localhost:8084/LoginModule/register.jsp">Register User</a>
                <%
                    Object status3 = request.getAttribute("status3");
                    if (status3 != null && status3.equals("Done")) {
                %>
                <h3 style="color:red;">Password changed successfully !!!</h3>
                <%
                } else if (status3 != null) {
                %>
                <h3 style="color:red;">Password could not be changed !!! Please write an email to nitesh.singh@niit.com.cn</h3>
                <%
                    }
                %>
                <br><br><br>
            </center>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
