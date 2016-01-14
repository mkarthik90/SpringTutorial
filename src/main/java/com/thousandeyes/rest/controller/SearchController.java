package com.thousandeyes.rest.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thousandeyes.bean.User;
import com.thousandeyes.rest.service.ISearchService;

@Controller("searchController")
public class SearchController {

	@Autowired
	private ISearchService searchService;

	@RequestMapping(value = "rest/followUser", method = RequestMethod.GET)
	public ResponseEntity<String> followUser(@RequestParam("user") String user, @RequestParam("follows") String follows,
			Principal principal) {

		searchService.followUser(user, follows);
		System.out.println(user);
		return null;
	}

	@RequestMapping(value = "rest/unFollow", method = RequestMethod.GET)
	public ResponseEntity<String> unfollowUser(@RequestParam("user") String user,
			@RequestParam("unfollow") String unfollow, Principal principal) {

		searchService.unFollowUser(user, unfollow);
		return null;
	}

	@RequestMapping(value = "rest/listOfFollowersForUser", method = RequestMethod.GET)
	public ResponseEntity<String> fetchListOfFollowersForUser(@RequestParam("user") String user) {

		List<User> userList = searchService.listOfFollowersForUser(user);
		StringBuilder jsonString = new StringBuilder("{");
		if (userList.isEmpty()) {
			jsonString.append("\"message\": \"No Followers\"");
			jsonString.append("}");
			return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
		}
		jsonString.append("\"Followers\":[");
		int followerNumber = 1;
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			if (followerNumber != 1) {
				jsonString.append(",");
			}
			jsonString.append("{");
			jsonString.append("\"Follower-" + followerNumber + "\":");
			jsonString.append("\"");
			jsonString.append(userObject.getUser());
			jsonString.append("\"");
			jsonString.append("}");
			followerNumber++;
		}
		jsonString.append("]");
		jsonString.append("}");

		return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "rest/listOfUsersFollowedBy", method = RequestMethod.GET)
	public ResponseEntity<String> fecthListOfUserFollowedBy(@RequestParam("user") String follows) {

		List<User> userList = searchService.fetchListOfUserFollowedBy(follows);
		StringBuilder jsonString = new StringBuilder("{");
		if (userList.isEmpty()) {
			jsonString.append("\"message\": \"Does not follow anyone\"");
			jsonString.append("}");
			return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
		}
		jsonString.append("\"Follows\":[");
		int followerNumber = 1;
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			if (followerNumber != 1) {
				jsonString.append(",");
			}
			jsonString.append("{");
			jsonString.append("\"Follows-" + followerNumber + "\":");
			jsonString.append("\"");
			jsonString.append(userObject.getFollows());
			jsonString.append("\"");
			jsonString.append("}");
			followerNumber++;
		}
		jsonString.append("]");
		jsonString.append("}");

		return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "rest/fetchTweets", method = RequestMethod.GET)
	public ResponseEntity<String> fetchTweets(@RequestParam("user") String user) {

		List<User> userList = searchService.fetchTweets(user);
		StringBuilder jsonString = new StringBuilder("{");
		if (userList.isEmpty()) {
			jsonString.append("\"message\": \"Does not have any tweet message\"");
			jsonString.append("}");
			return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
		}
		jsonString.append("\"Tweet Message\":[");
		int followerNumber = 1;
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			if (followerNumber != 1) {
				jsonString.append(",");
			}
			jsonString.append("{");
			jsonString.append("\"Messages\":");
			jsonString.append("\"");
			jsonString.append(userObject.getTweetMessage());
			jsonString.append("\"");

			jsonString.append(",");
			jsonString.append("\"Tweeted By\":");
			jsonString.append("\"");
			jsonString.append(userObject.getTweetdBy());
			jsonString.append("\"");

			jsonString.append("}");
			followerNumber++;
		}
		jsonString.append("]");
		jsonString.append("}");

		return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "rest/fetchTweetsWithSearch", method = RequestMethod.GET)
	public ResponseEntity<String> fetchTweetsWithSearchValue(@RequestParam("user") String user,
			@RequestParam("search") String search) {

		List<User> userList = searchService.fetchTweetsBySearch(user, search);
		StringBuilder jsonString = new StringBuilder("{");
		if (userList.isEmpty()) {
			jsonString.append("\"message\": \"Does not have any tweet message\"");
			jsonString.append("}");
			return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
		}
		jsonString.append("\"Tweet Message\":[");
		int followerNumber = 1;
		for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
			User userObject = (User) iterator.next();
			if (followerNumber != 1) {
				jsonString.append(",");
			}
			jsonString.append("{");
			jsonString.append("\"Messages\":");
			jsonString.append("\"");
			jsonString.append(userObject.getTweetMessage());
			jsonString.append("\"");

			jsonString.append(",");
			jsonString.append("\"Tweeted By\":");
			jsonString.append("\"");
			jsonString.append(userObject.getTweetdBy());
			jsonString.append("\"");

			jsonString.append("}");
			followerNumber++;
		}
		jsonString.append("]");
		jsonString.append("}");

		return new ResponseEntity<String>(jsonString.toString(), HttpStatus.OK);
	}

}
