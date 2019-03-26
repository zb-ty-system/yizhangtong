package com.zillionfortune.cif.support.mybatis.page;

/**
 * OracleDialect
 *
 * @author levis
 * @version 1.0 2013-4-08
 */
public class MySqlDialect implements Dialect {

    /**
     * oracle page dialect implement
     *
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuilder pagingBuilder = new StringBuilder(sql.length() + 50);
        pagingBuilder.append(sql);
        pagingBuilder.append(" limit ").append(offset).append(",").append(limit);
        return pagingBuilder.toString();
    }

}
