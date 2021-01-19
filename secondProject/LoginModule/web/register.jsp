<%-- 
    Document   : register
    Created on : Jun 13, 2019, 5:00:49 PM
    Author     : Nitesh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://localhost:8084/LoginModule/script/login.js"></script>
        <title>Register Page</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
            <div id="mainContent">
                <span id="loginName">${sessionScope.User.userName}</span>
                <br><br><br><br><br>
                <center>
                    <form action="http://localhost:8084/LoginModule/servlet/Register" method="POST">
                        <table style="outline: 1px solid;">
                            <caption style="border: 2px solid #eeeeee;">User Registration Form</caption>
                            <tr>
                                <td>Login ID</td>
                                <td><input type="text" placeholder="Enter your login name" name="loginId" id="loginId" onkeypress="checkUser(this);" onchange="checkUser(this);" required/></td>
                                <td><button onclick="return verifyUser();">Check Availability</button></td>
                                <td><div id="container" style="display: none;"></div></td>
                            </tr>
                            <tr>
                                <td>Full Name</td>
                                <td><input type="text" placeholder="Enter Your Full Name" name="userName" required/></td>
                            </tr>
                            <tr>
                                <td>User Type</td>
                                <td><input type="radio" name="userType" value="admin"/>Admin &nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="userType" value="customer"/>Customer</td>
                            </tr>
                            <tr>
                                <td>Mobile No.</td>
                                <td><input type="text" placeholder="Enter Your Mobile Number" name="userPhone" required/></td>
                            </tr>
                            <tr>
                                <td>Password</td>
                                <td><input type="password" placeholder="Enter Password" name="password" id="password" required/></td>
                            </tr>
                            <tr>
                                <td>Re-Password</td>
                                <td><input type="password" placeholder="Enter Password Again" name="rePassword" id="rePassword" required/></td>
                            </tr>
                            <tr>
                                <td>Security Question</td>
                                    <td>
                                        <select name="securityQuestion">
                                            <option value="What's your mother name?" selected>What's your mother name?</option>
                                            <option value="What's your high school name?">What's your high school name?</option>
                                            <option value="What's your math teacher name?">What's your math teacher name?</option>
                                        </select>
                                    </td>
                            </tr>
                            <tr>
                                <td>Security Answer</td>
                                <td><input type="text" placeholder="Enter Your Answer" name="securityAnswer" required/></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Register" onclick="return validatePassword();"/></td>
                                <td><input type="reset" value="    Reset    "/></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <a href="http://localhost:8084/LoginModule/login.jsp">Login</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="http://localhost:8084/LoginModule/resetPassword.jsp">Reset Password</a>
                    <%
                        Object status = request.getAttribute("status");
                        if(status!=null && status.toString().equals("false")){
                    %>
                    <h3 style="color:red;">Registration Not Done!! Please write an email to nitesh.singh@niit.com.cn</h3>
                    <%
                        }
                    %>
                    <br><br><br><br><br>
                </center>
            </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
