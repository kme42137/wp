package wpdemo.product.service.object;

import java.util.List;
import wpdemo.image.dao.model.Image;
import wpdemo.image.service.object.ImageServiceImpl;
import wpdemo.product.dao.jdbc.ProductDaoImpl;
import wpdemo.product.dao.model.IProduct;
import wpdemo.product.dao.model.Product;
import wpdemo.product.dao.model.ProductType;
import wpdemo.support.utill.WPErrors;
import wpdemo.support.utill.WPException;

/**
 * @author Kovacs Maria
 */
public class ProductServiceImpl {

    private IProduct dao = new ProductDaoImpl();

    public Product create(Product pProduct) {
        if (dao.get(pProduct.getId()) != null) {
            return null;
        } else if (pProduct.getName() == null || pProduct.getName().isEmpty()
                || pProduct.getDescription() == null || pProduct.getDescription().isEmpty() 
                || pProduct.getType()==ProductType.NONE) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        } else {
            return dao.create(pProduct);
        }
    }

    public Product modify(long pOldProductId, Product pProduct){
        if (pProduct.getName() == null || pProduct.getName().isEmpty()
                || pProduct.getDescription() == null || pProduct.getDescription().isEmpty()
                || pProduct.getType()==ProductType.NONE) {
            throw new WPException(WPErrors.EMPTY_FIELD);
        } else {
            return dao.modify(pOldProductId, pProduct);
        }
    }

    public boolean delete(long pProductId){
        ImageServiceImpl imageServ= new ImageServiceImpl();
        Image img = imageServ.getForProduct(pProductId);
        imageServ.delete(img);
        return dao.delete(pProductId);
    }

    public Product get(long pProductId){
        return dao.get(pProductId);
    }

    public List<Product> getByMerchant(long pMerchantId){
        return dao.getByMerchant(pMerchantId);
    }

    public List<Product> getByType(ProductType pType){
        return dao.getByType(pType);
    }

    public List<Product> search(ProductType pType, long pTownId, String pString){
        return dao.search(pType, pTownId, pString);
    }

}
