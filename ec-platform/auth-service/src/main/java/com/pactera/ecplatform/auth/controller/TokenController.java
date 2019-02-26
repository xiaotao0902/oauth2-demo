package com.pactera.ecplatform.auth.controller;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.ecplatform.auth.utils.Oauth2Utils;

@RestController
@RequestMapping("/oauth")
public class TokenController {

    @GetMapping("/check_token")
    public OAuth2AccessToken getToken(@RequestParam(value = "token") String token){
        OAuth2AccessToken oAuth2AccessToken = Oauth2Utils.checkTokenInOauth2Server(token);
        return oAuth2AccessToken;
    }
 
    @GetMapping("/getAuth")
    public OAuth2Authentication getAuth(@RequestParam(value = "token") String token){
        OAuth2Authentication oAuth2Authentication = Oauth2Utils.getAuthenticationInOauth2Server(token);
        return oAuth2Authentication;
    }

}
