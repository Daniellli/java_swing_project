/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;

public class CheckAnswer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String securityQuestion = request.getParameter("securityQuestion");
        String securityAnswer = request.getParameter("securityAnswer");
        String sa = DBUtils.getSecurityAnswer(loginId.trim(), securityQuestion.trim());
        if(sa.toLowerCase().equals(securityAnswer.toLowerCase())){
            request.setAttribute("loginID", loginId);
            request.setAttribute("securityQuestion", securityQuestion);
            request.setAttribute("securityAnswer", securityAnswer);
            request.setAttribute("status2", "You can now change the password below..");
        }
        else{
            request.setAttribute("status2", "Security answer does not match !!");
        }
        getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);
    }
}
