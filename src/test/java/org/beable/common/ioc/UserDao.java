package org.beable.common.ioc;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "A");
        hashMap.put("10002", "B");
        hashMap.put("10003", "C");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
