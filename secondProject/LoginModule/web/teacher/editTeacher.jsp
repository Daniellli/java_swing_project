<%-- 
    Document   : addTeacher
    Created on : Jun 16, 2019, 4:33:23 PM
    Author     : Nitesh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Teacher's Record</title>
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
                <form action="http://localhost:8084/LoginModule/teacher/editTeacherDetails.jsp" method="POST">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Edit Teacher's Record</caption>
                        <tr>
                            <td>Teacher ID</td>
                            <td><input type="text" value="${param.teacher_id}"  name="teacher_id" readonly required/></td>
                        </tr>
                        <tr>
                            <td>Full Name</td>
                            <td><input type="text" value="${param.teacher_name}" name="teacher_name" required/></td>
                        </tr>
                        <tr>
                            <td>Mobile No.</td>
                            <td><input type="text" value="${param.teacher_phone}" name="teacher_phone" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Edit Record"/></td>
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
