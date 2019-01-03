package com.magrabbit.pilot.bean;

public class SearchProductInfo {
	private int currentPage;
	private String searchText;
	private long priceFrom;
	private long priceTo;
	
	public SearchProductInfo() {
		super();
	}
	public SearchProductInfo(int currentPage, String searchText, long priceFrom, long priceTo) {
		super();
		this.currentPage = currentPage;
		this.searchText = searchText;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public long getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(long priceFrom) {
		this.priceFrom = priceFrom;
	}
	public long getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(long priceTo) {
		this.priceTo = priceTo;
	}
	
}
