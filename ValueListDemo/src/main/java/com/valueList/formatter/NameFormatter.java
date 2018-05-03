package com.valueList.formatter;

import com.valueList.untils.MaskUtils;

/**
 * @author iths
 * @date 2018/4/10.
 */
public class NameFormatter extends AbstractVlFormatter {
    public String format(String name, Object value) {
        if(value != null){
            String toString = value.toString();
            return MaskUtils.maskName(toString);
        }
        return null;
    }

    public String getFormaterName() {
        return "NAME";
    }
}
