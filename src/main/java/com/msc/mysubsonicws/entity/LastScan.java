package com.msc.mysubsonicws.entity;

import com.msc.dao.daoproject.annotation.Id;
import com.msc.dao.daoproject.annotation.Name;
import com.msc.dao.daoproject.annotation.PrimaryKey;
import java.math.BigInteger;

/**
 *
 * @author Michael
 */
@Name(name = "lastscan")
public class LastScan {

    @Id
    @PrimaryKey
    @Name(name = "lastscan")
    private BigInteger lastScan;

    /**
     * @return the lastScan
     */
    public BigInteger getLastScan() {
        return lastScan;
    }

    /**
     * @param lastScan the lastScan to set
     */
    public void setLastScan(BigInteger lastScan) {
        this.lastScan = lastScan;
    }
    
    
    
}
