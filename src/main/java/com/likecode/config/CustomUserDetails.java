package com.likecode.config;

import com.likecode.bean.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private User user;

    public CustomUserDetails(String username, User user, Collection<? extends GrantedAuthority> authorities) {
        super(username, user.getPassword(), true, true,
                true, user.getDel()== 0, authorities);
        this.user = user;
    }
}
