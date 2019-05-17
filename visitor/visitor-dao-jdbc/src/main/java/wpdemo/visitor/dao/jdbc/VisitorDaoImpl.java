package wpdemo.visitor.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.support.utill.ConnectionUtil;
import wpdemo.visitor.dao.model.IVisitorDao;
import wpdemo.visitor.dao.model.Visitor;

/**
 * @author Kovacs Maria
 */
public class VisitorDaoImpl extends ConnectionUtil implements IVisitorDao {   

    @Override
    public Visitor create(Visitor pVisitor) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO visitor(nickname,firstName, lastName, eMail, password,isMerchant) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pVisitor.getNickname());
            ps.setString(2, pVisitor.getFirstname());
            ps.setString(3, pVisitor.getLastName());
            ps.setString(4, pVisitor.geteMail());
            ps.setString(5, pVisitor.getPassword());
            ps.setBoolean(6, pVisitor.isIsMerchant());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pVisitor.setVisitorId(rs.getLong(1));
                return pVisitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Visitor modify(long pOldVisitorId, Visitor pVisitor) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE visitor SET nickname=?, firstName=?, lastName=?, eMail=?, password=?, isMerchant=? WHERE id=?");
            ps.setString(1, pVisitor.getNickname());
            ps.setString(2, pVisitor.getFirstname());
            ps.setString(3, pVisitor.getLastName());
            ps.setString(4, pVisitor.geteMail());
            ps.setString(5, pVisitor.getPassword());
            ps.setBoolean(6, pVisitor.isIsMerchant());
            ps.setLong(7, pOldVisitorId);
            ps.executeUpdate();
            pVisitor.setVisitorId(pOldVisitorId); // Ez kell???
            return pVisitor;
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public boolean delete(long pVisitorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Visitor get(long pVisitorId) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT nickname,firstName, lastName, eMail, password,isMerchant FROM visitor WHERE id=?");
            ps.setLong(1, pVisitorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Visitor rsVisitor = new Visitor();
                rsVisitor.setVisitorId(pVisitorId);
                rsVisitor.setNickname(rs.getString(1));
                rsVisitor.setFirstname(rs.getString(2));
                rsVisitor.setLastName(rs.getString(3));
                rsVisitor.seteMail(rs.getString(4));
                rsVisitor.setPassword(rs.getString(5));
                rsVisitor.setIsMerchant(rs.getBoolean(6));
                return rsVisitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Visitor getByName(String pNickname) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id,firstName, lastName, eMail, password,isMerchant FROM visitor WHERE nickname=?");
            ps.setString(1, pNickname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Visitor rsVisitor = new Visitor();
                rsVisitor.setVisitorId(rs.getLong(1));
                rsVisitor.setNickname(pNickname);
                rsVisitor.setFirstname(rs.getString(2));
                rsVisitor.setLastName(rs.getString(3));
                rsVisitor.seteMail(rs.getString(4));
                rsVisitor.setPassword(rs.getString(5));
                rsVisitor.setIsMerchant(rs.getBoolean(6));
                return rsVisitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Visitor getByEmail(String pEmail) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, nickname, firstName, lastName, password,isMerchant FROM visitor WHERE eMail=?");
            ps.setString(1, pEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Visitor rsVisitor = new Visitor();
                rsVisitor.setVisitorId(rs.getLong(1));
                rsVisitor.setNickname(rs.getString(2));
                rsVisitor.setFirstname(rs.getString(3));
                rsVisitor.setLastName(rs.getString(4));
                rsVisitor.seteMail(pEmail);
                rsVisitor.setPassword(rs.getString(5));
                rsVisitor.setIsMerchant(rs.getBoolean(6));
                return rsVisitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public long getMerchantId(Visitor pVisitor) {
        if(!pVisitor.isIsMerchant()){
            return 0;
        }
        try {
            PreparedStatement ps = con.prepareStatement("select merchant.id From merchant INNER JOIN visitor ON merchant.visitor_id=visitor.id WHERE visitor.id=?");
            ps.setLong(1, pVisitor.getVisitorId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Visitor login(String pName, String pPassword) {
         try {
            PreparedStatement ps = con.prepareStatement("SELECT id,firstName, lastName, eMail, isMerchant FROM visitor WHERE nickname=? AND password=? AND BINARY(nickname) = BINARY(?) AND BINARY(password) = BINARY(?)");
            ps.setString(1, pName);
            ps.setString(2, pPassword);
            ps.setString(3, pName);
            ps.setString(4, pPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Visitor rsVisitor = new Visitor();
                rsVisitor.setVisitorId(rs.getLong(1));
                rsVisitor.setNickname(pName);
                rsVisitor.setFirstname(rs.getString(2));
                rsVisitor.setLastName(rs.getString(3));
                rsVisitor.seteMail(rs.getString(4));
                rsVisitor.setPassword(pPassword);
                rsVisitor.setIsMerchant(rs.getBoolean(5));
                return rsVisitor;
            }        
            ps = con.prepareStatement("SELECT id, nickname, firstName, lastName, isMerchant FROM visitor WHERE eMail=? AND password=?");
            ps.setString(1, pName);
            ps.setString(2, pPassword);
            rs = ps.executeQuery();
            if (rs.next()) {
                Visitor rsVisitor = new Visitor();
                rsVisitor.setVisitorId(rs.getLong(1));
                rsVisitor.setNickname(rs.getString(2));
                rsVisitor.setFirstname(rs.getString(3));
                rsVisitor.setLastName(rs.getString(4));
                rsVisitor.seteMail(pName);
                rsVisitor.setPassword(pPassword);
                rsVisitor.setIsMerchant(rs.getBoolean(5));
                return rsVisitor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
