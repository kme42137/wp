package wpdemo.merchant.dao.model;

import java.util.List;
import wpdemo.town.dao.model.Town;


/**
 * @author Kovacs Maria
 */
public interface IMerchant {
    
    public Merchant create(Merchant pMerchant, long pVisitorId);

    public Merchant modify(long pOldMerchantId, Merchant pMerchant);

    public boolean delete(long pMerchantId);

    public Merchant get(long pMerchantId);
    
    public Merchant getByProduct(long pProductId);
    
    public Merchant getByNameToDisplay(String nameToDisplay);
    
    public List<Merchant> getByTown(Town pTown); 
    
    public long getVisitorId(Merchant pMerchant);
}
