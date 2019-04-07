package wpdemo.visitor.service.object;

import java.util.regex.Pattern;
import wpdemo.support.utill.WPErrors;
import wpdemo.support.utill.WPException;
import wpdemo.visitor.dao.jdbc.VisitorDaoImpl;
import wpdemo.visitor.dao.model.IVisitorDao;
import wpdemo.visitor.dao.model.Visitor;

/**
 * @author Kovacs Maria
 */
public class VisitorServiceImpl {

    private IVisitorDao dao = new VisitorDaoImpl();

    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
    public Visitor create(Visitor pVisitor) {
        if (pVisitor.getNickname() == null || pVisitor.getNickname().isEmpty()
                || pVisitor.getFirstname() == null || pVisitor.getFirstname().isEmpty()
                || pVisitor.getLastName() == null || pVisitor.getLastName().isEmpty()
                || pVisitor.geteMail() == null || pVisitor.geteMail().isEmpty()
                || pVisitor.getPassword() == null || pVisitor.getPassword().isEmpty()) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        }
        if (dao.getByName(pVisitor.getNickname()) != null) {
            throw new WPException(WPErrors.USED_NICKNAME);
        }
        if (dao.getByEmail(pVisitor.geteMail()) != null) {
            throw new WPException(WPErrors.USED_EMAIL);
        } 
        if(!isValid(pVisitor.geteMail())){
            throw new WPException(WPErrors.INVALID_EMAIL);
        }
        if(pVisitor.getPassword().length()<7){
            throw new WPException(WPErrors.SHORT_PASSWORD);
        }else {
            return dao.create(pVisitor);
        }
    }

    public Visitor modify(long pOldVisitorId, Visitor pVisitor){
    if (pVisitor.getNickname() == null || pVisitor.getNickname().isEmpty()
                || pVisitor.getFirstname() == null || pVisitor.getFirstname().isEmpty()
                || pVisitor.getLastName() == null || pVisitor.getLastName().isEmpty()
                || pVisitor.geteMail() == null || pVisitor.geteMail().isEmpty()
                || pVisitor.getPassword() == null || pVisitor.getPassword().isEmpty()) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        }
        Visitor temp = dao.getByName(pVisitor.getNickname());
        if (temp != null && !temp.getNickname().equals(dao.get(pOldVisitorId).getNickname())) {
            throw new WPException(WPErrors.USED_NICKNAME);
        }
        temp=dao.getByEmail(pVisitor.geteMail());
        if (temp != null && !temp.geteMail().equals(dao.get(pOldVisitorId).geteMail())) {
            throw new WPException(WPErrors.USED_NICKNAME);
        } else {
            return dao.create(pVisitor);
        }
    
    }

   /* public boolean delete(long pVisitorId);*/

    public Visitor get(long pVisitorId){
        return dao.get(pVisitorId);
    }

    public Visitor getByName(String pNickname){
        return dao.getByName(pNickname);
    }

    public Visitor getByEmail(String pEmail){
        return dao.getByEmail(pEmail);
    }
    
    public long getMerchantId(Visitor pVisitor){
        return dao.getMerchantId(pVisitor);
    }
    
    public Visitor login(String pName, String pPassword){
        if (pName == null || pName.isEmpty() || pPassword == null || pPassword.isEmpty()) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        }
        Visitor visitorIn = dao.login(pName, pPassword);
        if(visitorIn !=null){
            return visitorIn;
        }
        throw new WPException(WPErrors.LOGIN_DENIED);
    }
}
