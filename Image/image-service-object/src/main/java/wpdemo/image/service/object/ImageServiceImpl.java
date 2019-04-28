package wpdemo.image.service.object;

import java.util.Map;
import wpdemo.image.dao.jdbc.ImageDaoImpl;
import wpdemo.image.dao.model.IImage;
import wpdemo.image.dao.model.Image;
import wpdemo.image.dao.model.ImageType;

/**
 * @author Kovacs Maria
 */
public class ImageServiceImpl {
    private IImage dao = new ImageDaoImpl();
    
    public Image create(Image pImage){
       if(pImage.getType()==ImageType.PRODUCT && dao.getForProduct(pImage.getContactId())==null ||
               pImage.getType()!= ImageType.PRODUCT && dao.getByTypeForMerhant(pImage.getContactId(), pImage.getType())==null){
        return dao.create(pImage);
       }else{
       return null;
       }
    }
    
    public Image modify(long pOldImageId, Image pImage){
        return dao.modify(pOldImageId, pImage);
    }

    public boolean delete(Image pImage){
        return dao.delete(pImage);
    }

     public Image get(long pImageId, boolean isProduct){
        return dao.get(pImageId, isProduct);
    }
    
    public Image getForProduct(long pProductId){
       return dao.getForProduct(pProductId);
    }
    
    public Map<Integer, Image> getForMerchant(long pMerchantId){
        return dao.getForMerchant(pMerchantId);
    }
    
    public Image getByTypeForMerhant (long pMerchantId, ImageType pType){
        return dao.getByTypeForMerhant(pMerchantId, pType);
    }
}
