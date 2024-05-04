package com.example.pokemongame.dao;

import com.example.pokemongame.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    public QueryRunner queryRunner=new QueryRunner();
    /**
     * update()用来执行：insert/update/delete语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return 返回-1说明执行失败，返回其他值表示影响的行数
     */
    public int update(String sql,Object... args){
        Connection conn= DBUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     * @param <T> 返回的类型的泛型
     */
    public <T> T queryForone(Class<T> type,String sql,Object... args)
    {
        Connection conn=DBUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param type 返回的对象类型
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     * @param <T> 返回的类型的泛型
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args)
    {
        Connection conn=DBUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object... args){
        Connection conn=DBUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
