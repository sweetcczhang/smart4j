package zcc.smart4j.framework.ds.impl;

import zcc.smart4j.framework.ds.DataSourceFactory;

import javax.sql.DataSource;

/**
 * Created by 张城城 on 2018/4/27.
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {

    protected final String  driver="";
    protected final String  url = "";
    protected final String  username = "";
    protected final String  password = "";

    public final T getDataSource(){
        T ds = createDataSource();
        setDriver(ds,driver);
        setUrl(ds,url);
        setUsername(ds,username);
        setPassword(ds,password);
        setAdvancedConfig(ds);
        return ds;
    }


    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);

}
