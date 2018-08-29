package com.msc.mysubsonicws.dao;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.dao.daoproject.generic.GenericDaoImpl;
import com.msc.dao.daoproject.helper.SearchById;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.scan.ScanInitial;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class FolderDAO extends GenericDaoImpl<Folder> {

    public FolderDAO() {
        super(DAO.getConnection());
    }

    @Override
    protected Object convertFillObjectCustom(Class<?> clazz, Object res) {
        return null;
    }

    @Override
    protected String convertLogicCustom(Class<?> type, Object o) {
        return null;
    }

    private List<Folder> getFolders(String id, Field f) throws SQLException {
        SearchById sbi = new SearchById(f, id);
        return this.getObjectsById(sbi);

    }

    public List<Folder> getNextFolders(String id) {
        try {
            Field f = Folder.class.getField("id");
            return this.getFolders(id, f);
        } catch (Exception ex) {
            Logger.getLogger(FolderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Folder> getParentFolders(String id) {
        try {
            Field f = Folder.class.getField("parentId");
            return this.getFolders(id, f);
        } catch (Exception ex) {
            Logger.getLogger(FolderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Folder> getRootFolders() {
        try {
            Field f = Folder.class.getField("id");
            return this.getFolders(Folder.ROOT_ID, f);
        } catch (Exception ex) {
            Logger.getLogger(FolderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
