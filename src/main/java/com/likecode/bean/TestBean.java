package com.senyint.bean;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;

@Alias("testBean")
public class TestBean implements Serializable {

	private static final long serialVersionUID = 1L;

	String id;
	String co1;
	String co2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCo1() {
		return co1;
	}

	public void setCo1(String co1) {
		this.co1 = co1;
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}
}
