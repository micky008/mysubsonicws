package com.msc.mysubsonicws.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "player_musique_place")
public class PlayerMusiquePlace implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musique_id", nullable = false)
    private Musique musique;

    @Id
    private Integer place;

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the musique
     */
    public Musique getMusique() {
        return musique;
    }

    /**
     * @param musique the musique to set
     */
    public void setMusique(Musique musique) {
        this.musique = musique;
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
