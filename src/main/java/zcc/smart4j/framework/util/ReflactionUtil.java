package zcc.smart4j.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * Created by 张城城 on 2018/3/30.
 */
public final class ReflactionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflactionUtil.class);

    /**
     * 创建实例
     * @param cls
     * @return
     */
    public static Object newInstance(Class<?> cls){
        Object instace;
        try {
            instace = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instace error", e);
            throw new RuntimeException(e);
        }
        return instace;
    }

    /**
     * 调用实例的方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args){
        Object result;
        try {
            result = method.invoke(obj,args);
        }catch (Exception e){
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置属性的值
     * @param obj
     * @param field
     * @param value
     */
    public static void setFiled(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(obj, value);
        }catch (Exception e){
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
