package com.zb.dubbo.facade;

import java.io.Serializable;

public class SampleServiceResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 222325897510414759L;
	private String echoName;

	public String getEchoName() {
		return echoName;
	}

	public void setEchoName(String echoName) {
		this.echoName = echoName;
	}

}
