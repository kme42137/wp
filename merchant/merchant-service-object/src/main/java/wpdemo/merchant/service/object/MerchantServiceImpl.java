package wpdemo.merchant.service.object;

import java.util.List;
import wpdemo.merchant.dao.jdbc.MerchantDaoImpl;
import wpdemo.merchant.dao.model.IMerchant;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.support.utill.WPErrors;
import wpdemo.support.utill.WPException;
import wpdemo.town.dao.model.Town;

/**
 * @author Kovacs Maria
 */
public class MerchantServiceImpl {

    private IMerchant dao= new MerchantDaoImpl();
    
    public Merchant create(Merchant pMerchant){
        if(dao.getByVisitor(pMerchant.getVisitorId())!=null){
            return null;
        }else if (pMerchant.getNameToDisplay()== null || pMerchant.getNameToDisplay().isEmpty()
                || pMerchant.getIntroduction()== null || pMerchant.getIntroduction().isEmpty()
                || pMerchant.getDescription()== null || pMerchant.getDescription().isEmpty()
                || pMerchant.getTownIds()== null || pMerchant.getTownIds().isEmpty()) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        }else{
            return dao.create(pMerchant);
        }
    }

    public Merchant modify(long pOldMerchantId, Merchant pMerchant){
            if (pMerchant.getNameToDisplay()== null || pMerchant.getNameToDisplay().isEmpty()
                || pMerchant.getIntroduction()== null || pMerchant.getIntroduction().isEmpty()
                || pMerchant.getDescription()== null || pMerchant.getDescription().isEmpty()
                || pMerchant.getTownIds()== null || pMerchant.getTownIds().isEmpty()) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        }else{
            return dao.modify(pOldMerchantId, pMerchant);
        }
    }            

    public boolean delete(long pMerchantId){
        return dao.delete(pMerchantId);
    }

    public Merchant get(long pMerchantId){
        return dao.get(pMerchantId);
    }
    
    public Merchant getByVisitor(long pVisitorId){
        return dao.getByVisitor(pVisitorId);
    }
    
    public Merchant getByProduct(long pProductId){
        return dao.getByProduct(pProductId);
    }
    
    public List<Merchant> getByNameToDisplay(String nameToDisplay){
        return dao.getByNameToDisplay(nameToDisplay);
    }            
    
    public List<Merchant> getByTown(Town pTown){
        return dao.getByTown(pTown);
    } 
    
        public List<Merchant> search(long pTownId, String pString){
            return dao.search(pTownId, pString);
        }
        
}
