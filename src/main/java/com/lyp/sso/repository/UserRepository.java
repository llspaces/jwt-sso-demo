package com.lyp.sso.repository;

import com.lyp.sso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>@filename UserRepository</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2018/12/11 11:50
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsernameAndPassword(String username, String password);

}
