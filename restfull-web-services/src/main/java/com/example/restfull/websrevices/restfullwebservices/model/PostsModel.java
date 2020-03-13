package com.example.restfull.websrevices.restfullwebservices.model;

import java.util.Date;

public class PostsModel {

	private Integer id;
	private String message;
	private Date timestamp;
	private Integer userId;

	public PostsModel(Integer id, String message, Date timestamp, Integer userId) {
		super();
		this.id = id;
		this.message = message;
		this.timestamp = timestamp;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PostsModel [id=" + id + ", message=" + message + ", timestamp=" + timestamp + ", userId=" + userId
				+ "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
