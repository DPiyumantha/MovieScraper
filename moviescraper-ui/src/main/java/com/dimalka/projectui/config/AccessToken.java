package com.dimalka.projectui.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AccessToken {

    public static String getAccessToken(){
        OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return auth2AuthenticationDetails.getTokenType().concat(" ").concat(auth2AuthenticationDetails.getTokenValue());
    }
}
