package com.senyint.common.utils;

import java.lang.reflect.Field;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;

/***
 * 
 * @author ly
 * @version v1.0
 * @deprecated 有问题不过能用
 *
 */
public class AopTargetUtils {
	// 直接返回
	public static Object getTarget(Object proxy) throws Exception {
		return proxy;
	}

	/***
	 * 有问题1
	 * 
	 * @param proxy
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
		return target;
	}

	/***
	 * 有问题2
	 * 
	 * @param proxy
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);

		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
		return target;
	}
}
