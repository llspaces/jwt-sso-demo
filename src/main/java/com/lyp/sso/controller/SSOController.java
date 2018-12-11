package com.lyp.sso.controller;

import com.lyp.sso.constant.Constant;
import com.lyp.sso.service.UserService;
import com.lyp.sso.utils.JWTResponseData;
import com.lyp.sso.utils.JWTResult;
import com.lyp.sso.utils.JWTSubject;
import com.lyp.sso.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * <p>@filename SSOController</p>
 * <p>
 * <p>@description TODO</p>
 *
 * @author liyupeng
 * @version 1.0
 * @since 2018/12/11 11:36
 **/
@RestController
public class SSOController {

    @Autowired
    private UserService service;

    /**
     * 登录验证，返回JWT token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Object login(String username, String password){
        JWTResponseData responseData = null;
        // 认证用户信息
        if(service.isLogin(username, password)){
            JWTSubject subject = new JWTSubject(username);
            String jwtToken = JWTUtils.createJWT(UUID.randomUUID().toString(), Constant.JWT_ISSUER,
                JWTUtils.generalSubject(subject), Constant.JWT_EXPIRED);
            responseData = new JWTResponseData();
            responseData.setCode(Constant.HTTP_OK);
            responseData.setData(null);
            responseData.setMessage("登录成功");
            responseData.setToken(jwtToken);
        }else{
            responseData = new JWTResponseData();
            responseData.setCode(Constant.HTTP_ERROR);
            responseData.setData(null);
            responseData.setMessage("登录失败");
            responseData.setToken(null);
        }
        return responseData;
    }

    @GetMapping("/test")
    public Object testAll(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JWTResult result = JWTUtils.validateJWT(token);
        JWTResponseData responseData = new JWTResponseData();
        if(result.isSuccess()){
            responseData.setCode(Constant.HTTP_OK);
            responseData.setData(result.getClaims().getSubject());
            String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                result.getClaims().getIssuer(), result.getClaims().getSubject(),
                Constant.JWT_EXPIRED);
            responseData.setToken(newToken);
            return responseData;
        }else{
            responseData.setCode(Constant.HTTP_ERROR);
            responseData.setMessage("用户未登录");
            return responseData;
        }

    }


}
