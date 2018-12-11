package com.lyp.sso.utils;

import lombok.Data;

/**
 *
 */
@Data
public class JWTResponseData {
	// 返回码
	private Integer code;
	// 业务数据
	private Object data;
	// 返回描述
	private String message;
	// 身份标识
	private String token;
	
}
