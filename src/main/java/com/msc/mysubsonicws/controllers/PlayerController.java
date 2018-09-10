package com.msc.mysubsonicws.controllers;

import com.google.gson.Gson;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusicPlace;
import java.lang.reflect.Field;
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
    @Get("/rest/players/pmp/{idPlayer}") //recup le player
    @Post("/rest/players/pmp/delete") //delete des musique du player
     */
    @GET
    @Path("players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllPlayers() throws SQLException {
       // return FactoryDAO.playerDAO.getAll();
       return null;
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
        Player player = ps[0];
        //FactoryDAO.playerDAO.insert(player);
        
        return player;
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
        //FactoryDAO.playerDAO.update(player);
        return player;
    }

    @POST
    @Path("player/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Player> deletePlayer(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        Player[] ps = gson.fromJson(playerStr, Player[].class);
        List<Player> lps = Arrays.asList(ps);
        //FactoryDAO.playerDAO.delete(lps);
        return lps;
    }

    @GET
    @Path("pmp/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerMusicPlace getPmp(@PathParam("id") Integer id) throws SQLException {
//        Field f = FieldUtils.getField(PlayerMusicPlace.class, "playerId");
//        SearchById sbi = new SearchById(f, id);
//        List<PlayerMusicPlace> l = FactoryDAO.playerMusicPlayerDAO.getObjectsById(sbi);
//        return l != null ? l.get(0) : null;
return null;
    }

    
    @POST
    @Path("pmp/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<PlayerMusicPlace> insertPmp(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        PlayerMusicPlace[] ps = gson.fromJson(playerStr, PlayerMusicPlace[].class);        
        //FactoryDAO.playerMusicPlayerDAO.insert(ps);
       // return ps;
       return null;
    }

    @POST
    @Path("pmp/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerMusicPlace updatePmp(String playerStr) throws SQLException {
        if (playerStr == null || playerStr.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        PlayerMusicPlace player = gson.fromJson(playerStr, PlayerMusicPlace.class);
        //FactoryDAO.playerDAO.update(player);
        return player;
    }
    
}
