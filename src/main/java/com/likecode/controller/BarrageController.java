package com.likecode.controller;


import com.likecode.bean.Barrage;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.controller.BaseController;
import com.likecode.common.utils.IpUtil;
import com.likecode.service.BarrageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by wangkai on 2017/9/19.
 * 弹幕控制器
 */
@Log4j
@Controller
public class BarrageController extends BaseController{

	 @Autowired
	 BarrageService barrageServiceImpl;

	 @ResponseBody
	 @RequestMapping(value="barrages")
	 public ResultBean index() {
		 ResultBean bean=barrageServiceImpl.getBarrages();
		 return bean;
	 }
	@ResponseBody
	@RequestMapping(value="saveBarrage" ,method = RequestMethod.POST)
	public ResultBean love(Model model,String content,HttpServletRequest request) {
		Barrage barrage=new Barrage();
		barrage.setContent(content);
		barrage.setIp(IpUtil.getIP4(request));
		ResultBean bean=barrageServiceImpl.insertBarrage(barrage);
		return bean;
	}

}
