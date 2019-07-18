package com.comicspider.controller;

import com.comicspider.config.GlobalConfig;
import com.comicspider.utils.IOUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author doctor
 * @Date 19-7-18
 **/
@RestController
public class FileController {
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadComic(int comicId, int chapterId){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",chapterId+".zip");
        String path= GlobalConfig.ROOT_PATH+comicId+"/"+chapterId;
        return new ResponseEntity<>(IOUtil.getZipFile(path), headers, HttpStatus.OK);
    }
}
