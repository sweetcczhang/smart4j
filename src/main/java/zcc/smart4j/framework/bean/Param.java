package zcc.smart4j.framework.bean;

import zcc.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * Created by 张城城 on 2018/3/30.
 */
public class Param {
    private Map<String,Object> paramMap;
    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }
}
