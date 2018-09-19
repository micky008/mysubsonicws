package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.User;

/**
 *
 * @author Michael
 */
public class UserDAO extends AbstractDAO<User> {

    public User login(String login, String passwordEncrypted) {
        return this.getObject("from User where login='" + login + "' AND password='" + passwordEncrypted + "'");
    }

    public User login(User u) {
        return this.login(u.getLogin(), u.getPassword());
    }

}
