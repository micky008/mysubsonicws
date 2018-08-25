package com.msc.mysubsonicws.dao;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.dao.daoproject.generic.GenericDaoImpl;
import com.msc.mysubsonicws.entity.Musique;
import java.sql.Connection;

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

}
