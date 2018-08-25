package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.LastScan;
import java.io.File;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        scan(file, rootUuid, UUID.randomUUID());
                    }
                });
            } else if (isGoodFile(file)) {
                System.out.println("id=" + rootUuid.toString() + " - " + file.getAbsolutePath());
            }
        }
    }

    private void scan(File folder, UUID rootid, UUID id) throws SQLException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        scan(file, id, UUID.randomUUID());
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
        LastScan ls = new LastScan();
        ls.setLastScan(new BigInteger(""+System.currentTimeMillis()));
        FactoryDAO.lsatScanDAO.insert(ls);
    }

}
