package wpdemo.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import wpdemo.image.dao.model.Image;
import wpdemo.image.dao.model.ImageType;
import wpdemo.image.service.object.ImageServiceImpl;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.product.dao.model.Product;
import wpdemo.product.dao.model.ProductType;
import wpdemo.product.service.object.ProductServiceImpl;
import wpdemo.support.utill.Const;
import wpdemo.support.utill.WPException;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "ProductRegServlet", urlPatterns = {"/preg"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

public class ProductRegServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("merchant") == null) {
            request.setAttribute("message", "Kérem először a tevékenységét leíró adatokat rögzítse!");
            getServletContext().getRequestDispatcher("/error.jsp").include(request, response);
        } else {
            Map<Integer, String> types = new HashMap<>();
            for (int i = 1; i < ProductType.values().length; i++) {
                types.put(ProductType.values()[i].getId(), ProductType.values()[i].getMsg());
            }
            request.setAttribute("types", types);
            ProductServiceImpl productServ = new ProductServiceImpl();
            List<Product> prList = productServ.getByMerchant(((Merchant) request.getSession().getAttribute("merchant")).getId());
            if (prList != null) {
                ImageServiceImpl imageServ = new ImageServiceImpl();
                Map<Long, String> images = new HashMap<>();
                for (Product pr : prList) {
                    images.put(pr.getId(), imageServ.getForProduct(pr.getId()).getLocation());
                }
                request.setAttribute("products", prList);
                request.setAttribute("images", images);
            }
            getServletContext().getRequestDispatcher("/preg.jsp").include(request, response);
        }
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
        Merchant actMerchant = (Merchant) request.getSession().getAttribute("merchant");
        Map<String, String> messages = new HashMap<>();

        Product newProduct = new Product();
        newProduct.setMerchantId(actMerchant.getId());
        newProduct.setName(request.getParameter("pname"));
        newProduct.setDescription(request.getParameter("pdescription"));
        newProduct.setType(ProductType.values()[Integer.parseInt(request.getParameter("ptype"))]);

        Image newImage = new Image();
        File fileSaveDir = new File(Const.SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String ext;
        Part part = request.getPart("img");
        String fileName = part.getSubmittedFileName();
        String mimeType = getServletContext().getMimeType(fileName);
        if ( mimeType==null || mimeType.isEmpty()) {
            messages.put("img", "Valasszon egy kepet");
        } else if (!mimeType.startsWith("image/")) {
            messages.put("img", "Nem megfelelő formátumú a feltölteni kívánt kép.");
        } else {
            try {
                ProductServiceImpl productServ = new ProductServiceImpl();
                newProduct = productServ.create(newProduct);

                ImageServiceImpl imageServ = new ImageServiceImpl();
                ext = fileName.substring(fileName.indexOf("."));
                newImage.setContactId(newProduct.getId());
                newImage.setType(ImageType.PRODUCT);
                fileName = "m_" + newProduct.getId() + "_0" + ext;
                newImage.setLocation("img/" + fileName);
                part.write(Const.SAVE_DIR + File.separator + fileName);
                imageServ.create(newImage);
            } catch (WPException e) {
                if (newProduct.getName() == null || newProduct.getName().isEmpty()) {
                    messages.put("pname", e.getMessage());
                }
                if (newProduct.getDescription() == null || newProduct.getDescription().isEmpty()) {
                    messages.put("pdescription", e.getMessage());
                }
                if (newProduct.getType() == null) {
                    messages.put("ptype", e.getMessage());
                }
            }
        }
        if (!messages.isEmpty()) {
            request.setAttribute("userinput", newProduct);
            request.setAttribute("messages", messages);            
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
