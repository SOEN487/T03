package com.example.rest;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/files")
public class FileService {
    // https://www.javatpoint.com/jax-rs-file-download-example
    private static final String FILE_PATH = "/home/nick/Documents/SOEN487/T02/pom.xml";
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public File getFile() {
        File file = new File(FILE_PATH);
        return file;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream) {
        String fileLocation = "/home/nick/place.txt";
        //saving file
        try {
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}