package wpdemo.merchant.dao.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kovacs Maria
 */
public class Merchant {

    private long id;
    private String nameToDisplay;
    private String introduction;
    private String description;
    private List<Long> townIds = new ArrayList<>();

    public Merchant() {
        super();
    }

    public Merchant(long id, String nameToDisplay, String introduction, String description, List<Long> townIds) {
        this.id = id;
        this.nameToDisplay = nameToDisplay;
        this.introduction = introduction;
        this.description = description;
        this.townIds = townIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Long> getTownIds() {
        return townIds;
    }

    public void setTownIds(List<Long> townIds) {
        this.townIds = townIds;
    }
}
