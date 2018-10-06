package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.util.List;

/**
 *
 * @author Michael
 */
public class PlayerMusiquePlaceDAO extends AbstractDAO<PlayerMusiquePlace> {

    public List<PlayerMusiquePlace> getPmpByPlayer(Integer idPlayer) {
        return this.getObjects("from PlayerMusiquePlace pmp where pmp.player.id = " + idPlayer + " order by pmp.place");
    }
    
    public List<PlayerMusiquePlace> deleteByPlayer(Player player ){
        List<PlayerMusiquePlace> lpmp = getPmpByPlayer(player.getId());
        this.delete(lpmp);
        return lpmp;
    }
    

}
