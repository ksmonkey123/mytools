package ch.awae.mytools.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthInfo {

    public static UserInfo getUserInfo() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
