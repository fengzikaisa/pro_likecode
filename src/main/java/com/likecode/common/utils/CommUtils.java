package com.likecode.common.utils;

import java.lang.reflect.Method;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.likecode.common.bean.ParameterBean;
import com.likecode.common.bean.ResultBean;
import lombok.extern.log4j.Log4j;

/***
 * 
 * @author ly
 * @version v1.0
 * @deprecated 模块代理类
 */
@Service
@Log4j
public class CommUtils {
	public String CommSet(ParameterBean pb,Class<?> cls) {
		String clazzName3 = new Object() {  
	        public String getClassName() {  
	            String clazzName = cls.getName();  
	            return clazzName.substring(0, clazzName.lastIndexOf('$'));  
	        }  
	    }.getClassName();  
	    System.out.println(clazzName3+"==clazzName3");
		
		return null;
	}
	public String CommSet(ParameterBean pb) {
		ResultBean smr = new ResultBean();
		try {
			String interfaceUrl = pb.getInterfaceUrl();
			char[] cs = interfaceUrl.toCharArray();
			cs[0] += 32;
			interfaceUrl = String.valueOf(cs);
			WebApplicationContext wac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(ServletContextUtil.getServletContext());
			Object o = wac.getBean(interfaceUrl + "Impl");
			Object obj = AopTargetUtils.getTarget(o);
			String proxyName = o.toString();
			Class<?> clazz = Class.forName(proxyName.substring(0, proxyName.lastIndexOf("@")));
			Method m = clazz.getDeclaredMethod(pb.getMethod(), ParameterBean.class);
			Object resultObj = m.invoke(obj, pb);
			smr = (ResultBean) resultObj;
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			smr.setStatus(ConstantDefinition.SYSTEM_METHOD_JSON);
			throw new RuntimeException();
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage());
			smr.setStatus(ConstantDefinition.SYSTEM_METHOD_NULL);
			throw new RuntimeException();
		} catch (SecurityException e) {
			log.error(e.getMessage());
			smr.setStatus(ConstantDefinition.SYSTEM_METHOD_SECURITY);
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("CommUtils", e);
			e.printStackTrace();
			smr.setStatus(ConstantDefinition.SYSTEM_ERROR);
		}
		return smr.toString();
	}
}
