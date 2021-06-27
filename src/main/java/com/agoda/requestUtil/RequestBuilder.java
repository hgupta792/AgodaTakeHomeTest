package com.agoda.requestUtil;


import com.agoda.fileReader.ConfigFileReader;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class RequestBuilder {
    protected ThreadLocal<RequestSpecBuilder> localBuilder =  new ThreadLocal<RequestSpecBuilder>(); 
    private static RequestBuilder reqBuilder = new RequestBuilder();
    
    private RequestBuilder() {
    	
    }
    
    public static RequestBuilder getInstance()
    {
    	return reqBuilder;
    }
    
    
    public void setRequestBuilder(RequestSpecBuilder build)
    {
    	localBuilder.set(build);
    }
    
    public RequestSpecBuilder getRequestBuilder() {
    	return localBuilder.get();
    }
    
    public void removeReqBuilder() {
    	localBuilder.remove();
    }
    
    
    
   public void setQueryParamWithKeyValue(String key , Object value) {
    	getRequestBuilder().addQueryParam(key,value);
    }
    
   
    public void addRequestHeader(String headerName , String headerValue) {
    	getRequestBuilder().addHeader(headerName, headerValue);
    }
    
    public void setAcceptType(String type) {
    	switch (type.trim().toLowerCase()) {
    	case"json":
    		addRequestHeader("Accept-Type","application/json");
    		break;
    	}
    }
    
    public void setContentType(String type) {
    	switch (type.trim().toLowerCase()) {
    	case"json":
    		addRequestHeader("Content-Type","application/json");
    		break;
    	}
    }
    
    
     
    public void exectueAPI(String method,String endPoint){
    	String urlToCall = ConfigFileReader.getPropertyFile().getProperty("baseURL")+endPoint;
    	System.out.println(urlToCall);
        RequestSpecification request=RestAssured.given();
    	RequestSpecification requestSpecification = getRequestBuilder().build();
    	System.out.println("--------------RequestSpec---------------- \\n"+requestSpecification);
    	Response response=null;
    	request.spec(requestSpecification);
    	if(method.toLowerCase().trim().equals("post"))
    		response= request.post(urlToCall);
        else if(method.toLowerCase().trim().equals("get"))
        	response= request.get(urlToCall);
        else if(method.toLowerCase().trim().equals("put"))
        	response= request.put(urlToCall);
        else if(method.toLowerCase().trim().equals("delete"))
        	response= request.delete(urlToCall); 
    	System.out.println(response);
    	ResponseBuilder.getInstance().setResponse(response);    	
    }
 
}