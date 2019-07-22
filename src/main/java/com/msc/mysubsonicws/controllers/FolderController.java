package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.helpers.WSConfig;
import com.msc.mysubsonicws.scan.ScanInc;
import com.msc.mysubsonicws.scan.ScanInitial;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
  GET /rest/folders/id/{id} Récupère tous les folders parents 
  GET /rest/folders/parent/{idParent} Récupère tous les folders fils a partir du parent 
  GET /rest/folders/root Récupère tous les folders pour affichage 
  GET /rest/folders/scan/inc Scan incrementale. Methode a appelé quand on a ajoute des musiques 
  GET /rest/folders/scan/init Delete la base de musique ainsi que les musique des players et rescan depuis 0 le folder de musique
 */
/**
 * @author Michael
 */
@Path("/rest/folders/")
public class FolderController {

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Folder getNextFolders(@PathParam("id") String id) {
        return FactoryDAO.folderDAO.getFolderById(id);
    }

    @GET
    @Path("parent/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Folder> getChildFolders(@PathParam("id") String id) {
        return FactoryDAO.folderDAO.getChildFolders(id);
    }

    @GET
    @Path("root")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Folder> getRootFolders() {
        return FactoryDAO.folderDAO.getRootFolders();
    }

    @GET
    @Path("scan/initial")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean scanInitial() {
        try {
            ScanInitial si = new ScanInitial();
            WSConfig conf = WSConfig.getInstance();
            si.launchScan(conf.getFolderToScan());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @GET
    @Path("scan/inc")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean scanInc() {
        try {
            ScanInc si = new ScanInc();
            WSConfig conf = WSConfig.getInstance();
            si.launchScan(conf.getFolderToScan());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
