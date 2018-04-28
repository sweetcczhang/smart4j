package zcc.smart4j.framework.annotation;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;

/**
 * Created by 张城城 on 2018/3/30.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
