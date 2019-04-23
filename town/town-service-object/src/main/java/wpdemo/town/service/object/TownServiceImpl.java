package wpdemo.town.service.object;

import java.util.List;
import wpdemo.town.dao.jdbc.TownDaoImpl;
import wpdemo.town.dao.model.ITownDao;
import wpdemo.town.dao.model.Town;

/**
 * @author Kovacs Maria
 */
public class TownServiceImpl {
    private ITownDao dao = new TownDaoImpl();
    
    public Town get(long pTownId){
        return dao.get(pTownId);
    }
    
    public List<Town> getByCounty(String pCounty){
        return dao.getByCounty(pCounty);
    }
    
    public List<Town> getAll(){
        return dao.getAll();
    }
}
