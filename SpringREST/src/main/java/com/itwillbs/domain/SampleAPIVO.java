package com.itwillbs.domain;

/*
 * JSON
 * {
    "message": "https://images.dog.ceo/breeds/waterdog-spanish/20180706_194432.jpg",
    "status": "success"
	}
 * */

public class SampleAPIVO {
	private String message;
	private String status;

	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
