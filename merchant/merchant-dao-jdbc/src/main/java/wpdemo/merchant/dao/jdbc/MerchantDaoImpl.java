package wpdemo.merchant.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.merchant.dao.model.IMerchant;
import wpdemo.merchant.dao.model.Merchant;
import wpdemo.support.utill.ConnectionUtil;
import wpdemo.town.dao.model.Town;



/**
 * @author Kovacs Maria
 */
public class MerchantDaoImpl implements IMerchant{
    private Connection con;

    public MerchantDaoImpl() {
         try {
            con = ConnectionUtil.getConnection();
        } catch (Exception e) {
            System.exit(100);
        }
    }

    @Override
    public Merchant create(Merchant pMerchant, long pVisitorId) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO merchant(visitor_id, nameToDisplay, introduction, description) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, pVisitorId);
            ps.setString(2, pMerchant.getNameToDisplay());
            ps.setString(3,pMerchant.getIntroduction());
            ps.setString(4, pMerchant.getDescription());            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pMerchant.setId(rs.getLong(1));
                insertTownIds(pMerchant.getId(), pMerchant.getTownIds());
                return pMerchant;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    //visitor_id-hz nem nyul
    @Override
    public Merchant modify(long pOldMerchantId, Merchant pMerchant) {
         try {
            PreparedStatement ps = con.prepareStatement("UPDATE skills SET nameToDisplay=? introduction=? description=? WHERE id=?");
            ps.setString(1, pMerchant.getNameToDisplay());
            ps.setString(2, pMerchant.getIntroduction());
            ps.setString(3, pMerchant.getDescription());            
            ps.setLong(4, pOldMerchantId);
            ps.executeUpdate();
            insertTownIds(pOldMerchantId, pMerchant.getTownIds());
            deleteTownIds(pOldMerchantId, pMerchant.getTownIds());
            return pMerchant;
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean delete(long pMerchantId) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM towns_of_merchant WHERE merchant_id=?");
            ps.setLong(1, pMerchantId);
            ps.executeUpdate();
            ps = con.prepareStatement("DELETE FROM merchant WHERE id=?");
            ps.setLong(1, pMerchantId);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Merchant get(long pMerchantId) {
     try {
            PreparedStatement ps = con.prepareStatement("SELECT nameToDisplay, introduction, description FROM visitor WHERE id=?");
            ps.setLong(1, pMerchantId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Merchant rsMerchant = new Merchant();
                rsMerchant.setId(pMerchantId);
                rsMerchant.setNameToDisplay(rs.getString(1));
                rsMerchant.setIntroduction(rs.getString(2));
                rsMerchant.setDescription(rs.getString(3));
                rsMerchant.setTownIds(getTownIds(pMerchantId));
                return rsMerchant;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Merchant getByProduct(long pProductId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Merchant getByNameToDisplay(String nameToDisplay) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, introduction, description FROM visitor WHERE nameToDisplay=?");
            ps.setString(1, nameToDisplay);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Merchant rsMerchant = new Merchant();
                rsMerchant.setId(rs.getLong(1));
                rsMerchant.setNameToDisplay(nameToDisplay);
                rsMerchant.setIntroduction(rs.getString(2));
                rsMerchant.setDescription(rs.getString(3));
                rsMerchant.setTownIds(getTownIds(rs.getLong(1)));
                return rsMerchant;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Merchant> getByTown(Town pTown) {
        List<Merchant> rsList = new ArrayList<>();
        try {
            PreparedStatement ps =con.prepareStatement("select merchant.id, merchant.nameToDisplay, merchant.introduction, merchant.description " +
            "From merchant INNER JOIN towns_of_merchant ON merchant.id=towns_of_merchant.merchant_id WHERE towns_of_merchant.town_id=?");
            ps.setLong(1, pTown.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Merchant rsMerchant = new Merchant();
                rsMerchant.setId(rs.getLong(1));
                rsMerchant.setNameToDisplay(rs.getString(2));
                rsMerchant.setIntroduction(rs.getString(3));
                rsMerchant.setDescription(rs.getString(4));
                rsMerchant.setTownIds(getTownIds(rs.getLong(1)));
                rsList.add(rsMerchant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsList;
    }

     @Override
    public long getVisitorId(Merchant pMerchant) {
        try {
            PreparedStatement ps = con.prepareStatement("select visitor.id, From merchant INNER JOIN visitor ON merchant.visitor_id=visitor.id WHERE merchant.id=?");
            ps.setLong(1, pMerchant.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {                
                return rs.getLong(1);
            }
        }catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  0;
    }
    
    private void insertTownIds(long pMerchantId, List<Long> pTownIds) {
        try {
            StringBuilder str = new StringBuilder("INSERT IGNORE INTO towns_of_merchant VALUES (");
            str.append(pMerchantId);
            str.append(",");
            str.append(pTownIds.get(0));
            str.append(")");
            int i = 1;
            while (i < pTownIds.size()) {
                str.append(", (");
                str.append(pMerchantId);
                str.append(",");
                str.append(pTownIds.get(i));
                str.append(")");
                i++;
            }
            PreparedStatement ps = con.prepareStatement(str.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     private void deleteTownIds(long pMerchantId, List<Long> pTownIds) {
        try {
            StringBuilder str = new StringBuilder("DELETE FROM towns_of_merchant WHERE town_id NOT IN (");
            str.append(pTownIds.get(0));
            int i = 1;
            while (i < pTownIds.size()) {
                str.append(",");
                str.append(pTownIds.get(i));
                i++;
            }
            str.append(") AND merchant_id=");
            str.append(pMerchantId);
            PreparedStatement ps = con.prepareStatement(str.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private List<Long> getTownIds(long pMerchantId) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT town_id FROM towns_of_merchant WHERE merchant_id=?");
            ps.setLong(1, pMerchantId);
            ResultSet rs = ps.executeQuery();
            List<Long> rsTownIds = new ArrayList<>();
            while (rs.next()) {
                rsTownIds.add(rs.getLong(1));
            }
            return rsTownIds;
        } catch (SQLException ex) {
            Logger.getLogger(MerchantDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
