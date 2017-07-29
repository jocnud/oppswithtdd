package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

	@JsonProperty("full_url")
	private String fullURL;

	@JsonProperty("short_url")
	private String shortURL;
	
	

	public String getFullURL() {
		return fullURL;
	}



	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
	}



	public String getShortURL() {
		return shortURL;
	}



	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}



	public Link(String shortURL, String fullURL) {
		this.fullURL = fullURL;
		this.shortURL = shortURL;
	}
	
	public Link() {
	}

}
