package zcc.smart4j.framework;

import zcc.smart4j.framework.helper.ConfigHelper;
import zcc.smart4j.framework.helper.HelperLoader;

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
    }
}
