package wpdemo.image.dao.model;

import java.util.List;

/**
 * @author Kovacs Maria
 */
public interface IImage {
    
    public Image create(Image pImage);
    
    public Image modify(long pOldImageId, Image pImage);

    public boolean delete(long pIamgeId);

    public Image get(long pImageId);
    
    public Image getForProduct(long pProductId);
    
    public List<Image> getForMerchant(long pMerchantId);
    
    public Image getByTypeForMerhant (long pMerchantId, ImageType pType);
    
}
