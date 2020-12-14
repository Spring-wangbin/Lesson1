package com.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Utils {
    //通过标识名来创建相应连接池
    static ComboPooledDataSource dataSource = new ComboPooledDataSource("sqlserver");

    //从连接池中取用一个连接
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (Exception e){
            System.out.println("数据库连接出错:"+e);
            return null;
        }
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new Error("数据库连接关闭出错!", e);
            }
        }
        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                throw new Error("数据库连接关闭出错!", e);
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new Error("数据库连接关闭出错!", e);
            }
        }
    }
}
