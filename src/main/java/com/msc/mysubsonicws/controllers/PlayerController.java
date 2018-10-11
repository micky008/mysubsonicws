package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.media.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("/rest/players")
public class PlayerController {

    /*
    @Get("/rest/players/players")
    @Post("/rest/players/player/insert")
    @Post("/rest/players/player/delete")
    @Post("/rest/players/player/update")
    @Post("/rest/players/pmp/update")
    @Post("/rest/players/pmp/insert")
    @Get("/rest/players/pmp/{idPlayer}") //recup les pmp by player
    @Post("/rest/players/pmp/delete") //delete des musique du player
     */
    @GET
    @Path("players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllPlayers() {
        return FactoryDAO.playerDAO.getAll();

    }

    @POST
    @Path("player/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public Player insertPlayers(Player player) {
        return FactoryDAO.playerDAO.insert(player);
    }

    @POST
    @Path("player/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public Player updatePlayer(Player player) {
        return FactoryDAO.playerDAO.update(player);
    }

    @POST
    @Path("player/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public Player deletePlayer(Player player) {
        try {
            return FactoryDAO.playerDAO.delete(player);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("pmp/{idPlayer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayerMusiquePlace> getPmp(@PathParam("idPlayer") Integer id) {
        return FactoryDAO.playerMusicPlayerDAO.getPmpByPlayer(id);
    }

    @POST
    @Path("pmp/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public PlayerMusiquePlace[] insertPmp(PlayerMusiquePlace[] pmps) {
        try {
            return FactoryDAO.playerMusicPlayerDAO.insert(pmps);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("pmp/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public PlayerMusiquePlace[] updatePmp(PlayerMusiquePlace[] pmps) {
        try {
            if (pmps.length == 0) {
                return null;
            }
            return FactoryDAO.playerMusicPlayerDAO.update(pmps);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("pmp/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public PlayerMusiquePlace[] deletePmp(PlayerMusiquePlace[] pmps) {
        try {
            if (pmps.length == 0) {
                return null;
            }
            return FactoryDAO.playerMusicPlayerDAO.delete(pmps);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("pmp/deletePlayer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_FORM_URLENCODED})
    public List<PlayerMusiquePlace> deletePmp(Player player) {
        try {
            return FactoryDAO.playerMusicPlayerDAO.deleteByPlayer(player);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
