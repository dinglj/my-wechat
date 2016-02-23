package com.hpz.common.page;

import com.hpz.out.model.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * Created by mao on 2015/6/9.
 */



@Intercepts(@Signature(type = StatementHandler.class , method = "prepare", args = { Connection.class }))
public class PageInterceptor implements Interceptor {

    /**
     * 拦截后要执行的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        MetaObject metaObj = SystemMetaObject.forObject(statementHandler);
        StatementHandler delegate = (StatementHandler)metaObj.getValue("delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object paramObj = boundSql.getParameterObject();
        if (paramObj instanceof Page<?>) {
            Page<?> page = (Page<?>) paramObj;
            MetaObject meta = SystemMetaObject.forObject(delegate);
            MappedStatement mappedStatement = (MappedStatement)meta.getValue("mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            this.setTotalRecord(page, mappedStatement, connection);
            if(page.getTotal()==0){
                //TODO 不需要执行findsql
            }
            String sql = boundSql.getSql();
            String pageSql = this.getMysqlPageSql(page, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }

    /**
     * target 对象会自动注入
     * 拦截器对应的封装原始对象的方法
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 获取Mysql数据库的分页查询语句
     * @param page  分页对象
     * @param sqlBuffer   包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    private String getMysqlPageSql(Page<?> page, String sqlBuffer) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        int offset = (page.getPage() - 1) * page.getPageSize();
        return new MySql5Dialect().getLimitString(sqlBuffer, offset, page.getPageSize());
    }

    /**
     * 给当前的参数对象page设置总记录数
     * @param page Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection 当前的数据库连接
     */
    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement,
                                Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        String sql = boundSql.getSql();
        String countSql = this.getCountSql(sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql,parameterMappings, page);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                page.setRecords(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * 注: 原sql 为：SELECT * FROM tb_user u ORDER BY u.userid DESC
     * 替换后的sql 为：select count(*) FROM tb_user u ORDER BY u.userid DESC
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        return "select count(1) from (" + sql+") page";
    }

    /**
     * 利用反射进行操作的一个工具类
     */
    private static class ReflectUtil{

        /**
         * 利用反射获取指定对象里面的指定属性
         * @param obj 目标对象
         * @param fieldName 目标属性
         * @return 目标字段
         */
        private static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
                    .getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException e) {
                    // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
                }
            }
            return field;
        }

        /**
         * 利用反射设置指定对象的指定属性为指定的值
         * @param obj 目标对象
         * @param fieldName 目标属性
         * @param fieldValue 目标值
         */
        public static void setFieldValue(Object obj, String fieldName,
                                         String fieldValue) {
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
