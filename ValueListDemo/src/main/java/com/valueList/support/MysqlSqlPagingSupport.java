package com.valueList.support;

import net.mlw.vlh.adapter.jdbc.util.SqlPagingSupport;

/**
 * @author iths
 * @date 2018/4/8.
 */
public class MysqlSqlPagingSupport extends SqlPagingSupport {

    public static final String MYSQL = "mysql";

    private String pagedQueryPreSql;
    private String pagedQueryPostSql;

    @Override
    public StringBuffer getPagedQuery(String sql) {
        StringBuffer buffer = new StringBuffer(500);
        buffer.append(pagedQueryPreSql);
        buffer.append(sql);
        buffer.append(pagedQueryPostSql);

        return buffer;
    }

    @Override
    public StringBuffer getCountQuery(String sql) {
        StringBuffer buffer = new StringBuffer(sql.length()+100);
        String countSql = "select count(1) from ( "+sql+" ) as countPagingTable";
        return buffer.append(countSql);
    }

    @Override
    public void setDatabase(String database) {
        if (MYSQL.equalsIgnoreCase(database)) {
            this.pagedQueryPreSql = "";
            //处理分页
            this.pagedQueryPostSql = "  /~numberPageSize:LIMIT {numberPageSize}~/\n" +
                    "/~startPageSize:OFFSET {startPageSize}~/";
        } else {
            throw new NullPointerException(database + " is not supported (" + MYSQL + ").");
        }
    }

    @Override
    public void setPagedQueryPostSql(String pagedQueryPostSql) {
        this.pagedQueryPostSql = pagedQueryPostSql;
    }

    @Override
    public void setPagedQueryPreSql(String pagedQueryPreSql) {
        this.pagedQueryPreSql = pagedQueryPreSql;
    }

}
