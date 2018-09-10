package com.msc.mysubsonicws.main;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.dao.FolderDAO;
import com.msc.mysubsonicws.dao.MySessionFactory;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.helpers.WSConfig;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Michael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        Reader reader = new FileReader("config-dev.properties");
        prop.load(reader);
//        DAO.initConnection(prop);
//        DAO.setDebugMode(true);
        MySessionFactory.setUp();
        WSConfig.createInstance(prop);
        URI baseUri = UriBuilder.fromUri("http://" + WSConfig.getInstance().getDomain() + "/").port(WSConfig.getInstance().getPort()).build();
        ResourceConfig config = new ResourceConfig();
        config.packages("com.msc.mysubsonicws.controllers");
        //JdkHttpServerFactory.createHttpServer(baseUri, config);
        FolderDAO fd = FactoryDAO.folderDAO;
  //      fd.insertTest();
        List<Folder> lf = fd.getRootFolders();
        for (Folder f : lf){
            System.out.println(f.toString());
        }
    }

}
