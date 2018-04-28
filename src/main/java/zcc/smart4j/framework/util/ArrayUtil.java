package zcc.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by 张城城 on 2018/3/30.
 */
public final class ArrayUtil {

    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
    public static boolean isNotEmpty(Object[] array){
        return !isEmpty(array);
    }
}
