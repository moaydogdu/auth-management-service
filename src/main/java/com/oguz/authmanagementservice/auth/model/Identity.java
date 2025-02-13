package com.oguz.authmanagementservice.auth.model;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class Identity {

    // TODO : Burada bir instance variable olarak user tutabilirim, bu sayede construct edilirken atarım.
    //  Böylelikle birden fazla kez erişildiğinde oradan dönerim.

    /*
    private final UserService userService;

    public User getUser() {
        return userService.getUserByIdAndEmail(this.getUserId(), this.getEmail());
    }

    public String getEmail() {
        return this.getJwt().getClaim(EnbTokenClaims.USER_EMAIL.getValue());
    }

    public String getAccessToken() {
        return this.getJwt().getTokenValue();
    }

    public String getUserId() {
        return this.getJwt().getClaim(EnbTokenClaims.USER_ID.getValue());
    }

    private Jwt getJwt() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

     */

}
