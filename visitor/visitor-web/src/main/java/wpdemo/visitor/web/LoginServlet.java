package wpdemo.visitor.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.support.utill.WPException;
import wpdemo.visitor.dao.model.Visitor;
import wpdemo.visitor.service.object.VisitorServiceImpl;

/**
 * @author Kovacs Maria
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean loginOK = false;
        String errorMessage="";
        VisitorServiceImpl visitorServ = new VisitorServiceImpl();
        try {
            Visitor temp = visitorServ.login(request.getParameter("username"), request.getParameter("password"));
            if (temp != null) {
                request.getSession().setAttribute("user", temp);
                loginOK = true;
            }
        } catch (WPException e) {
            switch (e.errCode) {
                case 0:
                    errorMessage = "Minden mezőt ki kell tölteni.";
                    break;
                case 5:
                    errorMessage = e.getMessage();
                    break;
            }
        }
         if (!loginOK) {
            request.setAttribute("error", errorMessage);
            doGet(request, response);
        } else {
            response.sendRedirect("/wp-web/index.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
