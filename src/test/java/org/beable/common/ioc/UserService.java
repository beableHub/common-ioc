package org.beable.common.ioc;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class UserService {

    private UserDao userDao;

    public void queryUserInfo(String userId){
        System.out.println("查询用户信息:"+userDao.queryUserName(userId));
    }
}
