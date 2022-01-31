package org.beable.springframework;

/**
 * @author wuqing
 * @version 1.0
 * @date 2021/12/22
 */

public interface UserService {


    void queryUserInfo(String userId);

    void queryCompany(String userId);

    void queryLocation(String userId);

    String queryHost();

    String getHost();
}
