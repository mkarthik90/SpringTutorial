package com.thousandeyes.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KeyDAO implements IKeyDAO {

	@Autowired
	private TweetJDBCTemplate thousandeyesTemplate;

	@Override
	public void insertApiKey(String user, String apiKey) {
		String sql = "UPDATE USERDETAILS SET APIKEY =? WHERE USERNAME =?";
		thousandeyesTemplate.getJdbcTemplateObject().update(sql, apiKey, user);
	}

	@Override
	public List checkUserExists(String user) {
		String sql = "SELECT USERNAME FROM USERDETAILS WHERE USERNAME =?";
		List size = thousandeyesTemplate.getJdbcTemplateObject().queryForList(sql,user);
		return size;
	}

	@Override
	public void insertUserNamePassword(String userName, String password) {
		String sql = "INSERT INTO USERDETAILS (USERNAME,PASSWORD) VALUES (?,?)";
		thousandeyesTemplate.getJdbcTemplateObject().update(sql,userName,password);
	}

}
