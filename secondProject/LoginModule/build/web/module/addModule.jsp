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
        <title>Add Module</title>
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
                <form action="http://localhost:8084/LoginModule/module/insertModule.jsp" method="POST">
                    <table style="outline: 1px solid;">
                        <caption style="border: 2px solid #eeeeee;">Add Module Form</caption>
                        <tr>
                            <td>Module Name</td>
                            <td><input type="text" placeholder="Module's Name" name="module_name" required/></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><input type="text" placeholder="Enter Description" name="module_description" required/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Add Module"/></td>
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
