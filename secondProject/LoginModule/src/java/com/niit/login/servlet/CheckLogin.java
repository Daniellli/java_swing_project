/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;

public class CheckLogin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        boolean ret = DBUtils.verifyUser(loginId);
        if(ret){
            String securityQuestion = DBUtils.getSecurityQuestion(loginId);
            request.setAttribute("loginID", loginId);
            request.setAttribute("securityQuestion", securityQuestion);
        }
        else{
            request.setAttribute("status", "Login ID does not exist !!");
        }
        getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);
    }
}
