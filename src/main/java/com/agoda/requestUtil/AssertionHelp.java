package com.agoda.requestUtil;



import org.junit.*;

public class AssertionHelp {
	
	
	public void checkResponseCodeValue(int expVal)
	{
		Assert.assertEquals(expVal, ResponseBuilder.getInstance().getResponse().getStatusCode());
	}
	
	
	
	public void checkResponseAttributeValue(String attrVal,String expVal)
	{
		Assert.assertEquals(expVal, ResponseBuilder.getInstance().getValueFromJsonPath(attrVal));
	}
	
	

}
