package zcc.smart4j.framework;

import zcc.smart4j.framework.ds.DataSourceFactory;
import zcc.smart4j.framework.ds.impl.DefaultDataSourceFactory;
import zcc.smart4j.framework.core.ConfigHelper;
import zcc.smart4j.framework.util.ObjectUtil;
import zcc.smart4j.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例工厂
 * Created by 张城城 on 2018/4/27.
 */
public class InstanceFactory {

    /**
     * 缓存对应的实例
     */
    private static final Map<String,Object> cache = new ConcurrentHashMap<String, Object>();

    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";


    public static DataSourceFactory getDataSourceFactory(){
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
    }

    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass){
        //若缓存中存在对应的实例，则返回该实例
        if(cache.containsKey(cacheKey)){
            return (T) cache.get(cacheKey);
        }
        //从配置文件中获取相应的接口实现类配置
        String implClassName = ConfigHelper.getString(cacheKey);

        //若实现类配置不存在，则使用默认实现类
        if (StringUtil.isNotEmpty(implClassName)){
            implClassName = defaultImplClass.getName();
        }
        T instance = ObjectUtil.newInstance(implClassName);
        //若该实例不为空则放入缓存
        if (instance !=null){
            cache.put(cacheKey,instance);
        }
        //返回该实例

        return instance;
    }

}
