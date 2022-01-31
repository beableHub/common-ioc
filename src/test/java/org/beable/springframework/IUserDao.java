package org.beable.springframework;

public interface IUserDao {

    String queryUserName(String uId);

    String queryLocation();

    String queryCompany();
}
