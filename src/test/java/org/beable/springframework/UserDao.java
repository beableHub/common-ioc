package org.beable.springframework;

import org.beable.springframework.beans.factory.annotation.Value;
import org.beable.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao implements IUserDao{
    private static Map<String, String> hashMap = new HashMap<>();

    @Value("${company}")
    private String company;

    @Value("${location}")
    private String location;

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

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "A");
        hashMap.put("10002", "B");
        hashMap.put("10003", "C");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }
}
