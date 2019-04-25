package wpdemo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.merchant.service.object.MerchantServiceImpl;
import wpdemo.support.utill.WPException;
import wpdemo.town.service.object.TownServiceImpl;
import wpdemo.visitor.dao.model.Visitor;

/**
 * @author Kovacs Maria
 */
@WebServlet(name = "MRegServlet", urlPatterns = {"/mreg"})
public class MRegServlet extends HttpServlet {

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
        Visitor actVisitor = (Visitor) request.getSession().getAttribute("user");
        MerchantServiceImpl merchantServ = new MerchantServiceImpl();
        Merchant actMerchant = merchantServ.getByVisitor(actVisitor.getVisitorId());
        if (request.getParameter("userinput") == null) {
            request.setAttribute("userinput", actMerchant);
        }
        getServletContext().getRequestDispatcher("/mreg.jsp").include(request, response);
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
        Merchant newMerchant = new Merchant();
        TownServiceImpl townServ = new TownServiceImpl();
        MerchantServiceImpl merchantServ = new MerchantServiceImpl();
        Map<String, String> messages = new HashMap<>();
        boolean inputOk = false;
        newMerchant.setVisitorId(((Visitor) request.getSession().getAttribute("user")).getVisitorId());
        newMerchant.setNameToDisplay(request.getParameter("pnametodisplay"));
        newMerchant.setIntroduction(request.getParameter("pintroduction"));
        newMerchant.setDescription(request.getParameter("pdescription"));
        List<Long> townList = new ArrayList<>();
        long temp;
        for (int i = 1; i < 5; i++) {
            if (request.getParameter("town" + i) != null) {
                townList.add(Long.parseLong(request.getParameter("town" + i)));
            }
        }
        newMerchant.setTownIds(townList);
        try {
            Visitor actVisitor = (Visitor) request.getSession().getAttribute("user");
            Merchant actMerchant = merchantServ.getByVisitor(actVisitor.getVisitorId());
            if (actMerchant == null) {
                merchantServ.create(newMerchant);
            } else {
                merchantServ.modify(actMerchant.getId(), newMerchant);
            }
            inputOk = true;
        } catch (WPException e) {
            if (newMerchant.getNameToDisplay() == null || newMerchant.getNameToDisplay().isEmpty()) {
                messages.put("pnametodisplay", e.getMessage());
            }
            if (newMerchant.getIntroduction() == null || newMerchant.getIntroduction().isEmpty()) {
                messages.put("pintroduction", e.getMessage());
            }
            if (newMerchant.getDescription() == null || newMerchant.getDescription().isEmpty()) {
                messages.put("pdescription", e.getMessage());
            }
            if (newMerchant.getTownIds() == null || newMerchant.getTownIds().isEmpty()) {
                messages.put("town", e.getMessage());
            }
        }
        if (!inputOk) {
            request.setAttribute("userinput", newMerchant);
            request.setAttribute("messages", messages);
            doGet(request, response);
        } else {
            response.sendRedirect("mimgupload");
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
