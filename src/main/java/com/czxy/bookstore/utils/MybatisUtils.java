package com.czxy.bookstore.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.IOException;
import java.io.InputStream;


public class MybatisUtils {

    //成员位置定义静态的  session工厂与ThreadLocal
    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();

    static {
        //资源
        //获取配置文件中的内容，转为io流对象
        try {
            InputStream is = null;
            is = Resources.getResourceAsStream("SqlMapConfig.xml");
            //工厂
            //创建工厂构造者的对象
            SqlSessionFactoryBuilder builer = new SqlSessionFactoryBuilder();
            //使用工厂构造这创建工程，传入io形式的配置信息
            factory = builer.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启线程
     *
     * @return 当前线程的sqlsession
     */
    private static SqlSession openSession() {

        //从ThreadLocal中获取session
        SqlSession session = threadLocal.get();

        //判断如果没有session，获取session
        if (session == null) {
            //使用工厂创建session对象
            session = factory.openSession();
            //创建一个MapperHelper
            MapperHelper mapperHelper = new MapperHelper();
            //特殊配置
            Config config = new Config();
            // 设置UUID生成策略
            // 配置UUID生成策略需要使用OGNL表达式
            // 默认值32位长度:@java.util.UUID@randomUUID().toString().replace("-", "")
            //config.setUUID("");
            // 主键自增回写方法,默认值MYSQL,详细说明请看文档
//	        config.setIDENTITY("HSQLDB");
            // 支持方法上的注解
            // 3.3.1版本增加
            config.setEnableMethodAnnotation(true);
            config.setNotEmpty(true);
            // 序列的获取规则,使用{num}格式化参数，默认值为{0}.nextval，针对Oracle
            // 可选参数一共3个，对应0,1,2,分别为SequenceName，ColumnName, PropertyName
            //config.setSeqFormat("NEXT VALUE FOR {0}");
            // 设置全局的catalog,默认为空，如果设置了值，操作表时的sql会是catalog.tablename
            //config.setCatalog("");
            // 设置全局的schema,默认为空，如果设置了值，操作表时的sql会是schema.tablename
            // 如果同时设置了catalog,优先使用catalog.tablename
            //config.setSchema("");
            // 主键自增回写方法执行顺序,默认AFTER,可选值为(BEFORE|AFTER)
            //config.setOrder("AFTER");
            //设置配置
            mapperHelper.setConfig(config);
            // 注册通用Mapper接口 - 可以自动注册继承的接口
            mapperHelper.registerMapper(Mapper.class);
            mapperHelper.registerMapper(MySqlMapper.class);
//	        mapperHelper.registerMapper(SqlServerMapper.class);
//	        mapperHelper.registerMapper(IdsMapper.class);
            //配置完成后，执行下面的操作
            mapperHelper.processConfiguration(session.getConfiguration());
            //将创建号的session对象放入threadlocal中，供下次该线程使用
            threadLocal.set(session);
        }
        //如果有session，返回session
        return session;
    }


    public static <T> T getMapper(Class<T> thisMapper) throws IOException {

        //调用openSession方法，获得sqlsession
        SqlSession session = openSession();

        //通过SqlSession创建对应的Mapper实现类对象。传入要获得实现类的接口.class
        T mapper = session.getMapper(thisMapper);

        return mapper;
    }

    /**
     * 提交事务
     */
    public static void commit() {

        SqlSession session = openSession();

        //非空判断
        if (session != null) {
            //提交
            session.commit();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollBack() {

        SqlSession session = openSession();

        //非空判断
        if (session != null) {
            //提交
            session.rollback();
        }
    }

    /**
     * 提交并关闭资源
     */
    public static void commitAndclose() {

        SqlSession session = openSession();

        //非空判断
        if (session != null) {
            //提交
            session.commit();
            //关闭资源
            session.close();
            //从threadlocal中移除session
            threadLocal.remove();
        }
    }

    /**
     * 回滚并关闭资源
     */
    public static void rollBackAndclose() {

        SqlSession session = openSession();

        //非空判断
        if (session != null) {
            //提交
            session.rollback();
            //关闭资源
            session.close();
            //从threadlocal中移除session
            threadLocal.remove();
        }
    }

    /**
     * 提交并关闭资源
     */
    public static void close() {

        SqlSession session = openSession();

        //非空判断
        if (session != null) {
            //关闭资源
            session.close();
            //从threadlocal中移除session
            threadLocal.remove();
        }
    }
}
