package com.comicspider.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void ZipFileOutput(String path){
    }
}
