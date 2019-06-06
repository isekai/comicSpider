package com.comicspider.exception;

import com.comicspider.dto.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author doctor
 * @Date 19-6-3
 **/
@ControllerAdvice
@Slf4j
public class SpiderExceptionHandler {

    @ExceptionHandler(SpiderException.class)
    @ResponseBody
    public ResponseCode spiderException(SpiderException e){
        return new ResponseCode(e.getCode(), e.getMsg());
    }

/*    @ExceptionHandler(Exception.class)
    public String ExceptionProcess(Exception e){
      log.error(e.getMessage());
      return "error";
    }*/
}
