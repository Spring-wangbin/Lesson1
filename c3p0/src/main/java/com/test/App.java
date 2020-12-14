package com.test;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        DBUtil_BO dbBo = new DBUtil_BO();
        dbBo.conn=C3p0Utils.getConnection();//取用一个连接
        String sql = "select * from t_user";
        dbBo.st=dbBo.conn.prepareStatement(sql);//预处理sql语句
//        dbBo.st.setString(1, "");
//        dbBo.st.setString(2, "");
        //此时dbBo对象已经封装了一个数据库连接以及要执行的操作

        DBUtils.executeQuery(dbBo);//通过数据库操作类来执行这个操作封装类，结果封装回这个操作封装类

        //从dbBo类提取操作结果
        while (dbBo.rs.next()) {
            int uid =dbBo.rs.getInt("id");
            System.out.println(uid);
        }
        //结果集遍历完了，手动释放连接回连接池
        DBUtils.realseSource(dbBo);
    }
}
