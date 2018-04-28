package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.aop.AopHelper;
import zcc.smart4j.framework.util.ClassUtil;

/**
 * Created by 张城城 on 2018/3/30.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,

        };
        for (Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
