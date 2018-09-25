package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Folder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class FolderDAO extends AbstractDAO<Folder> {

    public FolderDAO() {
    }

    public List<Folder> getParentFolders(String id) {
        List<Folder> lf = this.getObjects("from Folder where idParent='" + id + "'");
        Collections.sort(lf, new MySort());
        return lf;
    }

    public List<Folder> getRootFolders() {
        List<Folder> lf = this.getObjects("from Folder where idParent='" + Folder.ROOT_ID + "'");
        Collections.sort(lf, new MySort());
        return lf;
    }

    public Folder getFolderById(String id) {
        return this.getObject("from Folder where id='" + id + "'");
    }

    private class MySort implements Comparator<Folder> {

        @Override
        public int compare(Folder o1, Folder o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

}
