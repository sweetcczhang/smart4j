package zcc.smart4j.framework.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcc.smart4j.framework.InstanceFactory;
import zcc.smart4j.framework.ds.DataSourceFactory;

import java.sql.Connection;

/**
 * 数据库操作助手
 * Created by 张城城 on 2018/3/30.
 */
public final class  DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 定义一个局部变量（使每个线程都拥有自己的连接）
     */
    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    /**
     * 获取数据源工厂
     */
    private static final DataSourceFactory dataSourceFactory = InstanceFactory.getDataSourceFactory();


    public static void beginTransaction(){
        //Connection conn =
    }

    public static void commitTransaction(){

    }



}
