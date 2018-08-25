package com.msc.mysubsonicws.scan;

import com.msc.mysubsonicws.entity.Musique;
import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 *
 * @author micky
 */
public class TagHelper {

    public static Musique getInfo(File audioFile) {
        Musique musique = new Musique();
        try {

            AudioFile f = AudioFileIO.read(audioFile);
            Tag tag = f.getTag();
            //Mp3File mp3 = new Mp3File(audioFile.getAbsolutePath());

            //musique.setBiterate(tag.getFirst(FieldKey.BPM));
            musique.setFullName(audioFile.getAbsolutePath().replace('\\', '/'));
            musique.setLastModified(audioFile.lastModified());

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
                musique.setYear(tag.getFirst(FieldKey.YEAR));
            } catch (Throwable e) {
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (musique.getTitre() == null) {
            String tst = audioFile.getName().substring(0, audioFile.getName().length() - 4);
            musique.setTitre(tst);
        }
        return musique;
    }

}
