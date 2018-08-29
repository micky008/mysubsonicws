package com.msc.mysubsonicws.entity;

import com.msc.dao.daoproject.annotation.Id;
import com.msc.dao.daoproject.annotation.Name;
import com.msc.dao.daoproject.annotation.PrimaryKey;

/**
 *
 * @author micky
 */
@Name(name = "folders")
public class Folder {

    public static final String ROOT_ID = "00000000-0000-0000-0000-000000000001";
    
    @Id
    @PrimaryKey
    private String id;

    @Id
    @PrimaryKey
    @Name(name = "id_parent")
    private String idParent;

    private String pathname;

    @Name(name = "img_album")
    private String imgAlbum;

    private String name;



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
    public String toString() {
        return getId() + " " + getIdParent() + " " + getName();
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
     * @return the idParent
     */
    public String getIdParent() {
        return idParent;
    }

    /**
     * @param idParent the idParent to set
     */
    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }



}
