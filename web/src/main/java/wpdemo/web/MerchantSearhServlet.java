package wpdemo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.image.dao.model.ImageType;
import wpdemo.image.service.object.ImageServiceImpl;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.merchant.service.object.MerchantServiceImpl;
import wpdemo.town.service.object.TownServiceImpl;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "MerchantSearhServlet", urlPatterns = {"/msearch"})
public class MerchantSearhServlet extends HttpServlet {

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
        TownServiceImpl townServ = new TownServiceImpl();
        request.setAttribute("townList", townServ.getAll());
        getServletContext().getRequestDispatcher("/msearch.jsp").include(request, response);
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
        long pTownId = Long.parseLong(request.getParameter("townid"));
        String qString = request.getParameter("qstring");
        MerchantServiceImpl merchantServ = new MerchantServiceImpl();
        List<Merchant> rsList = merchantServ.search(pTownId, qString);
        if (rsList != null) {
            ImageServiceImpl imageServ = new ImageServiceImpl();
            Map<Long, String> images = new HashMap<>();
            for (Merchant merchant : rsList) {
                images.put(merchant.getId(), imageServ.getByTypeForMerhant(merchant.getId(), ImageType.MERCHANT_BIG).getLocation());
            }
            request.setAttribute("rslist", rsList);
            request.setAttribute("images", images);
        } else {
            request.setAttribute("message", "Sajnos nincs eredménye a keresésnek.");
        }

        doGet(request, response);
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
