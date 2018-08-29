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

    
    public static WSConfig createInstance(Properties prop){
        WSConfig conf = getInstance();
        File f = new File(prop.getProperty("folder.scan"));
        conf.setFolderToScan(f);
        return conf;
    }
         
    private File folderToScan;

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
    
    
    
}
