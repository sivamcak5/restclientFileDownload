package org.sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.StandardCopyOption;
import java.util.Map;



import org.apache.commons.io.IOUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class DownloadController {
	
	
	public static void download(){
		try {
			
			RestPathBuilder builder = RestPathBuilder.dummmyPathDataUrl();
			
			Client client = Client.create();
			
			String path =builder.buildUrl();
			
			System.out.println(":path:"+path);
			WebResource webResource = client.resource(path);
			if(builder.getRequestHeaders()!=null && builder.getRequestHeaders().size()>0){
				for(Map.Entry<String, String> head: builder.getRequestHeaders().entrySet()){
					webResource.header(head.getKey(), head.getValue());
				}
			}

			ClientResponse response = webResource.get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			
			InputStream inputStream = response.getEntityInputStream();
			
			String resultPath="d://tmp/sample.csv";
			
			File file = new File(resultPath);
			 // get output stream of the response
			 
			// if you use java8 use this . this will be faster
		    /*java.nio.file.Files.copy(
		      inputStream, 
		      file.toPath(), 
		      StandardCopyOption.REPLACE_EXISTING);
		 
		    IOUtils.closeQuietly(inputStream);*/
			OutputStream outputStream = new FileOutputStream(file);
			IOUtils.copy(inputStream, outputStream);
			inputStream.close();
			outputStream.close();
			

			System.out.println("Output from Server created in ::"+file.getAbsolutePath());

		  } catch (Exception e) {

			e.printStackTrace();

		  }

	}
	
	public static void main(String arg[]){
		download();
	}

}
