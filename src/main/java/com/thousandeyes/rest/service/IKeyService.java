package com.thousandeyes.rest.service;

public interface IKeyService {

	
	public void insertAPIKey(String user,String apiKey);
	
	public boolean checkUserExists(String user);
	
	public void insertUserNamePassword(String userName,String password);
}
