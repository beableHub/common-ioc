package org.beable.common.util;

/**
 * @author qing.wu
 */
public class StringUtils {

    public static final String EMPTY = "";

    private StringUtils(){}

    public static boolean isEmpty(String str){
        return str == null ? true : str.isEmpty();
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static String lowerFirstChar(String str) {
        if (str != null && str.length() > 0){
            return str.substring(0,1).toLowerCase() + str.substring(1);
        }
        return str;
    }
}
