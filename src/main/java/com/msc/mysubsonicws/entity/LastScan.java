package com.msc.mysubsonicws.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "last_scan")
public class LastScan implements Serializable {

    @Id
    private BigInteger lastScan;

    public LastScan() {
    }

    public LastScan(BigInteger lastScan) {
        this.lastScan = lastScan;
    }

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
