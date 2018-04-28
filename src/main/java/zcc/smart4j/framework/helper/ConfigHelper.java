package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.ConfigConstant;
import zcc.smart4j.framework.util.PropsUtil;

import java.util.Map;
import java.util.Properties;

/**
 * 获取项目的配置信息
 * Created by 张城城 on 2018/3/29.
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH);
    }
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH);
    }


    public static String getString(String key){
        return PropsUtil.getString(CONFIG_PROPS,key);
    }

    public static String getString(String key, String defaultValue){
        return PropsUtil.getString(CONFIG_PROPS,key,defaultValue);
    }

    public static int getInt(String key){
        return PropsUtil.getInt(CONFIG_PROPS,key);
    }

    public static int getInt(String key, int defaultValue){
        return PropsUtil.getInt(CONFIG_PROPS,key, defaultValue);
    }

    public static boolean getBoolean(String key){
        return PropsUtil.getBoolean(CONFIG_PROPS,key);
    }

    public static Map<String,Object> getMap(String prefix){
        return PropsUtil.getMap(CONFIG_PROPS,prefix);
    }

}
