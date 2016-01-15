package com.thousandeyes.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thousandeyes.bean.User;
import com.thousandeyes.dao.ITweetDAO;

@Service
public class SearchService implements ISearchService {

	@Autowired
	private ITweetDAO tweetDAO;

	public void followUser(String user, String follower)throws Exception {
		tweetDAO.followUser(user, follower);
	}

	@Override
	public void unFollowUser(String user, String unfollow)throws Exception {
		tweetDAO.unFollowUser(user, unfollow);
	}

	public List<User> listOfFollowersForUser(String user) {
		return tweetDAO.listOfFollowersForUser(user);
	}

	@Override
	public List<User> fetchListOfUserFollowedBy(String user) {
		return tweetDAO.fetchListOfUserFollowedBy(user);
	}

	@Override
	public List<User> fetchTweets(String user) {
		return tweetDAO.fetchTweets(user);
	}

	@Override
	public List<User> fetchTweetsBySearch(String user, String search) {
		return tweetDAO.fetchTweetsSearch(user,search);
	}

	@Override
	public void insertTweetMessages(String user, String message) {
		tweetDAO.insertTweetMessages(user,message);
		
	}
}
