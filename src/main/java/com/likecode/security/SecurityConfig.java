package com.likecode.security;

import com.likecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
//@EnableWebSecurity: 禁用Boot的默认Security配置，配合@Configuration启用自定义配置
// （需要扩展WebSecurityConfigurerAdapter）
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true): 启用Security注解，
// 例如最常用的@PreAuthorize
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Configure spring security's authenticationManager with custom
        // user details service
        auth.userDetailsService(this.userService);
    }

    @Override
    //configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
    //定义URL路径应该受到保护，哪些不应该
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/index").authenticated()
                .anyRequest().permitAll()
                .and()
                //通过formLogin()定义当需要用户登录时候，转到的登录页面。
                .formLogin().loginProcessingUrl("/login").loginPage("/loginPage").permitAll()
                .and()
                //注销
                .logout().logoutSuccessUrl("/") //退出登录后的默认网址是”/”
                .permitAll();
        http.headers().frameOptions().disable();
        //关闭csrf 防止循环定向
        http.csrf().disable();
    }
}