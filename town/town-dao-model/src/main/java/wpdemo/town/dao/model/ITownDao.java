package wpdemo.town.dao.model;

import java.util.List;

/**
 * @author Kovacs Maria
 */
public interface ITownDao {

    public Town get(long pTownId);
    
    public List<Town> getByCounty(String pCounty);
    
    public List<Town> getAll();
}
