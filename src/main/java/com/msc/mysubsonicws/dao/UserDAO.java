package com.msc.mysubsonicws.dao;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.dao.daoproject.generic.GenericDaoImpl;
import com.msc.mysubsonicws.entity.User;

/**
 *
 * @author Michael
 */
public class UserDAO extends GenericDaoImpl<User>{

    public UserDAO() {
        super(DAO.getConnection());
    }

    @Override
    protected Object convertFillObjectCustom(Class<?> clazz, Object res) {
        return null;
    }

    @Override
    protected String convertLogicCustom(Class<?> type, Object o) {
        return null;
    }

}
