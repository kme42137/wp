package wpdemo.product.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.product.dao.model.IProduct;
import wpdemo.product.dao.model.Product;
import wpdemo.product.dao.model.ProductType;
import wpdemo.support.utill.ConnectionUtil;

/**
 * @author Kovacs Maria
 */
public class ProductDaoImpl extends ConnectionUtil implements IProduct {
       
    
    @Override
    public Product create(Product pProduct) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO product(name, merchant_id, product_type, description) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pProduct.getName());
            ps.setLong(2, pProduct.getMerchantId());
            ps.setInt(3, pProduct.getType().getId());
            ps.setString(4, pProduct.getDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pProduct.setId(rs.getLong(1));
                return pProduct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Product modify(long pOldProductId, Product pProduct) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE product SET name=?, product_type=?, description=? WHERE id=?");
            ps.setString(1, pProduct.getName());
            ps.setInt(2, pProduct.getType().getId());
            ps.setString(3, pProduct.getDescription());
            ps.setLong(4, pOldProductId);
            ps.executeUpdate();
            return pProduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public boolean delete(long pProductId) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE id=?");
            ps.setLong(1, pProductId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public Product get(long pProductId) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT name, merchant_id, product_type, description FROM product WHERE id=?");
            ps.setLong(1, pProductId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product rsProduct = new Product();
                rsProduct.setId(pProductId);
                rsProduct.setName(rs.getString(1));
                rsProduct.setMerchantId(rs.getLong(2));
                rsProduct.setType(ProductType.values()[rs.getInt(3)]);
                rsProduct.setDescription(rs.getString(4));
                return rsProduct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public List<Product> getByMerchant(long pMerchantId) {
        List<Product> rsList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, name, product_type, description FROM product WHERE merchant_id=?");
            ps.setLong(1, pMerchantId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product rsProduct = new Product();
                rsProduct.setId(rs.getLong(1));
                rsProduct.setName(rs.getString(2));
                rsProduct.setMerchantId(pMerchantId);
                rsProduct.setType(ProductType.values()[rs.getInt(3)]);
                rsProduct.setDescription(rs.getString(4));
                rsList.add(rsProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsList;
    }
    
    @Override
    public List<Product> getByType(ProductType pType) {
        List<Product> rsList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, name, merchant_id, description FROM product WHERE product_type=?");
            ps.setLong(1, pType.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product rsProduct = new Product();
                rsProduct.setId(rs.getLong(1));
                rsProduct.setName(rs.getString(2));
                rsProduct.setMerchantId(rs.getLong(3));
                rsProduct.setType(pType);
                rsProduct.setDescription(rs.getString(4));
                rsList.add(rsProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsList;
    }
    
    @Override
    public List<Product> search(ProductType pType, long pTownId, String pString) {
        List<Product> rsList = new ArrayList<>();
        if (pString.isEmpty() && pType.getId() == 0 && pTownId == 0) {
            return null;
        }
        pString = ConnectionUtil.serchString(pString);        
        String sqlString = "";
            if(pTownId==0){
                sqlString="SELECT id, name, merchant_id, product_type, description FROM product WHERE ";                
                if(pString.isEmpty()){
                    sqlString+="product_type="+pType.getId();                    
                }else if(pType==ProductType.NONE){                   
                    sqlString+="MATCH(name, description) AGAINST('"+pString+"' IN BOOLEAN MODE )";
                }else{
                    sqlString+="MATCH(name, description) AGAINST('"+pString+"' IN BOOLEAN MODE ) AND product_type="+pType.getId();                    
                }
            }else{
                sqlString="SELECT DISTINCT product.id, product.name, product.merchant_id, product.product_type, product.description FROM product INNER JOIN towns_of_merchant ON product.merchant_id=towns_of_merchant.merchant_id WHERE towns_of_merchant.town_id="+pTownId;                
                if(pType!=ProductType.NONE){
                    sqlString+=" AND product.product_type="+pType.getId();
                }
                if(!pString.isEmpty()){
                    sqlString+=" AND MATCH(product.name, product.description) AGAINST('"+pString+"' IN BOOLEAN MODE )";                    
                }
            }
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                Product rsProduct = new Product();
                rsProduct.setId(rs.getLong(1));
                rsProduct.setName(rs.getString(2));
                rsProduct.setMerchantId(rs.getLong(3));
                rsProduct.setType(ProductType.values()[rs.getInt(4)]);
                rsProduct.setDescription(rs.getString(5));
                rsList.add(rsProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsList;
    }
}
