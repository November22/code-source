package com.valueList.formatter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iths
 * @date 2018/4/10.
 */
public final class VlFormatterFactory{

    private static Map<String,VlFormatter> formatterMap = new HashMap<String, VlFormatter>();

    public static void regist(String name,VlFormatter vlFormatter){
        formatterMap.put(name,vlFormatter);
    }

    public static String format(String name, Object value) {
        if(formatterMap.containsKey(name)){
            VlFormatter vlFormatter = formatterMap.get(name);
            String format = vlFormatter.format(name, value);
            return format;
        }
        return value.toString();
    }
}
