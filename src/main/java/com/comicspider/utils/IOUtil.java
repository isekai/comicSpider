package com.comicspider.utils;

import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author doctor
 * @Date 19-5-23
 **/
public class IOUtil {
    public static void writeFile(String path,byte[] content){
        File file=new File(path);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream out=new FileOutputStream(file);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ZipFileOutput(String path, Map<String,byte[]> data){
        File zipFile=new File(path);
        if (!zipFile.getParentFile().exists()){
            zipFile.mkdirs();
        }
        try {
            FileOutputStream fos=new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry zipEntry;
            for (String dataFileName : data.keySet()){
                zipEntry=new ZipEntry(dataFileName);
                zos.putNextEntry(zipEntry);
                zos.write(data.get(dataFileName));
            }
            zos.closeEntry();
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
