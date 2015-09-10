package edu.asu.diging.tutorial.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import edu.asu.diging.tutorial.spring.domain.Mood;

@Service
public class MoodService implements IMoodService{

	public static List<String> moodList = new ArrayList<String>();
	public static Map<String,String> reasonMap = new HashMap<String,String>();

	@PostConstruct
	public void setMoods() {
		moodList.add("happy");
		moodList.add("sad");
		moodList.add("angry");
		reasonMap.put("happy", "Got a Mice!!");
		reasonMap.put("sad", "No MICE :( ");
		reasonMap.put("angry", "Mice is playing with me ");
	}

	@Override
	public Mood getCurrentMood() {
		Random randomGenerator = new Random();
		int choosingMood = randomGenerator.nextInt(3);
		Mood mood = new Mood(String.valueOf(moodList.get(choosingMood)));
		return mood;
	}
	
	@Override
	public Mood getReason(String currentMood) {
		Mood mood = new Mood(currentMood);
		mood.setReason(reasonMap.get(currentMood));
		return mood;
	}
	
}
