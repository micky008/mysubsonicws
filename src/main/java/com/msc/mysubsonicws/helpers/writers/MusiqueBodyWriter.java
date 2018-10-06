package com.msc.mysubsonicws.helpers.writers;

import com.google.gson.Gson;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 *
 * @author Michael
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MusiqueBodyWriter implements MessageBodyWriter<Object> {

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        Class clazz = type;
        try {
            if (type == ArrayList.class) {
                clazz = Class.forName(((ParameterizedTypeImpl) type1).getActualTypeArguments()[0].getTypeName());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MusiqueBodyWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (clazz == Musique.class || clazz == PlayerMusiquePlace.class);
    }

    @Override
    public void writeTo(Object t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        if (t instanceof ArrayList) {
            for (Object o : (ArrayList) t) {
                o = convert(o);
            }
        } else {
            t = convert(t);
        }
        Gson gson = new Gson();
        String json = gson.toJson(t);
        out.write(json.getBytes());
        out.flush();
        out.close();
    }

    private Object convert(Object o) {
        if (o instanceof Musique) {
            ((Musique) o).setFullName(null);
            ((Musique) o).setType(null);
        } else if (o instanceof PlayerMusiquePlace) {
            ((PlayerMusiquePlace) o).getMusique().setFullName(null);
            ((PlayerMusiquePlace) o).getMusique().setType(null);
        }
        return o;
    }

}
