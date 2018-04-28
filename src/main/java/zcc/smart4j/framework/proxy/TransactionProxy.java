package zcc.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcc.smart4j.framework.aop.annotation.Transaction;
import zcc.smart4j.framework.aop.Proxy;
import zcc.smart4j.framework.aop.ProxyChain;
import zcc.smart4j.framework.dao.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * Created by 张城城 on 2018/3/30.
 */
public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue(){
            return false;
        }
    };

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        //TODO
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if(!flag && method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
            }catch (Exception e){

            }
        }
        return null;
    }
}
