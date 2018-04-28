package zcc.smart4j.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by 张城城 on 2018/3/30.
 */
public abstract class AspectProxy implements Proxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);


    public final Object doProxy(ProxyChain proxyChain)throws Throwable{
        Object result = null;
        Class<?> clas = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();
        try {
            if(intercept(clas,method,params)){
                before(clas, method,params);
                result = proxyChain.doProxyChain();
                after(clas,method,params,result);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure", e);
            error(clas,method,params,e);
            throw e;
        }finally {
            end();
        }

        return result;
    }
    public void begin(){

    }
    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{

        return true;
    }
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable{

    }
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{

    }
    public void error(Class<?> cls, Method method,Object[] params, Throwable e){

    }
    public void end(){

    }
}
