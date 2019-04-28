package wpdemo.web;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
import wpdemo.merchant.service.object.MerchantServiceImpl;
import wpdemo.product.dao.model.Product;
import wpdemo.product.dao.model.ProductType;
import wpdemo.product.service.object.ProductServiceImpl;
import wpdemo.support.utill.Const;
import wpdemo.support.utill.WPException;
import wpdemo.visitor.dao.model.Visitor;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "ProductRegServlet", urlPatterns = {"/preg"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

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
        Map<Integer, String> types = new HashMap<>();
        for (ProductType pt : Arrays.asList(ProductType.values())) {
            types.put(pt.getId(), pt.getMsg());
        }
        request.setAttribute("types", types);
        getServletContext().getRequestDispatcher("/preg.jsp").include(request, response);
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
        MerchantServiceImpl merhantServ = new MerchantServiceImpl();
        Merchant actMechant = merhantServ.getByVisitor(((Visitor) request.getSession().getAttribute("user")).getVisitorId());
        ProductServiceImpl productServ = new ProductServiceImpl();
        Map<String, String> messages = new HashMap<>();        
        Product newProduct = new Product();
        newProduct.setMerchantId(actMechant.getId());
        newProduct.setName(request.getParameter("pname"));
        newProduct.setDescription(request.getParameter("pdescription"));
        try {
            newProduct.setType(ProductType.values()[Integer.parseInt(request.getParameter("ptype"))]);
            newProduct = productServ.create(newProduct);            
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
        } catch (NumberFormatException e) {
            messages.put("ptype", "Válasszon egyet a listából");
        }

        ImageServiceImpl imageServ = new ImageServiceImpl();
        Image savedImage = imageServ.getForProduct(newProduct.getId());
        Image newImage = new Image();

        File fileSaveDir = new File(Const.SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part part;
        String fileName;
        String mimeType;
        String ext;
            part = request.getPart("img");
            fileName = part.getSubmittedFileName();
            mimeType = getServletContext().getMimeType(fileName);
            if (mimeType == null && savedImage == null) {
                messages.put("img", "Valasszon egy kepet");
            } else if (mimeType != null && mimeType.startsWith("image/")) {
                ext = fileName.substring(fileName.indexOf("."));
                newImage.setContactId(newProduct.getId());
                newImage.setType(ImageType.PRODUCT);
                fileName = "m_" + newProduct.getId() + "_0" + ext;
                newImage.setLocation("img/" + fileName);
                part.write(Const.SAVE_DIR + File.separator + fileName);
                imageServ.create(newImage);
            } else if (mimeType != null && !mimeType.startsWith("image/")) {
                messages.put("img", "Nem megfelelő formátumú a feltölteni kívánt kép.");
            }        
        if (!messages.isEmpty()) {
            request.setAttribute("userinput", newProduct);
            request.setAttribute("messages", messages);
            doGet(request, response);
        } else {
            response.sendRedirect("preg");
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
