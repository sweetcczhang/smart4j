package zcc.smart4j.framework.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by 张城城 on 2018/4/28.
 */
public interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     * @param packageName
     * @return
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名下指定注解的所有类
     * @param packageName
     * @param annotationClass
     * @return
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
}
