package com.beans;

public class Broadcast {
	int id;
	private String title;
	private String description;
	private String sport;
	private String video;
	private String upload_date;
	
	public Broadcast() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Broadcast(String title, String description, String sport, String video, String upload_date) {
		super();
		this.title = title;
		this.description = description;
		this.sport = sport;
		this.video = video;
		this.upload_date = upload_date;
	}

	public Broadcast(int id, String title, String description, String sport, String video, String upload_date) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.sport = sport;
		this.video = video;
		this.upload_date = upload_date;
	}


	public Broadcast(int id, String title, String description, String video) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	@Override
	public String toString() {
		return "Broadcast [id=" + id + ", title=" + title + ", description=" + description + ", sport=" + sport
				+ ", video=" + video + ", upload_date=" + upload_date + "]";
	}
	
	
	
}
