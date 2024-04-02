package com.example.cms.entity;

public class Publish {

	private int publishId;

	private String seoTitle;

	private String seoDescription;

	private String[] seoTopic;
	
	private String publishURL;
	

	public String getPublishURL() {
		return publishURL;
	}

	public void setPublishURL(String publishURL) {
		this.publishURL = publishURL;
	}

	public int getPublishId() {
		return publishId;
	}

	public void setPublishId(int publishId) {
		this.publishId = publishId;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String[] getSeoTopic() {
		return seoTopic;
	}

	public void setSeoTopic(String[] seoTopic) {
		this.seoTopic = seoTopic;
	}

	
}
