package edu.asu.diging.tutorial.spring.service;

import edu.asu.diging.tutorial.spring.domain.Mood;

public interface IMoodService {
	public Mood getCurrentMood();
	public Mood getReason(String currentMood);
}
