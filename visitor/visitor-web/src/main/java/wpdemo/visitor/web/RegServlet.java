package wpdemo.visitor.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wpdemo.support.utill.WPException;
import wpdemo.visitor.dao.model.Visitor;
import wpdemo.visitor.service.object.VisitorServiceImpl;

/**
 * @author Kovacs Maria
 */
@WebServlet(name = "RegServlet", urlPatterns = {"/reg"})
public class RegServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/reg.jsp").include(request, response);

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
        Visitor newVisitor = new Visitor();
        Map<String, String> messages = new HashMap<>();
        boolean inputOk = false;
        newVisitor.setNickname(request.getParameter("pnickname"));
        newVisitor.setLastName(request.getParameter("plastname"));
        newVisitor.setFirstname(request.getParameter("pfirstname"));
        newVisitor.seteMail(request.getParameter("pemail"));
        if (request.getParameter("ppassword1").equals(request.getParameter("ppassword2"))) {
            newVisitor.setPassword(request.getParameter("ppassword1"));
        } else {
            messages.put("ppassword1", "Nem egyezik a ket megadott jelszo.");
        }
        newVisitor.setIsMerchant(true);
        VisitorServiceImpl visitorServ = new VisitorServiceImpl();
        try {
            visitorServ.create(newVisitor);
            inputOk = true;
        } catch (WPException e) {
            switch (e.errCode) {
                case 0:
                    if (newVisitor.getNickname() == null || newVisitor.getNickname().isEmpty()) {
                        messages.put("pnickname", e.getMessage());
                    }
                    if (newVisitor.getLastName() == null || newVisitor.getLastName().isEmpty()) {
                        messages.put("plastname", e.getMessage());
                    }
                    if (newVisitor.getFirstname() == null || newVisitor.getFirstname().isEmpty()) {
                        messages.put("pfirstname", e.getMessage());
                    }
                    if (newVisitor.geteMail() == null || newVisitor.geteMail().isEmpty()) {
                        messages.put("pemail", e.getMessage());
                    }
                    if (newVisitor.getPassword() == null || newVisitor.getPassword().isEmpty()) {
                        if (messages.get("ppassword1") == null) {
                            messages.put("ppassword1", e.getMessage());
                        }
                    }
                    break;
                case 1:
                    messages.put("pnickname", e.getMessage());
                    break;
                case 2:
                    messages.put("pemail", e.getMessage());
                    break;
                case 3:
                    messages.put("pemail", e.getMessage());
                case 4:
                    if (messages.get("ppassword1") == null) {
                        messages.put("ppassword1", e.getMessage());
                    }
                    break;
            }
        }
        if (!inputOk) {
            request.setAttribute("userinput", newVisitor);
            request.setAttribute("messages", messages);
            doGet(request, response);
        } else {
            response.sendRedirect("/login.jsp");           
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
