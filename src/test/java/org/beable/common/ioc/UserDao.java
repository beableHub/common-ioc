package org.beable.common.ioc;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    private String company;

    private String location;

    static {
        hashMap.put("10001", "A");
        hashMap.put("10002", "B");
        hashMap.put("10003", "C");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

    public String queryLocation(){
        return location;
    }

    public String queryCompany(){
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
