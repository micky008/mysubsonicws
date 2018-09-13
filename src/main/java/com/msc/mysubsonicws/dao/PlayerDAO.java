package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.util.List;

/**
 *
 * @author Michael
 */
public class PlayerDAO extends AbstractDAO<Player> { 
    
    public  List<Player> getAll(){
        return this.getObjects("from Player");
    }

    

}
