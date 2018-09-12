package com.msc.mysubsonicws.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author micky
 */
@Entity
@Table(name = "musiques")
public class Musique implements Serializable {

    @Id
    @Column(name = "musique_id")
    private String id;

    @Column(name = "folder_id", nullable = false)
    private String folderId;

    @Column(nullable = true)
    private String titre;

    @Column(nullable = true)
    private String artiste;

    @Column(nullable = true)
    private String genre;

    @Column(nullable = true)
    private String album;

    @Column(nullable = true)
    private String annee;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String type;

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the artiste
     */
    public String getArtiste() {
        return artiste;
    }

    /**
     * @param artiste the artiste to set
     */
    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return getId() + " " + getFolderId()+ " " + getTitre() + " " + getArtiste();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the folderId
     */
    public String getFolderId() {
        return folderId;
    }

    /**
     * @param folderId the folderId to set
     */
    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    /**
     * @return the annee
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    

}
