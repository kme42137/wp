package wpdemo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.image.service.object.ImageServiceImpl;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.merchant.service.object.MerchantServiceImpl;
import wpdemo.town.dao.model.Town;
import wpdemo.town.service.object.TownServiceImpl;
import wpdemo.visitor.service.object.VisitorServiceImpl;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "GetMerchantServlet", urlPatterns = {"/getmerchant"})
public class GetMerchantServlet extends HttpServlet {


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
        MerchantServiceImpl merchantServ = new MerchantServiceImpl();        
        ImageServiceImpl imageServ = new ImageServiceImpl();        
        VisitorServiceImpl visitorServ = new VisitorServiceImpl();
        Merchant actMerchant = merchantServ.get(Long.parseLong(request.getParameter("merchantid"))); 
        request.setAttribute("merchant", actMerchant);
        request.setAttribute("images", imageServ.getForMerchant(Long.parseLong(request.getParameter("merchantid"))));
        List<Town> actTowns = new ArrayList<>();
        TownServiceImpl townServ = new TownServiceImpl();        
        for(long id : actMerchant.getTownIds()){
            actTowns.add(townServ.get(id));
        }
        request.setAttribute("towns", actTowns);
        request.setAttribute("email", visitorServ.get(actMerchant.getVisitorId()).geteMail());
        getServletContext().getRequestDispatcher("/merchant.jsp").include(request, response);
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
