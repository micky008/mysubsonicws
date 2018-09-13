package com.msc.mysubsonicws.controllers;

import com.google.gson.Gson;
import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.sql.SQLException;
import java.util.Arrays;
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
    public Player insertPlayers(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        Player[] ps = gson.fromJson(playerStr, Player[].class);
        return FactoryDAO.playerDAO.insert(ps[0]);
    }

    @POST
    @Path("player/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player updatePlayer(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        Player player = gson.fromJson(playerStr, Player.class);
        return FactoryDAO.playerDAO.update(player);
    }

    @POST
    @Path("player/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Player deletePlayer(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        Player p = gson.fromJson(playerStr, Player.class);
        return FactoryDAO.playerDAO.delete(p);
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
    public PlayerMusiquePlace[] insertPmp(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        PlayerMusiquePlace[] ps = gson.fromJson(playerStr, PlayerMusiquePlace[].class);
        return FactoryDAO.playerMusicPlayerDAO.insert(ps);
    }

    @POST
    @Path("pmp/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerMusiquePlace[] updatePmp(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        PlayerMusiquePlace[] players = gson.fromJson(playerStr, PlayerMusiquePlace[].class);
        return FactoryDAO.playerMusicPlayerDAO.update(players);
    }

}
