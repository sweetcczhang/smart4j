package zcc.smart4j.framework.helper;

import zcc.smart4j.framework.aop.annotation.Inject;
import zcc.smart4j.framework.util.CollectionUtil;
import zcc.smart4j.framework.util.ReflactionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * Created by 张城城 on 2018/3/30.
 */
public final class IocHelper {
    static {
        //获取所有bean类和bean实例的映射关系
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for (Map.Entry<Class<?>,Object> beanEntry : beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanObject = beanEntry.getValue();
                //获取bean类定义的所有成员变量
                Field[] fields =beanClass.getFields();
                for(Field field : fields){
                    //判断当前字段是否带有Inject注解
                    if(field.isAnnotationPresent(Inject.class)){
                        Class<?> beanFieldClass = field.getType();
                        Object value = beanMap.get(beanFieldClass);
                        ReflactionUtil.setFiled(beanObject, field,value);
                    }
                }
            }
        }
    }
}
