package com.thousandeyes.rest.service;

import java.util.List;

import com.thousandeyes.bean.User;

public interface ISearchService {

	public void followUser(String user, String follower);
	
	public void unFollowUser(String user, String unfollow);
	
	public List<User> listOfFollowersForUser(String user);
	
	public List<User> fetchListOfUserFollowedBy(String user);
	
	public List<User> fetchTweets(String user);
}
