package com.thousandeyes.dao;

import java.util.List;

public interface IKeyDAO {

	public void insertApiKey(String user,String apiKey);
	
	public List checkUserExists(String user);
}
