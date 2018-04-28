package zcc.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * 数据库操作助手
 * Created by 张城城 on 2018/3/30.
 */
public final class  DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    public static void beginTransaction(){
        //Connection conn =
    }

    public static void commitTransaction(){

    }



}
