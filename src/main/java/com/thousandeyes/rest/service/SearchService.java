package com.thousandeyes.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thousandeyes.bean.User;
import com.thousandeyes.dao.ITweetJDBCTemplate;

@Service
public class SearchService implements ISearchService {

	@Autowired
	private ITweetJDBCTemplate tweetjdbcTemplate;

	public void followUser(String user, String follower) {
		tweetjdbcTemplate.followUser(user, follower);
	}

	@Override
	public void unFollowUser(String user, String unfollow) {
		tweetjdbcTemplate.unFollowUser(user, unfollow);
	}

	public List<User> listOfFollowersForUser(String user) {
		return tweetjdbcTemplate.listOfFollowersForUser(user);
	}

	@Override
	public List<User> fetchListOfUserFollowedBy(String user) {
		return tweetjdbcTemplate.fetchListOfUserFollowedBy(user);
	}

	@Override
	public List<User> fetchTweets(String user) {
		return tweetjdbcTemplate.fetchTweets(user);
	}

	@Override
	public List<User> fetchTweetsBySearch(String user, String search) {
		return tweetjdbcTemplate.fetchTweetsSearch(user,search);
	}
}
