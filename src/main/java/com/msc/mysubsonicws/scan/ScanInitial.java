package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.dao.FactoryDAO;
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

    private final String extentionFiles[] = {"mp3", "flac", "ogg"};
    protected static ExecutorService executor = null;

    protected static synchronized ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }

    private void readFolder(File folder) throws SQLException {
        UUID rootUuid = UUID.randomUUID();
        List<Musique> lm = new ArrayList<>();
        for (File file : folder.listFiles()) {
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
            } else if (isGoodFile(file)) {
                Musique m = TagHelper.getInfo(file);
                lm.add(m);
                System.out.println("id=" + rootUuid.toString() + " - " + file.getAbsolutePath());
            }
        }
        FactoryDAO.musiqueDAO.insert(lm);
    }

    private void scan(File folder, UUID rootid, UUID id) throws SQLException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
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
            } else {
                if (isGoodFile(file)) {
                    TagHelper.getInfo(file);
                    System.out.println("rootId=" + rootid.toString() + " - id =" + id.toString() + " - " + file.getAbsolutePath());
                }
            }
        }
    }

    private boolean isGoodFile(File file) { //fomctionne pour l'audio et la video
        String filename = file.getName().toLowerCase();
        for (String ext : extentionFiles) {
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
        //long debut = System.currentTimeMillis();
        new ScanInitial().readFolder(new File(args[0]));

    }

    public void launchScan(File initialFolder) throws SQLException {
        readFolder(initialFolder);
        FactoryDAO.lsatScanDAO.update(new BigInteger(""+System.currentTimeMillis()));
    }

}
