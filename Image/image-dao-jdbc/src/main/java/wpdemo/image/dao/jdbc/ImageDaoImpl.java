package wpdemo.image.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public ImageDaoImpl() {
        try {
            con = ConnectionUtil.getConnection();
        } catch (Exception e) {
            System.exit(100);
        }
    }

    public Image create(Image pImage) {
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

    public Image modify(long pOldImageId, Image pImage) {
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

    public boolean delete(long pIamgeId) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM image WHERE id=?");
            ps.setLong(1, pIamgeId);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Image get(long pImageId) {
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

    public Image getForProduct(long pProductId){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, location FROM image WHERE contact_id=? AND img_type=0");
            ps.setLong(1, pProductId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(rs.getLong(1));
                rsImage.setType(ImageType.PRODUCT);
                rsImage.setContactId(pProductId);
                rsImage.setLocation(rs.getString(2));                
                return rsImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    

    public List<Image> getForMerchant(long pMerchantId){
        List<Image> rsList= new ArrayList<>();
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
                rsList.add(rsImage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsList;
    }

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
