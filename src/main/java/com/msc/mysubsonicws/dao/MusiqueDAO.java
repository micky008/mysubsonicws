package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Musique;
import java.util.List;

/**
 *
 * @author Michael
 */
public class MusiqueDAO extends AbstractDAO<Musique> {    

    public Musique getMusiqueById(String id) {
        return this.getObject("from Musique where id='" + id + "'");
    }

    public List<Musique> getMusiquesByPlayer(int playerId) {
        return this.getObjects("select pmp.musique from PlayerMusiquePlaceDAO pmp where player =" + playerId);
    }

    public List<Musique> getMusiquesByFolder(String folderId) {
        return this.getObjects("from Musique where folderId='" + folderId + "'");
    }

}
