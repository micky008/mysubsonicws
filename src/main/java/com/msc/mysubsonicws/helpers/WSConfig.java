package com.msc.mysubsonicws.helpers;

import java.io.File;
import java.util.Properties;

/**
 *
 * @author Michael
 */
public class WSConfig {

    private static WSConfig CONFIG = null;

    public static WSConfig getInstance() {
        if (CONFIG == null) {
            CONFIG = new WSConfig();
        }
        return CONFIG;
    }

    public static WSConfig createInstance(Properties prop) {
        WSConfig conf = getInstance();
        File f = new File(prop.getProperty("folder.scan"));
        conf.setFolderToScan(f);
        conf.setDomain(prop.getProperty("ws.domain"));
        conf.setPort(Integer.parseInt(prop.getProperty("ws.port")));
        return conf;
    }

    private File folderToScan;

    private Integer port;

    private String domain;

    /**
     * @return the folderToScan
     */
    public File getFolderToScan() {
        return folderToScan;
    }

    /**
     * @param folderToScan the folderToScan to set
     */
    public void setFolderToScan(File folderToScan) {
        this.folderToScan = folderToScan;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

}
