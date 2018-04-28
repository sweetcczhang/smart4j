package zcc.smart4j.framework.util;

/**
 * Created by 张城城 on 2018/3/29.
 */
public final class CastUtil {

    public static String castString(Object obj){
        return castString(obj,"");
    }
    public static String castString(Object obj,String defalutValue){
        return obj !=null ? String.valueOf(obj):defalutValue;
    }

    public  static int castInt(Object obj){
        return castInt(obj,0);
    }
    public static int castInt(Object obj, int defaultValue){
        int value = defaultValue;
        if(obj!=null){
            String strValue = String.valueOf(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Integer.valueOf(strValue);
                }catch (NumberFormatException e){
                }
            }
        }
        return value;
    }
    public  static long castLong(Object obj){
        return castLong(obj,0);
    }
    public static long castLong(Object obj, long defaultValue){
        long value = defaultValue;
        if(obj!=null){
            String strValue = String.valueOf(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public  static Double castDouble(Object obj){
        return castDouble(obj,0);
    }

    public static Double castDouble(Object obj, double defaultValue){
        double value = defaultValue;
        if(obj!=null){
            String strValue = String.valueOf(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }
        return value;
    }
    public  static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue){
        boolean value = defaultValue;
        if(obj!=null){
           value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }

}
