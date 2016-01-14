package com.thousandeyes.rest.service;

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
		if(keyDAO.checkUserExists(user) > 0){
			return true;
		}
		return false;
	}

	@Override
	public void checkAPIKeys(String key) {
		IKeyDAO dao = new KeyDAO();
		dao.checkUserExists("test");
		System.out.println("Check");
		
	}
}
