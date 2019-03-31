package wpdemo.town.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wpdemo.support.utill.ConnectionUtil;
import wpdemo.town.dao.model.ITownDao;
import wpdemo.town.dao.model.Town;

/**
 * @author Kovacs Maria
 */
public class TownDaoImpl implements ITownDao{
    private Connection con;

    public TownDaoImpl() {
         try {
            con = ConnectionUtil.getConnection();
        } catch (Exception e) {
            System.exit(100);
        }
    }

    @Override
    public Town get(long pTownId) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT name FROM town WHERE id=?");
            ps.setLong(1, pTownId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Town rsTown = new Town();
                rsTown.setName(rs.getString(1));
                rsTown.setId(pTownId);
                return rsTown;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TownDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
