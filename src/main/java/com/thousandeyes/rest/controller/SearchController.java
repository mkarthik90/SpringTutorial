package com.thousandeyes.rest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thousandeyes.rest.service.ISearchService;

@Controller
public class SearchController {
	
	@Autowired
	private ISearchService searchService;

	@RequestMapping(value = "rest/findFollowers", method = RequestMethod.GET)
	public ResponseEntity<String> addConcept(@RequestParam("user")String user,@RequestParam("follower")String follower,Principal principal) {
		
		searchService.followUser(user, follower);
		System.out.println(user);
		return null;
	}
}
