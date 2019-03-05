package com.pactera.ecplatform.auth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

public class Oauth2Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oauth2Utils.class);
    private static String checkTokenUrl ;
    static {
        checkTokenUrl = ApplicationSupport.getParamVal("oauth.check_token");
    }

    /**
     * oauth2 
     * @param accessToken
     * @return
     */
    public static OAuth2AccessToken checkTokenInOauth2Server(String accessToken){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        return oAuth2AccessToken;
    }

    /**
     * oauth2 
     * @param accessToken
     * @return
     */
    public static OAuth2Authentication  getAuthenticationInOauth2Server(String accessToken){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        return oAuth2Authentication;
    }

    /**
     * 
     * @param tokenValue
     * @return
     */
    public static OAuth2Authentication getAuthenticationInOauth2Client(String tokenValue){
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
    public static OAuth2AccessToken checkTokenInOauth2Client(String tokenValue){
        if (StringUtils.isEmpty(tokenValue)) {
            return null;
        }
        try {
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
    public static OAuth2AccessToken readAccessToken(String tokenValue){
        TokenStore tokenStore = (TokenStore) ApplicationSupport.getBean("tokenStore");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
        return oAuth2AccessToken;
    }

}
