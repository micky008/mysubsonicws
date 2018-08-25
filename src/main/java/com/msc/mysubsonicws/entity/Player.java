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
    Integer id;

    String nom;

}
