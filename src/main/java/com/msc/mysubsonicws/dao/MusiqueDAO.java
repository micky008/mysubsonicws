package com.msc.mysubsonicws.dao;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.dao.daoproject.generic.GenericDaoImpl;
import com.msc.dao.daoproject.helper.SearchById;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.entity.Player;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class MusiqueDAO extends GenericDaoImpl<Musique> {

    @Override
    protected Object convertFillObjectCustom(Class<?> clazz, Object res) {
        return null;
    }

    @Override
    protected String convertLogicCustom(Class<?> type, Object o) {
        return null;
    }

    public MusiqueDAO() {
        super(DAO.getConnection());
    }

    public Musique getMusiqueById(String id) throws SQLException {
        SearchById sbi = new SearchById(null, id);
        try {
            sbi.setF(Musique.class.getDeclaredField("id"));
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(MusiqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(MusiqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.getObjectsById(sbi).get(0);
    }

    public List<Musique> getMusiquesByPlayer(int playerId) throws SQLException {
        String pmpName = FactoryDAO.playerMusicPlayerDAO.getTableName();
        return preparedSelectMulti("m ," + pmpName + " p, where p.musique_id = m.id and p.id=" + playerId, "m");
    }

    public List<Musique> getMusiquesByFolder(String folderId) throws SQLException {
        String folderName = FactoryDAO.folderDAO.getTableName();
        return preparedSelectMulti("m ," + folderName + " f, where f.id = m.folder_id and f.id=" + folderId, "m");
    }

}
