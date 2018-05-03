package com.valueList.formatter;

/**
 * @author iths
 * @date 2018/4/10.
 */
public abstract class AbstractVlFormatter implements VlFormatter {
    public AbstractVlFormatter(){
        VlFormatterFactory.regist(getFormaterName(),this);
    }
}
