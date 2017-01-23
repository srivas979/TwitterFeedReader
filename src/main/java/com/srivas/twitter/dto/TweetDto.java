package com.srivas.twitter.dto;

import java.util.ArrayList;

public class TweetDto {
	
	private long tweetId;
	private String userName;
	private String userScreenName;
	private String pathToUserProfileImage;
	private String tweetContent;
	private int retweetCount;
	private String creationDateDisplayString;
	private ArrayList<String> mediaImageUrlList = new ArrayList<String>();
	
	public long getTweetId() {
		return tweetId;
	}
	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserScreenName() {
		return userScreenName;
	}
	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	public String getPathToUserProfileImage() {
		return pathToUserProfileImage;
	}
	public void setPathToUserProfileImage(String pathToUserProfileImage) {
		this.pathToUserProfileImage = pathToUserProfileImage;
	}
	public String getTweetContent() {
		return tweetContent;
	}
	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	
	public String getCreationDateDisplayString() {
		return creationDateDisplayString;
	}
	public void setCreationDateDisplayString(String creationDateDisplayString) {
		this.creationDateDisplayString = creationDateDisplayString;
	}
	public ArrayList<String> getMediaImageUrlList() {
		return mediaImageUrlList;
	}
	public void setMediaImageUrlList(ArrayList<String> mediaImageUrlList) {
		this.mediaImageUrlList = mediaImageUrlList;
	}
	
}
