package com.likecode.controller;


import com.likecode.common.bean.ResultBean;
import com.likecode.service.UserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.likecode.common.controller.BaseController;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class Testcontroller extends BaseController{

	 @Autowired
	 UserStudentService userStudentServiceImpl;

	 @ResponseBody
	 @RequestMapping(value="index")
	 public ResultBean index() {
		 ResultBean bean=userStudentServiceImpl.getStudents();
		 log.info(bean);
		 return bean;
	 }

	@RequestMapping(value="index1")
	public String index1(Model model) {
		ResultBean bean=userStudentServiceImpl.getStudents();
		log.info(bean);
		model.addAttribute("bean",bean);
		return "NewFile";
	}

}
