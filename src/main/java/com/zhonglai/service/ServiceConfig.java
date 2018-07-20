package com.zhonglai.service;

import org.springframework.core.env.Environment;

/**
 * 系统配置
 * @author zhonglai
 *
 */
public class ServiceConfig {

	/**
	 * 服务器session超时时间
	 */
	private static Integer serverSessionTimeout = 2*60*60;

	/**
	 * 登陆token超时时间
	 */
	private static Integer loginTokenTimeout = 1*24*60*60;

	/**
	 * 服务器session超时时间
	 */
	public static Integer getServerSessionTimeout() {
		return serverSessionTimeout;
	}

	/**
	 * 登陆token超时时间
	 */
	public static Integer getLoginTokenTimeout() {
		return loginTokenTimeout;
	}

	/**
	 * 初始化配置
	 * @param environment
	 */
	public static void initServiceConfig(Environment environment)
	{
		//服务器session超时时间
		if(environment.containsProperty("SERVER_SESSION_TIMEOUT"))
		{
			ServiceConfig.serverSessionTimeout =  Integer.parseInt(environment.getRequiredProperty("SERVER_SESSION_TIMEOUT"));
		}

		//登陆token超时时间
		if(environment.containsProperty("LOGIN_TOKEN_TIMEOUT"))
		{
			ServiceConfig.loginTokenTimeout =  Integer.parseInt(environment.getRequiredProperty("LOGIN_TOKEN_TIMEOUT"));
		}
	}
}
