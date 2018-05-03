package com.valueList.adapter;

import com.valueList.formatter.VlFormatterFactory;
import net.mlw.vlh.ValueListInfo;
import net.mlw.vlh.adapter.jdbc.AbstractJdbcAdapter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iths
 * @date 2018/4/10.
 */
public class SqlPagingMapAdapter extends AbstractJdbcAdapter {

    private Map<String,String> formatter;

    /**
     * 对查询出来的数据进行格式处理
     * @param s
     * @param resultSet
     * @param numberPerPage
     * @param valueListInfo
     * @return
     * @throws SQLException
     */
    public List processResultSet(String s, ResultSet resultSet, int numberPerPage, ValueListInfo valueListInfo) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map map = null;
        for(int rowIndex = 0; resultSet.next() && rowIndex < numberPerPage; rowIndex++) {
            map = new HashMap();
            for(int i=1;i<=columnCount;i++){
                String columnName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(i);
                if(formatter.containsKey(columnName)){
                    value = formatterValue(columnName,value);
                }
                map.put(columnName,value);
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 需要格式化的数据
     * @param map
     */
    public void setFormatter(Map<String,String> map){
        formatter = map;
    }

    private String formatterValue(String columnName,Object value){
        String format = VlFormatterFactory.format(formatter.get(columnName), value);
        return format;
    }
}
