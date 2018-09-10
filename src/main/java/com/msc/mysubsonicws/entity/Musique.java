package com.msc.mysubsonicws.entity;

import com.msc.dao.daoproject.annotation.Id;
import com.msc.dao.daoproject.annotation.Name;
import com.msc.dao.daoproject.annotation.PrimaryKey;

/**
 *
 * @author micky
 */
@Name(name = "musiques")
public class Musique {

    @Id
    @PrimaryKey
    private String id;

    @Id
    @PrimaryKey
    @Name(name = "id_folder")
    private String idFolder;

    private String titre;

    private String artiste;

    private String genre;

    private String album;

    private String year;

    @Name(name = "fullname")
    private String fullName;

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
     * @return the year
     */
    public String getYear() {
	return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
	this.year = year;
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
	return getId() + " " + getIdFolder() + " " + getTitre()+" "+getArtiste();
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
     * @return the idFolder
     */
    public String getIdFolder() {
        return idFolder;
    }

    /**
     * @param idFolder the idFolder to set
     */
    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
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

}
