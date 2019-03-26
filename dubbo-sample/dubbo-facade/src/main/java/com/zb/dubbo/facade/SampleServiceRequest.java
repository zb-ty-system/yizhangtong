package com.zb.dubbo.facade;

import java.io.Serializable;

public class SampleServiceRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3210141886703414612L;

	/**
	 * 
	 */
	private String name;
	
	private String name1;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
