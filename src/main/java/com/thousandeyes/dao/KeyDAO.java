package com.thousandeyes.dao;

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
	public Integer checkUserExists(String user) {
		String sql = "SELECT USERNAME FROM USERDETAILS WHERE USERNAME =?";
		int size = thousandeyesTemplate.getJdbcTemplateObject().queryForInt(sql);
		return size;
	}

}
