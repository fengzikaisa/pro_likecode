package com.likecode.aop;

import com.likecode.bean.Blog;
import com.likecode.common.bean.ResultBean;
import com.likecode.service.BlogService;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by wangkai on 2017/10/13.
 */
@Aspect
@Component
@Log4j
public class BlogAspect {

    @Autowired
    BlogService blogService;

    @Pointcut("execution(public * com.likecode.controller..BlogController.detail*(..))")
    public void readCount() {

    }

    @Before("readCount()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info( "进入doBefore切面");

    }

    @AfterReturning(pointcut="readCount()",returning="returnValue")
    public void doAfterReturning(JoinPoint point, Object returnValue) {
        log.info("@AfterReturning：开始");
        log.info("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        log.info("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
        log.info("@AfterReturning：返回值为：" + returnValue);
        log.info("@AfterReturning：被织入的目标对象为：" + point.getTarget());
        String  id=point.getArgs()[point.getArgs().length-1].toString();
        log.info("id:"+id);
        ResultBean result=blogService.updateBlogStat(Integer.parseInt(id),"readCount");
        log.info("@AfterReturning：结束  result:"+result);
    }
}
