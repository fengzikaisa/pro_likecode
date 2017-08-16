package com.senyint.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;

	@Override
	public String toString() {
		return "Entity [name=" + name + ", sex=" + sex + "]";
	}
}
