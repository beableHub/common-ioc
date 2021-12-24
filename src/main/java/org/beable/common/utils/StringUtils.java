package org.beable.common.utils;

/**
 * @author qing.wu
 */
public class StringUtils {

    private StringUtils(){}

    public static boolean isEmpty(String str){
        return str == null ? true : str.isEmpty();
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
