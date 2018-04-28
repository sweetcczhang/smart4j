package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.util.ReflactionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Bean助手类
 * Created by 张城城 on 2018/3/30.
 */
public final class BeanHelper {

    /**
     * 用于存放bean类与bean实例的映射关系
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> cls : beanClassSet){
            Object object =ReflactionUtil.newInstance(cls);
            BEAN_MAP.put(cls,object);
        }
    }

    /**
     * 获取Bean映射
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class:"+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }

}
