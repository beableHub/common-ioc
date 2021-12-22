package org.beable.common.ioc;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class UserService {

    private String name;

    private Integer age;

    public UserService(String name){
        this.name = name;
    }


    public UserService(Integer age){
        this.age = age;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }
}
