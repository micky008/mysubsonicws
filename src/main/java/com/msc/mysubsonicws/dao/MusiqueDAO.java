package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class MusiqueDAO {

    public Musique getMusiqueById(String id) {
        Musique m = null;

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        m = (Musique) session.createQuery("from Musique where id='" + id + "'").uniqueResult();

        session.getTransaction().commit();
        session.close();
        return m;
    }
//

    public List<Musique> getMusiquesByPlayer(int playerId) {
        List<Musique> lm = null;

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        lm = session.createQuery("select pmp.musique from PlayerMusiquePlaceDAO pmp where player =" + playerId).list();

        session.getTransaction().commit();
        session.close();
        return lm;
    }
//

    public List<Musique> getMusiquesByFolder(String folderId) {
        List<Musique> lm = null;

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        lm = session.createQuery("from Musique where folder.id='" + folderId + "'").list();

        session.getTransaction().commit();
        session.close();
        return lm;
    }

    public void insert(Musique m) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(m);

        session.getTransaction().commit();
        session.close();
    }

    public void insert(List<Musique> lm) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(lm);

        session.getTransaction().commit();
        session.close();
    }

}
