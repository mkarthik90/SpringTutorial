package com.thousandeyes.dao;

import java.util.List;

import com.thousandeyes.bean.User;

public interface ITweetJDBCTemplate {

	public void followUser(String user, String follower);
	public void unFollowUser(String user, String unFollow);
	public List<User> listOfFollowersForUser(String user);
	public List<User> fetchListOfUserFollowedBy(String user);
	public List<User> fetchTweets(String user);
}
