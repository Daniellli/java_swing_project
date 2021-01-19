/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;
import com.niit.login.beans.Users;

public class Authenticate extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        boolean ret = DBUtils.authenticate(loginId,password,userType);
        if(ret==true && userType.equals("admin")){
            HttpSession session = request.getSession(true);
            Users usr = DBUtils.getUser(loginId);
            session.setAttribute("User", usr);
            getServletContext().getRequestDispatcher("/homePage.jsp").forward(request, response);
        }
        else{
            request.setAttribute("login", "false");
            request.setAttribute("loginId", loginId);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
