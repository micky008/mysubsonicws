package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.entity.LastScan;
import java.math.BigInteger;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class LastScanDAO {

    public BigInteger getLastScan() throws SQLException {
        BigInteger bi = null;

        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        LastScan ls = (LastScan) session.createQuery("from LastScan").uniqueResult();
        bi = ls.getLastScan();

        session.getTransaction().commit();
        session.close();
        return bi;
    }

    public void update(BigInteger bi) throws SQLException {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        LastScan ls = (LastScan) session.createQuery("from LastScan").uniqueResult();

        ls.setLastScan(bi);
        session.save(ls);
        session.getTransaction().commit();
        session.close();
    }

    public void insert(LastScan m) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        session.save(m);

        session.getTransaction().commit();
        session.close();
    }

    public void insert(BigInteger m) {
        Session session = MySessionFactory.getInstance().openSession();
        session.beginTransaction();

        LastScan ls = new LastScan(m);

        session.save(ls);

        session.getTransaction().commit();
        session.close();
    }

}
