package com.agoda.pojo;

import java.util.List;

public class getQuoteResponse {
	
	private int count;
	private int totalCount ;
	private int page;
	private int  totalPages;
	private int lastItemIndex;
	private List<getQuoteResponse_results> results;
	
	
	public int getCount() {
		return count;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getPage() {
		return page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getLastItemIndex() {
		return lastItemIndex;
	}
	public List<getQuoteResponse_results> getResults() {
		return results;
	}
	
	
	
	

}
