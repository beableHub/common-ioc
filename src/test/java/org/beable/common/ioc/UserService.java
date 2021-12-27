package org.beable.common.ioc;

import org.beable.common.beans.factory.DisposableBean;
import org.beable.common.beans.factory.InitializingBean;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
public class UserService implements InitializingBean, DisposableBean {

    private IUserDao userDao;

    public void queryUserInfo(String userId){
        System.out.println("查询用户信息:"+userDao.queryUserName(userId));
    }

    public void queryCompany(String userId){
        System.out.println("查询用户公司信息:"+userDao.queryCompany());
    }

    public void queryLocation(String userId){
        System.out.println("查询用户所在城市信息:"+userDao.queryLocation());
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
