package wpdemo.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.image.service.object.ImageServiceImpl;
import wpdemo.product.dao.model.Product;
import wpdemo.product.dao.model.ProductType;
import wpdemo.product.service.object.ProductServiceImpl;
import wpdemo.town.service.object.TownServiceImpl;

/**
 * @author Kovacs Maria
 */
@WebServlet(name = "ProductSearchServlet", urlPatterns = {"/psearch"})
public class ProductSearchServlet extends HttpServlet {

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
        Map<Integer, String> types = new HashMap<>();        
        for(int i = 1; i < ProductType.values().length; i++){
            types.put(ProductType.values()[i].getId(), ProductType.values()[i].getMsg());
        }        
        request.setAttribute("types", types);
        TownServiceImpl townServ = new TownServiceImpl();
        request.setAttribute("townList", townServ.getAll());
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
        ProductType pType = ProductType.values()[Integer.parseInt(request.getParameter("ptype"))];              
        String qString = request.getParameter("qstring");
        long pTownId = Long.parseLong(request.getParameter("townid"));
        if (qString.isEmpty() && pType == ProductType.NONE && pTownId == 0) {
            request.setAttribute("message", "Kérem adjon meg legalább egy kereséséi feltételt!");
        } else {
            ProductServiceImpl productServ = new ProductServiceImpl();
            List<Product> rsList = productServ.search(pType, pTownId, qString);
            if (!rsList.isEmpty()) {
                ImageServiceImpl imageServ = new ImageServiceImpl();
                Map<Long, String> images = new HashMap<>();
                for (Product product : rsList) {
                    images.put(product.getId(), imageServ.getForProduct(product.getId()).getLocation());
                }
                request.setAttribute("rslist", rsList);
                request.setAttribute("images", images);
            } else {
                request.setAttribute("message", "Sajnos nincs eredménye a keresésnek.");
            }
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
