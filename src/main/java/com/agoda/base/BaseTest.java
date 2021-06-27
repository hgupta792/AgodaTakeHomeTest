package com.agoda.base;

import java.util.Properties;

import com.agoda.requestUtil.AssertionHelp;
import com.agoda.requestUtil.RequestBuilder;

public class BaseTest extends AssertionHelp {

	public static Properties propVar = null;

	public void setBasicReqHeader() {
		RequestBuilder reqBuild = RequestBuilder.getInstance();
		reqBuild.setAcceptType(propVar.getProperty("acceptType"));
		reqBuild.setContentType(propVar.getProperty("contentType"));
	}

}
