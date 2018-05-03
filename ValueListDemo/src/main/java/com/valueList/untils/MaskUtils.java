package com.valueList.untils;

import com.alibaba.druid.util.StringUtils;

/**
 * @author iths
 * @date 2018/4/10.
 */
public class MaskUtils {
    public static String maskName(String name){
        if(!StringUtils.isEmpty(name)){
            return mask(name,1);
        }
        return name;
    }
    public static String mask(String str,int start,int end){

        return "";
    }

    public static String mask(String str,int start){
        int length = str.length();
        StringBuffer buffer = new StringBuffer(str);
        StringBuffer replace = new StringBuffer();
        for(int i=0;i<length-start;i++){
            replace.append("*");
        }
        buffer.replace(start,length,replace.toString());
        return buffer.toString();
    }
}
