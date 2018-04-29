package zcc.smart4j.framework.mvc;

/**
 * 处理器映射器
 * Created by 张城城 on 2018/4/28.
 */
public interface HandlerMapping {

    /**
     *
     * @param currentRequestMethod
     * @param currentRequestPath
     * @return
     */
    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
