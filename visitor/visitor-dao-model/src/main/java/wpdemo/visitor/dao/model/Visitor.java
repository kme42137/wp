package wpdemo.visitor.dao.model;

/**
 * @author Kovacs Maria
 */
public class Visitor {
    protected long visitorId;
    protected String nickname;
    protected String firstname;
    protected String lastName;
    protected String eMail;
    protected String password;    
    private boolean isMerchant;
    
    public Visitor() {
    }

    public Visitor(long visitorId, String nickname, String firstname, String lastName, String eMail, String password, boolean isMerchant) {
        this.visitorId = visitorId;
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.isMerchant = isMerchant;
    }

    public long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(long visitorId) {
        this.visitorId = visitorId;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsMerchant() {
        return isMerchant;
    }

    public void setIsMerchant(boolean isMerchant) {
        this.isMerchant = isMerchant;
    }   
}
