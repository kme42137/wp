package wpdemo.image.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.image.dao.model.Image;

/**
 * @author Kovacs Maria
 */
public class PImageDao {
    private Connection con;

    public PImageDao(Connection con) {
        this.con = con;
    }


    public Image create(Image pImage) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO product_image( contact_id, location) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);            
            ps.setLong(1, pImage.getContactId());
            ps.setString(2, pImage.getLocation());
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
            PreparedStatement ps = con.prepareStatement("UPDATE product_image SET contact_id=? location=? WHERE id=?");            
            ps.setLong(1, pImage.getContactId());
            ps.setString(2, pImage.getLocation());
            ps.setLong(3, pOldImageId);
            ps.executeUpdate();
            return pImage;

        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delete(long pIamgeId) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM product_image WHERE id=?");
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
            PreparedStatement ps = con.prepareStatement("SELECT contact_id, location FROM product_image WHERE id=?");
            ps.setLong(1, pImageId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(pImageId);                
                rsImage.setContactId(rs.getLong(1));
                rsImage.setLocation(rs.getString(2));                
                return rsImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Image getForProduct(long pProductId){
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, location FROM product_image WHERE contact_id=?");
            ps.setLong(1, pProductId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Image rsImage = new Image();
                rsImage.setId(rs.getLong(1));                
                rsImage.setContactId(pProductId);
                rsImage.setLocation(rs.getString(2));                
                return rsImage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }        
}
