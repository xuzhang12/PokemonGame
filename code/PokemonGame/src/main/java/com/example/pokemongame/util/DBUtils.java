package com.example.pokemongame.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接的工具类
 */
public class DBUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();  //从数据库连接池中获取连接
                conns.set(conn);                    //将连接放到ThreadLocal对象中，用于后面获取
                conn.setAutoCommit(false);          //设置为手动提交
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    /**
     * 提交并关闭连接
     */
    public static void commitAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /**
     * 回滚并关闭连接
     */
    public static void rollbackAndClose() {
        Connection conn = conns.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
}
