package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Player;
import java.util.List;

/**
 *
 * @author Michael
 */
public class PlayerDAO extends AbstractDAO<Player> {

    public List<Player> getAll() {
        return this.getObjects("from Player");
    }

    public Player getPlayerById(Integer id) {
        return this.getObject("from Player where id=" + id);
    }

}
