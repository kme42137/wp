package wpdemo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.merchant.service.object.MerchantServiceImpl;
import wpdemo.support.utill.WPException;
import wpdemo.visitor.dao.model.Visitor;
import wpdemo.visitor.service.object.VisitorServiceImpl;

/**
 *
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
        request.setAttribute("ismerchant", request.getParameter("ismerchant"));
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
        String errorMessage="";
        VisitorServiceImpl visitorServ = new VisitorServiceImpl();
        try {
            Visitor visitor = visitorServ.login(request.getParameter("username"), request.getParameter("password"));
            if (visitor != null) {
                request.getSession().setAttribute("user", visitor);
                MerchantServiceImpl merhantServ = new MerchantServiceImpl();
                Merchant actMechant = merhantServ.getByVisitor(visitor.getVisitorId());
                request.getSession().setAttribute("merchant", actMechant);                
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
         if (!errorMessage.isEmpty()) {
            request.setAttribute("error", errorMessage);
            doGet(request, response);
        } else {
            response.sendRedirect("index.jsp");
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
