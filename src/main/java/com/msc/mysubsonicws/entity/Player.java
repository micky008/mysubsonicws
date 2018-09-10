package com.msc.mysubsonicws.entity;

import com.msc.dao.daoproject.annotation.Id;
import com.msc.dao.daoproject.annotation.Name;
import com.msc.dao.daoproject.annotation.PrimaryKey;

/**
 *
 * @author Michael
 */
@Name(name = "players")
public class Player {

    @PrimaryKey
    @Id
    private Integer id;

    private String nom;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
}
