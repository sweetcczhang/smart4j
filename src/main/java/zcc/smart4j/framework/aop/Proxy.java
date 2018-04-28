package zcc.smart4j.framework.aop;

/**
 * Created by 张城城 on 2018/3/30.
 */
public interface Proxy {
    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
