package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michael
 */
@Path("rest/users")
public class UserController {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insert")
    public Boolean insert(User user) {
        user.convertPassword();
        FactoryDAO.userDAO.insert(user);
        return true;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Boolean login(User user) {
        user.convertPassword();
        return (FactoryDAO.userDAO.login(user) != null);
    }

}
