package com.thousandeyes.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thousandeyes.dao.ITweetJDBCTemplate;

@Service
public class SearchService implements ISearchService{

	
	@Autowired
	private ITweetJDBCTemplate tweetjdbcTemplate;
	
	
	public void followUser(String user, String follower){
		tweetjdbcTemplate.followUser(user, follower);
	}
}
