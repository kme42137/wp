package wpdemo.product.dao.model;

import java.util.List;

/**
 * @author Kovacs Maria
 */
public interface IProduct {
    
    public Product create(Product pProduct);

    public Product modify(long pOldProductId, Product pProduct);

    public boolean delete(long pProductId);

    public Product get(long pProductId);       
    
    public List<Product> getByMerchant(long pMerchantId);
    
    public List<Product> getByType(ProductType pType);
    
    public List<Product> search(ProductType pType, long pTownId, String pString);
}
