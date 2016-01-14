package com.thousandeyes.dao;

public interface IKeyDAO {

	public void insertApiKey(String user,String apiKey);
	
	public Integer checkUserExists(String user);
}
