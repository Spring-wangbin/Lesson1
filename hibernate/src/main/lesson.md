1. hibernate执行流程
    1. 创建 Configuration 实例，加载 Hibernate 核心配置文件和映射文件信息到 Configuration 对象中。
    2. 创建 SessionFactory 实例。通过 Configuration 对象读取到的配置文件信息创建 SessionFactory 对象，该对象中保存了当前数据库的配置信息和所有映射关系等信息。
    3. 创建 Session 实例，建立数据库连接。Session 主要负责执行持久化对象的增、删、改、查操作，创建一个 Session 就相当于创建一个新的数据库连接。
    4. 创建 Transaction 实例，开启一个事务。Transaction 用于事务管理，一个 Transaction 对象对应的事务可以包含多个操作。在使用 Hibernate 进行增、删、改操作时，必须先创建 Transaction 对象。需要注意的是，Hibernate 的事务默认是关闭的，需要手动开启事务和关闭事务。
    5. 利用 Session 接口通过的各种方法进行持久化操作。
    6. 提交事务，对实体对象持久化操作后，必须提交事务。
    7. 关闭 Session 与 SessionFactory，断开与数据库的连接。
2. hibernate的6个核心接口
    1. Configuration
        + Configuration主要用于启动、加载和管理Hibernate的配置文件信息，在启动Hibernate的过程中，Configuration 实例首先确定Hibernate文件位置，
        然后读取相关配置，最后创建一个唯一的 SessionFactory 实例。
        + Hibernate 通常使用 Configuration config=new Configuration().configure(); 的方式创建实例，此种方式默认会在 src 下读取 hibernate.cfg.xml 配置文件。如果不希望配置文件放置在 src 目录下，则可以在 configure() 方法中传入一个参数指定文件位置，其代码如下所示：
        Configuration config = new Configuration().configure("文件的位置");
        此种写法中 Hibernate 会到指定的位置查询配置文件。例如，读取 src 下 config 包中的 hibernate.cfg.xml 文件，可以将代码写成如下形式：
        Configuration config = new Configuration().configure("/config/hibernate.cfg.xml");
        + 需要注意的是，Configuration 对象只存在于系统的初始化阶段，它将 SessionFactory 创建完成后，就完成了自己的使命。
    2. SessionFactory
        + SessionFactory 接口负责读取并解析映射文件，以及建立Session对象，它在Hibernate中起到一个缓冲区的作用，会将 Configuration 对象中的所有配置信息、Hibernate 自动生成的 SQL 语句以及某些可重复利用的数据加载到缓冲区中。同时，它还维护了 Hibernate 的二级缓存。
            通常所使用的 SessionFactory 实例是通过 Configuration 对象获取的，其获取方法如下所示：
            SessionFactory sessionFactory = config.buildSessionFactory();
        + SessionFactory 具有以下特点。
          它是线程安全的，它的同一个实例能够供多个线程共享。
          它是重量级的，不能随意创建和销毁它的实例。
        + 由于 SessionFactory 是一个重量级的对象，占用的内存空间较大，所以通常情况下，一个应用程序只需要一个 SessionFactory 实例，只有应用中存在多个数据源时，才为每个数据源建立一个 SessionFactory 实例。为此，在实际开发时，通常会抽取出一个工具类提供 Session 对象。
    3. Session
        + Session 是 Java 应用程序和 Hibernate 进行交互时所使用的主要接口，是持久化操作的核心 API。它主要用于读取、创建和删除映射对象的实例，这一系列的操作将被转换为数据表中的增加、修改、查询和删除操作。
        + Session 是轻量级的，实例的创建和销毁不需要消耗太多的资源，同时它还是 Hibernate 的一级缓存，这个缓存主要用于存放当前工作单元加载的对象。
        + 获取 Session 实例有两种方式，一种是通过 openSession() 方法，另一种是通过 getCurrentSession() 方法。两种方法获取 Session 的代码如下所示：
            - //采用openSession方法创建Session
            - Session session = sessionFactory.openSession();
            - //采用getCurrentSession()方法创建Session
            - Session session = sessionFactory.getCurrentSession();
            - 以上两种获取 Session 实例的主要区别是：
            - 采用 openSession() 方法获取 Session 实例时，SessionFactory 直接创建一个新的 Session 实例，并且在使用完成后需要调用 close() 方法进行手动关闭；
            - 而 getCurrentSession() 方法创建的 Session 实例会被绑定到当前线程中，它在提交或回滚操作时会自动关闭。
        + 需要注意的是，Session 是线程不安全的，当多个并发线程同时操作一个 Session 实例时，就可能导致 Session 数据存取的混乱（当方法内部定义和使用 Session 时，不会出现线程问题）。因此设计软件架构时，应避免多个线程共享一个 Session 实例。
    4. Transaction
        + Transaction 接口主要是用于管理事务，它是 Hibernate 的数据库事务接口，且对底层的事务接口进行了封装。
    5. Query
        + Query 接口是 Hibernate 的查询接口，主要用于执行 Hibernate 的查询操作。Query 中包装了一个 HQL（Hibernate Query Language）查询语句，该语句采用了面向对象的查询方式，具有丰富灵活的查询特征。因此，Hibernate 官方推荐使用 HQL 语言进行查询。
        - 在 Hibernate 中，使用 Query 对象的步骤如下。
        - 获得 Hibernate Session 对象。
        - 编写 HQL 语句。
        - 调用 session.createQuery 创建查询对象。
        - 如果 HQL 语句包含参数，则调用 Query 的 setXxx 设置参数。
        - 调用 Query 对象的 list() 或 uniqueResult() 方法执行查询。
    6. Criteria
        + riteria 接口是 Hibernate 提供的一个面向对象的查询条件接口，通过它完全不需要考虑数据库底层如何实现，以及 SQL 语句如何编写。
            Criteria 查询又称为 QBC 查询（Query By Criteria），是 Hibernate 的另一种对象检索方式。
3. Hibernate持久化对象的状态及状态转换
    Hibernate 是持久层的 ORM 框架，专注于数据的持久化工作。在进行数据持久化操作时，持久化对象可能处于不同的状态当中。这些状态可分为三种，分别为瞬时态、持久态和脱管态。下面分别针对这三种状态进行简单介绍。
    1）瞬时态（transient）
    瞬时态也称为临时态或者自由态，瞬时态的对象是由 new 关键字开辟内存空间的对象，不存在持久化标识 OID（相当于主键值），且未与任何的 Session 实例相关联，在数据库中也没有记录，失去引用后将被 JVM 回收。瞬时对象在内存孤立存在，它是携带信息的载体，不和数据库的数据有任何关联关系。
    2）持久态（persistent）
    持久态的对象存在一个持久化标识 OID，当对象加入到 Session 缓存中时，就与 Session 实例相关联。它在数据库中存在与之对应的记录，每条记录只对应唯一的持久化对象。需要注意的是，持久态对象是在事务还未提交前变成持久态的。
    3）脱管态（detached）
    脱管态也称离线态或者游离态，当持久化对象与 Session 断开时就变成了脱管态，但是脱管态依然存在持久化标识 OID，只是失去了与当前 Session 的关联。需要注意的是，脱管态对象发生改变时 Hibernate 是不能检测到的。
4. Hibernate一级缓存详解
    + Hibernate 中的缓存分为一级缓存和二级缓存，这两个级别的缓存都位于持久化层，并且存储的都是数据库数据的备份。其中一级缓存是 Hibernate 的内置缓存，本节将针对 Hibernate 的一级缓存进行详细讲解。
    + 一级缓存其实就是 Session 缓存。Session 缓存是一块内存空间，用于存储与管理 Java 对象。
    + 在使用 Hibernate 查询对象时，首先会使用对象的 OID 值在 Hibernate 的一级缓存中查找，如果找到匹配的对象，则直接将该对象从一级缓存中取出使用；如果没有找到匹配的对象，则会去数据库中查询对应的数据。当从数据库中查询到所需数据时，该数据信息会存储到一级缓存中。由此可知，Hibernate 一级缓存的作用就是减少对数据库的访问次数。
5. Hibernate快照技术详解
    + Hibernate 向一级缓存中存入数据的同时，还会复制一份数据存入 Hibernate 快照中。当调用 commit() 方法时，会清理一级缓存中的数据操作），同时会检测一级缓存中的数据和快照区的数据是否相同。如果不同，则会执行 update() 方法，将一级缓存的数据同步到数据库中，并更新快照区；反之，则不会执行 update() 方法。
6. 一级缓存常用操作：刷出、清除和刷新
    1. 刷出（flush）
        + Hibernate 在事务提交前，会先执行flush方法
    2. 清除（clear）
        +  session.clear() 和 session.evict（goods）都可以实现清除一级缓存的功能。两者区别：clear() 方法是清空一级缓存中所有的数据，而 evict() 方法是清除一级缓存中的某一个对象。
    3. 刷新（refresh）
        + 程序在调用 Session 的 refresh() 方法时，会重新查询数据库，并更新 Hibernate 快照区和一级缓存中的数据
        + refresh() 方法可以使 Hibernate 快照区和一级缓存中的数据与数据库中的数据保持一致。
7. Hibernate 映射关系（暂省略）
8. Hibernate级联（cascade）与反转（inverse）
9. Hibernate的5种检索方式
    + 导航对象图检索方式
        - 导航对象图检索方式是根据已经加载的对象，导航到其他对象。它利用类与类之间的关系检索对象。
        - 例如，对于已经加载的学生对象，就可以利用学生对象自动导航找到该学生所对应的班级对象，但前提是需要在对象关系映射文件上配置两者多对一的关系。
            Student student = (Student)session.get(Student.class,1);
            Grade grade = student.getGrade();
    + OlD检索方式
        - OID 检索方式是指按照对象的 OID 检索对象。它使用 Session 对象的 get() 和 load() 方法加载某一条记录所对应的对象，其使用的前提是需要事先知道 OID 的值。
            Grade grade1 = (Grade)session.get(Grade.class,1);
            Grade grade2 = (Grade)session.get(Grade.class,1);
    + HQL检索方式  
        - HQL（Hibernate Query Language）是 Hibernate 查询语言的简称，它是一种面向对象的查询语言，与 SQL 查询语言有些类似，但它使用的是类、对象和属性的概念，而没有表和字段的概念。
    + QBC检索方式
        - QBC（Query By Criteria）是 Hibernate 提供的另一种检索对象的方式，它主要由 Criteria 接口、Criterion 接口和 Expression 类组成，并且支持在运行时动态生成查询语句。
    + 本地SQL检索方式
        - 本地 SQL 检索方式就是使用本地数据库的 SQL 查询语句进行查询。在 Hibernate 中，SQL 查询是通过 SQLQuery 接口表示的，该接口是 Query 接口的子接口，因此可以调用 Query 接口的方法。
        - 示例： SQLQuery sqlQuery = session.createSQLQuery("select * from user");
10. Hibernate HQL的5种常见检索方式
    + 指定别名 String hql = "from User as u where u.name='zhangsan '";
    + 投影查询 
        - String hql = "select u.name, u.age from User as u";
        - 当检索对象的部分属性时，Hibernate 返回的 List 中的每一个元素都是一个 Object 数组，而不再是 User 对象。
    + 动态实例查询
        - 使用投影查询时，返回的查询结果是一个对象数组。由于在输出数据时还需要处理顺序，因此操作十分不便。为了方便操作，提高检索效率，可将检索出来的数据重新封装到一个实体的实例中，这种方式就是动态实例查询
        - 需要注意的是，使用此种查询方式，需要在 User 实体类中添加一个有参的构造方法和一个无参的构造方法
        - String hql = "select new User (u.name, u.age) from User as u";
    + 条件查询
        1. 按参数位置查询
            String hql = "from User where name like ?"; // 编写 HQL,使用参数查询
            Query query = session.createQuery(hql); // 创建 Query对象
            query.setString(0, "%ang%"); // 为 HQL中的”？”代表的参数设置值
        2. 按参数名字查询
            String hql = "from User where id =:id"; // 编写 HQL
            Query query = session.createQuery(hql); // 创建 Query对象
            query.setParameter("id", 4); // 添加参数
    + 分页查询
        - 在批量查询数据时，在单个页面上会显示所有的查询结果，这在实际开发中是不合理的。通常情况下，开发人员会对查询结果进行分页显示
        - setFirstResult（int firstResult）：设定从哪个对象开始查询，参数 firstResult 表示这个对象在查询结果中的索引（索引的起始值为 0）。
        - setMaxResult（int maxResult）：设定一次返回多少个对象。通常与 setFirstResult（int firstResult）方法结合使用，从而限制结果集的范围。默认情况下，返回查询结果中的所有对象。
            String hql = "from User"; // 编写 HQL
            Query query = session.createQuery(hql); //创建 Query对象
            query.setFirstResult(1); // 从第 2 条开始查询
            query.setMaxResults(3); // 查询 3 条数据
11. QBC的2种检索方式 
12. Hibernate事务的配置
    + 使用 hibernate.current_session_context_class 参数配置本地事务和全局事务。其中，本地事务是指对一个数据库进行的操作，即只针对一个事务性资源进行操作；而全局事务是指由应用服务器管理的事务，它需要使用 JTA（Java Transaction API），可以用于多个事务性资源（跨多个数据库）。
    + 由于 JTA 的 API 非常笨重，一般只在应用服务器的环境中使用，并且全局事务的使用限制了应用代码的重用性，所以 Hibernate 的事务管理通常会选择使用本地事务。
        <!--使用本地事务-->
        <property name= "hibernate.current_session_context_class"> thread</property>
        <!--使用全局事务-->
        <property name= "hibernate.current_session_context_class">jta</property>
    + <!--设置事务隔离级别--> <property name= "hibernate.connection.isolation">2</property>
        1—Read uncommitted 读未提交。
        2—Read committed 读已提交。
        4—Repeatable read 可重复读。
        8—Serializable 串行化。
13. Hibernate悲观锁（pessimistic lock）
    + 悲观锁（pessimistic lock）是指在每次操作数据时，总是悲观地认为会有其他事务操作同一数据，因此，在整个数据处理过程中，会把数据处于锁定状态。
    + 悲观锁具有排他性，本质上由数据库实现。在锁定时间内，其他事务不能对数据进行存取等操作，这可能导致长时间的等待问题。
    + 一般来说，事务的隔离级别越高，越能保证数据库的完整性和一致性，但相对来说，隔离级别越高，对并发性能的影响也越大。
    因此，通常将数据库的隔离级别设置为 Read Committed，即读已提交数据，它既能防止脏读，又能有较好的并发性能。
    虽然这种隔离级别会导致不可重复读、幻读和第二类丢失更新这些并发问题，但可通过在应用程序中采用悲观锁和乐观锁加以控制。
14. Hibernate乐观锁（optimistic lock）
    + 相对于悲观锁而言，乐观锁（optimistic lock）通常认为多个事务同时操作同一数据的情况很少发生，因此乐观锁不进行数据库层次上的锁定，而是基于数据版本（Version）标识实现应用程序级别上的锁定机制，这既能保证多个事务的并发操作，又能有效防止第二类丢失更新的发生。
    + <!-- version字段必须紧跟放到id主键配置后边 -->
    + <property name="version" column="version" type="integer" />
15. Hibernate二级缓存详解
    + 一级缓存是 Session 级别的缓存，它是属于事务范围的缓存，这一级别的缓存由 Hibernate 管理，一般情况下无须进行干预。
    + 二级缓存是 SessionFactory 级别的缓存，它是属于进程范围的缓存，这一级别的缓存可以进行配置和更改，以及动态地加载和卸载，它是由 SessionFactory 负责管理的。
    + 二级缓存与一级缓存一样，也是根据对象的 ID 加载和缓存数据的。当执行某个查询获得的结果集为实体对象时，Hibernate 就会把获得的实体对象按照 ID 加载到二级缓存中。
    + 在访问指定的对象时，首先从一级缓存中查找，找到就直接使用，找不到则转到二级缓存中查找（必须配置和启用二级缓存）。如果在二级缓存中找到，就直接使用，否则会查询数据库，并将查询结果根据对象的 ID 放到一级缓存和二级缓存中。
    + SessionFactory 中的缓存可以分为两类，具体如下：
        1. 内置缓存
            - Hibernate 自带的只读属性的缓存，不可以被卸载。通常在Hibernate的初始化阶段会把映射的元数据和预定义的SQL语句放到SessionFactory的缓存中，映射元数据是映射文件中数据的复制，而预定义SQL语句是Hibernate根据映射元数据推导出来的。
        2. 外置缓存（二级缓存）
            - 一个可配置的缓存插件。在默认情况下，SessionFactory 不会启用这个缓存插件，外置缓存中的数据是数据库数据的复制，外置缓存的物理介质可以是内存或硬盘。
            - Hibernate 中的二级缓存可以分为四类，分别为类级别的缓存、集合级别的缓存、查询缓存和更新时间戳。
            - Class Cache Region    类级别的缓存，主要用于存储 PO（实体）对象。
            - Collection Cache Region   集合级别的缓存，用于存储集合数据。
            - Query Cache Region    查询缓存，会缓存一些常用查询语句的查询结果。
            - Update Timestamps     更新时间戳缓存，该区域存放了与查询结果相关的表在进行插入、更新或删除操作的时间戳，Hibernate 通过更新时间戳缓存区域判断被缓存的查询结果是否过期。
16. Hibernate二级缓存的并发访问策略
    + 由于在二级缓存中也会出现并发问题，因此在 Hibernate 的二级缓存中，可以设定以下四种类型的并发访问策略，以解决这些问题。每一种访问策略对应一种事务隔离级别，具体介绍如下：
    + 只读型（Read-Only）：提供 Serializable 事务隔离级别，对于从来不会被修改的数据，可以采用这种访问策略。
    + 读写型（Read-write）：提供 Read Committed 事务隔离级别，对于经常读但是很少被修改的数据，可以采用这种隔离类型，因为它可以防止脏读。
    + 非严格读写（Nonstrict-read-write）：不保证缓存与数据库中数据的一致性，提供 Read Uncommitted 事务隔离级别，对于极少被修改，而且允许脏读的数据，可以采用这种策略。
    + 事务型（Transactional）：仅在受管理环境下使用，它提供了 Repeatable Read 事务隔离级别。对于经常读但是很少被修改的数据，可以采用这种隔离类型，因为它可以防止脏读和不可重复读。
    + 二级缓存中存储的都是对象的散装数据，它们会重新 new 出一个新的对象。