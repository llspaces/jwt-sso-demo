package com.lyp.sso.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>@filename User</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2018/12/11 11:48
 **/
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String realname;

    @Column
    private Short age;

    @Column
    private BigDecimal balance;

}
