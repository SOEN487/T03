package com.example.rest;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/files")
public class FileService {

    // File path to download file
    private static final String FILE_PATH = "/home/nick/Documents/SOEN487/T03/pom.xml";

    /**
     * The API method will return the file located at FILE_PATH. Modify the path to return whatever
     * @return file associated with FILE_PATH
     */
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public File getFile() {
        File file = new File(FILE_PATH);
        return file;
    }

    /**
     * This API method will upload a file to the specified file location
     * @param uploadedInputStream file to upload
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream) {
        String fileLocation = "/home/nick/place.txt"; // file to save to
        //saving file
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileLocation);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}