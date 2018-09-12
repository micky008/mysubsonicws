package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.dao.MySessionFactory;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import java.io.File;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class ScanInitial {

    private final String extentionMediaFiles[] = {"mp3", "flac", "ogg"};
    private final String extentionPictureFiles[] = {"jpg", "png"};
    protected static ExecutorService executor = null;

    protected static synchronized ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newFixedThreadPool(2);
        }
        return executor;
    }

    private void readFolder(File rootFolder) throws SQLException {
        UUID rootUuid = UUID.fromString(Folder.ROOT_ID);
        List<Musique> lm = new ArrayList<>();
        Folder folder = null;
        folder = new Folder();
        folder.setId(rootUuid.toString());
        folder.setIdParent(null);
        folder.setName(rootFolder.getName());
        folder.setPathname(rootFolder.getAbsolutePath());
        folder = FactoryDAO.folderDAO.insert(folder);
        System.out.println("in add root folder");
        for (File file : rootFolder.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, rootUuid, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else if (isGoodFile(file, extentionMediaFiles)) {
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());
                lm.add(m);
            }
        }
        if (!lm.isEmpty()) {
            System.out.println("in add root musique");
            FactoryDAO.musiqueDAO.insert(lm);
        }
    }

    private void scan(File parentFolderFile, UUID rootid, UUID id) throws SQLException {
        System.out.println("in scan");
        Folder folder = new Folder();
        folder.setId(id.toString());
        folder.setIdParent(rootid.toString());
        folder.setName(parentFolderFile.getName());
        folder.setPathname(parentFolderFile.getAbsolutePath());
        folder = FactoryDAO.folderDAO.insert(folder);
        for (File file : parentFolderFile.listFiles()) {
            if (file.isDirectory()) {
                System.out.println("in scan is directory");
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, id, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else if (isGoodFile(file, extentionMediaFiles)) {
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());                
                System.out.println(m.toString());
                FactoryDAO.musiqueDAO.insert(m);
            } else if (isGoodFile(file, extentionPictureFiles)) {
                folder.setImgAlbum(file.getAbsolutePath());
                FactoryDAO.folderDAO.update(folder);
            } else {
                System.out.println("file=" + file.getAbsolutePath());
            }

        }
//        if (!lm.isEmpty()) {
//            System.out.println("in add musique folder");
//            FactoryDAO.musiqueDAO.insert(lm);
//        }
//        if (!lf.isEmpty()) {
//            System.out.println("in add folder");
//            FactoryDAO.folderDAO.insert(lf);
//        }
    }

    private boolean isGoodFile(File file, String[] exts) { //fomctionne pour l'audio et la video
        String filename = file.getName().toLowerCase();
        for (String ext : exts) {
            if (filename.endsWith(ext) && file.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("folder obligatoire ! Merci");
            return;
        }
        MySessionFactory.setUp();
        //long debut = System.currentTimeMillis();
        new ScanInitial().launchScan(new File(args[0]));
        //FactoryDAO.folderDAO.insertTest();
    }

    public void launchScan(File initialFolder) throws SQLException {
        readFolder(initialFolder);
        FactoryDAO.lsatScanDAO.update(new BigInteger("" + System.currentTimeMillis()));
    }

}
