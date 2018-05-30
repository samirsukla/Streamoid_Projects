package com.services.utility;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class CompressFolder {
    
    List<String> filesListInDir = new ArrayList<String>();

    public static void main(String[] args) {
        
        File dir = new File("/home/streamoid/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects"
        		+ "/automation-scripts/JS-SDK-Test/Output/24052018");
        String zipDirName = "/home/streamoid/Desktop/Samir_Streamoid/Selenium_Setup/Eclipse_program/Streamoid Projects"
        		+ "/automation-scripts/JS-SDK-Test/Output/24052018.zip";
        
        CompressFolder zipFiles = new CompressFolder();
        zipFiles.zipDirectory(dir, zipDirName);
    }

    /**
     * This method zips the directory
     * @param dir
     * @param zipDirName
     */
    private void zipDirectory(File dir, String zipDirName) {
        try {
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
//                System.out.println("Zipping "+filePath);
//                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
//                System.out.println(filePath+" "+filePath.length());
//                System.out.println(dir.getAbsolutePath()+" "+dir.getAbsolutePath().length());
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                    zos.setLevel(9);
                }
                
                
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @throws IOException
     */
    private void populateFilesList(File dir) throws IOException {
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(file);
        }
    }

}
