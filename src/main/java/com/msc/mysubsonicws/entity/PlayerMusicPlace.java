package com.msc.mysubsonicws.entity;

import com.msc.dao.daoproject.annotation.Name;

/**
 *
 * @author Michael
 */
@Name(name = "players_musiques_places")
public class PlayerMusicPlace {

    @Name(name = "player_id")
    private Integer playerId;
    
    @Name(name = "musique_id")
    private String musiqueId;
    
    private Integer place;

    /**
     * @return the playerId
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the musiqueId
     */
    public String getMusiqueId() {
        return musiqueId;
    }

    /**
     * @param musiqueId the musiqueId to set
     */
    public void setMusiqueId(String musiqueId) {
        this.musiqueId = musiqueId;
    }

    /**
     * @return the place
     */
    public Integer getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(Integer place) {
        this.place = place;
    }

}
