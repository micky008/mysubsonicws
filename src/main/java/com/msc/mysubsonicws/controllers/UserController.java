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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    @Path("insert")
    public Boolean insert(User user) {
        user.convertPassword();
        FactoryDAO.userDAO.insert(user);
        return true;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    @Path("login")
    public Boolean login(User user) {
        user.convertPassword();
        return (FactoryDAO.userDAO.login(user) != null);
    }

}
