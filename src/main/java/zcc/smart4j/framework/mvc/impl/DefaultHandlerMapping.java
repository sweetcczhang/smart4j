package zcc.smart4j.framework.mvc.impl;

import zcc.smart4j.framework.mvc.Handler;
import zcc.smart4j.framework.mvc.HandlerMapping;
import zcc.smart4j.framework.mvc.Requester;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张城城 on 2018/4/28.
 */
public class DefaultHandlerMapping implements HandlerMapping {
    public Handler getHandler(String currentRequestMethod, String currentRequestPath) {

        //定义一个Handler
        Handler handler = null;
        Map<Requester,Handler> actionMap = new HashMap<Requester, Handler>();

        for (Map.Entry<Requester,Handler> actionEntry : actionMap.entrySet()){
            // 从 Requester 中获取 Request 相关属性
            Requester requester = actionEntry.getKey();
            String requestMethod = requester.getRequestMethod();
            String requestPath = requester.getRequestPath();
            // 获取请求路径匹配器（使用正则表达式匹配请求路径并从中获取相应的请求参数）
            Matcher  requestPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);
            // 判断请求方法与请求路径是否同时匹配
            if (requestMethod.equalsIgnoreCase(currentRequestMethod) && requestPathMatcher.matches()){
                //获取Handler及其相关属性
                handler = actionEntry.getValue();
                //设置请求路径匹配器
                if (handler!= null){
                    handler.setRequestPathMatcher(requestPathMatcher);
                }
                //若匹配成功，则终止循环
                break;
            }
        }



        return handler;
    }
}
