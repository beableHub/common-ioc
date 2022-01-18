package org.beable.common.ioc;

import org.beable.common.beans.factory.DisposableBean;
import org.beable.common.beans.factory.InitializingBean;
import org.beable.common.beans.factory.annotation.Autowired;
import org.beable.common.beans.factory.annotation.Value;
import org.beable.common.stereotype.Component;

/**
 *
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */
@Component
public class UserServiceImpl implements UserService, InitializingBean, DisposableBean {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ClassService classService;

    @Value("${host}")
    private String host;

    public UserServiceImpl() {
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

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

    @Override
    public String queryHost(){
        System.out.println("UserService: host->"+ classService.queryHost());
        return classService.queryHost();
    }
}
