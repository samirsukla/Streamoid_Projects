package com.services.utility;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateAbsolutePath {

  public String makeAbsolutePath() {
	  
	  String relativePathStr="Result_File";
	  Path relativePath = Paths.get(relativePathStr);
	  Path absolutePath = relativePath.toAbsolutePath();
	 // System.out.println(absolutePath);
	  String absolutePathStr=absolutePath.toString();
	 
	  return absolutePathStr;
  }
  
  public String pathToAnimatorImage() {
	  String relativePathStr="Animator_Images";
	  Path relativePath = Paths.get(relativePathStr);
	  Path absolutePath = relativePath.toAbsolutePath();
	//  System.out.println(absolutePath);
	  String absolutePathStr=absolutePath.toString();
	 
	  return absolutePathStr;
	  
  }
}

