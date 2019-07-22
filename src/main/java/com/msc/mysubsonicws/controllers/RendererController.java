package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.dao.FolderDAO;
import com.msc.mysubsonicws.dao.MusiqueDAO;
import com.msc.mysubsonicws.dao.PlayerDAO;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.main.freemrker.FmConfig;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.StringBuilderWriter;

/**
 *
 * @author Michael
 */
@Path("rest/renderer/")
public class RendererController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("folder/{id-folder}")
    public String renderFolder(@PathParam("id-folder") String uuidFolder) throws Exception {
        MusiqueDAO mdao = FactoryDAO.musiqueDAO;
        FolderDAO fdao = FactoryDAO.folderDAO;

        Map<Folder, List<Musique>> map = new TreeMap<>(new MyComparator());

        Folder folder = fdao.getFolderById(uuidFolder);
        List<Musique> lm = mdao.getMusiquesByFolder(uuidFolder);
        if (lm != null && !lm.isEmpty()) {
            map.put(folder, lm);
        }

        fillMap(map, uuidFolder, fdao, mdao);

        try {
            return fillTemplate(lm, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR 500";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("playlist/{id-player}")
    public String renderPlaylisr(@PathParam("id-player") int idPlayer) {
        MusiqueDAO mdao = FactoryDAO.musiqueDAO;
        Player player = FactoryDAO.playerDAO.getPlayerById(idPlayer);
        List<Musique> lm = mdao.getMusiquesByPlayer(idPlayer);
        Map<Folder, List<Musique>> map = new TreeMap<>(new MyComparator());

        Folder folder = new Folder();
        folder.setName(player.getNom());
        folder.setId("" + idPlayer);

        map.put(folder, lm);

        try {
            return fillTemplate(lm, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR 500";
    }

    private void fillMap(Map<Folder, List<Musique>> map, String uuidFolder, FolderDAO fdao, MusiqueDAO mdao) {
        List<Folder> lf = fdao.getChildFolders(uuidFolder);
        for (Folder f : lf) {
            fillMap(map, f.getId(), fdao, mdao);
            List<Musique> lm = mdao.getMusiquesByFolder(f.getId());
            if (lm != null && !lm.isEmpty()) {
                map.put(f, lm);
            }
        }
    }

    private String fillTemplate(List<Musique> lm, Map<Folder, List<Musique>> map) throws IOException {
        FmConfig fm = new FmConfig();
        StringBuilder sb = new StringBuilder();
        try {
            for (Folder f : map.keySet()) {
                lm = map.get(f);
                sb.append(fm.fillZikTemplate(f, lm));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilderWriter sbw = new StringBuilderWriter(sb);
        InputStream is = getClass().getResourceAsStream("/myjs.js");
        IOUtils.copy(is, sbw, Charset.forName("UTF-8"));
        return sbw.toString();
    }

    private class MyComparator implements Comparator<Folder> {

        @Override
        public int compare(Folder o1, Folder o2) {
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        }
    }

}
