package wpdemo.merchant.dao.model;

import wpdemo.support.utill.VisitorBase;

/**
 * @author Kovacs Maria
 */
public class Merchant extends VisitorBase{
    private String nameToDisplay;
    private String introduction;
    private String description;

    public Merchant() {
        super();
    }

    public Merchant(String nameToDisplay, String introduction, String description, long id, String firstName, String lastName, String eMail) {
        super(id, firstName, lastName, eMail);
        this.nameToDisplay = nameToDisplay;
        this.introduction = introduction;
        this.description = description;
    }

    public String getNameToDisplay() {
        return nameToDisplay;
    }

    public void setNameToDisplay(String nameToDisplay) {
        this.nameToDisplay = nameToDisplay;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
