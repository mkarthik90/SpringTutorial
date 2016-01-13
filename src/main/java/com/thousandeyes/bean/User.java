package com.thousandeyes.bean;

public class User {

	private String user;
	private String follows;
	private String tweetMessage;
	private String tweetdBy;

	public String getTweetMessage() {
		return tweetMessage;
	}

	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}

	public String getTweetdBy() {
		return tweetdBy;
	}

	public void setTweetdBy(String tweetdBy) {
		this.tweetdBy = tweetdBy;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFollows() {
		return follows;
	}

	public void setFollows(String follows) {
		this.follows = follows;
	}

}
