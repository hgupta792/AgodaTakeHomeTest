package com.agoda.requestUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseBuilder {
	
	
	protected ThreadLocal<Response> localResponse =  new ThreadLocal<Response>(); 
    private static ResponseBuilder response = new ResponseBuilder();
    
	 private ResponseBuilder() {
	    	
	    }
    
    public static ResponseBuilder getInstance()
    {
    	return response;
    }
    
    
    
    public void setResponse(Response res)
    {
    	localResponse.set(res);
    }
    
    public Response getResponse() {
    	
    	return localResponse.get();
    }
    
	public void removeResponse() {
	    	
	    	localResponse.remove();
	    }
	

    /*
    ***Returns JsonPath object***
    * First convert the API's response to String type with "asString()" method.
    * Then, send this String formatted json response to the JsonPath class and return the JsonPath
    */
    public String getValueFromJsonPath (String attribute) {
        String json = getResponse().asString();
        String toReturn = new JsonPath(json).get(attribute).toString();
        System.out.println(toReturn);
        return toReturn;
    }
    
    
   
	

}
