package com.test.mybatis;

import com.test.mybatis.dao.UserMapper;
import com.test.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App_Mapper
{
    public static void main( String[] args ) throws IOException {
        String resources = "mybatis-config.xml";

        InputStream is = Resources.getResourceAsStream(resources);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = factory.openSession();

        System.out.println(session);

        UserMapper mapper = session.getMapper(UserMapper.class);

        User u = mapper.findUserById(1);

        System.out.println(u);

        Map<String,Object> u2 = mapper.getUserMap(1);

        System.out.println(u2);

        List<Map<String,Object>> al = mapper.getAllUserMap();

        System.out.println(al);

        Map<String,Object> r1 = mapper.execSql("select 2 as count");
        System.out.println(r1);

        session.close();

        is.close();
    }
}
