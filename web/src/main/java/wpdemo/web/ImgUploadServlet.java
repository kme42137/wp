package wpdemo.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import wpdemo.image.dao.model.ImageType;
import wpdemo.visitor.dao.model.Visitor;

/**
 *
 * @author Kovacs Maria
 */
@WebServlet(name = "ImgUploadServlet", urlPatterns = {"/imgupload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ImgUploadServlet extends HttpServlet {

    private static final String SAVE_DIR = "D:\\Java_probalkozasok\\szemet\\wp\\mimages";

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
        Visitor actVisitor = (Visitor) request.getSession().getAttribute("user");
        ImageType imgType = ImageType.values()[Integer.parseInt(request.getParameter("type"))];
        
        // gets absolute path of the web application
        //String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file                 
        // creates the save directory if it does not exists
        File fileSaveDir = new File(SAVE_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        String mimeType = getServletContext().getMimeType(fileName);
        if (mimeType.startsWith("image/")) {
            String ext = fileName.substring(fileName.indexOf("."));
            part.write(SAVE_DIR + File.separator + "m_" + actVisitor.getVisitorId() + "_" + imgType.getId() + ext);
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

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
