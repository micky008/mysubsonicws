package com.msc.mysubsonicws.dao;

import com.msc.mysubsonicws.dao.abstractdao.AbstractDAO;
import com.msc.mysubsonicws.entity.LastScan;
import java.math.BigInteger;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class LastScanDAO extends AbstractDAO<LastScan> {

    public BigInteger getLastScan() throws SQLException {
        LastScan ls = getObject("from LastScan");
        if (ls == null) {
            ls = new LastScan();
            ls.setLastScan(new BigInteger("0"));
        }
        return ls.getLastScan();
    }

    public void update(BigInteger bi) throws SQLException {
        LastScan ls = this.getObject("from LastScan");
        if (ls == null) {
            ls = new LastScan();
            ls.setLastScan(bi);
            super.insert(ls);
            return;
        }
        ls.setLastScan(bi);
        super.update(ls);
    }

    public void insert(BigInteger m) {
        LastScan ls = new LastScan(m);
        super.insert(ls);
    }

}
