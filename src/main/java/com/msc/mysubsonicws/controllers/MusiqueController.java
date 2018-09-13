package com.msc.mysubsonicws.controllers;

import com.msc.mysubsonicws.dao.FactoryDAO;
import com.msc.mysubsonicws.entity.Musique;
import com.msc.mysubsonicws.helpers.MediaStreamer;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author Michael
 */
@Path("rest/musiques")
public class MusiqueController {

    final int chunk_size = 1024 * 1024; // 1MB chunks

    @GET
    @Path("resolve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Musique resolve(@PathParam("id") String id) throws Exception {
        return FactoryDAO.musiqueDAO.getMusiqueById(id);        
    }

    @GET
    @Path("folder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Musique> getMusiqueByFolder(@PathParam("id") String id) throws Exception {
        return FactoryDAO.musiqueDAO.getMusiquesByFolder(id);        
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Musique getMusiqueById(@PathParam("id") String id) throws Exception {
        Musique m = resolve(id);
        m.setFullName(null);
        m.setType(null);
        m.setFolderId(null);        
        return m;
    }

    @GET
    @Path("player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Musique> getMusiqueByPlayer(@PathParam("id") Integer id) throws Exception {
        return FactoryDAO.musiqueDAO.getMusiquesByPlayer(id);        
    }

    @GET
    @Path("stream/{id}")
    public Response streamAudio(@HeaderParam("Range") String range, @PathParam("id") String id) throws Exception {
        Musique m = this.resolve(id);
        Response.ResponseBuilder r = buildStream(new File(m.getFullName()), range);
        r.header(HttpHeaders.CONTENT_TYPE, "audio/" + m.getType());
        return r.build();
    }

    /**
     * Adapted from
     * http://stackoverflow.com/questions/12768812/video-streaming-to-ipad-does-not-work-with-tapestry5/12829541#12829541
     *
     * @param asset Media file
     * @param range range header
     * @return Streaming output
     * @throws Exception IOException if an error occurs in streaming.
     */
    private Response.ResponseBuilder buildStream(final File asset, final String range) throws Exception {
        // range not requested : Firefox does not send range headers
        if (range == null) {
            StreamingOutput streamer = output -> {
                try (FileChannel inputChannel = new FileInputStream(asset).getChannel(); WritableByteChannel outputChannel = Channels.newChannel(output)) {
                    inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                }
            };
            return Response.ok(streamer).status(200).header(HttpHeaders.CONTENT_LENGTH, asset.length());
        }

        String[] ranges = range.split("=")[1].split("-");
        final int from = Integer.parseInt(ranges[0]);

        /*
          Chunk media if the range upper bound is unspecified. Chrome, Opera sends "bytes=0-"
         */
        int to = chunk_size + from;
        if (to >= asset.length()) {
            to = (int) (asset.length() - 1);
        }
        if (ranges.length == 2) {
            to = Integer.parseInt(ranges[1]);
        }

        final String responseRange = String.format("bytes %d-%d/%d", from, to, asset.length());
        final RandomAccessFile raf = new RandomAccessFile(asset, "r");
        raf.seek(from);

        final int len = to - from + 1;
        final MediaStreamer streamer = new MediaStreamer(len, raf);
        Response.ResponseBuilder res = Response.ok(streamer)
                .status(Response.Status.PARTIAL_CONTENT)
                .header("Accept-Ranges", "bytes")
                .header("Content-Range", responseRange)
                .header(HttpHeaders.CONTENT_LENGTH, streamer.getLenth())
                .header(HttpHeaders.LAST_MODIFIED, new Date(asset.lastModified()));
        return res;
    }

}
