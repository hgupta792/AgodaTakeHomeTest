package com.agoda.stepDef;


import com.agoda.base.BaseTest;
import com.agoda.fileReader.ConfigFileReader;
import com.agoda.requestUtil.RequestBuilder;
import com.agoda.requestUtil.ResponseBuilder;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;

public class AppHooks extends BaseTest {
	
	@Before
	public void setupBase(Scenario sc) {
		
		if(propVar==null)
			propVar = ConfigFileReader.getPropertyFile();
        RequestBuilder.getInstance().setRequestBuilder(new RequestSpecBuilder());
        setBasicReqHeader();
	}
	
	
	@After
	public void tearDown() {	
		RequestBuilder.getInstance().removeReqBuilder();
		ResponseBuilder.getInstance().removeResponse();
	}
	
	
	

}
