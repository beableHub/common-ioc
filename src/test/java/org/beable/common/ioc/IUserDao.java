package org.beable.common.ioc;

public interface IUserDao {

    String queryUserName(String uId);

    String queryLocation();

    String queryCompany();
}
