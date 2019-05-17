package wpdemo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {


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
        Map<String, Long> cart = (Map<String, Long>)request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new HashMap<>();            
        }
        cart.put(request.getParameter("cartname"), Long.parseLong(request.getParameter("cartvalue")));
        request.getSession().setAttribute("cart", cart);
        request.setAttribute("townList", request.getSession().getAttribute("townList"));
        request.setAttribute("types", request.getSession().getAttribute("types"));
        request.setAttribute("rslist", request.getSession().getAttribute("prslist"));
        request.setAttribute("images", request.getSession().getAttribute("pimages"));        
        response.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/psearch.jsp").include(request, response);        
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
