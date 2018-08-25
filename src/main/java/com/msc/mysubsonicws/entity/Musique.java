package com.msc.mysubsonicws.entity;

/**
 *
 * @author micky
 */
public class Musique {

    private Integer id;

    private Integer idFolder;

    private String titre;

    private String artiste;

    private String genre;

    private String album;

    private String year;

    private Integer biterate;

    private String fullName;

    private Long lastModified;

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
     * @return the idFolder
     */
    public Integer getIdFolder() {
	return idFolder;
    }

    /**
     * @param idFolder the idFolder to set
     */
    public void setIdFolder(Integer idFolder) {
	this.idFolder = idFolder;
    }

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
     * @return the biterate
     */
    public Integer getBiterate() {
	return biterate;
    }

    /**
     * @param biterate the biterate to set
     */
    public void setBiterate(Integer biterate) {
	this.biterate = biterate;
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

    /**
     * @return the lastModified
     */
    public Long getLastModified() {
	return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Long lastModified) {
	this.lastModified = lastModified;
    }

    @Override
    public String toString() {
	return id + " " + idFolder + " " + titre+" "+artiste;
    }

}
