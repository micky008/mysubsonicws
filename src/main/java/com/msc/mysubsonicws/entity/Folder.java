package com.msc.mysubsonicws.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author micky
 */
@Entity
@Table(name = "folders")
public class Folder implements Serializable {

    @Transient
    public static final String ROOT_ID = "00000000-0000-0000-0000-000000000001";

    @Id
    @Column(name = "folder_id")
    private String id;

    @Column(name = "parent_id")
    private String idParent;

    private String pathname;

    @Column(name = "img_album")
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
