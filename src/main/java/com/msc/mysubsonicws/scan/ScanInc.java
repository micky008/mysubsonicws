package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.dao.FactoryDAO;
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
public class ScanInc {

    private final String extentionMediaFiles[] = {"mp3", "flac", "ogg"};
    private final String extentionPictureFiles[] = {"jpg", "png"};
    protected static ExecutorService executor = null;

    protected static synchronized ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }

    private void readFolder(File rootFolder) throws SQLException {
        UUID rootUuid = UUID.fromString(Folder.ROOT_ID);
        List<Musique> lm = new ArrayList<>();
        List<Folder> lf = new ArrayList<>();
        Folder folder = null;
        for (File file : rootFolder.listFiles()) {
            folder = null;
            if (file.isDirectory()) {
                folder = new Folder();
                folder.setId(rootUuid.toString());
                folder.setIdParent(null);
                folder.setName(file.getName());
                folder.setPathname(file.getAbsolutePath());
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, rootUuid, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                lf.add(folder);
            } else if (isGoodFile(file, extentionMediaFiles)) {
                Musique m = TagHelper.getInfo(file);
                m.setId(UUID.randomUUID().toString());
                m.setFolderId(folder.getId());
                lm.add(m);
            } else if (isGoodFile(file, extentionPictureFiles)) {
                if (folder != null) {
                    folder.setImgAlbum(file.getAbsolutePath());
                }
            }
        }
        if (!lm.isEmpty()) {
            FactoryDAO.musiqueDAO.insert(lm);
        }
        if (!lf.isEmpty()) {
            FactoryDAO.folderDAO.insert(lf);
        }
    }

    private void scan(File parentFolder, UUID rootid, UUID id) throws SQLException {
        List<Musique> lm = new ArrayList<>();
        List<Folder> lf = new ArrayList<>();
        Folder folder = null;
        for (File file : parentFolder.listFiles()) {
            if (file.isDirectory()) {
                folder = new Folder();
                folder.setId(id.toString());
                folder.setIdParent(rootid.toString());
                folder.setName(file.getName());
                folder.setPathname(file.getAbsolutePath());
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, id, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInc.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                lf.add(folder);
            } else if (isGoodFile(file, extentionMediaFiles)) {
                Musique m = TagHelper.getInfo(file);
                m.setId(UUID.randomUUID().toString());
                m.setFolderId(folder.getId());
                lm.add(m);
            } else if (isGoodFile(file, extentionPictureFiles)) {
                if (folder != null) {
                    folder.setImgAlbum(file.getAbsolutePath());
                }
            }
        }
        if (!lm.isEmpty()) {
            FactoryDAO.musiqueDAO.insert(lm);
        }
        if (!lf.isEmpty()) {
            FactoryDAO.folderDAO.insert(lf);
        }
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
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("folder obligatoire ! Merci");
            return;
        }        
        new ScanInc().readFolder(new File(args[0]));

    }

    public void launchScan(File initialFolder) throws SQLException {
        readFolder(initialFolder);
        FactoryDAO.lsatScanDAO.update(new BigInteger("" + System.currentTimeMillis()));
    }

}
