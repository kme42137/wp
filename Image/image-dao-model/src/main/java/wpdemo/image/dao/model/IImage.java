package wpdemo.image.dao.model;

import java.util.List;
import java.util.Map;

/**
 * @author Kovacs Maria
 */
public interface IImage {
    
    public Image create(Image pImage);
    
    public Image modify(long pOldImageId, Image pImage);

    public boolean delete(long pIamgeId);

    public Image get(long pImageId);
    
    public Image getForProduct(long pProductId);
    
    public Map<Integer, Image> getForMerchant(long pMerchantId);
    
    public Image getByTypeForMerhant (long pMerchantId, ImageType pType);
    
}
