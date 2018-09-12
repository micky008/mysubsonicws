package com.msc.mysubsonicws.main;

import com.msc.mysubsonicws.dao.MySessionFactory;
import com.msc.mysubsonicws.helpers.WSConfig;
import java.io.FileReader;
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
        MySessionFactory.setUp();
        WSConfig.createInstance(prop);
        URI baseUri = UriBuilder.fromUri("http://" + WSConfig.getInstance().getDomain() + "/").port(WSConfig.getInstance().getPort()).build();
        ResourceConfig config = new ResourceConfig();
        config.packages("com.msc.mysubsonicws.controllers");
        JdkHttpServerFactory.createHttpServer(baseUri, config);
    }

}
