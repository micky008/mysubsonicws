package com.msc.mysubsonicws.dao;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.dao.daoproject.generic.GenericDaoImpl;
import com.msc.mysubsonicws.entity.Player;

/**
 *
 * @author Michael
 */
public class PlayerDAO extends GenericDaoImpl<Player> {

    public PlayerDAO() {
        super(DAO.getConnection());
    }

    @Override
    protected Object convertFillObjectCustom(Class<?> clazz, Object res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String convertLogicCustom(Class<?> type, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
