package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.bean.Handler;
import zcc.smart4j.framework.bean.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张城城 on 2018/3/30.
 */
public final class ControllerHelper {
    /**
     * 用来存储请求与处理器之间的映射关系
     */
    private  static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
    static {
        //TODO
    }
}
