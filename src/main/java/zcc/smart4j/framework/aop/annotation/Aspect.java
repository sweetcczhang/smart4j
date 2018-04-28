package zcc.smart4j.framework.aop.annotation;

import java.lang.annotation.*;

/**
 * Created by 张城城 on 2018/3/30.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
