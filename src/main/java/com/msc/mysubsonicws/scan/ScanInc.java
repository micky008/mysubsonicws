package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.helpers.Constantes;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
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

    protected static ExecutorService executor = null;

    protected static synchronized ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }

    private void readFolder(File rootFolder, final BigInteger lastScan) throws SQLException {
        UUID rootUuid = UUID.fromString(Folder.ROOT_ID);
        Folder folder = new Folder();
        folder.setId(rootUuid.toString());
        folder.setIdParent(null);
        folder.setName(rootFolder.getName());
        folder.setPathname(rootFolder.getAbsolutePath());
        List<Folder> folders = FactoryDAO.folderDAO.getRootFolders();
        for (File file : rootFolder.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            Folder bddFolder = getFolder(folders, file);
                            UUID newId = null;
                            if (bddFolder == null) {
                                newId = UUID.randomUUID();
                            } else {
                                newId = UUID.fromString(bddFolder.getId());
                            }
                            scan(file, rootUuid, newId, lastScan);
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else if (isGoodFile(file, Constantes.EXTENTION_MEDIA_FILE)) {
                if (!addInBdd(file, lastScan)) {
                    continue;
                }
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());
                FactoryDAO.musiqueDAO.insert(m);
            }
        }
    }

    private void scan(File parentFolderFile, UUID rootid, UUID id, final BigInteger lastScan) throws SQLException {
        Folder folder = new Folder();
        folder.setId(id.toString());
        folder.setIdParent(rootid.toString());
        folder.setName(parentFolderFile.getName());
        folder.setPathname(parentFolderFile.getAbsolutePath());
        List<Folder> folders = FactoryDAO.folderDAO.getParentFolders(id.toString());
        if (addInBdd(parentFolderFile, lastScan)) {
            folder = FactoryDAO.folderDAO.insert(folder);
        }
        for (File file : parentFolderFile.listFiles()) {
            if (file.isFile() && !addInBdd(file, lastScan)) {
                continue;
            }
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            Folder bddFolder = getFolder(folders, file);
                            UUID newId = null;
                            if (bddFolder == null) {
                                newId = UUID.randomUUID();
                            } else {
                                newId = UUID.fromString(bddFolder.getId());
                            }
                            scan(file, id, newId, lastScan);
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else if (isGoodFile(file, Constantes.EXTENTION_MEDIA_FILE)) {
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());
                FactoryDAO.musiqueDAO.insert(m);
            } else if (isGoodFile(file, Constantes.EXTENTION_PICTURE_FILE)) {
                folder.setImgAlbum(file.getAbsolutePath());
                FactoryDAO.folderDAO.update(folder);
            }
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

    private boolean addInBdd(File file, final BigInteger lastScan) {
        Path p = Paths.get(file.getAbsolutePath());
        try {
            BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
            return view.creationTime().toMillis() >= lastScan.longValue();
        } catch (IOException ex) {
            Logger.getLogger(ScanInc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private Folder getFolder(List<Folder> folders, File folder) {
        for (Folder f : folders) {
            if (f.getName().equals(folder.getName())) {
                return f;
            }
        }
        return null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("folder obligatoire ! Merci");
            return;
        }
        new ScanInc().launchScan(new File(args[0]));

    }

    public void launchScan(File initialFolder) throws SQLException {
        BigInteger lastScan = FactoryDAO.lsatScanDAO.getLastScan();
        readFolder(initialFolder, lastScan);
        FactoryDAO.lsatScanDAO.update(new BigInteger("" + System.currentTimeMillis()));
    }

}
