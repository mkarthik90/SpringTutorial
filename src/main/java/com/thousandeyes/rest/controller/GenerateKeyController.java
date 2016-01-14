package com.thousandeyes.rest.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thousandeyes.rest.service.IKeyService;

@Controller("generateKeyController")
public class GenerateKeyController {

	@Autowired
	private IKeyService keyService;

	@RequestMapping(value = "restKey/generateApiKey", method = RequestMethod.GET)
	public ResponseEntity<String> generateApiKey(@RequestParam("user") String userName) {

		if (keyService.checkUserExists(userName)) {
			StringBuilder builder = new StringBuilder("{");
			builder.append("\"ERROR MESSAGE\":");
			builder.append("\"");
			builder.append("USER DOESNOT EXISTS");
			builder.append("\"");
			builder.append("}");

			return new ResponseEntity<String>(builder.toString(), HttpStatus.OK);
		}

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		System.out.println(ts);

		String currentTime = ts.toString();
		currentTime = currentTime.replaceAll("([-:. ])+", "");
		currentTime = currentTime.substring(0, 16 - userName.length());
		StringBuilder apiKey = new StringBuilder(userName);
		apiKey.append(currentTime);

		keyService.insertAPIKey(userName, apiKey.toString());

		StringBuilder builder = new StringBuilder("{");
		builder.append("\"apiKey\":");
		builder.append("\"");
		builder.append(apiKey.toString());
		builder.append("\"");
		builder.append("}");

		return new ResponseEntity<String>(builder.toString(), HttpStatus.OK);
	}

}
