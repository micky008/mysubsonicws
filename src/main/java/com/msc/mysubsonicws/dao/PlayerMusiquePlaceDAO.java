package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.util.List;

/**
 *
 * @author Michael
 */
public class PlayerMusiquePlaceDAO extends AbstractDAO<PlayerMusiquePlace> {
    
    public List<PlayerMusiquePlace> getPmpByPlayer(Integer idPlayer){
        return this.getObjects("select pmp from PlayerMusiquePlace pmp, Player p where p.id= "+idPlayer);
    }
    
}
