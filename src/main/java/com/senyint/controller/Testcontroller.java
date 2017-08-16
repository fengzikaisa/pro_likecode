package com.senyint.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.senyint.bean.TestBean;
import com.senyint.common.bean.ParameterBean;
import com.senyint.common.controller.BaseController;
import com.senyint.common.utils.CommUtils;
import com.senyint.service.impl.TestServiceImpl;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class Testcontroller extends BaseController{

	// @Autowired
	// TestServiceImpl TestServiceImpl;
	//
	// @RequestMapping(value="index")
	// public String index() {
	// List<TestBean> list = new ArrayList<TestBean>();
	// list = this.TestServiceImpl.queryTest();
	// log.info("");
	// return list.toString();
	// }@RequestBody ParameterBean pb
	//
	@Resource
	CommUtils commUtils;

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody String index(@RequestBody ParameterBean pb) {
		System.out.println("111111111111");
		String clazzName3 = new Object() {  
	        public String getClassName() {  
	            String clazzName = this.getClass().getName();  
	            return clazzName.substring(0, clazzName.lastIndexOf('$'));  
	        }  
	    }.getClassName();  
	    System.out.println(clazzName3+"==clazzName3");
	    System.out.println(this.getClass().toString());
	    this.commUtils.CommSet(pb,this.getClass());
	    return clazzName3;
		//String rel =this.commUtils.CommSet(pb);
		//return rel;

	}

}
