package zcc.smart4j.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcc.smart4j.framework.core.ConfigHelper;
import zcc.smart4j.framework.helper.HelperLoader;
import zcc.smart4j.framework.mvc.Handler;
import zcc.smart4j.framework.mvc.HandlerExceptionResolver;
import zcc.smart4j.framework.mvc.HandlerInvoker;
import zcc.smart4j.framework.mvc.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 张城城 on 2018/3/30.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatchServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchServlet.class);

    private HandlerMapping handlerMapping;

    private HandlerInvoker handlerInvoker;

    private HandlerExceptionResolver handlerExceptionResolver;

    public void init(ServletConfig servletConfig) throws ServletException{
        //初始化相关helper类
        HelperLoader.init();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理jsp的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认路径
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");

    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //TODO
        request.setCharacterEncoding("UTF-8");
        String currentRequestMethod = request.getMethod();
        String currentRequestPath = request.getServletPath();
        LOGGER.debug("[Smart] {}:{}", currentRequestMethod, currentRequestPath);
        //将请求 “/”请求重定向到首页
        if (currentRequestPath.equals("/")){

            return;
        }
        if (currentRequestPath.endsWith("/")){
            currentRequestPath = currentRequestPath.substring(0, currentRequestPath.length()-1);
        }

        Handler handler = handlerMapping.getHandler(currentRequestMethod,currentRequestPath);
        if (handler == null){

        }
        try {
            handlerInvoker.invokerHandler(request,response,handler);
        } catch (Exception e) {
            handlerExceptionResolver.resolveHandlerException(request,response,e);
        }finally {

        }
    }
}
