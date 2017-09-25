package com.likecode.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.likecode.bean.TestBean;

@Mapper
public interface TestDao {
	List<TestBean> queryTest();

}
