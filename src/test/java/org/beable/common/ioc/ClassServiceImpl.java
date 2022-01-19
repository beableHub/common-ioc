package org.beable.common.ioc;

import org.beable.common.ioc.beans.factory.annotation.Autowired;
import org.beable.common.ioc.beans.factory.annotation.Value;
import org.beable.common.ioc.stereotype.Component;

@Component
public class ClassServiceImpl implements ClassService{

    @Autowired
    UserService userService;

    @Value("${company}")
    private String company;

    @Value("${location}")
    private String location;

    public String queryHost(){
        System.out.println("classService: host->"+userService.getHost());
        return userService.getHost();
    }
}
