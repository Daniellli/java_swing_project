/*Written By Nitesh*/
package com.niit.login.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.niit.login.utils.DBUtils;

public class VerifyUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        boolean ret = DBUtils.verifyUser(loginId);
        PrintWriter out = response.getWriter();
        //ServletOutputStream out = response.getOutputStream();
        if(ret)
            out.println("not available");
        else
            out.println("available");
        out.flush();
        out.close();
    }
}
