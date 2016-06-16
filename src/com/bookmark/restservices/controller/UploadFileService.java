package com.bookmark.restservices.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.bookmark.restservices.model.Output;
import com.bookmark.restservices.utils.JdbcUtil;
import com.bookmark.restservices.utils.LoadCSVtoDB;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/upload")
public class UploadFileService {

	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public JSONObject uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
		System.out.println("-----------------------");
		Output output = new Output(); 
		String uploadedFileLocation = "d://out/" + fileDetail.getFileName();
		
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		LoadCSVtoDB csvtodb= new LoadCSVtoDB(JdbcUtil.getInstance().getMySqlConnection(), ",");
		csvtodb.CSVtoDB(uploadedFileLocation, true,output);
		return JdbcUtil.getInstance().pojo2Json(output);

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			//out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				System.out.println("-->"+read);
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}