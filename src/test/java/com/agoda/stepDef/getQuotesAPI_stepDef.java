
package com.agoda.stepDef;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.agoda.base.BaseTest;
import com.agoda.pojo.getQuoteResponse;
import com.agoda.pojo.getQuoteResponse_results;
import com.agoda.requestUtil.RequestBuilder;
import com.agoda.requestUtil.ResponseBuilder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class getQuotesAPI_stepDef extends BaseTest{
	
	/*
	 * Scenario1
	 */
   
    @Given("I call URL with endpoint {string} with tag value {string}")
    public void callGetQuotesWithTags(String endpoint, String tagValue) {
    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("tags", tagValue);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    @Then("I only see results with tag {string} in response")
    public void checkResultWithTag(String tagValue) {
    	getQuoteResponse responseAsPojo = ResponseBuilder.getInstance().getResponse().as(getQuoteResponse.class);
    	Assert.assertTrue("All tags in result does not contains "+tagValue,checkResultWithTagValue(tagValue,responseAsPojo.getResults()));
    }
   
       
    @Then("I see status code {int}")
    public void checkStatusCode(int agr) {
	    checkResponseCodeValue(agr);
    }
	
    
    /*
     * Scenario3
     */
    @Given("I call URL with endpoint {string} with author value {string}")
    public void callGetQuotesWithAuthor(String endpoint, String authorValue) {
    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("author", authorValue);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    @Then("I only see results with author {string} in response")
    public void checkResultWithAuthor(String authValue) {
    	getQuoteResponse responseAsPojo = ResponseBuilder.getInstance().getResponse().as(getQuoteResponse.class);
    	Assert.assertTrue("All author in result does not contains "+authValue,checkResultWithAuthorValue(authValue,responseAsPojo.getResults()));
    }
   
    
    
    /*
     * Scenario4
     */
    @Given("I call URL with endpoint {string} with page value {string}")
    public void callGetQuotesWithPage(String endpoint, String pageVal) {
    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("page", pageVal);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    @Then("I only see results for pageNumber {string} in response")
    public void checkResultWithPage(String pageValue) {
    	getQuoteResponse responseAsPojo = ResponseBuilder.getInstance().getResponse().as(getQuoteResponse.class);
    	Assert.assertTrue("Result for page "+pageValue+" is not correct",checkResultWithPageValue(pageValue,responseAsPojo));
    }
    
    
    
    /*
     * Scenario 5
     */
    @Given("I call URL with endpoint {string} with tag value {string} and author value {string}")
    public void callGetQuotesWithAuthorAndTag(String endpoint, String tagValue , String authorValue) {    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("author", authorValue);
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("tags", tagValue);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    
    /*
     * Scenario 6
     */
    @Given("I call URL with endpoint {string} with page value {string} and author value {string}")
    public void callGetQuotesWithAuthorAndPage(String endpoint, String pageValue , String authorValue) {    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("author", authorValue);
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("page", pageValue);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    
    /*
     * Scenario 7
     */
    @Given("I call URL with endpoint {string} with page value {string} and tag value {string}")
    public void callGetQuotesWithTagAndPage(String endpoint, String pageValue , String tagValue) {    	
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("tags", tagValue);
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("page", pageValue);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    /*
     * Scenario 8
     */
    @Given("I call URL with endpoint {string} with page value {string} and tag value {string} and authorValue {string}")
    public void callGetQuotesWithTagAndPageAndAuthor(String endpoint, String pageVal, String tagVal, String authorVal) {
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("author", authorVal);
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("page", pageVal);
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("tags", tagVal);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    
    /*
     * Scenario 9
     */
    @Given("I call URL with incorrect endpoint {string} with page value {string}")
    public void callGetQuotesWithIncorrectEndpt(String endpoint, String pageVal) {
    	RequestBuilder.getInstance().setQueryParamWithKeyValue("page", pageVal);
    	RequestBuilder.getInstance().exectueAPI("GET", endpoint);
    }
    
    @Then("I see error message {string}")
    public void checkErrorMsg(String errorMsg) {
    	checkResponseAttributeValue("statusMessage",errorMsg);
    }

    
    
	  
	  /*
	   * Methods used above
	   */
    
    /*
     * Check if tags separated with ',' '|'  or single tag are in results.tags
     */
	  private boolean checkResultWithTagValue(String tagValue, List<getQuoteResponse_results> result) 
	  {
		  boolean flag=false;
		  int resultSize = result.size();
		  int count=0;
		  List<String> tagVals=new ArrayList<String>();
		  if(tagValue.contains(",")) {
			  tagVals = Arrays.asList(tagValue.split(","));
		  }
		  else if(tagValue.contains("|")) {	
			  tagVals = Arrays.asList(tagValue.split("\\|"));
		  }			  		  
		  else
			  tagVals.add(tagValue); 
		  
		  System.out.println(tagVals);
			
		  if(resultSize!=0) {
		  for(int i=0;i<resultSize;i++) {
			  
			  int allVals=0;
			  System.out.println("Tags: "+ result.get(i).getTags());
			  for(int j=0;j<tagVals.size();j++) {
				  if(result.get(i).getTags().contains(tagVals.get(j)))
					  allVals++;				  
			  }
			  
			  if(tagValue.contains(",") || tagVals.size()==1) {
				  System.out.println("Logic for tags AND");
				  if(allVals==tagVals.size())
					  count++;		
			  }
			  else if(tagValue.contains("|")){	
				  System.out.println("Logic for tags OR");
				  if(allVals>=1)
					  count++;
			  }
			  	  
		  }
		 }
		  
		  System.out.println(count +" "+resultSize);
		  if(count==resultSize)
			  flag=true;
		  
		  return flag;
	  }
	  
	  /*
	   * Check if author is present in results.author
	   */
	  private boolean checkResultWithAuthorValue(String authorValue, List<getQuoteResponse_results> result) 
	  {
		  boolean flag=false;
		  int resultSize = result.size();
		  int count=0;
		  
		 if(resultSize!=0) {
		  for(int i=0;i<resultSize;i++) {
			  if(result.get(i).getAuthor().equals(authorValue))
					  count++;	  		 
		  }
		 }
		  
		  System.out.println(count +" "+resultSize);
		  if(count==resultSize)
			  flag=true;
		  
		  return flag;
	  }
	  
	  /*
	   * Check if pagination is correct in response
	   */
	  private boolean checkResultWithPageValue(String pageValue, getQuoteResponse response) 
	  {
		  boolean flag=false;
		  int countInResponse = response.getCount();
		  int totalPages = response.getTotalPages();
		  int page= response.getPage();
		  int totalCount = response.getTotalCount();
		  List<getQuoteResponse_results> resultsInResponse = response.getResults();
		  
		 if(Integer.parseInt(pageValue)<totalPages) {			 
			 if(Integer.parseInt(pageValue)==page && resultsInResponse.size()==20 && resultsInResponse.size()==countInResponse)
				 flag=true;
		 }
		 else if ((Integer.parseInt(pageValue)>totalPages)) {
			 if(Integer.parseInt(pageValue)==page && resultsInResponse.size()==0 && resultsInResponse.size()==countInResponse)
				 flag=true;
		 }
		 else {
			 
			 int lastPageCount = (totalCount%20);
			 if(Integer.parseInt(pageValue)==page && resultsInResponse.size()==lastPageCount && resultsInResponse.size()==countInResponse)
				 flag=true;
		 }
		  
		  return flag;
	  }
}