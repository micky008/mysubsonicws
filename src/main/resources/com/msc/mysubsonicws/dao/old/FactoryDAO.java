package com.msc.mysubsonicws.dao.old;

/**
 *
 * @author Michael
 */
public class FactoryDAO {

    public static final FolderDAO folderDAO = new FolderDAO();
    public static final MusiqueDAO musiqueDAO = new MusiqueDAO();
    public static final PlayerDAO playerDAO = new PlayerDAO();
    public static final PlayerMusiquePlaceDAO playerMusicPlayerDAO = new PlayerMusiquePlaceDAO();
    public static final UserDAO userDAO = new UserDAO();
    public static final LastScanDAO lsatScanDAO = new LastScanDAO();
    
    
}
