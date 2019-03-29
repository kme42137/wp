package wpdemo.merchant.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import wpdemo.merchant.dao.model.IMerchant;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.support.utill.ConnectionUtil;

/**
 * @author Kovacs Maria
 */
public class MerchantDaoImpl implements IMerchant{
    private Connection con;

    public MerchantDaoImpl() {
         try {
            con = ConnectionUtil.getConnection();
        } catch (Exception e) {
            System.exit(100);
        }
    }

    @Override
    public Merchant create(Merchant pMerchant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Merchant modify(long pOldMerchantId, Merchant pMerchant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(long pMerchantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Merchant get(long pMerchantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Merchant getByProduct(long pProductId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Merchant> getByNameToDisplay(String nameToDisplay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
