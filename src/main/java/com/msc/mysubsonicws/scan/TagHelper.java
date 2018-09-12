package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.entity.Musique;
import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.flac.FlacTag;

/**
 *
 * @author micky
 */
public class TagHelper {

    public static Musique getInfo(File audioFile) {
        Musique musique = new Musique();
        AudioFile f = null;
        try {

            f = AudioFileIO.read(audioFile);
            Tag tag = f.getTag();
            //Mp3File mp3 = new Mp3File(audioFile.getAbsolutePath());

            //musique.setBiterate(tag.getFirst(FieldKey.BPM));
            musique.setFullName(audioFile.getAbsolutePath().replace('\\', '/'));

            try {
                musique.setAlbum(tag.getFirst(FieldKey.ALBUM));
            } catch (Throwable e) {
            }
            try {
                musique.setArtiste(tag.getFirst(FieldKey.ARTIST));
            } catch (Throwable e) {
            }
            try {
                musique.setGenre(tag.getFirst(FieldKey.GENRE));
            } catch (Throwable e) {
            }
            try {
                musique.setTitre(tag.getFirst(FieldKey.TITLE));
            } catch (Throwable e) {
            }
            try {
                musique.setAnnee(tag.getFirst(FieldKey.YEAR));
            } catch (Throwable e) {
            }
            if (f instanceof MP3File) {
                musique.setType("mp3");
            } else if (f.getTag() instanceof FlacTag) {
                musique.setType("flac");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (musique.getType() == null) {
            int pos = audioFile.getName().lastIndexOf('.');
            String type = audioFile.getName().substring(pos + 1);
            musique.setType(type);
        }
        if (musique.getTitre() == null) {
            String tst = audioFile.getName().substring(0, audioFile.getName().length() - musique.getType().length() + 1);
            musique.setTitre(tst);
        }

        return musique;
    }

}
