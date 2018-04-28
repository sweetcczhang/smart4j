package zcc.smart4j.framework.ds.impl;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Created by 张城城 on 2018/4/27.
 */
public class DefaultDataSourceFactory extends AbstractDataSourceFactory<BasicDataSource> {



    public BasicDataSource createDataSource() {
        return new BasicDataSource();
    }

    public void setDriver(BasicDataSource ds, String driver) {
        ds.setDriverClassName(driver);
    }

    public void setUrl(BasicDataSource ds, String url) {
        ds.setUrl(url);
    }

    public void setUsername(BasicDataSource ds, String username) {
        ds.setUsername(username);
    }

    public void setPassword(BasicDataSource ds, String password) {
        ds.setPassword(password);
    }

    public void setAdvancedConfig(BasicDataSource ds) {
        ds.setValidationQuery("select 1 form dual");
    }
}
