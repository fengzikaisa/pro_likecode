package com.senyint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senyint.bean.TestBean;
import com.senyint.common.bean.ParameterBean;
import com.senyint.common.bean.ResultBean;
import com.senyint.common.utils.ConstantDefinition;
import com.senyint.dao.TestDao;
import com.senyint.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDao TestDao;

	@Override
	public ResultBean queryTest(ParameterBean pb) {
		List<TestBean> list = this.TestDao.queryTest();
		return new ResultBean(ConstantDefinition.SYSTEM_SUCCESS, list);
	}
}
