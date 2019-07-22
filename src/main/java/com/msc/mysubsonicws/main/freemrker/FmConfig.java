package com.msc.mysubsonicws.main.freemrker;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Folder;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.entity.Player;
import com.msc.mysubsonicws.entity.PlayerMusiquePlace;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class FmConfig {

    private static Configuration confFm;

    public static void init() throws IOException {
        confFm = new Configuration(Configuration.VERSION_2_3_28);
        TemplateLoader templateLoader = new ClassTemplateLoader(FmConfig.class, "/");
        confFm.setTemplateLoader(templateLoader);
        confFm.setDefaultEncoding("UTF-8");
        confFm.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        confFm.setLogTemplateExceptions(true);
        confFm.setWrapUncheckedExceptions(true);
    }

    public String fillZikTemplate(Folder f, List<Musique> lm) throws IOException, TemplateException {
        Map<String, Object> root = new HashMap<>();
        root.put("folder", f);
        root.put("ziks", lm);
        Template temp = confFm.getTemplate("playerFolder.html");
        StringWriter sw = new StringWriter();
        temp.process(root, sw);
        return sw.toString();
    }

public String fillZikPlaylist(Player p) throws IOException, TemplateException {
        Map<String, Object> root = new HashMap<>();
        root.put("player", p);

        List<PlayerMusiquePlace> lpmp = FactoryDAO.playerMusicPlayerDAO.getPmpByPlayer(p.getId());
        List<Musique> lm = new ArrayList(lpmp.size());

        for (PlayerMusiquePlace pmp : lpmp){
            lm.add(pmp.getMusique());
        }

        root.put("ziks", lm);
        Template temp = confFm.getTemplate("player.html");
        StringWriter sw = new StringWriter();
        temp.process(root, sw);
        return sw.toString();
    }

    
}
