package com.thousandeyes.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thousandeyes.dao.IKeyDAO;
import com.thousandeyes.dao.KeyDAO;

@Service("keyService")
public class KeyService implements IKeyService {

	@Autowired
	private IKeyDAO keyDAO;

	@Override
	public void insertAPIKey(String user, String apiKey) {
		keyDAO.insertApiKey(user, apiKey);
	}

	@Override
	public boolean checkUserExists(String user) {
		// Returns true if user exists
		List userList = keyDAO.checkUserExists(user);
		if (userList != null && !userList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void insertUserNamePassword(String userName, String password) {
		keyDAO.insertUserNamePassword(userName, password);

	}
}
