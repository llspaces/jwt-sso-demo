package com.lyp.sso.utils;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * 结果对象。
 */
@Data
public class JWTResult {
	/**
	 * 错误编码。在JWTUtils中定义的常量。
	 * 200为正确
	 */
	private int errCode;

	/**
	 * 是否成功，代表结果的状态。
	 */
	private boolean success;

	/**
	 * 验证过程中payload中的数据。
	 */
	private Claims claims;

}
