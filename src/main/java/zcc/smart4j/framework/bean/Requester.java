package zcc.smart4j.framework.bean;

/**
 * @author: Zhang Chengcheng
 * @create: 2018-06-20 12:24
 **/
public class Requester {

    private String requestMethod;
    private String requestPath;

    public Requester(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }
}
