1. mybatis-config.xml为mybatis的全局配置文件
    + <typeAlias> 元素定义了一个别名User，它代表着 com.test.mybatis.entity.User 这个类。这样定义后，在 MyBatis 上下文中就可以使用别名去代替全限定名了。
    + <environment> 元素的定义，这里描述的是数据库。它里面的 <transactionManager> 元素是配置事务管理器，这里采用的是 MyBatis 的 JDBC 管理器方式。
    + <dataSource> 元素配置数据库，其中属性 type="POOLED" 代表采用 MyBatis 内部提供的连接池方式，最后定义一些关于 JDBC 的属性信息。
    + <mapper> 元素代表引入的那些映射器，在谈到映射器时会详细讨论它
  mapper.xml文件为sql映射文件，在mybatis-config.xml中配置注册（mappers标签）
    <mappers>
        <mapper resource="userMapper.xml"/>
    </mappers>
  注意：初始化配置文件的本质就是创建Configuration对象，将解析的xml数据封装到Configuration内部的属性中
        MyBatis 配置项的顺序不能颠倒。如果颠倒了它们的顺序，那么在 MyBatis 启动阶段就会发生异常，导致程序无法运行
2. 配置文件加载流程：
    获取配置文件输入流
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml")
    创建SqlSessionFactoryBuilder对象，通过build(is)读取解析配置文件，返回SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
    创建SqlSession对象用于执行数据操作，默认事务开启
        SqlSession session = factory.openSession();
3. 传统工作模式(手动调用SqlSession的api))：
    调用SqlSession的api接口方法，最后调用JDBC执行SQL语句，封装结果返回
        List<User> list = sqlSession.selectList("com.demo.mapper.UserMapper.getUserByName",params);
        <com.demo.mapper.UserMapper>：UserMapper.xml文件的命名空间
        <getUserByName>：UserMapper.xml文件中对应的id，用于映射sql语句
        <params>:sql语句需要嵌入的参数
4. 使用Mapper接口（面向切面的开发趋势，通过接口调用mapper配置文件的sql语句）：
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> list = mapper.findUserById("idvalue");

5. 加载配置文件源码解析（自行研究）

6. 核心接口SqlSession
    实现类：DefaultSqlSession 和 SqlSessionManager
    DefaultSqlSession是单线程使用的，SqlSessionManager在多线程环境下使用.
    SqlSession作用类似一个JDBC中的Connection对象，代表一个资源连接的启用。有以下三个作用：
        获取Mapper接口
        发送SQL给数据库
        控制事务

7. Mapper.xml文件基础标签讲解：
    <mapper>元素中的属性 namespace 所对应的是一个接口的全限定名，于是 MyBatis 上下文就可以通过它找到对应的接口。
    <select>元素表明是一条查询语句，属性id标识了这条SQL，
        属性 parameterType="long"说明传递给SQL的是一个long型的参数，
             parameterType="Map"表示入参是一个map对象，通过#{键}映射map值
        resultType="User"表示返回的是一个User类型的返回值。User是之前mybatis-config.xml中配置的别名，指代：com.test.mybatis.entity.User
        resultType="java.util.HashMap"表示返回Map集合
        resultMap属性与resultMap元素标签一起使用
        resultType和resultMap属性，都可以返回List集合或者单个对象