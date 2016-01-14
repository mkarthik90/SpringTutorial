package com.thousandeyes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thousandeyes.bean.User;

@Repository
public class TweetDAO implements ITweetDAO {

	@Autowired
	private TweetJDBCTemplate thousandEyestemplate;

	@Override
	public void unFollowUser(String user, String unFollow) {

		String sql = "DELETE FROM FOLLOWTABLE WHERE USER=? AND FOLLOWS=?";
		int numberOfRecordsDeleted = thousandEyestemplate.getJdbcTemplateObject().update(sql, user, unFollow);
	}

	@Override
	public List<User> listOfFollowersForUser(String user) {

		String sql = "SELECT * FROM FOLLOWTABLE WHERE FOLLOWS=?";
		List<User> userList = new ArrayList<User>();

		List<Map<String, Object>> users = thousandEyestemplate.getJdbcTemplateObject().queryForList(sql, user);
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			User userBean = new User();
			userBean.setUser(map.get("USER"));
			userBean.setFollows(map.get("FOLLOWS"));
			userList.add(userBean);
		}
		return userList;
	}

	@Override
	public List<User> fetchListOfUserFollowedBy(String user) {
		String sql = "SELECT * FROM FOLLOWTABLE WHERE USER=?";
		List<User> userList = new ArrayList<User>();

		List<Map<String, Object>> users = thousandEyestemplate.getJdbcTemplateObject().queryForList(sql, user);
		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			User userBean = new User();
			userBean.setUser(map.get("USER"));
			userBean.setFollows(map.get("FOLLOWS"));
			userList.add(userBean);
		}
		return userList;
	}

	@Override
	public List<User> fetchTweets(String user) {
		// Can also be done through Joining the table.
		List<User> usersList = fetchListOfUserFollowedBy(user);
		List userList = new ArrayList();
		List<User> tweetList = new ArrayList<User>();
		userList.add(user);
		for (Iterator iterator = usersList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			userList.add(userObject.getFollows());
		}

		String sql = "SELECT * FROM TWEETS WHERE TWEETEDBY IN (:userIds)";

		Map params = Collections.singletonMap("userIds", userList);
		List<Map<String, Object>> messages = thousandEyestemplate.getNamedJdbcTemplate().queryForList(sql, params);
		formTweetDetails(tweetList, messages);

		return tweetList;
	}

	@Override
	public List<User> fetchTweetsSearch(String user, String search) {

		// Can also be done through Joining the table.
		List<User> usersList = fetchListOfUserFollowedBy(user);
		List userList = new ArrayList();
		List<User> tweetList = new ArrayList<User>();
		userList.add(user);
		for (Iterator iterator = usersList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			userList.add(userObject.getFollows());
		}

		String sql = "SELECT * FROM TWEETS WHERE MESSAGE LIKE :search AND TWEETEDBY IN (:userIds)";

		Map params = new HashMap();
		params.put("userIds", userList);
		params.put("search", "%" + search + "%");
		List<Map<String, Object>> messages = thousandEyestemplate.getNamedJdbcTemplate().queryForList(sql, params);

		formTweetDetails(tweetList, messages);
		return tweetList;

	}

	public void followUser(String user, String follower) {
		String sql = "INSERT INTO FOLLOWTABLE (USER,FOLLOWS) VALUES (?,?)";
		thousandEyestemplate.getJdbcTemplateObject().update(sql, user, follower);
	}

	private void formTweetDetails(List<User> tweetList, List<Map<String, Object>> messages) {
		for (Iterator iterator = messages.iterator(); iterator.hasNext();) {
			Map<String, String> map = (Map<String, String>) iterator.next();
			User tweetDetails = new User();
			tweetDetails.setTweetMessage(map.get("MESSAGE"));
			tweetDetails.setTweetdBy(map.get("TWEETEDBY"));
			tweetList.add(tweetDetails);
		}
	}

}
