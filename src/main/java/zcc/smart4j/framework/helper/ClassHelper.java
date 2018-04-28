package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.aop.annotation.Controller;
import zcc.smart4j.framework.aop.annotation.Service;
import zcc.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张城城 on 2018/3/30.
 */
public final class ClassHelper {
    private static final List<Class<?>> CLASS_SET;
    static {
        String basePacke = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePacke);
    }
    public static List<Class<?>> getClassList(){
        return CLASS_SET;
    }
    public static List<Class<?>> getServiceClassaList(){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        for (Class<?> cls :CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    public static List<Class<?>> getControllerClassSet(){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        for (Class<?> cls :CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    public static List<Class<?>> getBeanClassList(){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassaList());
        return classSet;
    }

    /**
     * 获取某应用包下某父类(或接口)的所有子类（或实现类）
     * @param superClass
     * @return
     */
    public static List<Class<?>> getClassSetBySupper(Class<?> superClass){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包下带有某类的注解的所有类
     * @param annotationClass
     * @return
     */
    public static List<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
