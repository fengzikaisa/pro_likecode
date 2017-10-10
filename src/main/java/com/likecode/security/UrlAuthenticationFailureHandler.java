//package com.likecode.security;
//
//import com.alibaba.fastjson.JSON;
//import com.likecode.common.bean.ResultBean;
//import com.likecode.common.utils.ConstantDefinition;
//import com.likecode.common.utils.RequestUtil;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 自定义登录失败处理Handler
// * <p>
// * 对ajax请求和普通请求进行分别的处理
// * </p>
// */
//public class UrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException {
//        String loginErrorMsg = "登录异常";
//
//        if (exception instanceof BadCredentialsException) {
//            loginErrorMsg = "用户名密码不匹配";
//        }  else if (exception instanceof UsernameNotFoundException) {
//            loginErrorMsg = "该账号未注册";
//        }  else if (exception instanceof LockedException) {
//            loginErrorMsg = "帐户被锁";
//        } else if (exception instanceof DisabledException) {
//            loginErrorMsg = "该帐户已禁用";
//        }
//        if (RequestUtil.isAjax()) {
//            ResultBean result = new ResultBean();
//            result.setStatus(ConstantDefinition.SYSTEM_ERROR);
//            result.setMsg(loginErrorMsg);
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write(JSON.toJSONString(result));
//        } else {
//            request.getSession().setAttribute("loginErrorMsg", loginErrorMsg);
//            super.onAuthenticationFailure(request, response, exception);
//        }
//    }
//}
