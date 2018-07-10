package com.services.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageDownloadFromURL {
	static CreateAbsolutePath createPath;
  
  public String downloadImage(String imageUrl) throws IOException {
	  createPath=new CreateAbsolutePath();
	  String pathToImageFolder=createPath.pathToAnimatorImage();
	    String imageName = imageUrl.replaceAll("https://d1z7sptgse6ho6.cloudfront.net/", "");
		String destinationFile = pathToImageFolder+"/"+imageName;
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
		
		return imageName;
  }
}
