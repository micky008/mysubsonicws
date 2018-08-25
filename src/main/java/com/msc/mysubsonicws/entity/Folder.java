package com.msc.mysubsonicws.entity;


/**
 *
 * @author micky
 */
public class Folder {


    private Integer id;

    private Integer idParent;

    private String pathname;

    private String imgAlbum;
    
    private String name;

    
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
     * @return the idParent
     */
    public Integer getIdParent() {
        return idParent;
    }

    /**
     * @param idParent the idParent to set
     */
    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    /**
     * @return the pathName
     */
    public String getPathname() {
        return pathname;
    }

    /**
     * @param pathName the pathName to set
     */
    public void setPathname(String pathName) {
        this.pathname = pathName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the imgFolder
     */
    public String getImgAlbum() {
        return imgAlbum;
    }

    /**
     * @param imgFolder the imgFolder to set
     */
    public void setImgAlbum(String imgFolder) {
        this.imgAlbum = imgFolder;
    }
    
    @Override
    public String toString(){
	return id+" "+idParent+" "+name;
    } 
    
}
