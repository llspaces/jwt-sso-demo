package com.lyp.sso.service;

import com.lyp.sso.entity.User;
import com.lyp.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>@filename UserService</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2018/12/11 12:01
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean isLogin(String username, String password) {
        boolean flag = false;
        User user = repository.findUserByUsernameAndPassword(username, password);
        if(null != user) {
            flag = true;
        }
        return flag;
    }
}
