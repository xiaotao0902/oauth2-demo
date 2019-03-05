package com.pactera.ecplatform.product.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.pactera.ecplatform.product.domain.OauthConfig;
import com.pactera.ecplatform.product.repository.OauthConfigRepository;

@Component
public class Oauth2Utils {
	
	public static Oauth2Utils oauth2Utils;
	
	@Autowired
	private OauthConfigRepository repository;

    private final Logger LOGGER = LoggerFactory.getLogger(Oauth2Utils.class);
    
    private String checkTokenUrl = "";
 
    @PostConstruct
    public void init() {
    	oauth2Utils = this;
    }
    
    public void getOauthConfig() {
    	List<OauthConfig> oauthConfigs = (List<OauthConfig>) oauth2Utils.repository.findAll();
    	if(oauthConfigs!=null && oauthConfigs.size()!=0) {
    		checkTokenUrl = oauthConfigs.get(0).getCheck_token();
    	}
    }

    /**
     * oauth2 
     * @param accessToken
     * @return
     */
    public OAuth2AccessToken checkTokenInOauth2Server(String accessToken){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        return oAuth2AccessToken;
    }

    /**
     * oauth2 
     * @param accessToken
     * @return
     */
    public OAuth2Authentication  getAuthenticationInOauth2Server(String accessToken){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        return oAuth2Authentication;
    }

    /**
     * 
     * @param tokenValue
     * @return
     */
    public OAuth2Authentication getAuthenticationInOauth2Client(String tokenValue){
        if (StringUtils.isEmpty(tokenValue)) {
            return null;
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            OAuth2Authentication oAuth2Authentication = restTemplate.getForObject(checkTokenUrl+"?token="+tokenValue, OAuth2Authentication.class);
            return oAuth2Authentication;
        }catch (Exception e){
            LOGGER.error("getAuthenticationInOauth2Client failure:",e);
            return null;
        }
    }

    /**
     * 
     * @param tokenValue
     * @return
     */
    public OAuth2AccessToken checkTokenInOauth2Client(String tokenValue){
        if (StringUtils.isEmpty(tokenValue)) {
            return null;
        }
        try {
        	if("".equals(checkTokenUrl)) {
        		getOauthConfig();
        	}
            RestTemplate restTemplate = new RestTemplate();
            OAuth2AccessToken oAuth2AccessToken = restTemplate.getForObject(checkTokenUrl+"?token="+tokenValue, OAuth2AccessToken.class);
            return oAuth2AccessToken;
        }catch (Exception e){
            LOGGER.error("checkTokenInOauth2Client failure:",e);
            return null;
        }
    }

    /**
     *  OAuth2AccessToken
     * @param tokenValue
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken readAccessToken(String tokenValue){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
        return oAuth2AccessToken;
    }

}
