package com.lyp.sso.utils;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lyp.sso.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * JWT工具
 *
 * @author liyupeng
 * @since 2018-12-11 12:04
 */
public class JWTUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static SecretKey generalKey() {
        try {
            // 不管哪种方式最终得到一个byte[]类型的key就行
            byte[] encodedKey = Constant.JWT_SECERT.getBytes("UTF-8");
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 签发JWT
     *
     * @param id        jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
     * @param iss       jwt签发者
     * @param subject   jwt所面向的用户
     * @param ttlMillis 有效期,单位毫秒
     * @return token
     * @throws Exception
     */
    public static String createJWT(String id, String iss, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder().setId(id).setIssuer(iss).setSubject(subject).setIssuedAt(now)
            .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public static JWTResult validateJWT(String jwtStr) {
        JWTResult checkResult = new JWTResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(Constant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(Constant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(Constant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    /**
     * 生成subject信息
     *
     * @param subObj - 要转换的对象。
     * @return java对象->JSON字符串出错时返回null
     */
    public static String generalSubject(Object subObj) {
        try {
            return MAPPER.writeValueAsString(subObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
