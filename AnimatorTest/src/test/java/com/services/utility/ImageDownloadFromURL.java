package com.services.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageDownloadFromURL {
  
  public String downloadImage(String imageUrl) throws IOException {
	    String imageName = imageUrl.replaceAll("https://s3.eu-west-1.amazonaws.com/streamoid.animator.images/", "");
		String destinationFile = "/Users/samirsukla/Desktop/Animator_Images/"+imageName;
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
