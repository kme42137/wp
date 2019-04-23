package wpdemo.image.service.object;

import java.util.List;
import wpdemo.image.dao.jdbc.ImageDaoImpl;
import wpdemo.image.dao.model.IImage;
import wpdemo.image.dao.model.Image;
import wpdemo.image.dao.model.ImageType;

/**
 * @author Kovacs Maria
 */
public class ImageSerciceImpl {
    private IImage dao = new ImageDaoImpl();
    
    public Image create(Image pImage){
        return dao.create(pImage);
    }
    
    public Image modify(long pOldImageId, Image pImage){
        return dao.modify(pOldImageId, pImage);
    }

    public boolean delete(long pIamgeId){
        return dao.delete(pIamgeId);
    }

    public Image get(long pImageId){
        return dao.get(pImageId);
    }
    
    public Image getForProduct(long pProductId){
       return dao.getForProduct(pProductId);
    }
    
    public List<Image> getForMerchant(long pMerchantId){
        return dao.getForMerchant(pMerchantId);
    }
    
    public Image getByTypeForMerhant (long pMerchantId, ImageType pType){
        return dao.getByTypeForMerhant(pMerchantId, pType);
    }
}
