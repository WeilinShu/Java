//A plain old Servlet

package testPackage;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import testPackage.Message;

@WebServlet("/hello")
public class Program1 extends HttpServlet {
    String target = "forward.jsp";

    Message model1 = new Message();

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        request.setAttribute("message", model1.msg());
        RequestDispatcher dispatcher = 
            request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    } 
}
