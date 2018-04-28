package zcc.smart4j.framework.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * Created by 张城城 on 2018/3/29.
 * 属性文件加载工具类
 */
@SuppressWarnings("Since15")
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * @param filename
     * @return
     */
    public static Properties loadProps(String filename){
        Properties prop = null;
        InputStream is =null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if(is==null){
                throw new FileNotFoundException(filename + "file is not found");
            }
            prop = new Properties();
            prop.load(is);
        }catch (IOException e){
            LOGGER.error("load properties file failure",e);
        }finally {
            if (is!=null){
                try {
                    is.close();
                }catch (IOException e){
                    LOGGER.error("close input stream failure",e);
                }
            }
        }
        return prop;
    }

    /**
     *
     * @param props
     * @param key
     * @return
     */
    public static String getString(Properties props,String key){
        return  getString(props,key,"");
    }

    /**
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue){
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value =properties.getProperty(key);
        }
        return value;
    }

    public static int getInt(Properties properties, String key, int defaultVaue){
        int value = defaultVaue;
        if (properties.contains(key)) {
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }
    public static int getInt(Properties properties, String key){
        return getInt(properties,key,0);
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultVaue){
        boolean value = defaultVaue;
        if (properties.contains(key)) {
            value = CastUtil.castBoolean(properties.getProperty(key));
        }
        return value;
    }
    public static boolean getBoolean(Properties properties, String key){
        return getBoolean(properties,key,false);
    }

    /**
     * 获取指定前缀的相关属性
     */
    public static Map<String, Object> getMap(Properties props, String prefix) {
        Map<String, Object> kvMap = new LinkedHashMap<String, Object>();
        Set<String> keySet = props.stringPropertyNames();
        if (CollectionUtil.isNotEmpty(keySet)) {
            for (String key : keySet) {
                if (key.startsWith(prefix)) {
                    String value = props.getProperty(key);
                    kvMap.put(key, value);
                }
            }
        }
        return kvMap;
    }

}
