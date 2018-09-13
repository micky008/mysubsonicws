package com.msc.mysubsonicws.dao.abstractdao;

import com.msc.mysubsonicws.dao.MySessionFactory;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.LastScan;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class AbstractDAO<T> {

    public synchronized T insert(T f) {

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(f);

        session.getTransaction().commit();
        session.close();

        return f;
    }

    public synchronized T update(T m) {

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.update(m);

        session.getTransaction().commit();
        session.close();
        return m;
    }
    
    public synchronized List<T> insert(List<T> lm) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        for (T f : lm) {
            session.save(f);
        }

        session.getTransaction().commit();
        session.close();
        return lm;
    }

    protected T getObject(String hql) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();
        T obj = (T) session.createQuery(hql).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return obj;
    }

    protected List<T> getObjects(String hql) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();
        List<T> obj = session.createQuery(hql).list();
        session.getTransaction().commit();
        session.close();
        return obj;
    }

}
