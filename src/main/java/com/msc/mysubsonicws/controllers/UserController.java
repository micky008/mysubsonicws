package com.msc.mysubsonicws.controllers;

import com.google.gson.Gson;
import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.dao.UserDAO;
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
    public Response insert(String userStr) {
        Gson gson = new Gson();
        User u = gson.fromJson(userStr, User.class);
        u.convertPassword();
        FactoryDAO.userDAO.insert(u);
        return Response.ok("ok").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(String userStr) {
        Gson gson = new Gson();
        User u = gson.fromJson(userStr, User.class);
        u.convertPassword();
        if (FactoryDAO.userDAO.login(u) != null) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

}
