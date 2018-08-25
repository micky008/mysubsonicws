package com.msc.mysubsonicws.scan;

import java.io.File;
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

    public void readFolder(File folder) {
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

    public void scan(File folder, UUID rootid, UUID id) {
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
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("folder obligatoire ! Merci");
            return;
        }
        //long debut = System.currentTimeMillis();
        new ScanInitial().readFolder(new File(args[0]));
        
    }

}
