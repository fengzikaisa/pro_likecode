package com.likecode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by wangkai on 2017/11/14.
 */

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("wangkai").password("qwe123").roles("USER");
        auth.userDetailsService(customUserService()).passwordEncoder(md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/blog/addPage","/blog/add", "/friendshipLink").authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess").failureUrl("/loginFail").permitAll()
                .and().logout().logoutSuccessUrl("/blog").permitAll();
    }

    @Bean
    public static Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }
}
