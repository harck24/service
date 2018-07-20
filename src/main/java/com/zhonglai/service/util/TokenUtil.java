package com.zhonglai.service.util;

import com.google.gson.JsonObject;
import com.zhonglai.serialization.GsonConstructor;
import com.zhonglai.service.dto.LoginToken;
import com.zhonglai.util.CommonConstants;
import com.zhonglai.util.CommonUtil;
import com.zhonglai.util.DESUtil;
import com.zhonglai.util.GsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Token工具
 * @author Administrator
 *
 */
public class TokenUtil {
	

	public static String getToken(LoginToken loginToken)
	{
		String token = DESUtil.encode(GsonConstructor.get().toJson(loginToken), CommonConstants.ENCODE_KEY);
		return token;
	}
	
	public static LoginToken decodeToken(String login_token)
	{
		login_token = DESUtil.decode(login_token, CommonConstants.ENCODE_KEY);
		LoginToken loginToken = GsonConstructor.get().fromJson(login_token,LoginToken.class);
		return loginToken;
	}

	public static void main(String[] args) {
		System.out.println(DESUtil.decode("6F3CF978B9F30AEE378E57881314613B","eJPtginO1"));
	}

}
