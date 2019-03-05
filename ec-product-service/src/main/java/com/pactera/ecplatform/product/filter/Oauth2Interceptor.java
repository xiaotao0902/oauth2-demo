package com.pactera.ecplatform.product.filter;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pactera.ecplatform.product.utils.Oauth2Utils;
import com.pactera.ecplatform.product.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Oauth2Interceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "access_token, Authorization, content-type, X-Requested-With, XMLHttpRequest");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		Oauth2Utils config = new Oauth2Utils();
		String accessToken = request.getParameter("access_token");
		OAuth2AccessToken oauth2AccessToken = config.checkTokenInOauth2Client(accessToken);
		if (oauth2AccessToken == null) {// 非法的Token值
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			ResponseUtils.responseData(response, "unauthorized token");
			return false;
		} else if (oauth2AccessToken.isExpired()) {// token失效
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			ResponseUtils.responseData(response, "token expire");
			return false;
		}
		return true;
	}

}
