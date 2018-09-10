package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class FolderDAO {

    public List<Folder> getNextFolders(String id) {
                List<Folder> lf = null;
        
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();
        
        lf = session.createQuery( "from Folder where id='"+id+"'" ).list();
        
        session.getTransaction().commit();
        session.close();
        return lf;
    }

    public List<Folder> getParentFolders(String id) {
                List<Folder> lf = null;
        
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();
        
        lf = session.createQuery( "from Folder where idParent='"+id+"'" ).list();
        
        session.getTransaction().commit();
        session.close();
        return lf;
    }

    public List<Folder> getRootFolders() {
       
        List<Folder> lf = null;
        
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();
        
        lf = session.createQuery( "from Folder where id='"+Folder.ROOT_ID+"'" ).list();
        
        session.getTransaction().commit();
        session.close();
        return lf;
    }

    
    public void insert(Folder m) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(m);

        session.getTransaction().commit();
        session.close();
    }

    public void insert(List<Folder> lm) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(lm);

        session.getTransaction().commit();
        session.close();
    }

    
    public void insertTest(){
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
