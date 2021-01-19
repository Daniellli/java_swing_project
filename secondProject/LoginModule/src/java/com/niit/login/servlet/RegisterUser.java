/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;
import com.niit.login.beans.Users;

public class RegisterUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String userName = request.getParameter("userName");
        String userType = request.getParameter("userType");
        String userPhone = request.getParameter("userPhone");
        String password = request.getParameter("password");
        String securityQuestion = request.getParameter("securityQuestion");
        String securityAnswer = request.getParameter("securityAnswer");
        Users usr = new Users(loginId, userName, userType, userPhone, password, securityQuestion, securityAnswer);
        boolean ret = DBUtils.registerUsers(usr);
        if(!ret){
            request.setAttribute("status", "false");
            getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
        }
        else
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
