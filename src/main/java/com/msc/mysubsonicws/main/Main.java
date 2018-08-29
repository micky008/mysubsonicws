package com.msc.mysubsonicws.main;

import com.msc.dao.daoproject.generic.DAO;
import com.msc.mysubsonicws.helpers.WSConfig;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.Properties;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
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
        DAO.initConnection(prop);
        DAO.setDebugMode(true);
        WSConfig.createInstance(prop);
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig();
        config.packages("com.msc.mysubsonicws.controllers");
        JdkHttpServerFactory.createHttpServer(baseUri, config);
    }

}
