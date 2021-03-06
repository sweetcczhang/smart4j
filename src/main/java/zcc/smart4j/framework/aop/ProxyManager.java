package zcc.smart4j.framework.aop;

import java.lang.reflect.Method;
import java.util.List;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import zcc.smart4j.framework.aop.Proxy;
import zcc.smart4j.framework.aop.ProxyChain;

/**
 * Created by 张城城 on 2018/3/30.
 */
public class ProxyManager {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass, o,method,methodProxy,objects,proxyList).doProxyChain();
            }
        });
    }
}
