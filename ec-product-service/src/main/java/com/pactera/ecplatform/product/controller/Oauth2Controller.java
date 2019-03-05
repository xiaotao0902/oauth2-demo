package com.pactera.ecplatform.product.controller;


import java.util.Arrays;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.ecplatform.product.utils.ApplicationSupport;



@RestController
@RequestMapping("/api/oauth2")
public class Oauth2Controller {

	@RequestMapping(value = "/clientMode",method = RequestMethod.POST)
	public Object getToken(@RequestParam(value = "client_id") String client_id,
								   @RequestParam(value = "client_secret") String client_secret,
								   @RequestParam(value = "grant_type") String grant_type
								   ){
		ClientCredentialsResourceDetails clientCredentials = new ClientCredentialsResourceDetails();
		clientCredentials.setAccessTokenUri(ApplicationSupport.getParamVal("oauth.token"));
		clientCredentials.setScope(Arrays.asList("read", "write"));
		clientCredentials.setClientId(client_id);
		clientCredentials.setClientSecret(client_secret);
		clientCredentials.setGrantType(grant_type);
		ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = provider.obtainAccessToken(clientCredentials, new DefaultAccessTokenRequest());
		} catch (Exception e) {
			e.printStackTrace();
			return "not found token";
		}
		return accessToken;
	}

}
