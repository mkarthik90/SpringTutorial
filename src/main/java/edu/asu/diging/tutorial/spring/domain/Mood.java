package edu.asu.diging.tutorial.spring.domain;

public class Mood {

    private String feeling;
    private String reason;

    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Mood(String mood) {
        feeling = mood;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }
}
