package com.thousandeyes.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.thousandeyes.bean.User;

public class TweetJDBCTemplate implements ITweetJDBCTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void followUser(String user, String follower) {
		String sql = "INSERT INTO FOLLOWTABLE (USER,FOLLOWS) VALUES (?,?)";
		jdbcTemplateObject.update(sql, user, follower);
	}

	@Override
	public void unFollowUser(String user, String unFollow) {

		String sql = "DELETE FROM FOLLOWTABLE WHERE USER=? AND FOLLOWS=?";
		int numberOfRecordsDeleted = jdbcTemplateObject.update(sql, user, unFollow);
	}

	@Override
	public List<User> listOfFollowersForUser(String user) {

		String sql = "SELECT * FROM FOLLOWTABLE WHERE FOLLOWS=?";
		List<User> userList = new ArrayList<User>();

		List<Map<String, Object>> users = jdbcTemplateObject.queryForList(sql, user);
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

		List<Map<String, Object>> users = jdbcTemplateObject.queryForList(sql, user);
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
		//Can also be done through Joining the table.
		List<User> usersList = fetchListOfUserFollowedBy(user);
		/*Set<String> userSet = new HashSet<String>();
		userSet.add(user);
		for (Iterator iterator = usersList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			userSet.add(userObject.getUser());
		}*/
		
		List userList = new ArrayList();
		userList.add(user);
		for (Iterator iterator = usersList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			userList.add(userObject.getFollows());
		}
		
		String sql = "SELECT * FROM TWEETS WHERE TWEETEDBY IN (:userIds)";
		
		/*MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userIds", userSet);*/
		try{
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userIds", userList);
		List messages = jdbcTemplateObject.queryForList(sql, Collections.singletonMap("userIds", params));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}



}
