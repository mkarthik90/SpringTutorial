package com.thousandeyes.dao;

import java.util.List;

import com.thousandeyes.bean.User;

public interface ITweetDAO {
	public void followUser(String user, String follower) throws Exception;

	public void unFollowUser(String user, String unFollow) throws Exception;

	public List<User> listOfFollowersForUser(String user);

	public List<User> fetchListOfUserFollowedBy(String user);

	public List<User> fetchTweets(String user);

	public List<User> fetchTweetsSearch(String user, String search);

	public void insertTweetMessages(String user, String message);
}
