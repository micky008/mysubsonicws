package com.msc.mysubsonicws.helpers.writers;

import com.google.gson.Gson;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.helpers.Constantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.apache.commons.io.IOUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 *
 * @author Michael
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class FolderBodyWriter implements MessageBodyWriter<Object> {

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        Class clazz = type;
        try {
            if (type == ArrayList.class) {
                clazz = Class.forName(((ParameterizedTypeImpl) type1).getActualTypeArguments()[0].getTypeName());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FolderBodyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (clazz == Folder.class);
    }

    @Override
    public void writeTo(Object t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        if (t instanceof ArrayList) {
            for (Object o : (ArrayList) t) {
                if (o instanceof Folder) {
                    o = convert((Folder) o);
                }
            }
        } else if (t instanceof Folder) {
            t = convert((Folder) t);
        }
        Gson gson = new Gson();
        String json = gson.toJson(t);
        out.write(json.getBytes());
        out.flush();
        out.close();
    }

    private Folder convert(Folder o) throws IOException {
        o.setPathname(null);
        if (o.getImgAlbum() != null && !o.getImgAlbum().equals("")) {
            File file = new File(o.getImgAlbum());
            FileInputStream fis = new FileInputStream(file);
            byte[] buf = IOUtils.toByteArray(fis);
            IOUtils.closeQuietly(fis);
            String imgb64 = Base64.getEncoder().encodeToString(buf);
            buf = null;
            for (String ext : Constantes.EXTENTION_PICTURE_FILE) {
                if (o.getImgAlbum().endsWith(ext)) {
                    String line = "data:image/" + ext + ";charset=utf-8;base64," + imgb64;
                    o.setImgAlbum(line);
                }
            }
        }
        return o;
    }

}
