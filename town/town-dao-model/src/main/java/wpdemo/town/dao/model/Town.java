package wpdemo.town.dao.model;

/**
 * @author Kovacs Maria
 */
public class Town {

    public long id;
    public String name;
    public String county;

    public Town() {
    }

    public Town(long id, String name, String county) {
        this.id = id;
        this.name = name;
        this.county = county;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
