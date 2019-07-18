package com.comicspider.utils;

import com.comicspider.enums.ExceptionEnum;
import com.comicspider.exception.SpiderException;

import java.io.*;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
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
        try(FileOutputStream out=new FileOutputStream(file)) {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getZipFile(String sourcePath){
        File sourceFile=new File(sourcePath);
        if (!sourceFile.exists()){
            throw new SpiderException(ExceptionEnum.FILE_NOT_FOUND);
        }
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ZipOutputStream zos=new ZipOutputStream(baos);
        compress(zos,sourceFile,sourceFile.getName());
        try {
            zos.finish();
            zos.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static void compress(ZipOutputStream zos,File sourceFile,String fileName){
        if (sourceFile.isDirectory()){
            File[] files=sourceFile.listFiles();
            if (files == null || files.length == 0) {
                try {
                    zos.putNextEntry(new ZipEntry(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                for (File file : files) {
                    compress(zos, file, file.getName());
                }
            }
        } else {
            try(FileInputStream fos=new FileInputStream(sourceFile)) {
                zos.putNextEntry(new ZipEntry(fileName));
                int len;
                while ((len=fos.read())!=-1){
                    zos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFileOutput(String path, Map<String,byte[]> data){
        File zipFile=new File(path);
        if (!zipFile.getParentFile().exists()){
            zipFile.mkdirs();
        }
        try(FileOutputStream fos=new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos)) {
            ZipEntry zipEntry;
            for (String dataFileName : data.keySet()){
                zipEntry=new ZipEntry(dataFileName);
                zos.putNextEntry(zipEntry);
                zos.write(data.get(dataFileName));
            }
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,byte[]> zipFileInput(String path){
        Map<String,byte[]> data=new LinkedHashMap<>();
        try {
            ZipFile zipFile=new ZipFile(path);
            Enumeration<?> entries=zipFile.entries();
            ZipEntry entry;
            while (entries.hasMoreElements()){
                entry = (ZipEntry) entries.nextElement();
                try (InputStream is=zipFile.getInputStream(entry)){
                    byte[] fileByte=new byte[is.available()];
                    if (is.read(fileByte)!=-1){
                        data.put(entry.getName(),fileByte);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
