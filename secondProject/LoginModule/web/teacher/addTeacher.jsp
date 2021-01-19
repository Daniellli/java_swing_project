<%-- 
    Document   : addTeacher
    Created on : Jun 16, 2019, 4:33:23 PM
    Author     : Nitesh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Teacher</title>
        <link rel="stylesheet" href="http://localhost:8084/LoginModule/css/mystyle.css"/>
    </head>
    <body>
        <%
            Object usr = session.getAttribute("User");
                if(usr==null){
                    request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
        %>
        <jsp:include page="/header.jsp"/>
        <div id="mainContent">
            <span id="loginName">Welcome ${sessionScope.User.userName}</span>
            <br><br><br><br><br>
            <center>
                <form action="http://localhost:8084/LoginModule/teacher/insertTeacher.jsp" method="POST">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Teacher Registration Form</caption>
                        <tr>
                            <td>Full Name</td>
                            <td><input type="text" placeholder="Teacher's Full Name" name="teacher_name" required/></td>
                        </tr>
                        <tr>
                            <td>Gender</td>
                            <td><input type="radio" name="teacher_gender" value="male"/>Male &nbsp;&nbsp;&nbsp;
                                <input type="radio" name="teacher_gender" value="female"/>Female</td>
                        </tr>
                        <tr>
                            <td>Mobile No.</td>
                            <td><input type="text" placeholder="Teacher's Mobile No." name="teacher_phone" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Register"/></td>
                            <td><input type="reset" value="    Reset    "/></td>
                        </tr>
                    </table>
                </form>
                <h3>${param.status}</h3>
            </center>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
