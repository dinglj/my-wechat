package com.hpz.common.page;

/**
 *
 * Created by mao on 2015/6/9.
 */
public class MySql5Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    /**
     * 得到分页SQL
     * @param querySql 查询sql
     * @param offset 起始位置
     * @param limit  每页显示条数
     * @return 分页SQL
     */
    public String getLimitString(String querySql, int offset, int limit){
        /***** 对sql语句做特殊字符处理 ****/
        return getFormatSql(querySql) + " limit "+ offset + " ," + limit;
    }

    /**
     * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
     * 去掉sql中的分号,为后续分页sql做准备工作
     * 例：select *     FROM tb_user    u    WHERE   u.aa and u.bb;
     * 经过此方法处理后变为： select * FROM tb_user u WHERE u.aa and u.bb
     *
     * @param  sql SQL语句
     * @return 如果sql是NULL返回空，否则返回转化后的SQL
     */
    private String getFormatSql(String sql) {
        /***************************************************************************
         * replaceAll("[\r\n]", " ") 匹配 新行(\n)或者 回车符(\r)，将其替换为一个空格
         * replaceAll("\\s{2,}", " "); 匹配若语句中出现 连续两个空白字符那么替换成 一个空格
         * *************************************************************************/
        sql = sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");

        /*******************************************************************
         * [^\\s,]+，表示非空白字符一次或多次
         * 例：select * FROM tb_user u;
         * 如上语句经过处理后,会去掉分号：select * FROM tb_user u
         ********************************************************************/
        if(sql.contains(";")){
            sql = sql.replaceAll("\\;", "");
        }

        /*******************************************************************
         *
         * 例：select * FROM tb_user u WHERE u.aa and u.bb
         * 如上语句经过处理后：select * FROM tb_user u WHERE aa and bb
         ********************************************************************/
//		sql = sql.replaceAll("[^\\s,]+\\.", "");

        return sql;
    }

}
