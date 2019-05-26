package com.comicspider.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author doctor
 * @Date 19-5-26
 **/
public class StringUtil {

    /**
    * @param str 字符串, regex 正则表达式, group 组
    * @return string
    **/
    public static String findByRegex(String str,String regex,int group){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        if (matcher.find()){
            return matcher.group(group);
        }
        return null;
    }

}
