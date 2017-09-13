package com.likecode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.likecode.bean.TestBean;
import com.likecode.common.bean.ParameterBean;
import com.likecode.common.bean.ResultBean;
import com.likecode.common.utils.ConstantDefinition;
import com.likecode.dao.TestDao;
import com.likecode.service.TestService;

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
