package com.likecode.common;

import com.likecode.config.CustomUserDetails;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SystemHelper {

    private SystemHelper() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static CustomUserDetails userDetails() {
        if (isLogin()) {
            return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public static Integer currUserId() {
        if (isLogin()) {
            CustomUserDetails userDetails = userDetails();
            if (userDetails != null) {
                return userDetails.getUser().getId();
            }
        }
        return null;
    }

    /**
     * 判断是否已登录
     */
    public static boolean isLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        } else {
            return false;
        }
    }

}
