package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.sql.SQLException;
import java.util.List;
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
    public List<Player> getAllPlayers() throws SQLException {
        return FactoryDAO.playerDAO.getAll();

    }

    @POST
    @Path("player/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player insertPlayers(Player player) throws SQLException {
        return FactoryDAO.playerDAO.insert(player);
    }

    @POST
    @Path("player/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player updatePlayer(Player player) throws SQLException {
        return FactoryDAO.playerDAO.update(player);
    }

    @POST
    @Path("player/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player deletePlayer(Player player) throws SQLException {
        return FactoryDAO.playerDAO.delete(player);
    }

    @GET
    @Path("pmp/{idPlayer}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayerMusiquePlace> getPmp(@PathParam("idPlayer") Integer id) throws SQLException {
        return FactoryDAO.playerMusicPlayerDAO.getPmpByPlayer(id);
    }

    @POST
    @Path("pmp/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerMusiquePlace[] insertPmp(PlayerMusiquePlace[] pmps) throws SQLException {
        return FactoryDAO.playerMusicPlayerDAO.insert(pmps);
    }

    @POST
    @Path("pmp/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerMusiquePlace[] updatePmp(PlayerMusiquePlace[] pmps) throws SQLException {
        return FactoryDAO.playerMusicPlayerDAO.update(pmps);
    }

}
