package com.test.mybatis;

import com.test.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        String resources = "mybatis-config.xml";

        InputStream is = Resources.getResourceAsStream(resources);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = factory.openSession();

        System.out.println(session);

        User u = session.selectOne("com.test.mybatis.dao.UserMapper.findUserById",1);

        System.out.println(u);

        List<User> ul = session.selectList("com.test.mybatis.dao.UserMapper.findAllUser");

        System.out.println(ul);

        session.close();

        is.close();
    }
}
