/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}