package wpdemo.web;

import java.io.File;
import java.io.IOException;
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
import wpdemo.support.utill.Const;

/**
 * @author Kovacs Maria
 */
@WebServlet(name = "MImgUploadServlet", urlPatterns = {"/mimgupload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class MImgUploadServlet extends HttpServlet {
    

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
        Merchant actMerchant = (Merchant)request.getSession().getAttribute("merchant");
        ImageServiceImpl imageServ = new ImageServiceImpl();
        request.setAttribute("savedimages", imageServ.getForMerchant(actMerchant.getId()));
        getServletContext().getRequestDispatcher("/imgreg.jsp").include(request, response);
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
        ImageServiceImpl imageServ = new ImageServiceImpl();
        Map<String, String> messages = new HashMap<>();
        Map<Integer, Image> savedImages = imageServ.getForMerchant(actMerchant.getId());

        File fileSaveDir = new File(Const.SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part part;
        String fileName;
        String mimeType;
        String ext;
        Image newImage = new Image();
        for (int i = 1; i < 4; i++) {
            part = request.getPart("file" + i);
            fileName = part.getSubmittedFileName();
            mimeType = getServletContext().getMimeType(fileName);
            if (mimeType == null && savedImages.get(i) == null) {
                messages.put("file" + i, "Minden lehetőséget használjon ki kép választásra.");
            } else if (mimeType != null && mimeType.startsWith("image/")) {
                ext = fileName.substring(fileName.indexOf("."));
                newImage.setContactId(actMerchant.getId());
                newImage.setType(ImageType.values()[i]);
                fileName = "m_" + actMerchant.getId() + "_" + i + ext;
                newImage.setLocation("img/" + fileName);
                part.write(Const.SAVE_DIR + File.separator + fileName);
                imageServ.create(newImage);
            } else if (mimeType != null && !mimeType.startsWith("image/")) {
                messages.put("file" + i, "Nem megfelelő formátumú a feltölteni kívánt kép.");
            }
        }
        if (!messages.isEmpty()) {
            request.setAttribute("messages", messages);
            doGet(request, response);
        } else {
            request.setAttribute("merchantid", actMerchant.getId());            
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("getmerchant?merchantid=" + actMerchant.getId());
        }

    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
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
