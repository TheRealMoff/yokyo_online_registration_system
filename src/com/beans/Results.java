package com.beans;

public class Results {
	
	int id;
	private String sport;
	private String venue;
	private String time;
	private String teams_fixtured;
	private String date;
	private String results;
	
	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Results(String sport, String venue, String time, String teams_fixtured, String date, String results) {
		super();
		this.sport = sport;
		this.venue = venue;
		this.time = time;
		this.teams_fixtured = teams_fixtured;
		this.date = date;
		this.results = results;
	}

	public Results(int id, String sport, String venue, String time, String teams_fixtured, String date,
			String results) {
		super();
		this.id = id;
		this.sport = sport;
		this.venue = venue;
		this.time = time;
		this.teams_fixtured = teams_fixtured;
		this.date = date;
		this.results = results;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTeams_fixtured() {
		return teams_fixtured;
	}

	public void setTeams_fixtured(String teams_fixtured) {
		this.teams_fixtured = teams_fixtured;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "Results [id=" + id + ", sport=" + sport + ", venue=" + venue + ", time=" + time + ", teams_fixtured="
				+ teams_fixtured + ", date=" + date + ", results=" + results + "]";
	}
	
}
