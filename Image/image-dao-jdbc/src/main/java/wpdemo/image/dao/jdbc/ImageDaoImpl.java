package wpdemo.image.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.image.dao.model.IImage;
import wpdemo.image.dao.model.Image;
import wpdemo.image.dao.model.ImageType;
import wpdemo.support.utill.ConnectionUtil;

/**
 * @author Kovacs Maria
 */
public class ImageDaoImpl implements IImage {

    private Connection con;
    private PImageDao imgDao;

    public ImageDaoImpl() {
        try {
            con = ConnectionUtil.getConnection();
            imgDao = new PImageDao();
        } catch (Exception e) {
            System.exit(100);
        }
    }

    @Override
    public Image create(Image pImage) {
        if(pImage.getType()==ImageType.PRODUCT){
            return imgDao.create(pImage);
        }
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO image(img_type, contact_id, location) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pImage.getType().getId());
            ps.setLong(2, pImage.getContactId());
            ps.setString(3, pImage.getLocation());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pImage.setId(rs.getLong(1));
                return pImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Image modify(long pOldImageId, Image pImage) {
        if(pImage.getType()==ImageType.PRODUCT){
            return imgDao.modify(pOldImageId, pImage);
        }
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE image SET img_type=? contact_id=? location=? WHERE id=?");
            ps.setInt(1, pImage.getType().getId());
            ps.setLong(2, pImage.getContactId());
            ps.setString(3, pImage.getLocation());
            ps.setLong(4, pOldImageId);
            ps.executeUpdate();
            return pImage;

        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean delete(Image pImage) {
        if(pImage.getType()==ImageType.PRODUCT){
            return imgDao.delete(pImage.getId());
        }
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM image WHERE id=?");
            ps.setLong(1, pImage.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Image get(long pImageId, boolean isProduct) {
        if(isProduct){
            return imgDao.get(pImageId);
        }
        try {
            PreparedStatement ps = con.prepareStatement("SELECT img_type, contact_id, location FROM image WHERE id=?");
            ps.setLong(1, pImageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(pImageId);
                rsImage.setType(ImageType.values()[rs.getInt(1)]);
                rsImage.setContactId(rs.getLong(2));
                rsImage.setLocation(rs.getString(3));                
                return rsImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Image getForProduct(long pProductId){
        return imgDao.getForProduct(pProductId);
    }    

    @Override
    public Map<Integer, Image> getForMerchant(long pMerchantId){
        Map<Integer, Image> rsMap = new HashMap<>();
         try {
            PreparedStatement ps = con.prepareStatement("SELECT id, img_type, location FROM image WHERE contact_id=? AND img_type!=0");
            ps.setLong(1, pMerchantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(rs.getLong(1));
                rsImage.setType(ImageType.values()[rs.getInt(2)]);
                rsImage.setContactId(pMerchantId);
                rsImage.setLocation(rs.getString(3));
                rsMap.put(rs.getInt(2), rsImage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsMap;
    }

    @Override
    public Image getByTypeForMerhant(long pMerchantId, ImageType pType){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, location FROM image WHERE contact_id=? AND img_type=?");
            ps.setLong(1, pMerchantId);
            ps.setInt(2, pType.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(rs.getLong(1));
                rsImage.setType(pType);
                rsImage.setContactId(pMerchantId);
                rsImage.setLocation(rs.getString(2));                
                return rsImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
