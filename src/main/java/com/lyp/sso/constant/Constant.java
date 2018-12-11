package com.lyp.sso.constant;

/**
 * <p>@filename Constant</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2018/12/11 12:06
 **/
public class Constant {

    public static final String JWT_SECERT = "jwt_sso_demo";
    public static final String  JWT_ISSUER = "jwt_sso";

    //Token过期
    public static final int JWT_ERRCODE_EXPIRE = 1005;
    //验证不通过
    public static final int JWT_ERRCODE_FAIL = 1006;

    public static final long JWT_EXPIRED = 1*60*1000;

    public static final int HTTP_OK = 200;

    public static final int HTTP_ERROR = 500;
}
