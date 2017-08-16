package com.senyint.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.senyint.bean.TestBean;

@Mapper
public interface TestDao {
	public List<TestBean> queryTest();

}
