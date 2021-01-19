/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;

public class ResetPassword extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String securityQuestion = request.getParameter("securityQuestion");
        String securityAnswer = request.getParameter("securityAnswer");
        String password = request.getParameter("password");
        boolean ret = DBUtils.resetPassword(loginId.trim(), securityQuestion.trim(), securityAnswer.trim(),password.trim());
        if(ret){
            request.setAttribute("status3", "Done");
        }
        else{
            request.setAttribute("status3", "None");
        }
        getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);
    }
}
