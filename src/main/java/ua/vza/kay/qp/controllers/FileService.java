package ua.vza.kay.qp.controllers;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import ua.vza.kay.qp.utils.DefineOS;

import javax.enterprise.context.RequestScoped;
import javax.json.JsonArray;
import javax.mail.internet.MimeUtility;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by kycenko on 10.08.15.
 */
@Path("files")
@RequestScoped
public class FileService {

    private final String PATHFL = DefineOS.osDefine();

    @Path("path")
    @GET
    @Produces("text/html")
    public String getUploadPath(){
        return PATHFL;
    }

    @Path("upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws UnsupportedEncodingException {
        String nameFile = new String(fileDetail.getFileName().getBytes("ISO-8859-1"),"UTF-8");
        StringBuilder uploadedFileLocation = new StringBuilder();
        uploadedFileLocation.append(PATHFL);
        uploadedFileLocation.append(nameFile);
        String output = null;
        try {
            writeToFile(uploadedInputStream, uploadedFileLocation.toString());
            output = fileDetail.getFileName();
        } catch (Exception e) {
            return Response.status(500).entity("Файл существует").build();
        } finally {
            if(uploadedInputStream != null) try {
                uploadedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Response.status(200).entity(output).build();
    }
    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws Exception{
        OutputStream out = null;
        try {
            File uploadFile = new File(uploadedFileLocation);
            if(uploadFile.exists()) throw new Exception("Файл существует");
            else {
                int read = 0;
                byte[] bytes = new byte[1024];
                out = new FileOutputStream(uploadFile);
                while ((read = uploadedInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //DOWNLOAD FILE
    @Path("get_file")
    @GET
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getFile(@QueryParam("file") String fileName) throws UnsupportedEncodingException {
        File file = new File(PATHFL+fileName);
        Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-disposition", "attachment; filename=\"" + MimeUtility.encodeWord(fileName) + "\"");
//        responseBuilder.header("Content-Type", "application/pdf");
        return responseBuilder.build();
    }

    //DEL FILE FROM FILE STOREG AND DB
    @Path("del_file")
    @GET
    @Consumes("application/json")
    public Response delFile(@QueryParam("file") String file){
        if (new File(PATHFL + file).delete()) {
            return Response.status(200).entity("Файл удален успешно!").build();
        }
        else return Response.status(500).entity("Ошибка удаления файла!").build();
    }

    @Path("del_array_file")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/html")
    public Response delListFile(JsonArray listFile) {
        String msg = "Файлы удалены успешно!";
        int status = 200;
        for(int i = 0; i<listFile.size(); i++) {
            String fileName = listFile.getJsonObject(i).getString("name").trim();
            if (!new File(PATHFL + fileName).delete()) {
                msg = "Ошибка удаления файла!";
                status = 500;
            }
        }
        return Response.status(status).entity(msg).build();
    }

}
