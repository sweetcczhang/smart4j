package zcc.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcc.smart4j.framework.annotation.Aspect;
import zcc.smart4j.framework.annotation.Service;
import zcc.smart4j.framework.proxy.AspectProxy;
import zcc.smart4j.framework.proxy.Proxy;
import zcc.smart4j.framework.proxy.ProxyManager;
import zcc.smart4j.framework.proxy.TransactionProxy;
import zcc.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 方法拦截助手类
 * Created by 张城城 on 2018/3/30.
 */
public final class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    /**
     * 初始化Aop助手类
     */
    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>,List<Proxy>> targetMap = createTargetMap(proxyMap);
            for(Map.Entry<Class<?>,List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure",e);
        }
    }
    /**
     * 获取指定目标的代理类
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect)throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation !=null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    /**
     * 获取代理类与目标类的映射关系
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>,Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(proxyMap);
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 获取目标类与代理对象之间对的映射关系
     * @param proxyMap
     * @return
     */
    private static Map<Class<?>,List<Proxy>>createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>,List<Proxy>>  targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>,Set<Class<?>>> proxyEnty: proxyMap.entrySet()){
            Class<?> proxyClass = proxyEnty.getKey();
            Set<Class<?>> targetClassSet = proxyEnty.getValue();
            for (Class<?> targetClass : targetClassSet){
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if(targetMap.containsKey(targetClass)){
                    targetMap.get(targetClass).add(proxy);
                }else {
                    List<Proxy> list = new ArrayList<Proxy>();
                    list.add(proxy);
                    targetMap.put(targetClass,list);
                }
            }
        }
        return targetMap;
    }
    private static void addAspectProxy(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception{
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySupper(AspectProxy.class);
        for (Class<?> cls : proxyClassSet){
            if (cls.isAnnotationPresent(Aspect.class)){
                Aspect aspect = cls.getAnnotation(Aspect.class);
                Set<Class<?>> targetClass = createTargetClassSet(aspect);
                proxyMap.put(cls,targetClass);
            }
        }
    }
    private static void addTransactionProxy(Map<Class<?>,Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class,serviceClassSet);
    }
}
