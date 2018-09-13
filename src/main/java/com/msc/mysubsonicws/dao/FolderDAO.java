package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.Folder;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class FolderDAO extends AbstractDAO<Folder> {

    public FolderDAO() {
    }


    public List<Folder> getParentFolders(String id) {
        return this.getObjects("from Folder where idParent='" + id + "'");
    }

    public List<Folder> getRootFolders() {
        return this.getObjects("from Folder where idParent='" + Folder.ROOT_ID + "'");
    }

    public Folder getFolderById(String id) {
        return this.getObject("from Folder where id='" + id + "'");
    }

    public void insertTest() {
        Folder f = new Folder();
        f.setId(Folder.ROOT_ID);
        f.setIdParent(null);
        f.setImgAlbum(null);
        f.setName("truc");
        f.setPathname("c:/truc");
        Folder f2 = new Folder();
        f2.setId(UUID.randomUUID().toString());
        f2.setIdParent(Folder.ROOT_ID);
        f2.setImgAlbum(null);
        f2.setName("truc2");
        f2.setPathname("c:/truc2");

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(f);
        session.save(f2);

        session.getTransaction().commit();
        session.close();

    }
}
